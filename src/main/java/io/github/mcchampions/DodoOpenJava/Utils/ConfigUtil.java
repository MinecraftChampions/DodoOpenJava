package io.github.mcchampions.DodoOpenJava.Utils;

import io.github.mcchampions.DodoOpenJava.Configuration.file.FileConfiguration;
import io.github.mcchampions.DodoOpenJava.Configuration.file.YamlConfiguration;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关于 配置文件 的一些方法
 */
public class ConfigUtil {

    /**
     * 加载文件
     *
     * @param child 文件路径
     * @return fileConfiguration 文件
     */
    public static FileConfiguration load(String child) {
        File file = new File(child);
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * 加载文件
     *
     * @param file 文件
     * @return fileConfiguration 文件
     */
    public static FileConfiguration load(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * 加载目录下全部文件
     *
     * @param directoryStr 目录
     * @return 全部文件
     */
    public static Map<String, FileConfiguration> loadDirectory(String directoryStr) {

        Map<String, FileConfiguration> map = new HashMap<>();
        File directory = new File(directoryStr);
        // 获取全部配置
        File[] spawnFileList = directory.listFiles();
        if (spawnFileList == null || spawnFileList.length == 0) {
            return map;
        }
        // 循环加载文件
        for (File file : spawnFileList) {
            map.put(file.getName(), load(directoryStr + file.getName()));
        }
        return map;
    }

    /**
     * 设置节点
     *
     * @param fileConfiguration 文件
     * @param path              yml节点
     * @param value             内容
     * @param child             文件路径
     */
    public static void setPath(FileConfiguration fileConfiguration, String path, Object value, String child) {
        setPath(fileConfiguration, path, value, null, child);
    }

    /**
     * 设置节点
     *
     * @param fileConfiguration 文件
     * @param path              yml节点
     * @param value             内容
     * @param comments          注释
     * @param child             文件路径
     */
    public static void setPath(FileConfiguration fileConfiguration, String path, Object value, List<String> comments, String child) {
        try {
            fileConfiguration.set(path, value);
            fileConfiguration.save(new File(child));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodError ignored) {
        }
    }

    /**
     * 判断是否包含 然后设置节点
     *
     * @param fileConfiguration 文件
     * @param path              yml节点
     * @param value             内容
     * @param comments          注释
     * @param child             文件路径
     */
    public static Boolean setPathIsNotContains(FileConfiguration fileConfiguration, String path, Object value, List<String> comments, String child) {
        if (fileConfiguration.contains(path)) {
            return false;
        }
        setPath(fileConfiguration, path, value, comments, child);
        return true;
    }

    /**
     * 复制文件
     *
     * @param inFile 原本的文件对象
     * @param outFile 复制到的文件对象
     * @return true就是成功，false就是失败
     */
    public static Boolean copy(File inFile, File outFile) {
        if (!inFile.exists()) {
            return false;
        }

        FileChannel in = null;
        FileChannel out = null;

        try {
            in = new FileInputStream(inFile).getChannel();
            out = new FileOutputStream(outFile).getChannel();

            long pos = 0;
            long size = in.size();

            while (pos < size) {
                pos += in.transferTo(pos, 10 * 1024 * 1024, out);
            }
        } catch (IOException ioe) {
            return false;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioe) {
                return false;
            }
        }
        return true;
    }

    /**
     * 复制Jar包里的文件
     * @param inPath 文件位于jar包里的路径（前面不带/）（如“config.yml")
     * @param outPath 复制到的文件路径（如C:/config.yml)
     * @return true 成功，false 失败
     */
    public static Boolean copyResourcesToFile(String inPath, String outPath) throws IOException {
        int firstIndex = outPath.lastIndexOf(System.getProperty("path.separator")) + 1;
        int lastIndex = outPath.lastIndexOf(File.separator) + 1;
        File OutPath = new File(outPath.substring(firstIndex, lastIndex));
        if (!OutPath.exists()) {
            OutPath.mkdirs();
        }

        File file = new File(outPath);

        if (new File(inPath).exists()) {
            return false;
        }
        try (InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(inPath)) {
            if (inputStream != null) {
                FileUtils.copyToFile(inputStream, file);
            }
        }
        return true;
    }

    /**
     * 获取当前jar包的路径（不包含jar包本身）
     * @return 路径（如：”C:/Test/“)
     */
    public static String getJarPath() {
        String path = java.net.URLDecoder.decode(System.getProperty("java.class.path"));
        int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        return path.substring(firstIndex, lastIndex);
    }
    /**
     * 读取文件
     *
     * @param fileName 文件
     * @return 返回字符串
     */
    public static String readFile(File fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            Reader reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
