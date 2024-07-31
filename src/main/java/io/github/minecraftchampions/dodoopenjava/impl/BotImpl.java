package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Island;
import io.github.minecraftchampions.dodoopenjava.api.v2.*;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandManager;
import io.github.minecraftchampions.dodoopenjava.debug.DebugLogger;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.event.AbstractEventTrigger;
import io.github.minecraftchampions.dodoopenjava.event.EventManager;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.WebSocketEventTrigger;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Objects;

/**
 * 机器人实例
 *
 * @author qscbm187531
 */
@Getter
@RequiredArgsConstructor
@Slf4j
public class BotImpl implements Bot {
    public String toString() {
        return getClientId();
    }

    /**
     * 机器人唯一标识
     */
    @NonNull
    private final String clientId;

    /**
     * 机器人鉴权Token
     */
    @NonNull
    private final String token;

    @Override
    public boolean equals(Object object) {
        if (object instanceof Bot bot) {
            return this == bot || Objects.equals(bot.getAuthorization(), this.getAuthorization());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return clientId.hashCode() + token.hashCode();
    }

    /**
     * 事件管理器
     */
    private final EventManager eventManager = new EventManager(this);

    private final Api api = new Api(this);

    /**
     * 获取authorization
     *
     * @return authorization
     */
    @Override
    public String getAuthorization() {
        return BaseUtils.generateAuthorization(clientId, token);
    }

    /**
     * 启用日志记录器
     */
    @Override
    public void enableDebugMode() {
        DodoOpenJava.enableDebugMode(this);
    }

    /**
     * 卸载日志记录器
     */
    @Override
    public void disableDebugMode() {
        DodoOpenJava.disableDebugMode(this);
    }

    /**
     * 注册事件监听器
     *
     * @param listener 监听器
     */
    @Override
    public synchronized void registerListener(@NonNull Listener listener) {
        if (eventTrigger == null) {
            initEventListenSystem(new WebSocketEventTrigger(this));
        }
        if (!eventTrigger.isConnect()) {
            eventTrigger.start();
        }
        getEventManager().registerListener(listener);
    }

    /**
     * 移除事件监听器
     */
    @Override
    public synchronized void removeEventTrigger() {
        if (eventTrigger != null) {
            eventTrigger.close();
            eventTrigger = null;
        }
    }

    @Override
    public Island getIsland(@NonNull String islandSourceId) {
        Result result = getApi().getIslandApi().getIslandList();
        if (result.isFailure()) {
            log.error("获取频道信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        if (result.getJSONObjectData().toString().contains("\"islandSourceId\":\"" + islandSourceId + "\"")) {
            return new IslandImpl(islandSourceId, this);
        }
        return null;
    }

    /**
     * 注册事件监听器
     *
     * @param listeners 监听器
     */
    @Override
    public void registerListeners(@NonNull Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    /**
     * 注册命令
     *
     * @param commandExecutor 命令处理器
     */
    @Override
    public void registerCommand(@NonNull CommandExecutor commandExecutor) {
        commandManager.registerCommand(commandExecutor);
    }

    /**
     * 获取Api请求记录器
     *
     * @return ApiResultsLogger
     */
    @Override
    public DebugLogger getDebugLogger() {
        DebugLogger apiResultsLogger = DodoOpenJava.DEBUG_LOGGER_MAP.get(this.getAuthorization());
        if (apiResultsLogger != null) {
            return apiResultsLogger;
        } else {
            log.error("没有启用日志记录系统就调用Bot#getApiResultsLogger方法,bot:{}", getAuthorization());
            return null;
        }
    }

    /**
     * 注册命令
     *
     * @param commandExecutors 命令处理器
     */
    @Override
    public void registerCommands(@NonNull CommandExecutor... commandExecutors) {
        for (CommandExecutor commandExecutor : commandExecutors) {
            registerCommand(commandExecutor);
        }
    }

    private final CommandManager commandManager = new CommandManager(this);

    private AbstractEventTrigger eventTrigger;

    /**
     * 传入一个EventTrigger(要先进行初始化)
     * 如果不传入默认WebSocket
     *
     * @param t EventTrigger
     */
    @Override
    public synchronized void initEventListenSystem(@NonNull AbstractEventTrigger t) {
        if (t.getBot() != this) {
            log.error("传入错误的AbstractEventTrigger");
            log.error("{}#getBot!=this", t);
            return;
        }
        if (eventTrigger != null) {
            eventTrigger.close();
        }
        eventTrigger = t;
    }

    /**
     * 卸载这个bot
     */
    @Override
    public void disable() {
        DodoOpenJava.disableBot(this);
    }

    /**
     * 获取机器人名字
     *
     * @return 名字
     */
    @Override
    public String getName() {
        return this.getApi().getBotApi().getBotInfo().getJSONObjectData().getString("nickName");
    }

    /**
     * 获取机器人DodoId
     *
     * @return dodoId
     */
    @Override
    public String getDodoSourceId() {
        return this.getApi().getBotApi().getBotInfo().getJSONObjectData().getString("dodoSourceId");
    }

    /**
     * 获取机器人头像URL
     *
     * @return url
     */
    @Override
    public String getAvatarUrl() {
        JSONObject jsonObject = new JSONObject();
        return this.getApi().getBotApi().getBotInfo().getJSONObjectData().getString("avatarUrl");
    }

    /**
     * Api类
     */
    @Getter
    public static class Api {
        private final Bot bot;

        Api(Bot bot) {
            this.bot = bot;
            botApi = new BotApi(bot);
            channelApi = new ChannelApi(bot);
            channelArticleApi = new ChannelArticleApi(bot);
            channelMessageApi = new ChannelMessageApi(bot);
            channelVoiceApi = new ChannelVoiceApi(bot);
            eventApi = new EventApi(bot);
            giftApi = new GiftApi(bot);
            integralApi = new IntegralApi(bot);
            islandApi = new IslandApi(bot);
            memberApi = new MemberApi(bot);
            ntfApi = new NtfApi(bot);
            personalApi = new PersonalApi(bot);
            resourceApi = new ResourceApi(bot);
            roleApi = new RoleApi(bot);
        }

        public final BotApi botApi;
        public final ChannelApi channelApi;
        public final ChannelArticleApi channelArticleApi;
        public final ChannelMessageApi channelMessageApi;
        public final ChannelVoiceApi channelVoiceApi;
        public final EventApi eventApi;
        public final GiftApi giftApi;
        public final IntegralApi integralApi;
        public final IslandApi islandApi;
        public final MemberApi memberApi;
        public final NtfApi ntfApi;
        public final PersonalApi personalApi;
        public final ResourceApi resourceApi;
        public final RoleApi roleApi;
    }
}