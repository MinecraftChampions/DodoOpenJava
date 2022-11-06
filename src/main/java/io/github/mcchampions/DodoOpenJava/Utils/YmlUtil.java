package io.github.mcchampions.DodoOpenJava.Utils;

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
