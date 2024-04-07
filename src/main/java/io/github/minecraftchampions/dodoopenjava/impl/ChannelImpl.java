package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Channel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * 频道实现
 */
@Slf4j
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ChannelImpl implements Channel {
    @NonNull
    private String channelId;

    @NonNull
    private String islandId;

    @NonNull
    private Bot bot;

    public ChannelImpl(String channelId, Bot bot) {
        this.channelId = channelId;
        this.bot = bot;
        this.islandId = bot.getApi().V2.getChannelApi().getChannelInfo(channelId).ifFailure(result -> {
                    log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
                })
                .ifSuccess((Function<Result, String>) (result) ->
                        result.getJSONObjectData().getJSONObject("data").getString("islandSourceId")
                );
    }

    @Override
    public Result editChannelName(String name) {
        return bot.getApi().V2.getChannelApi().editChannel(islandId, name, channelId);
    }

    @Override
    public String getChannelName() {
        return bot.getApi().V2.getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("channelName"));
    }

    @Override
    public int getChannelType() {
        return bot.getApi().V2.getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("channelType"));
    }

    @Override
    public int getDefaultFlag() {
        return bot.getApi().V2.getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, Integer>) result ->
                result.getJSONObjectData().getJSONObject("data").getInt("defaultFlag"));
    }

    @Override
    public String getGroupId() {
        return bot.getApi().V2.getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("groupId"));
    }

    @Override
    public String getGroupName() {
        return bot.getApi().V2.getChannelApi().getChannelInfo(channelId).ifSuccess((Function<Result, String>) result ->
                result.getJSONObjectData().getJSONObject("data").getString("groupName"));
    }

    @Override
    public Result delete() {
        return bot.getApi().V2.getChannelApi().deleteChannel(getIslandId(), getChannelId());
    }
}
