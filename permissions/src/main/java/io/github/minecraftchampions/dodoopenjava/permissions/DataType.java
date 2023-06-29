package io.github.minecraftchampions.dodoopenjava.permissions;

/**
 * ���ݴ洢����
 */
public enum DataType {
    /**
     * YAML�ļ�
     */
    YAML("YAML"),
    /**
     * JSON�ļ�
     */
    JSON("JSON"),
    /**
     * Xml�ļ�
     */
    Xml("Xml"),
    /**
     * Toml�ļ�
     */
    Toml("Toml");

    DataType(String type) {
        this.type = type;
    }

    /**
     * ���ݴ洢��ʽ
     */
    private final String type;

    /**
     * ��ȡ����
     * @return ����
     */
    public String getType() {
        return type;
    }
}
