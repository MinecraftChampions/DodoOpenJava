package io.github.minecraftchampions.dodoopenjava.event;

import io.github.minecraftchampions.dodoopenjava.Bot;
import lombok.Getter;

@Getter
public class EventTrigger {
    boolean isConnect = false;

    protected Bot bot;

    public void start() {}

    public void close() {}

}
