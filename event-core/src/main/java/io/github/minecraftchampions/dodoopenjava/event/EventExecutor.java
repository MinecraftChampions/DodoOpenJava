package io.github.minecraftchampions.dodoopenjava.event;

import org.jetbrains.annotations.NotNull;

/**
 * 异常
 */
public interface EventExecutor {
    void execute(@NotNull Listener listener, @NotNull Event event) ;
}
