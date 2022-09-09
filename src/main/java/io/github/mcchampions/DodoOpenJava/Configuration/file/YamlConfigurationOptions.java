package io.github.mcchampions.DodoOpenJava.Configuration.file;

import org.apache.commons.lang3.Validate;

/**
 * 用于控制YamlConfiguration的输入和输出的各种设置
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
     * 获取应使用多少空格来缩进每行。
     *
     * 最小值为2，最大值为9。
     *
     * @return 缩进多少
     */
    public int indent() {
        return indent;
    }

    /**
     *设置每行缩进应使用多少空格。
     *
     * 最小值为2，最大值为9
     *
     * @param value 缩进空格数量
     * @return 返回 this
     */
    public YamlConfigurationOptions indent(int value) {
        Validate.isTrue(value >= 2, "缩进最少要2个空格");
        Validate.isTrue(value <= 9, "缩进最多只能有9个空格");

        this.indent = value;
        return this;
    }
}
