package io.github.mcchampions.DodoOpenJava.Event;

import org.jetbrains.annotations.NotNull;

/**
 * �쳣
 */
public interface EventExecutor {
    void execute(@NotNull Listener listener, @NotNull Event event) ;
}
