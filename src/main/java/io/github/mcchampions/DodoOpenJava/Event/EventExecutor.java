package io.github.mcchampions.DodoOpenJava.Event;

import org.jetbrains.annotations.NotNull;

/**
 * 异常
 */
public interface EventExecutor {
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException;
}
