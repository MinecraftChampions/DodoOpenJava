package io.github.mcchampions.DodoOpenJava.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * Configuration�Ļ���. ����������չ�����ļ���ȡ���඼Ӧ��ʵ�����·���.
 */
public interface ConfigurationSection {
    /**
     * ��ȡ�������ļ��ļ�����.
     * ���Ϊ true, �򷵻ذ������е��ܷ��ʵ��ļ��ļ���. �����ڻ�ȡӲ���е�һ��Ŀ¼���Ǳ���ȫ����Ŀ¼.
     *
     * ����:
     * top1. Second1
     * top1. Second2
     *
     * @param deep ��ȡȫ����, ���߽�����ȡ����.
     * @return ������һ�� set, װ���ŷ���Ҫ��ļ�.
     */
    Set<String> getKeys(boolean deep);

    /**
     * ��ȡ��������ļ��ļ�ֵ����.
     * ���Ϊ true, �򷵻ذ������е��ܷ��ʵ��ļ���ֵ�ļ���. �����ڻ�ȡӲ���е�һ��Ŀ¼���Ǳ���ȫ����Ŀ¼.
     *
     * ���Ϊ false, �򷵻ر��ļ���ֵ�ļ���.
     *
     * @param deep ��ȡȫ����ֵ����(true), ���߽�����ȡ����ֵ����(false).
     * @return ����һ�� Map.
     */
    Map<String, Object> getValues(boolean deep);

    /**
     * ��� ConfigurationSection �Ƿ����ָ��·��.
     * ������·��������, ����ָ��һ��ȱʡֵ, Ҳ������ true.
     *
     * @param path Ҫ����·��
     * @return ����˲��ְ��������·��������ͨ��Ĭ�ϵĻ��߱�����.
     * @throws IllegalArgumentException ���·���� null ʱ�׳����쳣.
     */
    boolean contains(String path);

    /**
     * ���ָ��·���Ƿ��� Set.
     * ���·������, ������ Set, �򷵻� false.
     *
     * ���·��������, �򷵻� false.
     *
     * ���·��������, ����ȱʡ�б��д��ڸ�·��, ����ȱʡ�б����ظ�ƥ��ù���, ֱ������һ���ʵ���ֵ.
     *
     * @param path ���·��.
     * @return ���·������, ������ Set, �򷵻� false. ���·��������, �򷵻� false. ���·��������, ����ȱʡ�б��д��ڸ�·��, ����ȱʡ�б����ظ�ƥ��ù���, ֱ������һ���ʵ���ֵ.
     * @throws IllegalArgumentException ���·��Ϊ null ����׳����쳣.
     */
    boolean isSet(String path);

    /**
     * �Ӹ� Configuration �л�ȡ��� ConfigurationSection ��·��.
     * ������ ConfigurationSection �Ѿ��Ǹ�Ŀ¼, ������һ�����ַ���.
     *
     * ������ ConfigurationSection �������κθ�Ŀ¼, ������ null.
     *
     * ���Ҫ��ȡ��� ConfigurationSection ����,Ҳ����·���е����һ��, ��Ӧ��ʹ�� getName() ����ȡ.
     *
     * @return ���Ƭ������������·��.
     */
    String getCurrentPath();

    /**
     * ��ȡ Configuration ������
     * ��Ӧ���� getCurrentPath() �ĺ���һ��
     *
     * @return ����
     */
    String getName();

    /**
     * ��ȡ������ConfigurationSection�ĸ�����
     *
     * �����κ����ñ����⽫�������Լ��Ķ���
     *
     * ����ý������κ�ԭ���ٰ���������У����类�滻Ϊ��ͬ��ֵ������ܷ���null��
     *
     * @return �����˽ڵĸ����á�
     */
    Configuration getRoot();

    /**
     * ��ȡֱ�Ӱ��������ýڵĸ����ýڡ�
     *
     * �����κ����ñ����⽫����null��
     *
     * ����ý������κ�ԭ���ٰ������丸���У����类�滻Ϊ����ֵ������ܻ᷵��null��
     *
     * @return �����˽ڵĸ��ڡ�
     */
    ConfigurationSection getParent();

    /**
     * ��ָ��·����ȡһ�� Object ���͵�ֵ.
     * ������ Object ������, ����ָ��һ��ȱʡֵ, �⽫����ȱʡֵ.
     *
     * ������ Object ������, ����û��ָ��ȱʡֵ, �򷵻� null.
     *
     * @param path ��ȡ Object ��·��.
     * @return ����һ�� Object.
     */
    Object get(String path);

    /**
     * ��ָ��·���ϻ�ȡһ�� Object , ����޷���ȡ, ��ֱ�ӷ���Ĭ��ֵ.
     * ��� Object �޷��� Configuration �б���ȡ, �򲻻᳢��ȥȱʡ�б���ȥѰ��, ����ֱ�ӷ���ָ����Ĭ��ֵ.
     *
     * @param path ��ȡ Object ��·��.
     * @param def ��ָ��·����û��ֵ, �������ֵ.
     * @return ����һ�� Object.
     */
    Object get(String path, Object def);

    /**
     * ��ָ��·������Ϊ����ֵ��
     * ���ֵΪ�գ���ɾ������Ŀ��������ֵ��ʲô���κ�������Ŀ�������滻��
     * ĳЩʵ�ֿ��ܶ������Դ洢�����������ơ��й���ϸ��Ϣ����μ����Ե�javadocs��
     * �κ�ʵ�ֶ���Ӧ�������洢���û�ConfigurationSection����Ϊ��ʹ��createSection��java.lang.String����
     *
     * @param path ·��
     * @param value ֵ
     */
    void set(String path, Object value);

    /**
     * ��ָ����·���ϴ����յ�ConfigurationSection��
     *
     * ��ǰ�ڴ�·�������õ��κ�ֵ���������ǡ����ǰһ��ֵ������ConfigurationSection��������������
     *
     * @param path ·��
     * @return �½��Ĳ���
     */
    ConfigurationSection createSection(String path);

    /**
     * ��ָ����·���ϴ����յ�ConfigurationSection��
     *
     * ��ǰ�ڴ�·�������õ��κ�ֵ���������ǡ����ǰһ��ֵ������ConfigurationSection��������������
     *
     * @param path ·��
     * @param map Ҫʹ�õ�ֵ��
     * @return �½��Ĳ���
     */
    ConfigurationSection createSection(String path, Map<?, ?> map);

    /**
     * ��ָ��·����ȡһ�� String ���͵�ֵ.
     * ������ String ������, ����ָ��һ��ȱʡֵ, �⽫����ȱʡֵ.
     *
     * ������ String ������, ����û��ָ��ȱʡֵ, �򷵻� null.
     *
     * @param path ·��
     * @return ֵ.
     */
    String getString(String path);

    /**
     * ��ָ��·���ϻ�ȡһ�� String , ����޷���ȡ, ��ֱ�ӷ���Ĭ��ֵ.
     * ����޷���ȡ��һ�� String, �����᳢��ȥȱʡ�б���ȥ��ȡ, ����ֱ�ӷ���ָ����Ĭ��ֵ.
     *
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return ֵ.
     */
    String getString(String path, String def);

    /**
     * �ж�ָ��·����ֵ�����������Ƿ�ΪString
     *
     * @param path ·��
     * @return ���·������, ������ String, �򷵻� false. ���·��������, �򷵻� false. ���·��������, ����ȱʡ�б��д��ڸ�·��, ����ȱʡ�б����ظ�ƥ��ù���, ֱ������һ���ʵ���ֵ.
     */
    boolean isString(String path);

    /**
     * ��ָ��·����ȡһ�� Int ���͵�ֵ.
     * ������ Int ������, ����ָ��һ��ȱʡֵ, �⽫����ȱʡֵ.
     *
     * ������ Int ������, ����û��ָ��ȱʡֵ, �򷵻� null.
     *
     * @param path ·��
     * @return ֵ
     */
    int getInt(String path);

    /**
     ��ָ��·���ϻ�ȡһ�� int, ����޷���ȡ, ��ֱ�ӷ���Ĭ��ֵ.
     ����޷���ȡ��һ�� int, �����᳢��ȥȱʡ�б���ȥ��ȡ, ����ֱ�ӷ���ָ����Ĭ��ֵ.
     *
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return ����һ�� int.
     */
    int getInt(String path, int def);

    /**
     * ���ָ��·���Ƿ��� int.
     * ���·������, ������ int, �򷵻� false.
     *
     * ���·��������, �򷵻� false.
     *
     * ���·��������, ����ȱʡ�б��д��ڸ�·��, ����ȱʡ�б����ظ�ƥ��ù���, ֱ������һ���ʵ���ֵ.
     *
     * @param path ·��
     * @return ���·������, ������ int, �򷵻� false. ���·��������, �򷵻� false. ���·��������, ����ȱʡ�б��д��ڸ�·��, ����ȱʡ�б����ظ�ƥ��ù���, ֱ������һ���ʵ���ֵ.
     */
    boolean isInt(String path);

    /**
     * ͬ���������������ֻ�����������ͱ���� Boolean
     *
     * @param path ·��
     * @return ֵ
     */
    boolean getBoolean(String path);

    /**
     * ͬ���������������ֻ�����������ͱ���� Boolean
     *
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return ֵ.
     */
    boolean getBoolean(String path, boolean def);

    /**
     * ͬ���������������ֻ�����������ͱ���� Boolean
     *
     * @param path ·��.
     * @return ͬ��������.
     */
    boolean isBoolean(String path);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @return ֵ
     */
    double getDouble(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return ֵ.
     */
    double getDouble(String path, double def);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ͬ��������.
     */
    boolean isDouble(String path);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @return ֵ.
     */
    long getLong(String path);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @param def Ĭ��ֵ.
     * @return ֵ.
     */
    long getLong(String path, long def);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @return ͬ��������.
     */
    boolean isLong(String path);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @return ֵ.
     */
    List<?> getList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @param def Ĭ��ֵ.
     * @return ֵ.
     */
    List<?> getList(String path, List<?> def);

    /**
     * ͬ��������
     *
     * @param path ·��.
     * @return ͬ��������.
     */
    boolean isList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<String> getStringList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Integer> getIntegerList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Boolean> getBooleanList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Double> getDoubleList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Float> getFloatList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Long> getLongList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Byte> getByteList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Character> getCharacterList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Short> getShortList(String path);

    /**
     * ͬ��������
     *
     * @param path ·��
     * @return ֵ.
     */
    List<Map<?, ?>> getMapList(String path);

    /**
     * ConfigurationSection getConfigurationSection(String path)
     * ��ȡһ�� ConfigurationSection ,����һ����ָ��·����Ϊ������µ�������,�޸Ļ�ͬ��.
     * ������ ConfigurationSection ������, ����ָ��һ��ȱʡֵ, �⽫����ȱʡֵ.
     *
     * ������ ConfigurationSection ������, ����û��ָ��ȱʡֵ, �򷵻� null.
     *
     * �����Ի��Ľ���: ������һ�������ļ�����
     *
     *  root:
     *    branch1:
     *      branch1_1: something
     *      branch1_2: something
     *    branch2:
     *      branch2_1: something
     *      branch3_2: something
     *
     * ������� getConfigurationSection(java.lang.String) ����Ϊ("branch1") ,��᷵��
     *    branch1:
     *      branch1_1: something
     *      branch1_2: something
     *
     * �����޸Ļ�ͬ��
     *
     * @param path ��ȡ ConfigurationSection ��·��.
     * @return ����һ�� ConfigurationSection.
     */

    ConfigurationSection getConfigurationSection(String path);

    /**
     * ���ָ��·���Ƿ��� ConfigurationSection.
     * ���·������, ������ ConfigurationSection, �򷵻� false.
     *
     * ���·��������, �򷵻� false.
     *
     * ���·��������, ����ȱʡ�б��д��ڸ�·��, ����ȱʡ�б����ظ�ƥ��ù���, ֱ������һ���ʵ���ֵ.
     *
     * @param path ·��
     * @return ָ��·���Ƿ��� ConfigurationSection.
     */
    boolean isConfigurationSection(String path);

    /**
     * �� getRoot() �ж����Ĭ�������л�ȡ��Ч��ConfigurationSection��
     *
     * �����Ŀ¼������Ĭ��ֵ������Ĭ��ֵ��������·����ֵ�����ߴ�·���ϵ�ֵ����ConfigurationSection���򽫷���null��
     *
     * @return �������е� Configuration
     */
    ConfigurationSection getDefaultSection();

    /**
     * ��ָ��·�����һ��ȱʡֵ.
     * ���ȱʡֵ Configuration û�б��ṩ, ���Զ�����һ���µ�.
     *
     * ���ֵΪ null, ��ʾ��ȱʡֵ Configuration ��ɾ�����·���ϵ�Ĭ��ֵ
     *
     * ��� getDefaultSection() ���ص�ֵΪ null, ����һ���µ�
     *
     * @param path ·��
     * @param value ֵ
     * @throws IllegalArgumentException ��·��Ϊ null ʱ�׳����쳣.
     */
    void addDefault(String path, Object value);
}
