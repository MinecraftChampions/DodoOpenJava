package io.github.mcchampions.DodoOpenJava.Configuration;

import java.util.Map;

import org.apache.commons.lang3.Validate;

/**
 * 这是一个配置实现，不保存或从任何源加载，只将所有值存储在内存中。这对于提供缺省值的临时配置非常有用。
 */
public class MemoryConfiguration extends MemorySection implements Configuration {
    protected Configuration defaults;
    protected MemoryConfigurationOptions options;

    /**
     * 创建没有缺省值的空 MemoryConfiguration
     */
    public MemoryConfiguration() {}

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
        Validate.notNull(path, "路径不能为null!");

        if (defaults == null) {
            defaults = new MemoryConfiguration();
        }

        defaults.set(path, value);
    }

    public void addDefaults(Map<String, Object> defaults) {
        Validate.notNull(defaults, "缺省值不能为null!");

        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            addDefault(entry.getKey(), entry.getValue());
        }
    }

    public void addDefaults(Configuration defaults) {
        Validate.notNull(defaults, "缺省值不能为null!");

        addDefaults(defaults.getValues(true));
    }

    public void setDefaults(Configuration defaults) {
        Validate.notNull(defaults, "缺省值不能为null!");

        this.defaults = defaults;
    }

    public Configuration getDefaults() {
        return defaults;
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
