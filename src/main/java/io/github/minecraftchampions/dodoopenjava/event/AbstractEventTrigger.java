package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.Bot;
import lombok.Getter;

@Getter
public abstract class AbstractEventTrigger {
    protected Bot bot;

    public abstract void start();

    public abstract void close();

    public abstract boolean isConnect();
}
