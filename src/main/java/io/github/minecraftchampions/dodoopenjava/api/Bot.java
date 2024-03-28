package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.ApiResultsLogger;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandManager;
import io.github.minecraftchampions.dodoopenjava.event.AbstractEventTrigger;
import io.github.minecraftchampions.dodoopenjava.event.EventManager;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.impl.BotImpl.Api;
import lombok.NonNull;

/**
 * 机器人
 */
public interface Bot {
    /**
     * 获取authorization
     *
     * @return authorization
     */
    String getAuthorization();

    /**
     * 启用日志记录器
     */
    void enableApiResultsLogger();

    /**
     * 卸载日志记录器
     */
    void disableApiResultsLogger();

    /**
     * 注册事件监听器
     *
     * @param listener 监听器
     */
    void registerListener(@NonNull Listener listener);

    /**
     * 移除事件监听器
     */
    void removeEventTrigger();

    /**
     * 注册事件监听器
     *
     * @param listeners 监听器
     */
    void registerListeners(@NonNull Listener... listeners);

    /**
     * 注册命令
     *
     * @param commandExecutor 命令处理器
     */
    void registerCommand(@NonNull CommandExecutor commandExecutor);

    /**
     * 获取Api请求记录器
     *
     * @return ApiResultsLogger
     */
    ApiResultsLogger getApiResultsLogger();

    /**
     * 注册命令
     *
     * @param commandExecutors 命令处理器
     */
    void registerCommands(@NonNull CommandExecutor... commandExecutors);

    /**
     * 传入一个EventTrigger(要先进行初始化)
     * 如果不传入默认WebSocket
     *
     * @param t EventTrigger
     */
    void initEventListenSystem(@NonNull AbstractEventTrigger t);

    /**
     * 卸载这个bot
     */
    void disable();

    /**
     * 获取机器人名字
     *
     * @return 名字
     */
    String getName();

    /**
     * 获取机器人DodoId
     *
     * @return dodoId
     */
    String getDodoSourceId();

    /**
     * 获取机器人头像URL
     *
     * @return url
     */
    String getAvatarUrl();

    String getClientId();

    String getToken();

    EventManager getEventManager();

    Api getApi();

    CommandManager getCommandManager();

    AbstractEventTrigger getEventTrigger();
}
