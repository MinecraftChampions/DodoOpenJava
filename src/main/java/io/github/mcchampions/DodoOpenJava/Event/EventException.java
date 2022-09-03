package io.github.mcchampions.DodoOpenJava.Event;

/**
 * 异常
 */
public class EventException extends Exception{
    private final Throwable cause;

    /**
     * 在给定的异常的基础上构建个新的EventException。
     *
     * @param throwable 触发这个异常的异常
     */
    public EventException(Throwable throwable) {
        cause = throwable;
    }

    /**
     * 构造一个新的EventException。
     */
    public EventException() {
        cause = null;
    }

    /**
     * 在给定的信息上构造一个新的EventException。
     *
     * @param cause 因为什么而触发此异常
     * @param message 信息
     */
    public EventException(Throwable cause, String message) {
        super(message);
        this.cause = cause;
    }

    /**
     * 在给定的信息的基础上构造一个新的EventException。
     *
     * @param message 信息
     */
    public EventException(String message) {
        super(message);
        cause = null;
    }

    /**
     * 如果适用，就会返回触发这个异常的异常。
     *
     * @return 内部异常, 找不到返回null
     */
    @Override
    public Throwable getCause() {
        return cause;
    }
}
