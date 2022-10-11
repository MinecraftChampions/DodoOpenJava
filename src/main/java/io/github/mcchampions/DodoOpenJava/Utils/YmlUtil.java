package io.github.mcchampions.DodoOpenJava.Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

/**
 * 关于 Yml 的一些实用性方法
 * @author qscbm187531
 */
public class YmlUtil {
    /**
     * yml转化为bean
     *
     * @param fileName 文件名
     * @return bean
     */
    public static <T> T toBean(String fileName) throws IOException {
        return new Yaml().load(Files.newInputStream(new File(setYml(fileName)).toPath()));
    }

    /**
     * ynl转换为map
     *
     * @param fileName 文件名
     * @return map
     */
    public static Map<String, Object> toMap(String fileName) throws FileNotFoundException {
        return new Yaml().load(new BufferedReader(new FileReader(setYml(fileName))));
    }

    /**
     * 设置
     *
     * @param fileName 文件名
     * @return 文件名
     */
    public static String setYml(String fileName) {
        if (!fileName.contains(".yml")) {
            fileName += ".yml";
        }
        return fileName;
    }
}
