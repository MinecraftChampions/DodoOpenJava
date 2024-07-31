package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.api.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * 频道实现
 */
@Slf4j
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelImpl implements Channel {
    @NonNull
    private String channelId;

    @NonNull
    private String islandSourceId;

    @NonNull
    private Bot bot;

    public ChannelImpl(@NonNull String channelId, @NonNull Bot bot) {
        this.channelId = channelId;
        this.bot = bot;
        this.islandSourceId = bot.getApi().getChannelApi().getChannelInfo(channelId).ifFailure(result -> {
                    log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
                })
                .ifSuccess((Function<Result, String>) (result) ->
                        result.getJSONObjectData().getJSONObject("data").getString("islandSourceId")
                );
    }

    @Override
    public Result editChannelName(@NonNull String name) {
        return bot.getApi().getChannelApi().editChannel(islandSourceId, name, channelId);
    }

    @Override
    public String getChannelName() {
        return bot.getApi().getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("channelName"));
    }

    @Override
    public ChannelType getChannelType() {
        return ChannelType.of(bot.getApi().getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("channelType")));
    }

    @Override
    public int getDefaultFlag() {
        return bot.getApi().getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("defaultFlag"));
    }

    @Override
    public String getGroupId() {
        return bot.getApi().getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("groupId"));
    }

    @Override
    public String getGroupName() {
        return bot.getApi().getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("groupName"));
    }

    @Override
    public Result delete() {
        return bot.getApi().getChannelApi().deleteChannel(getIslandSourceId(), getChannelId());
    }

    @Override
    public Island getIsland() {
        return new IslandImpl(getIslandSourceId(), bot);
    }
}
