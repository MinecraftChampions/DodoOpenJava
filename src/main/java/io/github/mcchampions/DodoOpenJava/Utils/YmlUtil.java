package io.github.mcchampions.DodoOpenJava.Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

/**
 * 关于 Yml 的一些实用性方法
 */
public class YmlUtil {
    /**
     * bean转化为yml
     *
     * @param obj      bean
     * @param fileName 文件名
     */
    public static void beanToYml(Object obj, String fileName) throws IOException {
        new Yaml().dump(obj, new FileWriter(setYml(fileName)));
    }

    /**
     * yml转化为bean
     *
     * @param fileName 文件名
     * @return bean
     */
    public static <T> T ymlToBean(String fileName) throws IOException {
        return new Yaml().load(Files.newInputStream(new File(setYml(fileName)).toPath()));
    }

    /**
     * ynl转换为map
     *
     * @param fileName 文件名
     * @return map
     */
    public static Map<String, Object> ymlToMap(String fileName) throws FileNotFoundException {
        return new Yaml().load(new BufferedReader(new FileReader(setYml(fileName))));
    }

    /**
     * 设置
     *
     * @param fileName 文件名
     * @return 文件名
     */
    private static String setYml(String fileName) {
        if (!fileName.contains(".yml")) {
            fileName += ".yml";
        }
        return fileName;
    }
}
