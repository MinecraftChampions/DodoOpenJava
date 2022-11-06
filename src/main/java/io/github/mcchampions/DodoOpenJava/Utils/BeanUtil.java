package io.github.mcchampions.DodoOpenJava.Utils;

import org.yaml.snakeyaml.Yaml;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���� Bean ��һЩʵ�÷���
 * @author qscbm187531
 */
public class BeanUtil {
    private final static Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");
    private final static Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");
    private final static String CLASS = "class";

    /**
     * ��ȡ�����setter������
     *
     * @param object ����
     * @return �����setter�����б�
     */
    public static List<Method> getSetterMethods(Object object) {
        List<Method> setterMethods = new ArrayList<>();
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        return setterMethods;
    }

    /**
     * ��ȡ�����getter������
     *
     * @param object ����
     * @return �����getter�����б�
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
     * beanתmap
     *
     * @param object ����
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


    /**
     * beanת��Ϊyml
     *
     * @param obj      bean
     * @param fileName �ļ���
     */
    public static void toYml(Object obj, String fileName) throws IOException {
        new Yaml().dump(obj, new FileWriter(YmlUtil.setYml(fileName)));
    }
}
