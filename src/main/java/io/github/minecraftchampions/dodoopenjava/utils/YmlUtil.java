package io.github.minecraftchampions.dodoopenjava.utils;

import lombok.NonNull;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

/**
 * 关于 Yml 的一些实用性方法
 *
 * @author qscbm187531
 */
public class YmlUtil {
    /**
     * yml转化为bean
     *
     * @param fileName 文件名
     * @return bean
     */
    public static <T> T toBean(@NonNull String fileName) throws IOException {
        return new Yaml().load(Files.newInputStream(new File(setYml(fileName)).toPath()));
    }

    /**
     * bean转化为yml
     *
     * @param obj      bean
     * @param fileName 文件名
     */
    public static void toYml(@NonNull Object obj, @NonNull String fileName) throws IOException {
        new Yaml().dump(obj, new FileWriter(YmlUtil.setYml(fileName)));
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
     * 将文件后缀名改为.yml
     *
     * @param fileName 文件名
     * @return 文件名
     */
    public static String setYml(String fileName) {
        if (fileName.lastIndexOf(".yml") != (fileName.length() - 4)) {
            fileName += ".yml";
        }
        return fileName;
    }
}
