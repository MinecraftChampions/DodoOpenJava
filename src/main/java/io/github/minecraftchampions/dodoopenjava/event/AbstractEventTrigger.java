package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.api.Bot;
import lombok.Getter;

/**
 * 事件触发器
 *
 * @author qscbm187531
 */
@Getter
public abstract class AbstractEventTrigger {
    protected Bot bot;

    /**
     * 开始监听
     */
    public abstract void start();

    /**
     * 关闭监听
     */
    public abstract void close();

    /**
     * 是否正在监听
     *
     * @return true/false
     */
    public abstract boolean isConnect();
}