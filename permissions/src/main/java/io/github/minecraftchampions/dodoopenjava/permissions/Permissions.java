package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.*;

/**
 * 权限系统核心
 * @author qscbm187531
 */
public class Permissions {
    public static DataType type;

    /**
     * 初始化
     * @param type 存储类型
     * @return true成功，false失败
     */
    public static Boolean init(DataType type) {
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
        return true;
    }
}