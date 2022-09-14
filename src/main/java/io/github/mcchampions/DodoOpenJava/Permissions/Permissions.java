package io.github.mcchampions.DodoOpenJava.Permissions;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.*;
import io.github.mcchampions.DodoOpenJava.Permissions.Data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限系统核心
 */
public class Permissions implements Listener {
    public static Boolean initFileListener = false;

    public static EventManage EM = new EventManage();

    public static DataType type;

    public static List<String> Authorizations = new ArrayList<>();

    /**
     * 初始化（使用MongoDB数据库）
     * @param ip IP地址
     * @param port 端口
     * @param Authorization 所有机器人的Authorization
     * @return true成功，false失败
     */
    public static Boolean initMongoDB(String ip, int port, List<String> Authorization) {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,port);
        }
        for (String an : Authorization) {
            EM.registerEvents(new Permissions(),an);
        }
        Permissions.type = DataType.MongoDB;
        Permissions.Authorizations = Authorization; 
        return true;
    }

    /**
     * 初始化（使用MongoDB数据库）
     * @param ip IP地址
     * @param Authorization 所有机器人的Authorization
     * @return true成功，false失败
     */
    public static Boolean initMongoDB(String ip, List<String> Authorization) {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,25575);
        }
        for (String an : Authorization) {
            EM.registerEvents(new Permissions(),an);
        }
        Permissions.type = DataType.MongoDB;
        Permissions.Authorizations = Authorization; 
        return true;
    }

    /**
     * 初始化
     * @param type 存储类型
     * @param Authorization 所有机器人的Authorization
     * @return true成功，false失败
     */
    public static Boolean init(DataType type, List<String> Authorization) {
        switch (type.toString()) {
            case "YAML" -> {
                YamlData.init();
                System.out.println("a");
            }
            case "JSON" -> JsonData.init();
            case "Xml" -> XmlData.init();
            case "Toml" -> TomlData.init();
        }
        for (String an : Authorization) {
            EM.registerEvents(new Permissions(),an);
        }
        Permissions.type = type;
        Permissions.Authorizations = Authorization;
        return true;
    }

    /**
     * 初始化（使用MongoDB数据库）
     * @param ip IP地址
     * @param port 端口
     * @param Authorization Authorization
     * @return true成功，false失败
     */
    public static Boolean initMongoDB(String ip, int port, String Authorization) {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,port);
        }
        EM.registerEvents(new Permissions(),Authorization);
        Permissions.type = DataType.MongoDB;
        Permissions.Authorizations = List.of(Authorization); 
        return true;
    }

    /**
     * 初始化（使用MongoDB数据库）
     * @param ip IP地址
     * @param Authorization Authorization
     * @return true成功，false失败
     */
    public static Boolean initMongoDB(String ip, String Authorization) {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,25575);
        }
        EM.registerEvents(new Permissions(),Authorization);
        Permissions.type = DataType.MongoDB;
        Permissions.Authorizations = List.of(Authorization);
        return true;
    }

    /**
     * 初始化
     * @param type 存储类型
     * @param Authorization Authorization
     * @return true成功，false失败
     */
    public static Boolean init(DataType type, String Authorization) {
        switch (type.toString()) {
            case "YAML" -> YamlData.init();
            case "JSON" -> JsonData.init();
            case "Xml" -> XmlData.init();
            case "Toml" -> TomlData.init();
            default -> {
                return false;
            }
        }
        System.out.println("d");
        EM.registerEvents(new Permissions(),Authorization);
        System.out.println("d");
        Permissions.type = type;
        Permissions.Authorizations = List.of(Authorization);
        System.out.println("d"); 
        System.out.println("d");
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
}