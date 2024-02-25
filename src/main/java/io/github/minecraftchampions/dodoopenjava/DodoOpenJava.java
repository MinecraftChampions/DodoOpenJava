package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.util.BaseUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * API
 *
 * @author qscbm187531
 */
@Slf4j
public class DodoOpenJava {
    public static final String BASEURL = "https://botopen.imdodo.com/api/v2/";

    private static final ArrayList<Bot> BOT_LIST = new ArrayList<>();

    public static final Map<String, ApiResultsLogger> LOGGER_MAP = new ConcurrentHashMap<>();

    /**
     * 新建 Bot
     *
     * @param clientId 唯一标识
     * @param token    token
     * @return Bot
     */
    public static synchronized Bot createBot(@NonNull String clientId, @NonNull String token) {
        Bot bot = new Bot(clientId, token);
        BOT_LIST.add(bot);
        return bot;
    }

    /**
     * 获取已经创建的Bot列表
     *
     * @return 返回的是浅拷贝的列表
     */
    public static List<Bot> getBotList() {
        return BaseUtil.castList(BOT_LIST.clone(), Bot.class);
    }

    /**
     * 根据Authorization获取Bot
     *
     * @return bot
     */
    public static Optional<Bot> getBot(@NonNull String authorization) {
        for (Bot bot : BOT_LIST) {
            if (bot.getAuthorization().equals(authorization)) {
                return Optional.of(bot);
            }
        }
        return Optional.empty();
    }

    /**
     * 卸载Bot
     *
     * @param bot bot
     */
    public synchronized static void disableBot(Bot bot) {
        BOT_LIST.remove(bot);
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
        synchronized (LOGGER_MAP) {
            if (LOGGER_MAP.containsKey(authorization)) {
                log.warn("已经调用过DodoOpenJava#enableApiResultsLogger");
                return;
            }
            LOGGER_MAP.put(authorization, new ApiResultsLogger(authorization));
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
        synchronized (LOGGER_MAP) {
            LOGGER_MAP.remove(authorization);
        }
    }
}