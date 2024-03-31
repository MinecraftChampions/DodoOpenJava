package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Channel;
import lombok.Getter;

/**
 * 频道实现
 */
@Getter
public class ChannelImpl implements Channel {
    private String channelId;

    private String islandId;

    private Bot bot;
    @Override
    public Result editChannelName(String name) {
        return null;
    }

    @Override
    public String getChannelName() {
        return null;
    }

    @Override
    public int getChannelType() {
        return 0;
    }

    @Override
    public int getDefaultFlag() {
        return 0;
    }

    @Override
    public String getGroupId() {
        return null;
    }

    @Override
    public String getGroupName() {
        return null;
    }

    @Override
    public Result create() {
        return null;
    }

    @Override
    public Result delete() {
        return null;
    }
}
