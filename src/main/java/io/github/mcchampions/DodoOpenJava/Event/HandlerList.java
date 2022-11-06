package io.github.mcchampions.DodoOpenJava.Event;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * һ�������¼�����, �洢ÿ���¼���
 */
public class HandlerList {
    /**
     * ����������HandlerList������.�������ֶ������ϵͳ�ٶȵĹؼ�.
     */
    private volatile RegisteredListener[] handlers = null;

    private final EnumMap<EventPriority, ArrayList<RegisteredListener>> handlerslots;

    /**
     * �����Ѿ�������HandlerList,����bakeAll().
     */
    private static ArrayList<HandlerList> allLists = new ArrayList<>();

    /**
     * ��EventPriority�������ͳ�ʼ��һ���µ�HandlerList.
     * <p>
     * HandlerList���ᱻ��ӵ�Ԫ�б�Ϊ��bakeAll()����.
     * <p>
     */
    public HandlerList() {
        handlerslots = new EnumMap<>(EventPriority.class);
        for (EventPriority o : EventPriority.values()) {
            handlerslots.put(o, new ArrayList<>());
        }
        synchronized (allLists) {
            allLists.add(this);
        }
    }

    /**
     * �ڴ������б���ע��һ��������.
     *
     * @param listener Ҫע��ļ�����
     */
    public synchronized void register(@NotNull RegisteredListener listener) {
        if (handlerslots.get(listener.getPriority()).contains(listener))
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority());
        handlers = null;
        handlerslots.get(listener.getPriority()).add(listener);
    }

    /**
     * �ڴ����б���ע��һ������������(����ע�������).
     *
     * @param listeners Ҫע��ļ�����
     */
    public void registerAll(@NotNull Collection<RegisteredListener> listeners) {
        for (RegisteredListener listener : listeners) {
            register(listener);
        }
    }

    /**
     * �ϲ�һ��HashMap��ArrayLists����ά���� - �������Ҫ��ʲôҲ������.
     */
    public synchronized void bake() {
        if (handlers != null) return; // don't re-bake when still valid
        List<RegisteredListener> entries = new ArrayList<>();
        for (Map.Entry<EventPriority, ArrayList<RegisteredListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new RegisteredListener[0]);
    }

    /**
     * ��ȡ������������б���ص���ע��ļ�����.
     *
     * @return ע����ļ�����������
     */
    @NotNull
    public RegisteredListener[] getRegisteredListeners() {
        RegisteredListener[] handlers;
        while ((handlers = this.handlers) == null) bake(); // This prevents fringe cases of returning null
        return handlers;
    }

    /**
     * ��ȡÿ���¼����͵����д��������б�.
     *
     * @return ���д��������б�
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static ArrayList<HandlerList> getHandlerLists() {
        synchronized (allLists) {
            return (ArrayList<HandlerList>) allLists.clone();
        }
    }
}