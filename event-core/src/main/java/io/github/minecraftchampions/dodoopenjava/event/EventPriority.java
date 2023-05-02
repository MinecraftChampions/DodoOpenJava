package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.event.EventHandler;

/**
 * ����һ���¼������ȼ�.
 * @see EventHandler
 */
public enum EventPriority {
    /**
     * �¼��е��õ���Ҫ�Էǳ���,����Ӧ����������,������������Խ�һ���Զ�����
     */
    LOWEST(0),
    /**
     * �¼��е��õ���Ҫ�ԱȽϵ�
     */
    LOW(1),
    /**
     * �¼��е��õ���Ҫ��һ��
     *
     */
    NORMAL(2),
    /**
     * �¼��е��õ���Ҫ�ԱȽϸ�
     */
    HIGH(3),
    /**
     * �¼�����÷ǳ���Ҫ��������¼�����ʲô������������¼�����
     */
    HIGHEST(4),
    /**
     * û���޸ĵ��¼������ȼ�Ӧ�ñ������
     */
    MONITOR(5);

    private final int slot;

    EventPriority(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
