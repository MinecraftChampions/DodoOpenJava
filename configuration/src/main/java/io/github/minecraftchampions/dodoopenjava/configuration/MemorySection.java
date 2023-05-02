package io.github.minecraftchampions.dodoopenjava.configuration;

import io.github.minecraftchampions.dodoopenjava.utils.NumberUtil;
import okio.ByteString;
import org.apache.commons.lang3.Validate;

import java.util.*;

/**
 * ConfigurationSection ��ʵ����
 */
public class MemorySection implements ConfigurationSection {
    protected final Map<String, Object> map = new LinkedHashMap<>();
    private final Configuration root;
    private final ConfigurationSection parent;
    private final String path;
    private final String fullPath;

    /**
     * ���Ĭ�Ϲ��캯���������ã�
     */
    protected MemorySection() {
        if (!(this instanceof Configuration)) {
            throw new IllegalStateException("Cannot construct a root MemorySection when not a Configuration");
        }

        this.path = "";
        this.fullPath = "";
        this.parent = null;
        this.root = (Configuration) this;
    }

    /**
     * ���캯��
     *
     * @param parent ����1
     * @param path ·��
     * @throws IllegalArgumentException �쳣ʱ�׳�
     */
    protected MemorySection(ConfigurationSection parent, String path) {
        Validate.notNull(parent, "Parent cannot be null");
        Validate.notNull(path, "Path cannot be null");

        this.path = path;
        this.parent = parent;
        this.root = parent.getRoot();

        Validate.notNull(root, "Path cannot be orphaned");

        this.fullPath = createPath(parent, path);
    }

    public Set<String> getKeys(boolean deep) {
        Set<String> result = new LinkedHashSet<>();

        Configuration root = getRoot();
        if (root != null && root.options().copyDefaults()) {
            ConfigurationSection defaults = getDefaultSection();

            if (defaults != null) {
                result.addAll(defaults.getKeys(deep));
            }
        }

        mapChildrenKeys(result, this, deep);

        return result;
    }

    public Map<String, Object> getValues(boolean deep) {
        Map<String, Object> result = new LinkedHashMap<>();

        Configuration root = getRoot();
        if (root != null && root.options().copyDefaults()) {
            ConfigurationSection defaults = getDefaultSection();

            if (defaults != null) {
                result.putAll(defaults.getValues(deep));
            }
        }

        mapChildrenValues(result, this, deep);

        return result;
    }

    public boolean contains(String path) {
        return get(path) != null;
    }

    public boolean isSet(String path) {
        Configuration root = getRoot();
        if (root == null) {
            return false;
        }
        if (root.options().copyDefaults()) {
            return contains(path);
        }
        return get(path, null) != null;
    }

    public String getCurrentPath() {
        return fullPath;
    }

    public String getName() {
        return path;
    }

    public Configuration getRoot() {
        return root;
    }

    public ConfigurationSection getParent() {
        return parent;
    }

    public void addDefault(String path, Object value) {
        Validate.notNull(path, "Path cannot be null");

        Configuration root = getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot add default without root");
        }
        if (root == this) {
            throw new UnsupportedOperationException("Unsupported addDefault(String, Object) implementation");
        }
        root.addDefault(createPath(this, path), value);
    }

    public ConfigurationSection getDefaultSection() {
        Configuration root = getRoot();
        Configuration defaults = root == null ? null : root.getDefaults();

        if (defaults != null) {
            if (defaults.isConfigurationSection(getCurrentPath())) {
                return defaults.getConfigurationSection(getCurrentPath());
            }
        }

        return null;
    }

    public void set(String path, Object value) {
        Validate.notEmpty(path, "Cannot set to an empty path");

        Configuration root = getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot use section without a root");
        }

        final char separator = root.options().pathSeparator();
        // i1 is the leading (higher) index
        // i2 is the trailing (lower) index
        int i1 = -1, i2;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String node = path.substring(i2, i1);
            ConfigurationSection subSection = section.getConfigurationSection(node);
            if (subSection == null) {
                section = section.createSection(node);
            } else {
                section = subSection;
            }
        }

        String key = path.substring(i2);
        if (section == this) {
            if (value == null) {
                map.remove(key);
            } else {
                map.put(key, value);
            }
        } else {
            section.set(key, value);
        }
    }

    public Object get(String path) {
        return get(path, getDefault(path));
    }

    public Object get(String path, Object def) {
        Validate.notNull(path, "Path cannot be null");

        if (path.length() == 0) {
            return this;
        }

        Configuration root = getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot access section without a root");
        }

        final char separator = root.options().pathSeparator();
        // i1 is the leading (higher) index
        // i2 is the trailing (lower) index
        int i1 = -1, i2;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            section = section.getConfigurationSection(path.substring(i2, i1));
            if (section == null) {
                return def;
            }
        }

        String key = path.substring(i2);
        if (section == this) {
            Object result = map.get(key);
            return (result == null) ? def : result;
        }
        return section.get(key, def);
    }

    public ConfigurationSection createSection(String path) {
        Validate.notEmpty(path, "Cannot create section at empty path");
        Configuration root = getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create section without a root");
        }

        final char separator = root.options().pathSeparator();
        // i1 is the leading (higher) index
        // i2 is the trailing (lower) index
        int i1 = -1, i2;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String node = path.substring(i2, i1);
            ConfigurationSection subSection = section.getConfigurationSection(node);
            if (subSection == null) {
                section = section.createSection(node);
            } else {
                section = subSection;
            }
        }

        String key = path.substring(i2);
        if (section == this) {
            ConfigurationSection result = new MemorySection(this, key);
            map.put(key, result);
            return result;
        }
        return section.createSection(key);
    }

    public ConfigurationSection createSection(String path, Map<?, ?> map) {
        ConfigurationSection section = createSection(path);

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                section.createSection(entry.getKey().toString(), (Map<?, ?>) entry.getValue());
            } else {
                section.set(entry.getKey().toString(), entry.getValue());
            }
        }

        return section;
    }

    /**
     * ��ȡStringֵ
     * @param path ·��
     * @return Stringֵ
     */
    public String getString(String path) {
        Object def = getDefault(path);
        return getString(path, def != null ? ByteString.encodeUtf8(def.toString()).utf8() : null);
    }

    /**
     * ��ȡStringֵ
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return Stringֵ
     */
    public String getString(String path, String def) {
        Object val = get(path, def);
        return (val != null) ? ByteString.encodeUtf8(val.toString()).utf8() : def;
    }

    /**
     * �ж�ָ��·���Ƿ�ΪString����
     * @param path ·��
     * @return true/false
     */
    public boolean isString(String path) {
        Object val = get(path);
        return val instanceof String;
    }

    /**
     * ��ȡIntֵ
     * @param path ·��
     * @return Intֵ
     */
    public int getInt(String path) {
        Object def = getDefault(path);
        return getInt(path, (def instanceof Number) ? NumberUtil.toInt(def) : 0);
    }

    /**
     * ��ȡIntֵ
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return Stringֵ
     */
    public int getInt(String path, int def) {
        Object val = get(path, def);
        return (val instanceof Number) ? NumberUtil.toInt(val) : def;
    }

    /**
     * �ж�·���Ƿ�ΪIntֵ
     * @param path ·��
     * @return true����false
     */
    public boolean isInt(String path) {
        Object val = get(path);
        return val instanceof Integer;
    }

    /**
     * ��ȡ����ֵ
     * @param path ·��
     * @return ����ֵ
     */
    public boolean getBoolean(String path) {
        Object def = getDefault(path);
        return getBoolean(path, (def instanceof Boolean) ? (Boolean) def : false);
    }

    /**
     * ��ȡ����ֵ
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return ����ֵ
     */
    public boolean getBoolean(String path, boolean def) {
        Object val = get(path, def);
        return (val instanceof Boolean) ? (Boolean) val : def;
    }

    /**
     * �ж�ָ��·����ֵ�Ƿ�Ϊ����ֵ
     * @param path ·��
     * @return true������false
     */
    public boolean isBoolean(String path) {
        Object val = get(path);
        return val instanceof Boolean;
    }

    /**
     * ��ȡDoubleֵ
     * @param path ·��
     * @return Doubleֵ
     */
    public double getDouble(String path) {
        Object def = getDefault(path);
        return getDouble(path, (def instanceof Number) ? NumberUtil.toDouble(def) : 0);
    }

    /**
     * ��ȡDoubleֵ
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return Doubleֵ
     */
    public double getDouble(String path, double def) {
        Object val = get(path, def);
        return (val instanceof Number) ? NumberUtil.toDouble(val) : def;
    }

    /**
     * �ж�ָ��·����ֵ�Ƿ�ΪDoubleֵ
     * @param path ·��
     * @return true������false
     */
    public boolean isDouble(String path) {
        Object val = get(path);
        return val instanceof Double;
    }

    /**
     * ��ȡLongֵ
     * @param path ·��
     * @return longֵ
     */
    public long getLong(String path) {
        Object def = getDefault(path);
        return getLong(path, (def instanceof Number) ? NumberUtil.toLong(def) : 0);
    }

    /**
     * ��ȡLongֵ
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return longֵ
     */
    public long getLong(String path, long def) {
        Object val = get(path, def);
        return (val instanceof Number) ? NumberUtil.toLong(val) : def;
    }

    /**
     * �ж��Ƿ�ΪLongֵ
     * @param path ·��
     * @return true/false
     */
    public boolean isLong(String path) {
        Object val = get(path);
        return val instanceof Long;
    }

    /**
     * ��ȡ����
     * @param path ·��
     * @return ����
     */
    public List<?> getList(String path) {
        Object def = getDefault(path);
        return getList(path, (def instanceof List) ? (List<?>) def : null);
    }

    /**
     * ��ȡ����
     * @param path ·��
     * @param def Ĭ��ֵ
     * @return ����
     */

    public List<?> getList(String path, List<?> def) {
        Object val = get(path, def);
        return (List<?>) ((val instanceof List) ? val : def);
    }

    /**
     * �ж�ָ��·����ֵ�Ƿ�Ϊ����
     * @param path ·��
     * @return true����false
     */
    public boolean isList(String path) {
        Object val = get(path);
        return val instanceof List;
    }

    /**
     * ��ȡString����
     * @param path ·��
     * @return ����
     */
    public List<String> getStringList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<String> result = new ArrayList<>();

        for (Object object : list) {
            if ((object instanceof String) || (isPrimitiveWrapper(object))) {
                result.add(String.valueOf(object));
            }
        }

        return result;
    }

    /**
     * ��ȡInteger����
     * @param path ·��
     * @return ����
     */
    public List<Integer> getIntegerList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Integer> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Integer) {
                result.add((Integer) object);
            } else if (object instanceof String) {
                try {
                    result.add(Integer.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((int) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).intValue());
            }
        }

        return result;
    }

    /**
     * ��ȡBoolean����
     * @param path ·��
     * @return ����
     */
    public List<Boolean> getBooleanList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Boolean> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Boolean) {
                result.add((Boolean) object);
            } else if (object instanceof String) {
                if (Boolean.TRUE.toString().equals(object)) {
                    result.add(true);
                } else if (Boolean.FALSE.toString().equals(object)) {
                    result.add(false);
                }
            }
        }

        return result;
    }

    /**
     * ��ȡDouble����
     * @param path ·��
     * @return ����
     */
    public List<Double> getDoubleList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Double> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Double) {
                result.add((Double) object);
            } else if (object instanceof String) {
                try {
                    result.add(Double.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((double) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).doubleValue());
            }
        }

        return result;
    }

    /**
     * ��ȡFloat����
     * @param path ·��
     * @return ����
     */
    public List<Float> getFloatList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Float> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Float) {
                result.add((Float) object);
            } else if (object instanceof String) {
                try {
                    result.add(Float.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((float) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).floatValue());
            }
        }

        return result;
    }

    /**
     * ��ȡLong����
     * @param path ·��
     * @return ����
     */
    public List<Long> getLongList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Long> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Long) {
                result.add((Long) object);
            } else if (object instanceof String) {
                try {
                    result.add(Long.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((long) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).longValue());
            }
        }

        return result;
    }

    /**
     * ��ȡByte����
     * @param path ·��
     * @return ����
     */
    public List<Byte> getByteList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Byte> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Byte) {
                result.add((Byte) object);
            } else if (object instanceof String) {
                try {
                    result.add(Byte.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((byte) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).byteValue());
            }
        }

        return result;
    }

    /**
     * ��ȡCharacter����
     * @param path ·��
     * @return ����
     */
    public List<Character> getCharacterList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Character> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Character) {
                result.add((Character) object);
            } else if (object instanceof String str) {

                if (str.length() == 1) {
                    result.add(str.charAt(0));
                }
            } else if (object instanceof Number) {
                result.add((char) ((Number) object).intValue());
            }
        }

        return result;
    }

    /**
     * ��ȡShort����
     * @param path ·��
     * @return ����
     */
    public List<Short> getShortList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Short> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Short) {
                result.add((Short) object);
            } else if (object instanceof String) {
                try {
                    result.add(Short.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((short) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).shortValue());
            }
        }

        return result;
    }

    public List<Map<?, ?>> getMapList(String path) {
        List<?> list = getList(path);
        List<Map<?, ?>> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        for (Object object : list) {
            if (object instanceof Map) {
                result.add((Map<?, ?>) object);
            }
        }

        return result;
    }

    public ConfigurationSection getConfigurationSection(String path) {
        Object val = get(path, null);
        if (val != null) {
            return (val instanceof ConfigurationSection) ? (ConfigurationSection) val : null;
        }

        val = get(path, getDefault(path));
        return (val instanceof ConfigurationSection) ? createSection(path) : null;
    }

    public boolean isConfigurationSection(String path) {
        Object val = get(path);
        return val instanceof ConfigurationSection;
    }

    protected boolean isPrimitiveWrapper(Object input) {
        return input instanceof Integer || input instanceof Boolean ||
                input instanceof Character || input instanceof Byte ||
                input instanceof Short || input instanceof Double ||
                input instanceof Long || input instanceof Float;
    }

    protected Object getDefault(String path) {
        Validate.notNull(path, "Path cannot be null");

        Configuration root = getRoot();
        Configuration defaults = root == null ? null : root.getDefaults();
        return (defaults == null) ? null : defaults.get(createPath(this, path));
    }

    protected void mapChildrenKeys(Set<String> output, ConfigurationSection section, boolean deep) {
        if (section instanceof MemorySection sec) {

            for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
                output.add(createPath(section, entry.getKey(), this));

                if ((deep) && (entry.getValue() instanceof ConfigurationSection subsection)) {
                    mapChildrenKeys(output, subsection, true);
                }
            }
        } else {
            Set<String> keys = section.getKeys(deep);

            for (String key : keys) {
                output.add(createPath(section, key, this));
            }
        }
    }

    protected void mapChildrenValues(Map<String, Object> output, ConfigurationSection section, boolean deep) {
        if (section instanceof MemorySection sec) {

            for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
                output.put(createPath(section, entry.getKey(), this), entry.getValue());

                if (entry.getValue() instanceof ConfigurationSection) {
                    if (deep) {
                        mapChildrenValues(output, (ConfigurationSection) entry.getValue(), true);
                    }
                }
            }
        } else {
            Map<String, Object> values = section.getValues(deep);

            for (Map.Entry<String, Object> entry : values.entrySet()) {
                output.put(createPath(section, entry.getKey(), this), entry.getValue());
            }
        }
    }

    /**
     * ����һ��·��
     */
    public static String createPath(ConfigurationSection section, String key) {
        return createPath(section, key, (section == null) ? null : section.getRoot());
    }

    /**
     * ����һ��·��
     */
    public static String createPath(ConfigurationSection section, String key, ConfigurationSection relativeTo) {
        Validate.notNull(section, "Cannot create path without a section");
        Configuration root = section.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create path without a root");
        }
        char separator = root.options().pathSeparator();

        StringBuilder builder = new StringBuilder();
        for (ConfigurationSection parent = section; (parent != null) && (parent != relativeTo); parent = parent.getParent()) {
            if (builder.length() > 0) {
                builder.insert(0, separator);
            }

            builder.insert(0, parent.getName());
        }

        if ((key != null) && (key.length() > 0)) {
            if (builder.length() > 0) {
                builder.append(separator);
            }

            builder.append(key);
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        Configuration root = getRoot();
        return getClass().getSimpleName() +
                "[path='" +
                getCurrentPath() +
                "', root='" +
                (root == null ? null : root.getClass().getSimpleName()) +
                "']";
    }
}
