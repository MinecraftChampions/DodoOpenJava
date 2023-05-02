package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.event.EventExecutor;
import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.EventPriority;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.events.EventException;

/**
 * ���������
 */
public class RegisteredListener {
    private final Listener listener;
    private final EventPriority priority;
    private final EventExecutor executor;

    /**
     * ע�������
     * @param listener ������
     * @param priority ���ȼ�
     */
    public RegisteredListener(@NotNull final Listener listener, @NotNull final EventExecutor executor, @NotNull final EventPriority priority) {
        this.listener = listener;
        this.priority = priority;
        this.executor = executor;
    }

    /**
     * ��ȡ������
     *
     * @return ������
     */
    @NotNull
    public Listener getListener() {
        return listener;
    }

    /**
     * ��ȡ���ȼ�
     *
     * @return ���ȼ�
     */
    @NotNull
    public EventPriority getPriority() {
        return priority;
    }

    /**
     * �����¼�
     *
     * @param event �¼�
     * @throws EventException �¼��쳣ʱ�׳��쳣
     */
    public void callEvent(@NotNull final Event event) throws EventException {
        executor.execute(listener, event);
    }
}
