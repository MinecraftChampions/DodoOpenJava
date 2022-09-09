package io.github.mcchampions.DodoOpenJava.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.List;

/**
 * Configuration的基类. 所有用于扩展配置文件读取的类都应当实现以下方法.
 */
public interface ConfigurationSection {
    /**
     * 获取此配置文件的键集合.
     * 如果为 true, 则返回包括所有的能访问到的键的集合. 类似于获取硬盘中第一层目录还是遍历全部子目录.
     *
     * 例如:
     * top1. Second1
     * top1. Second2
     *
     * @param deep 获取全部键, 或者仅仅获取表层键.
     * @return 将返回一个 set, 装载着符合要求的键.
     */
    Set<String> getKeys(boolean deep);

    /**
     * 获取这个配置文件的键值集合.
     * 如果为 true, 则返回包括所有的能访问到的键和值的集合. 类似于获取硬盘中第一层目录还是遍历全部子目录.
     *
     * 如果为 false, 则返回表层的键和值的集合.
     *
     * @param deep 获取全部键值集合(true), 或者仅仅获取表层键值集合(false).
     * @return 返回一个 Map.
     */
    Map<String, Object> getValues(boolean deep);

    /**
     * 检查 ConfigurationSection 是否包含指定路径.
     * 如果这个路径不存在, 但已指定一个缺省值, 也将返回 true.
     *
     * @param path 要检查的路径
     * @return 如果此部分包含请求的路径，可以通过默认的或者被设置.
     * @throws IllegalArgumentException 如果路径是 null 时抛出此异常.
     */
    boolean contains(String path);

    /**
     * 检查指定路径是否是 Set.
     * 如果路径存在, 但不是 Set, 则返回 false.
     *
     * 如果路径不存在, 则返回 false.
     *
     * 如果路径不存在, 但在缺省列表中存在该路径, 则在缺省列表中重复匹配该规则, 直到返回一个适当的值.
     *
     * @param path 检查路径.
     * @return 如果路径存在, 但不是 Set, 则返回 false. 如果路径不存在, 则返回 false. 如果路径不存在, 但在缺省列表中存在该路径, 则在缺省列表中重复匹配该规则, 直到返回一个适当的值.
     * @throws IllegalArgumentException 如果路径为 null 则会抛出此异常.
     */
    boolean isSet(String path);

    /**
     * 从根 Configuration 中获取这个 ConfigurationSection 的路径.
     * 如果这个 ConfigurationSection 已经是根目录, 将返回一个空字符串.
     *
     * 如果这个 ConfigurationSection 不属于任何根目录, 将返回 null.
     *
     * 如果要获取这个 ConfigurationSection 名字,也就是路径中的最后一节, 你应该使用 getName() 来获取.
     *
     * @return 这个片段相对于其根的路径.
     */
    String getCurrentPath();

    /**
     * 获取 Configuration 的名称
     * 它应该在 getCurrentPath() 的后面一节
     *
     * @return 名称
     */
    String getName();

    /**
     * 获取包含此ConfigurationSection的根配置
     *
     * 对于任何配置本身，这将返回其自己的对象。
     *
     * 如果该节由于任何原因不再包含在其根中，例如被替换为不同的值，则可能返回null。
     *
     * @return 包含此节的根配置。
     */
    Configuration getRoot();

    /**
     * 获取直接包含此配置节的父配置节。
     *
     * 对于任何配置本身，这将返回null。
     *
     * 如果该节由于任何原因不再包含在其父级中，例如被替换为其他值，则可能会返回null。
     *
     * @return 包含此节的父节。
     */
    ConfigurationSection getParent();

    /**
     * 在指定路径获取一个 Object 类型的值.
     * 如果这个 Object 不存在, 但已指定一个缺省值, 这将返回缺省值.
     *
     * 如果这个 Object 不存在, 并且没有指定缺省值, 则返回 null.
     *
     * @param path 获取 Object 的路径.
     * @return 返回一个 Object.
     */
    Object get(String path);

    /**
     * 在指定路径上获取一个 Object , 如果无法获取, 则直接返回默认值.
     * 如果 Object 无法在 Configuration 中被获取, 则不会尝试去缺省列表中去寻找, 而是直接返回指定的默认值.
     *
     * @param path 获取 Object 的路径.
     * @param def 当指定路径上没有值, 返回这个值.
     * @return 返回一个 Object.
     */
    Object get(String path, Object def);

    /**
     * 将指定路径设置为给定值。
     * 如果值为空，则将删除该条目。无论新值是什么，任何现有条目都将被替换。
     * 某些实现可能对您可以存储的内容有限制。有关详细信息，请参见各自的javadocs。
     * 任何实现都不应允许您存储配置或ConfigurationSection，请为此使用createSection（java.lang.String）。
     *
     * @param path 路径
     * @param value 值
     */
    void set(String path, Object value);

    /**
     * 在指定的路径上创建空的ConfigurationSection。
     *
     * 以前在此路径上设置的任何值都将被覆盖。如果前一个值本身是ConfigurationSection，它将被孤立。
     *
     * @param path 路径
     * @return 新建的部分
     */
    ConfigurationSection createSection(String path);

    /**
     * 在指定的路径上创建空的ConfigurationSection。
     *
     * 以前在此路径上设置的任何值都将被覆盖。如果前一个值本身是ConfigurationSection，它将被孤立。
     *
     * @param path 路径
     * @param map 要使用的值。
     * @return 新建的部分
     */
    ConfigurationSection createSection(String path, Map<?, ?> map);

    /**
     * 在指定路径获取一个 String 类型的值.
     * 如果这个 String 不存在, 但已指定一个缺省值, 这将返回缺省值.
     *
     * 如果这个 String 不存在, 并且没有指定缺省值, 则返回 null.
     *
     * @param path 路径
     * @return 值.
     */
    String getString(String path);

    /**
     * 在指定路径上获取一个 String , 如果无法获取, 则直接返回默认值.
     * 如果无法获取到一个 String, 将不会尝试去缺省列表中去获取, 而是直接返回指定的默认值.
     *
     * @param path 路径
     * @param def 默认值
     * @return 值.
     */
    String getString(String path, String def);

    /**
     * 判断指定路径的值的数据类型是否为String
     *
     * @param path 路径
     * @return 如果路径存在, 但不是 String, 则返回 false. 如果路径不存在, 则返回 false. 如果路径不存在, 但在缺省列表中存在该路径, 则在缺省列表中重复匹配该规则, 直到返回一个适当的值.
     */
    boolean isString(String path);

    /**
     * 在指定路径获取一个 Int 类型的值.
     * 如果这个 Int 不存在, 但已指定一个缺省值, 这将返回缺省值.
     *
     * 如果这个 Int 不存在, 并且没有指定缺省值, 则返回 null.
     *
     * @param path 路径
     * @return 值
     */
    int getInt(String path);

    /**
     在指定路径上获取一个 int, 如果无法获取, 则直接返回默认值.
     如果无法获取到一个 int, 将不会尝试去缺省列表中去获取, 而是直接返回指定的默认值.
     *
     * @param path 路径
     * @param def 默认值
     * @return 返回一个 int.
     */
    int getInt(String path, int def);

    /**
     * 检查指定路径是否是 int.
     * 如果路径存在, 但不是 int, 则返回 false.
     *
     * 如果路径不存在, 则返回 false.
     *
     * 如果路径不存在, 但在缺省列表中存在该路径, 则在缺省列表中重复匹配该规则, 直到返回一个适当的值.
     *
     * @param path 路径
     * @return 如果路径存在, 但不是 int, 则返回 false. 如果路径不存在, 则返回 false. 如果路径不存在, 但在缺省列表中存在该路径, 则在缺省列表中重复匹配该规则, 直到返回一个适当的值.
     */
    boolean isInt(String path);

    /**
     * 同上面的其他方法，只不过数据类型变成了 Boolean
     *
     * @param path 路径
     * @return 值
     */
    boolean getBoolean(String path);

    /**
     * 同上面的其他方法，只不过数据类型变成了 Boolean
     *
     * @param path 路径
     * @param def 默认值
     * @return 值.
     */
    boolean getBoolean(String path, boolean def);

    /**
     * 同上面的其他方法，只不过数据类型变成了 Boolean
     *
     * @param path 路径.
     * @return 同其他方法.
     */
    boolean isBoolean(String path);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @return 值
     */
    double getDouble(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @param def 默认值
     * @return 值.
     */
    double getDouble(String path, double def);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 同其他方法.
     */
    boolean isDouble(String path);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @return 值.
     */
    long getLong(String path);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @param def 默认值.
     * @return 值.
     */
    long getLong(String path, long def);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @return 同其他方法.
     */
    boolean isLong(String path);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @return 值.
     */
    List<?> getList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @param def 默认值.
     * @return 值.
     */
    List<?> getList(String path, List<?> def);

    /**
     * 同其他方法
     *
     * @param path 路径.
     * @return 同其他方法.
     */
    boolean isList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<String> getStringList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Integer> getIntegerList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Boolean> getBooleanList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Double> getDoubleList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Float> getFloatList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Long> getLongList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Byte> getByteList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Character> getCharacterList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Short> getShortList(String path);

    /**
     * 同其他方法
     *
     * @param path 路径
     * @return 值.
     */
    List<Map<?, ?>> getMapList(String path);

    /**
     * ConfigurationSection getConfigurationSection(String path)
     * 获取一个 ConfigurationSection ,它是一个以指定路径作为基点的新的配置项,修改会同步.
     * 如果这个 ConfigurationSection 不存在, 但已指定一个缺省值, 这将返回缺省值.
     *
     * 如果这个 ConfigurationSection 不存在, 并且没有指定缺省值, 则返回 null.
     *
     * 更人性化的解释: 现在有一个配置文件如下
     *
     *  root:
     *    branch1:
     *      branch1_1: something
     *      branch1_2: something
     *    branch2:
     *      branch2_1: something
     *      branch3_2: something
     *
     * 如果调用 getConfigurationSection(java.lang.String) 参数为("branch1") ,则会返回
     *    branch1:
     *      branch1_1: something
     *      branch1_2: something
     *
     * 并且修改会同步
     *
     * @param path 获取 ConfigurationSection 的路径.
     * @return 返回一个 ConfigurationSection.
     */

    ConfigurationSection getConfigurationSection(String path);

    /**
     * 检查指定路径是否是 ConfigurationSection.
     * 如果路径存在, 但不是 ConfigurationSection, 则返回 false.
     *
     * 如果路径不存在, 则返回 false.
     *
     * 如果路径不存在, 但在缺省列表中存在该路径, 则在缺省列表中重复匹配该规则, 直到返回一个适当的值.
     *
     * @param path 路径
     * @return 指定路径是否是 ConfigurationSection.
     */
    boolean isConfigurationSection(String path);

    /**
     * 从 getRoot() 中定义的默认配置中获取等效的ConfigurationSection。
     *
     * 如果根目录不包含默认值，或者默认值不包含此路径的值，或者此路径上的值不是ConfigurationSection，则将返回null。
     *
     * @return 根配置中的 Configuration
     */
    ConfigurationSection getDefaultSection();

    /**
     * 给指定路径添加一个缺省值.
     * 如果缺省值 Configuration 没有被提供, 则自动创建一个新的.
     *
     * 如果值为 null, 表示从缺省值 Configuration 中删除这个路径上的默认值
     *
     * 如果 getDefaultSection() 返回的值为 null, 则建立一个新的
     *
     * @param path 路径
     * @param value 值
     * @throws IllegalArgumentException 当路径为 null 时抛出此异常.
     */
    void addDefault(String path, Object value);
}
