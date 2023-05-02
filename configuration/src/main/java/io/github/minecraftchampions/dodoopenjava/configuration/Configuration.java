package io.github.minecraftchampions.dodoopenjava.configuration;

import java.util.Map;

/**
 * �����ļ��Ļ���.
 */
public interface Configuration extends ConfigurationSection {
    /**
     * ����ָ��·����ȱʡֵ.
     * ���û��Ĭ�ϵ� Configuration. ��ô���Ὠ��һ���µ� MemoryConfiguration ���ڱ���.
     *
     * ���ֵΪ null ����ֵ������Ĭ�ϵ�����Դ��ɾ��.
     *
     * @param path ·��
     * @param value ȱʡֵ
     * @throws IllegalArgumentException ���·��Ϊ null .
     */
    void addDefault(String path, Object value);

    /**
     * ��ָ��map����ļ�ֵ�����뵽ȱʡֵ�б�.
     * ���û��ȱʡֵ Configuration, ��ô���Ὠ��һ���µ�ȱʡֵ MemoryConfiguration ���ڱ���.
     *
     * ���ֵΪ null , ����ɾ����·���ϵ�ȱʡֵ.
     *
     * @param defaults Map �ļ���·��, ֵ�Ƕ�Ӧ·����ֵ.
     * @throws IllegalArgumentException ���defaultsΪnull.
     */
    void addDefaults(Map<String, Object> defaults);

    /**
     * ��ָ�� Configuration ȫ�����뵽ȱʡֵ�б�.
     * ���û��ȱʡֵ Configuration, ��ô���Ὠ��һ���µ� MemoryConfiguration ���ڱ���.
     *
     * �����ʹ��setDefaults(org.bukkit.configuration.Configuration)������ȱʡֵ�б���Դ.
     *
     * @param defaults ����Ҫ���Ƶ�Ĭ��ֵ�б������
     * @throws IllegalArgumentException ���defaultsΪnull
     */
    void addDefaults(Configuration defaults);

    /**
     * �����µ�ȱʡֵ�б�.
     * ��ֱ���滻ԭ�е�ȱʡֵ�б�(�����).
     *
     * @param defaults �µ� Configuration .
     * @throws IllegalArgumentException ������Ϊ null �� defaults == getDefaults() ʱ, �׳����쳣.
     */
    void setDefaults(Configuration defaults);

    /**
     * ��ȡ��� Configuration ��ȱʡֵ Configuration.
     * ������ù�ȱʡֵ, ��ʹû������ȱʡֵ�б�, Ҳ�᷵�� Configuration.
     *
     * �����û��, �򷵻� null.
     *
     * @return ����ȱʡֵ�б�, ���û���򷵻� null.
     */
    Configuration getDefaults();

    /**
     * ��ȡ��� Configuration �� ConfigurationOptions.
     * �����޸�����,ֱ���޸ķ���ֵ����.
     *
     * @return ��������ļ���һЩ����(��ʽ֮���).
     */
    ConfigurationOptions options();
}
