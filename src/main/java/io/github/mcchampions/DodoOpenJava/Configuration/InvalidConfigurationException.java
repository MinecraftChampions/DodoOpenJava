package io.github.mcchampions.DodoOpenJava.Configuration;

/**
 * 尝试加载无效配置时引发异常
 */
public class InvalidConfigurationException extends Exception {

    /**
     * 创建InvalidConfigurationException的新实例，但不包含消息或原因。
     */
    public InvalidConfigurationException() {}

    /**
     * 使用指定的消息构造InvalidConfigurationException的实例。
     *
     * @param msg 异常的详细信息。
     */
    public InvalidConfigurationException(String msg) {
        super(msg);
    }

    /**
     * 构造具有指定原因的InvalidConfigurationException实例。
     *
     * @param cause 异常的原因。
     */
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的消息和原因构造InvalidConfigurationException的实例。
     *
     * @param cause 原因
     * @param msg 信息
     */
    public InvalidConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
