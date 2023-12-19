package io.github.minecraftchampions.dodoopenjava;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * API
 */
public class DodoOpenJava {
    public static final String BASEURL = "https://botopen.imdodo.com/api/v2/";

    @Getter
    private static final Set<Bot> bots = new HashSet<>();

    /**
     * 新建 Bot
     *
     * @param clientId 唯一标识
     * @param token    token
     * @return Bot
     */
    public static Bot createBot(@NonNull String clientId, @NonNull String token) {
        Bot bot = new Bot(clientId, token);
        bots.add(bot);
        return bot;
    }
}
