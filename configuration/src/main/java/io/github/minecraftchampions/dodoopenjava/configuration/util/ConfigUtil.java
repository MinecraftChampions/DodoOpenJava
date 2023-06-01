package io.github.minecraftchampions.dodoopenjava.configuration.util;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.github.minecraftchampions.dodoopenjava.configuration.file.FileConfiguration;
import io.github.minecraftchampions.dodoopenjava.configuration.file.YamlConfiguration;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static io.github.minecraftchampions.dodoopenjava.configuration.file.FileConfiguration.UTF8_OVERRIDE;
import static io.github.minecraftchampions.dodoopenjava.configuration.file.FileConfiguration.UTF_BIG;

/**
 * ���� �����ļ� ��һЩ����
 * @author qscbm187531
 */
public class ConfigUtil {
    /**
     * ת��Ϊbyte����
     *
     * @param filePath �ļ�·��
     * @return ����
     */
    public static byte[] inputStream2ByteArray(String filePath) {
        return inputStream2ByteArray(new File(filePath));
    }

    /**
     * ת��Ϊbyte����
     *
     * @param file �ļ�
     * @return ����
     */
    public static byte[] inputStream2ByteArray(File file) {
        try (InputStream in = new FileInputStream(file)) {
            return toByteArray(in);
        } catch (IOException e) {
            return new byte[0];
        }
    }

    /**
     * ת��Ϊbyte����
     *
     * @param in ������
     * @return ����
     */
    private static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * �����ļ�
     *
     * @param child �ļ�·��
     * @return FileConfiguration�ļ�
     */
    public static FileConfiguration load(String child) {
        return YamlConfiguration.loadConfiguration(new File(child));
    }

    /**
     * �����ļ�
     *
     * @param file �ļ�
     * @return FileConfiguration�ļ�
     */
    public static FileConfiguration load(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * ����Ŀ¼��ȫ���ļ�
     *
     * @param directoryStr Ŀ¼
     * @return ȫ���ļ�
     */
    public static Map<String, FileConfiguration> loadDirectory(String directoryStr) {
        Map<String, FileConfiguration> map = new HashMap<>();
        File directory = new File(directoryStr);
        File[] spawnFileList = directory.listFiles();
        if (spawnFileList == null || spawnFileList.length == 0) {
            return map;
        }
        for (File file : spawnFileList) {
            map.put(file.getName(), load(directoryStr + file.getName()));
        }
        return map;
    }

    /**
     * ���ýڵ�
     *
     * @param fileConfiguration �ļ�
     * @param path yml�ڵ�
     * @param value ����
     * @param child �ļ�·��
     */
    public static void setPath(FileConfiguration fileConfiguration, String path, Object value, String child) {
        try {
            fileConfiguration.set(path, value);
            fileConfiguration.save(new File(child));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodError ignored) {
        }
    }

    /**
     * �ж��Ƿ���� Ȼ�����ýڵ�
     *
     * @param fileConfiguration �ļ�
     * @param path              yml�ڵ�
     * @param value             ����
     * @param child             �ļ�·��
     */
    public static Boolean setPathIsNotContains(FileConfiguration fileConfiguration, String path, Object value, String child) {
        if (fileConfiguration.contains(path)) {
            return false;
        }
        setPath(fileConfiguration, path, value, child);
        return true;
    }

    /**
     * �����ļ�
     *
     * @param inFile ԭ�����ļ�����
     * @param outFile ���Ƶ����ļ�����
     * @return true���ǳɹ���false����ʧ��
     */
    public static Boolean copy(File inFile, File outFile) {
        if (!inFile.exists()) {
            return false;
        }

        try (FileChannel in = new FileInputStream(inFile).getChannel(); FileChannel out = new FileOutputStream(outFile).getChannel()) {
            long pos = 0;
            long size = in.size();
            while (pos < size) {
                pos += in.transferTo(pos, 10 * 1024 * 1024, out);
            }
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    /**
     * ����Jar������ļ�
     * @param inPath �ļ�λ��jar�����·����ǰ�治��/�����硰config.yml")
     * @param outPath ���Ƶ����ļ�·������C:/config.yml)
     * @return true �ɹ���false ʧ��
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
     * ��ȡ��ǰjar����·����������jar������
     * @return ·�����磺��C:/Test/��)
     */
    public static String getJarPath() {
        String path = java.net.URLDecoder.decode(System.getProperty("java.class.path"));
        int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        return path.substring(firstIndex, lastIndex);
    }
    /**
     * ��ȡ�ļ�
     *
     * @param fileName �ļ�
     * @return �����ַ���
     */
    public static String readFile(File fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            Reader streamReader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = streamReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            fileReader.close();
            streamReader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����ַ������ļ�
     * @param file �ļ�
     * @param data ����
     */
    public static void saveToFile(String data,File file) throws IOException {
        Files.createParentDirs(file);
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), UTF8_OVERRIDE && !UTF_BIG ? Charsets.UTF_8 : Charset.defaultCharset())) {
            writer.write(data);
        }
    }
}
