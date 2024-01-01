package io.github.minecraftchampions.dodoopenjava;

import lombok.Getter;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * API
 */
public class DodoOpenJava {
    public static final String BASEURL = "https://botopen.imdodo.com/api/v2/";

    private static final HashSet<Bot> bots = new HashSet<>();

    @Getter
    private static final HashMap<String, ApiResultsLogger> logMap = new HashMap<>();

    public static final Logger LOGGER = LoggerFactory.getLogger(DodoOpenJava.class);

    /**
     * 新建 Bot
     *
     * @param clientId 唯一标识
     * @param token    token
     * @return Bot
     */
    public static synchronized Bot createBot(@NonNull String clientId, @NonNull String token) {
        Bot bot = new Bot(clientId, token);
        bots.add(bot);
        return bot;
    }

    public static Set<Bot> getBots() {
        return (HashSet<Bot>) bots.clone();
    }

    public synchronized static void disableBot(Bot bot) {
        bots.remove(bot);
        bot.getCommandManager().unregisterAllCommands();
        bot.getEventManager().unregisterAllListeners();
        bot.removeEventTrigger();
        logMap.remove(bot.getAuthorization());
    }

    public static void enableApiResultsLogger(Bot bot) {
        enableApiResultsLogger(bot.getAuthorization());
    }

    public static synchronized void enableApiResultsLogger(String authorization) {
        if (logMap.containsKey(authorization)) {
            LOGGER.warn("已经调用过DodoOpenJava#enableApiResultsLogger");
            return;
        }
        logMap.put(authorization, new ApiResultsLogger(authorization));
    }
}
