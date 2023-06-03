package io.github.minecraftchampions.dodoopenjava.utils;

public class ClassUtil {
    /**
     * 判断class是否存在
     * @param className 类
     * @return 是 or 否
     */
    public static boolean classExists(String className) {
        try {
            ClassLoader classLoader = ClassUtil.class.getClassLoader();
            Class.forName(className, false, classLoader);
            return true;
        } catch (ClassNotFoundException exception) {
            return false;
        }
    }
}
