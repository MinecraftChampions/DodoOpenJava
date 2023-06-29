package io.github.minecraftchampions.dodoopenjava.event;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 一个处理事件的类, 存储每个事件。
 */
public class HandlerList {
    /**
     * 包含了所有HandlerList的数组.此数组字段是这个系统速度的关键.
     */
    private volatile RegisteredListener[] handlers = null;

    private final EnumMap<EventPriority, ArrayList<RegisteredListener>> handlerslots;

    /**
     * 所有已经创建的HandlerList,用于bakeAll().
     */
    private static ArrayList<HandlerList> allLists = new ArrayList<>();

    /**
     * 用EventPriority来创建和初始化一个新的HandlerList.
     * <p>
     * HandlerList将会被添加到元列表，为了bakeAll()方法.
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
     * 在处理器列表中注册一个监听器.
     *
     * @param listener 要注册的监听器
     */
    public synchronized void register(@NotNull RegisteredListener listener) {
        if (handlerslots.get(listener.getPriority()).contains(listener))
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority());
        handlers = null;
        handlerslots.get(listener.getPriority()).add(listener);
    }

    /**
     * 在处理列表中注册一个监听器集合(批量注册监听器).
     *
     * @param listeners 要注册的监听器
     */
    public void registerAll(@NotNull Collection<RegisteredListener> listeners) {
        for (RegisteredListener listener : listeners) {
            register(listener);
        }
    }

    public synchronized void bake() {
        if (handlers != null) return;
        List<RegisteredListener> entries = new ArrayList<>();
        for (Map.Entry<EventPriority, ArrayList<RegisteredListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new RegisteredListener[0]);
    }

    /**
     * 获取与这个处理器列表相关的已注册的监听器.
     *
     * @return 注册过的监听器的数组
     */
    @NotNull
    public RegisteredListener[] getRegisteredListeners() {
        RegisteredListener[] handlers;
        while ((handlers = this.handlers) == null) bake();
        return handlers;
    }

    /**
     * 获取每个事件类型的所有处理器的列表.
     *
     * @return 所有处理器的列表
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static ArrayList<HandlerList> getHandlerLists() {
        synchronized (allLists) {
            return (ArrayList<HandlerList>) allLists.clone();
        }
    }
}