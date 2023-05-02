package io.github.minecraftchampions.dodoopenjava.configuration.util;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

/**
 * ���� Yml ��һЩʵ���Է���
 * @author qscbm187531
 */
public class YmlUtil {
    /**
     * ymlת��Ϊbean
     *
     * @param fileName �ļ���
     * @return bean
     */
    public static <T> T toBean(String fileName) throws IOException {
        return new Yaml().load(Files.newInputStream(new File(setYml(fileName)).toPath()));
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
    /**
     * ynlת��Ϊmap
     *
     * @param fileName �ļ���
     * @return map
     */
    public static Map<String, Object> toMap(String fileName) throws FileNotFoundException {
        return new Yaml().load(new BufferedReader(new FileReader(setYml(fileName))));
    }

    /**
     * ����
     *
     * @param fileName �ļ���
     * @return �ļ���
     */
    public static String setYml(String fileName) {
        if (!fileName.contains(".yml")) {
            fileName += ".yml";
        }
        return fileName;
    }
}
