package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import io.github.minecraftchampions.dodoopenjava.event.AbstractEvent;

/**
 * 频道消息相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractChannelMessageEvent extends AbstractEvent {
    public AbstractChannelMessageEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractChannelMessageEvent() {
        this(false);
    }

    /**
     * 转换 为Int数据类型的 消息类型关键字 为 String 类型
     *
     * @param type 消息类型
     * @return 消息类型
     */
    public static String intMessageTypeToMessageType(Integer type) {
        return switch (type) {
            case 1 -> "文字消息";
            case 2 -> "图片消息";
            case 3 -> "视频消息";
            case 4 -> "分享消息";
            case 5 -> "文件消息";
            case 6 -> "卡片消息";
            default -> "未知消息";
        };
    }

    /**
     * 转换 为Int数据类型的 性别关键字 为 String 类型
     *
     * @param intSex 性别
     * @return 性别
     */
    public static String intSexToSex(Integer intSex) {
        return switch (intSex) {
            case 0 -> "女";
            case 1 -> "男";
            default -> "保密";
        };
    }
}
