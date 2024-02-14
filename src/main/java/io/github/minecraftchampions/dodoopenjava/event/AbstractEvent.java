package io.github.minecraftchampions.dodoopenjava.event;

import lombok.Getter;

/**
 * 代表事件.
 *
 * @author qscbm187531
 */
public abstract class AbstractEvent {
    private String name;
    private final boolean async;
    @Getter
    protected Class<? extends AbstractEvent> eventType;

    /**
     * 为了更简单清晰的代码而制造。这个构造器取得的是同步的事件。
     */
    public AbstractEvent() {
        this(false);
    }

    /**
     * 这个构造器用于显示声明一个事件是同步还是异步的.
     *
     * @param isAsync true则为异步事件
     */
    public AbstractEvent(boolean isAsync) {
        this.async = isAsync;
    }

    /**
     * 获取这个事件的名称,默认情况下,他是事件的类的{@linkplain Class#getSimpleName() 简短名称}.
     *
     * @return 这个事件的名称
     */
    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    /**
     * 是否异步事件
     *
     * @return 默认情况下返回false, 异步事件返回true
     */
    public final boolean isAsynchronous() {
        return async;
    }
}
