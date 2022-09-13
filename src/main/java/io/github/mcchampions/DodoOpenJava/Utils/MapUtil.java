package io.github.mcchampions.DodoOpenJava.Utils;

import org.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * 一些有关于 Map 的实用方法
 */
public class MapUtil {
    /**
     * 遍历 Map
     * @param map 指定Map
     * @return 返回集合（集合中内置了一个集合，索引0是key，1是Value）
     */
    public static List<List<Object>> ergodicMaps(Map<?, ?> map) {
        if (map.isEmpty()) return new ArrayList<>();
        Iterator<? extends Map.Entry<?, ?>> iter = map.entrySet().iterator();
        List<List<Object>> list = new ArrayList<>();
        while (iter.hasNext()) {
            List<Object> List = new ArrayList<>();
            Map.Entry<?, ?> entry =  iter.next();
            List.add(entry.getKey());
            List.add(entry.getValue());
            list.add(List);
        }
        return list;
    }

    /**
     * map转bean
     *
     * @param type beanType
     * @param map  map
     * @return bean
     */
    public static <T> T mapToBean(Class<T> type, Map<?, ?> map) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    descriptor.getWriteMethod().invoke(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * map转json
     *
     * @param map  map
     * @return bean
     */
    public static JSONObject mapToJson(Map<?, ?> map) {
        return new JSONObject(map);
    }
}
