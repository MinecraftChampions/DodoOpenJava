package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.*;

/**
 * Ȩ��ϵͳ����
 * @author qscbm187531
 */
public class Permissions {
    public static DataType type;

    /**
     * ��ʼ��
     * @param type �洢����
     * @return true�ɹ���falseʧ��
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