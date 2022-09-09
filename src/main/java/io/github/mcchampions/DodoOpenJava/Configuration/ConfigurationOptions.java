package io.github.mcchampions.DodoOpenJava.Configuration;

/**
 * Configuration 的配置类.
 */
public class ConfigurationOptions {
    private char pathSeparator = '.';
    private boolean copyDefaults = false;
    private final Configuration configuration;

    protected ConfigurationOptions(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 返回关联这个配置的 Configuration.
     */
    public Configuration configuration() {
        return configuration;
    }

    /**
     * 获取用于分离 ConfigurationSection 中路径的 char.
     * 这个char并不会影响数据的储存, 它只是路径的分隔符. 只会影响你在程序中怎样读取数据.默认为 '.'.
     *
     * @return 路径分割符
     */
    public char pathSeparator() {
        return pathSeparator;
    }

    /**
     * 设置用于分离 ConfigurationSection 中路径的 char.
     * 这个 char 并不会影响数据的储存, 它只是路径的分隔符. 只会影响你在程序中怎样读取数据.默认为 '.'.
     * @param value Path 路径分割符.
     * @return 返回 this .
     */
    public ConfigurationOptions pathSeparator(char value) {
        this.pathSeparator = value;
        return this;
    }

    /**
     * 检查这个Configuration 是不是直接从缺省值 Configuration 那里复制过来的.
     * 如果为真, 表明这个列表中的值都是从缺省值 Configuration 中复制过来的.
     *
     * 这个列表将被锁定. 并始终返回缺省值列表中的值. 可以看作是只读的缺省值列表.
     *
     * 默认值是false.
     */
    public boolean copyDefaults() {
        return copyDefaults;
    }

    /**
     * 如果这个Configuration从 它的默认Configuration那里直接 复制值, 就设为true.
     * 如果值为 true, 将直接从默认源中复制所有的值.
     *
     * 机器翻译(使得不可能分别设置，默认情况下所提供的值的值和区分。?). 其结果ConfigurationSection.contains(java.lang.String), 将始终返回相同的值ConfigurationSection.isSet(java.lang.String).
     *
     * 默认值是false
     */
    public ConfigurationOptions copyDefaults(boolean value) {
        this.copyDefaults = value;
        return this;
    }
}
