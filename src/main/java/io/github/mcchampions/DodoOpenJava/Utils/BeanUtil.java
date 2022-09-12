package io.github.mcchampions.DodoOpenJava.Utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 关于 Bean 的一些实用方法
 */
public class BeanUtil {
    private final static String CLASS = "class";
    /**
     * bean转map
     *
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> beanToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!CLASS.equals(key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
