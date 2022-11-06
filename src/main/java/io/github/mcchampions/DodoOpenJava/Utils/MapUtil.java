package io.github.mcchampions.DodoOpenJava.Utils;

import org.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * һЩ�й��� Map ��ʵ�÷���
 * @author qscbm187531
 */
public class MapUtil {
    /**
     * ���� Map
     * @param map ָ��Map
     * @return ���ؼ��ϣ�������������һ�����ϣ�����0��key��1��Value��
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
     * mapתbean
     *
     * @param type beanType
     * @param map  map
     * @return bean
     */
    public static <T> T toBean(Class<T> type, Map<?, ?> map) {
        T object = null;
        try {
            BeanInfo info = Introspector.getBeanInfo(type);
            object = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    descriptor.getWriteMethod().invoke(object, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * mapתjson
     *
     * @param map  map
     * @return bean
     */
    public static JSONObject toJson(Map<?, ?> map) {
        return new JSONObject(map);
    }
}
