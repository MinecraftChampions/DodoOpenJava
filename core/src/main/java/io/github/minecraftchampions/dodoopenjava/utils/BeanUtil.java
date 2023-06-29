package io.github.minecraftchampions.dodoopenjava.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关于 Bean 的一些实用方法
 * @author qscbm187531
 */
public class BeanUtil {
    //getter方法的正则表达式
    private final static Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");
    //setter方法的正则表达式
    private final static Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");
    private final static String CLASS = "class";

    /**
     * 获取对象的setter方法。
     *
     * @param object 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object object) {
        List<Method> setterMethods = new ArrayList<>();
        Method[] methods = object.getClass().getMethods(); // 获取方法列表
        for (Method method : methods) {
            //匹配
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param object 对象
     * @return 对象的getter方法列表
     */
    public static List<Method> getGetterMethods(Object object) {
        List<Method> getterMethods = new ArrayList<>();
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        return getterMethods;
    }

    /**
     * bean转map
     *
     * @param object 对象
     * @return map
     */
    public static Map<String, Object> toMap(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!CLASS.equals(key)) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
