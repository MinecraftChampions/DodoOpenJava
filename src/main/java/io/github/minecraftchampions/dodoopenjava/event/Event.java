package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.debug.Result;
import lombok.Getter;
import org.json.JSONObject;

/**
 * 代表事件.
 *
 * @author qscbm187531
 */
@Getter
public abstract class Event implements Comparable<Result> {
    protected long timestamp;

    protected String eventId;

    protected String name;
    protected final boolean async;

    protected String jsonString;
    protected JSONObject jsonObject;
    @Getter
    protected Class<? extends Event> eventType;

    /**
     * 为了更简单清晰的代码而制造。这个构造器取得的是同步的事件。
     */
    public Event() {
        this(false);
    }

    /**
     * 这个构造器用于显示声明一个事件是同步还是异步的.
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

    @Override
    public int compareTo(Result o) {
        return 0;
    }

    @Override
    public String toString() {
        return jsonString;
    }
}