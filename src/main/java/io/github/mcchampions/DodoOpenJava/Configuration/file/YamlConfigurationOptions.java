package io.github.mcchampions.DodoOpenJava.Configuration.file;

import org.apache.commons.lang3.Validate;

/**
 * ���ڿ���YamlConfiguration�����������ĸ�������
 */
public class YamlConfigurationOptions extends FileConfigurationOptions {
    private int indent = 2;

    protected YamlConfigurationOptions(YamlConfiguration configuration) {
        super(configuration);
    }

    @Override
    public YamlConfiguration configuration() {
        return (YamlConfiguration) super.configuration();
    }

    @Override
    public YamlConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    public YamlConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    @Override
    public YamlConfigurationOptions header(String value) {
        super.header(value);
        return this;
    }

    @Override
    public YamlConfigurationOptions copyHeader(boolean value) {
        super.copyHeader(value);
        return this;
    }

    /**
     * ��ȡӦʹ�ö��ٿո�������ÿ�С�
     *
     * ��СֵΪ2�����ֵΪ9��
     *
     * @return ��������
     */
    public int indent() {
        return indent;
    }

    /**
     *����ÿ������Ӧʹ�ö��ٿո�
     *
     * ��СֵΪ2�����ֵΪ9
     *
     * @param value �����ո�����
     * @return ���� this
     */
    public YamlConfigurationOptions indent(int value) {
        Validate.isTrue(value >= 2, "��������Ҫ2���ո�");
        Validate.isTrue(value <= 9, "�������ֻ����9���ո�");

        this.indent = value;
        return this;
    }
}
