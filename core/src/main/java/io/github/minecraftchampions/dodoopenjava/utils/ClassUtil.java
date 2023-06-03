package io.github.minecraftchampions.dodoopenjava.utils;

public class ClassUtil {
    /**
     * �ж�class�Ƿ����
     * @param className ��
     * @return �� or ��
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
