package io.github.minecraftchampions.dodoopenjava.api;

import lombok.Getter;

/**
 * 频道类别
 */
@Getter
public enum ChannelType {
    /**
     * 消息频道
     */
    MESSAGE(1),
    /**
     * 语音频道
     */
    VOICE(2),
    /**
     * 帖子频道
     */
    ARTICLE(4),
    /**
     * 链接频道
     */
    LINK(5),
    /**
     * 资料频道
     */
    INFORMATION(6);

    private final int type;

    ChannelType(int type) {
        this.type = type;
    }

    public static ChannelType of(int type) {
        return switch (type) {
            case 1 -> MESSAGE;
            case 2 -> VOICE;
            case 4 -> ARTICLE;
            case 5 -> LINK;
            case 6 -> INFORMATION;
            default -> throw new RuntimeException("错误的类型");
        };
    }
}
