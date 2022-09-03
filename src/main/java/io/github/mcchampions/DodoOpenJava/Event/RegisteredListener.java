package io.github.mcchampions.DodoOpenJava.Event;

import org.jetbrains.annotations.NotNull;

/**
 * 监听器相关
 */
public class RegisteredListener {
    private final Listener listener;
    private final EventPriority priority;
    private final EventExecutor executor;

    private final String ad;

    /**
     * 注册监听器
     * @param listener 监听器
     * @param priority 优先级
     * @param ad Ad
     */
    public RegisteredListener(@NotNull final Listener listener, @NotNull final EventExecutor executor, @NotNull final EventPriority priority, String ad) {
        this.listener = listener;
        this.priority = priority;
        this.executor = executor;
        this.ad = ad;
        EventTrigger.main(this.ad);

    }

    /**
     * 获取监听器
     *
     * @return 监听器
     */
    @NotNull
    public Listener getListener() {
        return listener;
    }

    /**
     * 获取优先级
     *
     * @return 优先级
     */
    @NotNull
    public EventPriority getPriority() {
        return priority;
    }

    /**
     * 触发事件
     *
     * @param event 事件
     * @throws EventException 事件异常时抛出异常
     */
    public void callEvent(@NotNull final Event event) throws EventException {
        executor.execute(listener, event);
    }
}
