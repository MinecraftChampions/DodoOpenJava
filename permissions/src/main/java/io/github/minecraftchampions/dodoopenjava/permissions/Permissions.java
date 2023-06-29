package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.*;

import java.io.IOException;

/**
 * Ȩ��ϵͳ����
 */
public class Permissions {
    public static PermData permData = new PermData();
    public static DataType type;

    public static boolean initialized;

    /**
     * ��ʼ��
     * @param type �洢����
     * @return true�ɹ���falseʧ��
     */
    public static boolean init(DataType type) {
        if (initialized) {
            return false;
        } else {
            initialized = true;
        }
        FileMonitor fileMonitor = new FileMonitor(1000);
        switch (type.getType()) {
            case "YAML" -> {
                YamlData yamlData = new YamlData();
                fileMonitor.monitor(yamlData.Group, new FileListener());
                fileMonitor.monitor(yamlData.User, new FileListener());
                permData = yamlData;
            }
            case "JSON" -> {
                JsonData jsonData = new JsonData();
                fileMonitor.monitor(jsonData.Group, new FileListener());
                fileMonitor.monitor(jsonData.User, new FileListener());
                permData = jsonData;
            }
            case "Xml" -> {
                XmlData xmlData = new XmlData();
                fileMonitor.monitor(xmlData.Group, new FileListener());
                fileMonitor.monitor(xmlData.User, new FileListener());
                permData = xmlData;
            }
            case "Toml" -> {
                TomlData tomlData = new TomlData();
                fileMonitor.monitor(tomlData.Group, new FileListener());
                fileMonitor.monitor(tomlData.User, new FileListener());
                permData = tomlData;
            }
            default -> {
                return false;
            }
        }
        try {
            fileMonitor.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Permissions.type = type;
        return true;
    }

    /**
     * �Զ������ļ�
     * ÿ��10���ӱ���һ��
     */
    public static void autoSave() {
        Thread thread = new Thread(target);
        thread.start();
    }

    private static Runnable target = new AutoSaveC();

    private static class AutoSaveC implements Runnable {
        private boolean init = false;
        @Override
        public void run() {
            if (init) {
                System.out.println("��Ҫ�ظ������Զ����淽��");
                return;
            } else {
                init = true;
            }
            while(true) {
                try {
                    Thread.sleep(10*60*60);
                    switch (Permissions.type.getType()) {
                        case "YAML" -> permData.saveToFile();
                        case "JSON" -> permData.saveToFile();
                        case "Xml" -> permData.saveToFile();
                        case "Toml" -> permData.saveToFile();
                        default -> System.err.println("����Ĵ洢����");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}