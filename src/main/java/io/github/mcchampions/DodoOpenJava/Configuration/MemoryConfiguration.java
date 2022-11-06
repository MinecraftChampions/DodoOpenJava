package io.github.mcchampions.DodoOpenJava.Configuration;

import java.util.Map;

import org.apache.commons.lang3.Validate;

/**
 * ����һ������ʵ�֣����������κ�Դ���أ�ֻ������ֵ�洢���ڴ��С�������ṩȱʡֵ����ʱ���÷ǳ����á�
 */
public class MemoryConfiguration extends MemorySection implements Configuration {
    protected Configuration defaults;
    protected MemoryConfigurationOptions options;

    /**
     * ����û��ȱʡֵ�Ŀ� MemoryConfiguration
     */
    public MemoryConfiguration() {}

    /**
     * ʹ��ָ����������Ϊ����ȱʡֵ��Դ������ MemoryConfiguration
     *
     * @param defaults ȱʡֵ
     * @throws IllegalArgumentException ȱʡֵΪnull
     */
    public MemoryConfiguration(Configuration defaults) {
        this.defaults = defaults;
    }

    @Override
    public void addDefault(String path, Object value) {
        Validate.notNull(path, "·������Ϊnull!");

        if (defaults == null) {
            defaults = new MemoryConfiguration();
        }

        defaults.set(path, value);
    }

    public void addDefaults(Map<String, Object> defaults) {
        Validate.notNull(defaults, "ȱʡֵ����Ϊnull!");

        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            addDefault(entry.getKey(), entry.getValue());
        }
    }

    public void addDefaults(Configuration defaults) {
        Validate.notNull(defaults, "ȱʡֵ����Ϊnull!");

        addDefaults(defaults.getValues(true));
    }

    public void setDefaults(Configuration defaults) {
        Validate.notNull(defaults, "ȱʡֵ����Ϊnull!");

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
