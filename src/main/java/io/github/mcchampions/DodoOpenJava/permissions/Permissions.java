package io.github.mcchampions.DodoOpenJava.permissions;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.*;
import io.github.mcchampions.DodoOpenJava.Utils.ConfigUtil;
import io.github.mcchampions.DodoOpenJava.permissions.Data.Json;
import io.github.mcchampions.DodoOpenJava.permissions.Data.MongoDB;
import io.github.mcchampions.DodoOpenJava.permissions.Data.Xml;
import io.github.mcchampions.DodoOpenJava.permissions.Data.Yaml;

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

    public static String ad;

    /**
     * 初始化
     * @param type 数据存储类型
     * @param ip IP
     * @param ad 机器人AD
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean init(DataType type, String ip, String ad) throws IOException {
        return init(type,ip,25077,ad);
    }

    /**
     * 初始化
     * @param type 数据存储类型
     * @param ad 机器人AD
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean init(DataType type,String ad) throws IOException {
        return init(type,"127.0.0.1",25077,ad);
    }

    /**
     * 初始化
     * @param type 数据存储类型
     * @param port 端口
     * @param ad 机器人AD
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean init(DataType type,int port,String ad) throws IOException {
        return init(type,"127.0.0.1",port,ad);
    }

    /**
     * 初始化
     * @param type 数据存储类型
     * @param ip ip
     * @param ad 机器人AD
     * @param port 端口
     * @return true成功，false失败
     * @throws IOException 初始化出现问题抛出
     */
    public static Boolean init(DataType type,String ip, int port,String ad) throws IOException {
        if (Objects.equals(type.toString(), "MongoDB")) {
            MongoDB mongoDB = new MongoDB();
            mongoDB.initPermissions(ip,port);
        } else if (Objects.equals(type.toString(), "YAML")) {
            Yaml.init();
        } else if (Objects.equals(type.toString(), "JSON")) {
            Json.init();
        } else if (Objects.equals(type.toString(), "JSON")) {
            Xml.init();
        } else {
            return false;
        }
        EM.registerEvents(new Permissions(),ad);
        Permissions.type = type;
        Permissions.ad = ad;
        listener();
        return true;
    }

    @EventHandler
    public void onMessageEvent(MessageEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    @EventHandler
    public void onCardMessageButtonClickEvent(CardMessageButtonClickEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    @EventHandler
    public void onCardMessageListSubmitEvent(CardMessageListSubmitEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    @EventHandler
    public void onCardMessageFormSubmitEvent(CardMessageFormSubmitEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    @EventHandler
    public void onMemberJoinEvent(MemberJoinEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    @EventHandler
    public void onMessageReactionEvent(MessageReactionEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    @EventHandler
    public void onPersonalMessageEvent(PersonalMessageEvent e) {
        User.editUserGroup(e.getDodoId(),User.getUserGroup(e.getDodoId()));
        User.addPerm(e.getDodoId(), "");
    }

    /**
     * 监听文件更改
     * @throws IOException 处理异常时抛出
     */
    public static void listener() throws IOException {
        if (initFileListener) return;
        if (Objects.equals(type.toString(), "MongoDB")) return;
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
                    init(type,ad);
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