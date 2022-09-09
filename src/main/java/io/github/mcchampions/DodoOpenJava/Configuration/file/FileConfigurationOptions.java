package io.github.mcchampions.DodoOpenJava.Configuration.file;

import io.github.mcchampions.DodoOpenJava.Configuration.*;

/**
 * 用于控制文件配置的输入和输出的各种设置
 */
public class FileConfigurationOptions extends MemoryConfigurationOptions {
    private String header = null;
    private boolean copyHeader = true;

    protected FileConfigurationOptions(MemoryConfiguration configuration) {
        super(configuration);
    }

    @Override
    public FileConfiguration configuration() {
        return (FileConfiguration) super.configuration();
    }

    @Override
    public FileConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    public FileConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    public String header() {
        return header;
    }

    public FileConfigurationOptions header(String value) {
        this.header = value;
        return this;
    }

    public boolean copyHeader() {
        return copyHeader;
    }

    public FileConfigurationOptions copyHeader(boolean value) {
        copyHeader = value;

        return this;
    }
}
