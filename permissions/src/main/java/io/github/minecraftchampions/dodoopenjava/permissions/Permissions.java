package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.*;

import java.util.Objects;

/**
 * Ȩ��ϵͳ����
 * @author qscbm187531
 */
public class Permissions {
    public static DataType type;

    public static String Authorization;

    /**
     * ��ʼ����ʹ��MongoDB���ݿ⣩
     * @param ip IP��ַ
     * @param port �˿�
     * @param Authorization Authorization
     * @return true�ɹ���falseʧ��
     */
    public static Boolean initMongoDB(String ip, int port, String Authorization) {
        if (!Objects.equals(type.getType(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,port);
        }
        Permissions.type = DataType.MongoDB;
        Permissions.Authorization = Authorization;
        return true;
    }

    /**
     * ��ʼ����ʹ��MongoDB���ݿ⣩
     * @param ip IP��ַ
     * @param Authorization Authorization
     * @return true�ɹ���falseʧ��
     */
    public static Boolean initMongoDB(String ip, String Authorization) {
        if (!Objects.equals(type.getType(), "MongoDB")) {
            MongoDBData mongoDB = new MongoDBData();
            mongoDB.initPermissions(ip,25575);
        }
        Permissions.type = DataType.MongoDB;
        Permissions.Authorization = Authorization;
        return true;
    }

    /**
     * ��ʼ��
     * @param type �洢����
     * @param Authorization Authorization
     * @return true�ɹ���falseʧ��
     */
    public static Boolean init(DataType type, String Authorization) {
        switch (type.getType()) {
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