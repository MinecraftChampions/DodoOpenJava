package io.github.mcchampions.DodoOpenJava.Permissions;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.*;
import io.github.mcchampions.DodoOpenJava.Permissions.Data.*;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

/**
 * 权限系统核心
 */
public class Permissions implements Listener {
    public static Boolean initFileListener = false;

    public static EventManage EM = new EventManage();

    public static DataType type;

    public static String Authorization;

    /**
     * 初始化（使用MongoDB数据库）
     * @param ip IP地址
     * @param port 端口
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean initMongoDB(String ip, int port, String Authorization) throws IOException {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,port);
        }
        EM.registerEvents(new Permissions(),Authorization);
        Permissions.type = DataType.MongoDB;
        Permissions.Authorization = Authorization;
        listener();
        return true;
    }

    /**
     * 初始化（使用MongoDB数据库）
     * @param ip IP地址
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean initMongoDB(String ip, String Authorization) throws IOException {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,25575);
        }
        EM.registerEvents(new Permissions(),Authorization);
        Permissions.type = DataType.MongoDB;
        Permissions.Authorization = Authorization;
        listener();
        return true;
    }

    /**
     * 初始化
     * @param type 存储类型
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean init(DataType type, String Authorization) throws IOException {
        switch (type.toString()) {
            case "YAML" -> YamlData.init();
            case "JSON" -> JsonData.init();
            case "Xml" -> XmlData.init();
            case "Toml" -> TomlData.init();
            default -> {
                return false;
            }
        }
        EM.registerEvents(new Permissions(),Authorization);
        Permissions.type = type;
        Permissions.Authorization = Authorization;
        listener();
        return true;
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onMessageEvent(MessageEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onCardMessageButtonClickEvent(CardMessageButtonClickEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onCardMessageListSubmitEvent(CardMessageListSubmitEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onCardMessageFormSubmitEvent(CardMessageFormSubmitEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onMemberJoinEvent(MemberJoinEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onMessageReactionEvent(MessageReactionEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onPersonalMessageEvent(PersonalMessageEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onChannelArticleCommentEvent(ChannelArticleCommentEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onChannelArticleEvent(ChannelArticleEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onChannelVoiceMemberJoinEvent(ChannelVoiceMemberJoinEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听事件并判断用户权限是否初始化，如果没有则初始化
     * @param e 事件
     */
    @EventHandler
    public void onChannelVoiceMemberLeaveEvent(ChannelVoiceMemberLeaveEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听文件更改
     * @throws IOException 处理异常时抛出
     */
    public static void listener() throws IOException {
        if (initFileListener) return;
        if (Objects.equals(type.toString(), "MongoDBData")) return;
        initFileListener = true;
        Path path = Paths.get(ConfigUtil.getJarPath() + "permissions/");
        WatchService watcher = FileSystems.getDefault().newWatchService();
        path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
        try {
            while (true) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    init(type,Authorization);
                }
                if (!key.reset()) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}