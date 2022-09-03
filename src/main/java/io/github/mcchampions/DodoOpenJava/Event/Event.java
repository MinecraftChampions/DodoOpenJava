package io.github.mcchampions.DodoOpenJava.Event;

import org.jetbrains.annotations.NotNull;

/**
 * 代表事件.
 * <p>
 * 所有事件需要添加一个名为 getHandlerList() 的静态方法，返回与{@link #getHandlers()}一样的{@link HandlerList}.
 * <p>
 * 注:前一句话翻译成代码形式, 就是这样:
 * <pre>
    private static final HandlerList handlers = new HandlerList();
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
 * </pre>
 *
 */
public abstract class Event {
    private String name;
    private final boolean async;

    /**
     * 为了更简单清晰的代码而制造。这个构造器取得的是同步的事件。
     */
    public Event() {
        this(false);
    }

    /**
     * 这个构造器用于显示声明一个事件是同步还是异步的.
     * <p>
     * 原文：This constructor is used to explicitly declare an event as synchronous
     * or asynchronous.
     *
     * @param isAsync true则为异步事件
     */
    public Event(boolean isAsync) {
        this.async = isAsync;
    }

    /**
     * 获取这个事件的名称,默认情况下,他是事件的类的{@linkplain Class#getSimpleName() 简短名称}.
     *
     * @return 这个事件的名称
     */
    @NotNull
    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    @NotNull
    public abstract HandlerList getHandlers();

    /**
     * 任何自定义事件应该不与其他事件同步,必须使用特定的构造器.这是对使用异步事件的一些警告(注意事项)：
     * <ul>
     * <li>这个事件永远不会触发内部代码触发的同步事件.尝试这么做的结果会得到{@link java.lang.IllegalStateException}.</li>
     * <li>不过，异步事件处理器可能触发同步或异步事件.</li>
     * <li>事件可能在多个时间任何优先级被触发.</li>
     * <li>任何新注册或未注册的处理器将在一个事件开始执行后被忽略.</li>
     * <li>这个事件的处理器可能阻塞一段时间.</li>
     * <li>一些实现可能会有选择地声明一个事件是异步的.这一行为应被明确定义.</li>
     * <li>异步调用不会计算在插件定时系统中.</li>
     * </ul>
     * @return 默认情况下返回false, 事件触发异步了返回true
     */
    public final boolean isAsynchronous() {
        return async;
    }

    public enum Result {

        /**
         * 拒绝此事件.根据不同的情况下,由事件的动作表明将不发生或者拒绝.某些操作可能不会被拒绝.
         */
        DENY,
        /**
         * 既不拒绝也不允许.服务器将进行正常处理.
         */
        DEFAULT,
        /**
         * 允许/强制允许此事件.如果可能,事件将发生作用,即使服务器通常不允许这个动作.某些操作可能不会被允许由事件的动作表明
         */
        ALLOW;
    }
}
