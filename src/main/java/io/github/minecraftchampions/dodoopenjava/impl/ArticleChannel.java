package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Channel;
import io.github.minecraftchampions.dodoopenjava.api.ChannelType;
import lombok.NonNull;

import java.util.function.Function;

/**
 * 帖子频道
 */
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

    /**
     * 发布帖子
     *
     * @param channelId 频道id
     * @param title     标题
     * @param content   内容
     * @param imageUrl  图片链接
     * @return id
     */
    public String addArticle(@NonNull String channelId, @NonNull String title,
                             @NonNull String content, @NonNull String imageUrl) {
        return getBot().getApi().V2.getChannelArticleApi().addChannelArticle(channelId, title, content, imageUrl).ifSuccess((Function<Result, String>) result -> result.getJSONObjectData().getJSONObject("data").getString("articleId"));
    }


}
