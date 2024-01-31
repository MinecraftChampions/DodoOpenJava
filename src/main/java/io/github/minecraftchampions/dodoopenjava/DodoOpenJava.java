package io.github.minecraftchampions.dodoopenjava;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * API
 */
@Slf4j
public class DodoOpenJava {
    public static final String BASEURL = "https://botopen.imdodo.com/api/v2/";

    private static final HashSet<Bot> bots = new HashSet<>();

    @Getter
    private static final Map<String, ApiResultsLogger> logMap = new ConcurrentHashMap<>();

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

    /**
     * 卸载Bot
     *
     * @param bot bot
     */
    public synchronized static void disableBot(Bot bot) {
        bots.remove(bot);
        bot.getCommandManager().unregisterAllCommands();
        bot.getEventManager().unregisterAllListeners();
        bot.removeEventTrigger();
        bot.disableApiResultsLogger();
    }

    /**
     * 启用日志服务
     *
     * @param bot bot
     */
    public static void enableApiResultsLogger(Bot bot) {
        enableApiResultsLogger(bot.getAuthorization());
    }

    /**
     * 启用日志服务
     *
     * @param authorization authorization
     */
    public static void enableApiResultsLogger(String authorization) {
        synchronized (logMap) {
            if (logMap.containsKey(authorization)) {
                log.warn("已经调用过DodoOpenJava#enableApiResultsLogger");
                return;
            }
            logMap.put(authorization, new ApiResultsLogger(authorization));
        }
    }

    /**
     * 卸载日志服务
     *
     * @param bot bot
     */
    public static void disableApiResultsLogger(Bot bot) {
        disableApiResultsLogger(bot.getAuthorization());
    }

    /**
     * 卸载日志服务
     *
     * @param authorization authorization
     */
    public static void disableApiResultsLogger(String authorization) {
        synchronized (logMap) {
            logMap.remove(authorization);
        }
    }
}
