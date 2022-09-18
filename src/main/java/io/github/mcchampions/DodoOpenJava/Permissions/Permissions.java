package io.github.mcchampions.DodoOpenJava.Permissions;

import io.github.mcchampions.DodoOpenJava.Event.EventManage;
import io.github.mcchampions.DodoOpenJava.Permissions.Data.*;

import java.util.Objects;

/**
 * 权限系统核心
 */
public class Permissions {

    public static EventManage EM = new EventManage();

    public static DataType type;

    public static String Authorization;

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
        Permissions.type = DataType.MongoDB;
        Permissions.Authorization = Authorization;
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
        Permissions.type = DataType.MongoDB;
        Permissions.Authorization = Authorization;
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
        Permissions.type = type;
        Permissions.Authorization = Authorization;
        return true;
    }
}