package io.github.mcchampions.DodoOpenJava.Event;

import org.jetbrains.annotations.NotNull;

/**
 * “Ï≥£
 */
public interface EventExecutor {
    void execute(@NotNull Listener listener, @NotNull Event event) ;
}
