package io.github.minecraftchampions.dodoopenjava.configuration;

/**
 * ���Լ�����Ч����ʱ�����쳣
 */
public class InvalidConfigurationException extends Exception {

    /**
     * ����InvalidConfigurationException����ʵ��������������Ϣ��ԭ��
     */
    public InvalidConfigurationException() {}

    /**
     * ʹ��ָ������Ϣ����InvalidConfigurationException��ʵ����
     *
     * @param msg �쳣����ϸ��Ϣ��
     */
    public InvalidConfigurationException(String msg) {
        super(msg);
    }

    /**
     * �������ָ��ԭ���InvalidConfigurationExceptionʵ����
     *
     * @param cause �쳣��ԭ��
     */
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * ʹ��ָ������Ϣ��ԭ����InvalidConfigurationException��ʵ����
     *
     * @param cause ԭ��
     * @param msg ��Ϣ
     */
    public InvalidConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
