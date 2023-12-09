package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandManager;
import io.github.minecraftchampions.dodoopenjava.event.EventManager;
import io.github.minecraftchampions.dodoopenjava.event.EventTrigger;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.WebSocketEventTrigger;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import lombok.Getter;

/**
 * 机器人实例
 */
@Getter
public class Bot {
    /**
     * 机器人唯一标识
     */
    private final String clientId;

    /**
     * 机器人鉴权Token
     */
    private final String token;

    /**
     * 事件管理器
     */
    private final EventManager eventManager = new EventManager();

    public Bot(String clientId,String token) {
        this.clientId = clientId;
        this.token = token;
    }

    /**
     * 获取authorization
     *
     * @return authorization
     */
    public String getAuthorization() {
        return BaseUtil.Authorization(clientId,token);
    }

    /**
     * 注册事件监听器
     * @param listener 监听器
     */
    public void registerListener(Listener listener) {
        if (eventTrigger == null) {
            initEventListenSystem(new WebSocketEventTrigger(this));
        }
        if (!eventTrigger.isConnect()) {
            eventTrigger.start();
        }
        getEventManager().registerListener(listener);
    }

    /**
     * 注册事件监听器
     * @param listeners 监听器
     */
    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }

    /**
     * 注册命令
     * @param commandExecutor 命令处理器
     */
    public void registerCommand(CommandExecutor commandExecutor) {
        commandManager.registerCommand(commandExecutor);
    }

    /**
     * 注册命令
     * @param commandExecutors 命令处理器
     */
    public void registerCommands(CommandExecutor... commandExecutors) {
        for (CommandExecutor commandExecutor : commandExecutors) {
            registerCommand(commandExecutor);
        }
    }

    private final CommandManager commandManager = new CommandManager(this);

    private EventTrigger eventTrigger;

    /**
     * 传入一个EventTrigger(要先进行初始化)
     * 如果不传入默认WebSocket
     * @param t EventTrigger
     */
    public void initEventListenSystem(EventTrigger t) {
        if (t.getBot() != this) {
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
    public void disable() {
        commandManager.unregisterAllCommands();
        eventManager.unregisterAllListeners();
        if (eventTrigger != null) {
            eventTrigger.close();
        }
        eventTrigger = null;
        DodoOpenJava.getBots().remove(this);
    }
}
