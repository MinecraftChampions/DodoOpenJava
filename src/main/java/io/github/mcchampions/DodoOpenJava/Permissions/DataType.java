package io.github.mcchampions.DodoOpenJava.Permissions;

/**
 * 数据存储类型
 * @author qscbm187531
 */
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
     * MongoDB数据库
     */
    MongoDB("MongoDB"),
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
     */
    private final String type;

    /**
     * 获取类型
     * @return 类型
     */
    public String getType() {
        return type;
    }
}
