package io.github.mcchampions.DodoOpenJava.Configuration;

import java.util.Map;

/**
 * 配置文件的基类.
 */
public interface Configuration extends ConfigurationSection {
    /**
     * 设置指定路径的缺省值.
     * 如果没有默认的 Configuration. 那么将会建立一个新的 MemoryConfiguration 用于保存.
     *
     * 如果值为 null ，该值将被从默认的配置源中删除.
     *
     * @param path 路径
     * @param value 缺省值
     * @throws IllegalArgumentException 如果路径为 null .
     */
    void addDefault(String path, Object value);

    /**
     * 把指定map里面的键值都加入到缺省值列表.
     * 如果没有缺省值 Configuration, 那么将会建立一个新的缺省值 MemoryConfiguration 用于保存.
     *
     * 如果值为 null , 将会删除该路径上的缺省值.
     *
     * @param defaults Map 的键是路径, 值是对应路径的值.
     * @throws IllegalArgumentException 如果defaults为null.
     */
    void addDefaults(Map<String, Object> defaults);

    /**
     * 把指定 Configuration 全部加入到缺省值列表.
     * 如果没有缺省值 Configuration, 那么将会建立一个新的 MemoryConfiguration 用于保存.
     *
     * 你可以使用setDefaults(org.bukkit.configuration.Configuration)来设置缺省值列表来源.
     *
     * @param defaults 包含要复制的默认值列表的配置
     * @throws IllegalArgumentException 如果defaults为null
     */
    void addDefaults(Configuration defaults);

    /**
     * 设置新的缺省值列表.
     * 将直接替换原有的缺省值列表(如果有).
     *
     * @param defaults 新的 Configuration .
     * @throws IllegalArgumentException 当参数为 null 或 defaults == getDefaults() 时, 抛出此异常.
     */
    void setDefaults(Configuration defaults);

    /**
     * 获取这个 Configuration 的缺省值 Configuration.
     * 如果设置过缺省值, 即使没有设置缺省值列表, 也会返回 Configuration.
     *
     * 如果都没有, 则返回 null.
     *
     * @return 返回缺省值列表, 如果没有则返回 null.
     */
    Configuration getDefaults();

    /**
     * 获取这个 Configuration 的 ConfigurationOptions.
     * 如需修改配置,直接修改返回值即可.
     *
     * @return 这个配置文件的一些配置(格式之类的).
     */
    ConfigurationOptions options();
}
