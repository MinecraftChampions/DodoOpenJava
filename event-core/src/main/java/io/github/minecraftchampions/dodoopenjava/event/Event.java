package io.github.minecraftchampions.dodoopenjava.event;

import org.jetbrains.annotations.NotNull;

/**
 * �����¼�.
 * <p>
 * �����¼���Ҫ���һ����Ϊ getHandlerList() �ľ�̬������������{@link #getHandlers()}һ����{@link HandlerList}.
 * <p>
 * ע:ǰһ�仰����ɴ�����ʽ, ��������:
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
 * @author qscbm187531
 */
public abstract class Event {
    private String name;
    private final boolean async;

    /**
     * Ϊ�˸��������Ĵ�������졣���������ȡ�õ���ͬ�����¼���
     */
    public Event() {
        this(false);
    }

    /**
     * ���������������ʾ����һ���¼���ͬ�������첽��.
     * <p>
     * ԭ�ģ�This constructor is used to explicitly declare an event as synchronous
     * or asynchronous.
     *
     * @param isAsync true��Ϊ�첽�¼�
     */
    public Event(boolean isAsync) {
        this.async = isAsync;
    }

    /**
     * ��ȡ����¼�������,Ĭ�������,�����¼������{@linkplain Class#getSimpleName() �������}.
     *
     * @return ����¼�������
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
     * �κ��Զ����¼�Ӧ�ò��������¼�ͬ��,����ʹ���ض��Ĺ�����.���Ƕ�ʹ���첽�¼���һЩ����(ע������)��
     * <ul>
     * <li>����¼���Զ���ᴥ���ڲ����봥����ͬ���¼�.������ô���Ľ����õ�{@link java.lang.IllegalStateException}.</li>
     * <li>�������첽�¼����������ܴ���ͬ�����첽�¼�.</li>
     * <li>�¼������ڶ��ʱ���κ����ȼ�������.</li>
     * <li>�κ���ע���δע��Ĵ���������һ���¼���ʼִ�к󱻺���.</li>
     * <li>����¼��Ĵ�������������һ��ʱ��.</li>
     * <li>һЩʵ�ֿ��ܻ���ѡ�������һ���¼����첽��.��һ��ΪӦ����ȷ����.</li>
     * <li>�첽���ò�������ڲ����ʱϵͳ��.</li>
     * </ul>
     * @return Ĭ������·���false, �¼������첽�˷���true
     */
    public final boolean isAsynchronous() {
        return async;
    }

    public enum Result {

        /**
         * �ܾ����¼�.���ݲ�ͬ�������,���¼��Ķ������������������߾ܾ�.ĳЩ�������ܲ��ᱻ�ܾ�.
         */
        DENY,
        /**
         * �Ȳ��ܾ�Ҳ������.��������������������.
         */
        DEFAULT,
        /**
         * ����/ǿ��������¼�.�������,�¼�����������,��ʹ������ͨ���������������.ĳЩ�������ܲ��ᱻ�������¼��Ķ�������
         */
        ALLOW
    }
}
