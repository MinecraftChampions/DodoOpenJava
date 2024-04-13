package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Channel;
import io.github.minecraftchampions.dodoopenjava.api.ChannelType;
import lombok.NonNull;

public class ArticleChannel extends ChannelImpl {
    private ArticleChannel(@NonNull String channelId, @NonNull String islandSourceId, @NonNull Bot bot) {
        super(channelId, islandSourceId, bot);
    }

    public static ArticleChannel of(@NonNull Channel channel) {
        if (channel.getChannelType() != ChannelType.ARTICLE) {
            throw new RuntimeException("错误的频道类型");
        }
        return new ArticleChannel(channel.getChannelId(), channel.getIslandSourceId(), channel.getBot());
    }
}
