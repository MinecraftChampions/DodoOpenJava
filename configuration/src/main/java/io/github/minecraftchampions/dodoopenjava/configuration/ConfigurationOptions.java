package io.github.minecraftchampions.dodoopenjava.configuration;

/**
 * Configuration ��������.
 */
public class ConfigurationOptions {
    private char pathSeparator = '.';
    private boolean copyDefaults = false;
    private final Configuration configuration;

    protected ConfigurationOptions(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * ���ع���������õ� Configuration.
     */
    public Configuration configuration() {
        return configuration;
    }

    /**
     * ��ȡ���ڷ��� ConfigurationSection ��·���� char.
     * ���char������Ӱ�����ݵĴ���, ��ֻ��·���ķָ���. ֻ��Ӱ�����ڳ�����������ȡ����.Ĭ��Ϊ '.'.
     *
     * @return ·���ָ��
     */
    public char pathSeparator() {
        return pathSeparator;
    }

    /**
     * �������ڷ��� ConfigurationSection ��·���� char.
     * ��� char ������Ӱ�����ݵĴ���, ��ֻ��·���ķָ���. ֻ��Ӱ�����ڳ�����������ȡ����.Ĭ��Ϊ '.'.
     * @param value Path ·���ָ��.
     * @return ���� this .
     */
    public ConfigurationOptions pathSeparator(char value) {
        this.pathSeparator = value;
        return this;
    }

    /**
     * ������Configuration �ǲ���ֱ�Ӵ�ȱʡֵ Configuration ���︴�ƹ�����.
     * ���Ϊ��, ��������б��е�ֵ���Ǵ�ȱʡֵ Configuration �и��ƹ�����.
     *
     * ����б�������. ��ʼ�շ���ȱʡֵ�б��е�ֵ. ���Կ�����ֻ����ȱʡֵ�б�.
     *
     * Ĭ��ֵ��false.
     */
    public boolean copyDefaults() {
        return copyDefaults;
    }

    /**
     * ������Configuration�� ����Ĭ��Configuration����ֱ�� ����ֵ, ����Ϊtrue.
     * ���ֵΪ true, ��ֱ�Ӵ�Ĭ��Դ�и������е�ֵ.
     *
     * ��������(ʹ�ò����ֱܷ����ã�Ĭ����������ṩ��ֵ��ֵ�����֡�?). ����ConfigurationSection.contains(java.lang.String), ��ʼ�շ�����ͬ��ֵConfigurationSection.isSet(java.lang.String).
     *
     * Ĭ��ֵ��false
     */
    public ConfigurationOptions copyDefaults(boolean value) {
        this.copyDefaults = value;
        return this;
    }
}
