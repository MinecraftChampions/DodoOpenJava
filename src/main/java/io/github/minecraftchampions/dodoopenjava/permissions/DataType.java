package io.github.minecraftchampions.dodoopenjava.permissions;

import lombok.Getter;

/**
 * 数据存储类型
 */
@Getter
public enum DataType {
    /**
     * YAML文件
     */
    YAML("YAML"),
    /**
     * JSON文件
     */
    JSON("JSON"),
    /**
     * Xml文件
     */
    Xml("Xml"),
    /**
     * Toml文件
     */
    Toml("Toml");

    DataType(String type) {
        this.type = type;
    }

    /**
     * 数据存储方式
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
