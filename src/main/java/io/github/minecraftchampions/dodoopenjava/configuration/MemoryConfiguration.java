package io.github.minecraftchampions.dodoopenjava.configuration;

import lombok.Getter;

import java.util.Map;

/**
 * 这是一个配置实现，不保存或从任何源加载，只将所有值存储在内存中。这对于提供缺省值的临时配置非常有用。
 */
public class MemoryConfiguration extends MemorySection implements Configuration {
    @Getter
    protected Configuration defaults;
    protected MemoryConfigurationOptions options;

    /**
     * 创建没有缺省值的空 MemoryConfiguration
     */
    public MemoryConfiguration() {
    }

    /**
     * 使用指定的配置作为所有缺省值的源创建空 MemoryConfiguration
     *
     * @param defaults 缺省值
     * @throws IllegalArgumentException 缺省值为null
     */
    public MemoryConfiguration(Configuration defaults) {
        this.defaults = defaults;
    }

    @Override
    public void addDefault(String path, Object value) {
        if (defaults == null) {
            defaults = new MemoryConfiguration();
        }

        defaults.set(path, value);
    }

    public void addDefaults(Map<String, Object> defaults) {
        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            addDefault(entry.getKey(), entry.getValue());
        }
    }

    public void addDefaults(Configuration defaults) {
        addDefaults(defaults.getValues(true));
    }

    public void setDefaults(Configuration defaults) {
        this.defaults = defaults;
    }

    @Override
    public ConfigurationSection getParent() {
        return null;
    }

    public MemoryConfigurationOptions options() {
        if (options == null) {
            options = new MemoryConfigurationOptions(this);
        }

        return options;
    }
}
