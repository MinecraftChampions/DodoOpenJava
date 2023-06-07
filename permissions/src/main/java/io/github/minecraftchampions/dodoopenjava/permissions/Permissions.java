package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.JsonData;
import io.github.minecraftchampions.dodoopenjava.permissions.data.TomlData;
import io.github.minecraftchampions.dodoopenjava.permissions.data.XmlData;
import io.github.minecraftchampions.dodoopenjava.permissions.data.YamlData;

import java.io.IOException;

/**
 * 权限系统核心
 * @author qscbm187531
 */
public class Permissions {
    public static DataType type;

    public static boolean initialized;

    /**
     * 初始化
     * @param type 存储类型
     * @return true成功，false失败
     */
    public static Boolean init(DataType type) {
        if (initialized) {
            return false;
        } else {
            initialized = true;
        }
        FileMonitor fileMonitor = new FileMonitor(1000);
        switch (type.getType()) {
            case "YAML" -> {
                fileMonitor.monitor(YamlData.Group, new FileListener());
                fileMonitor.monitor(YamlData.User, new FileListener());
                YamlData.init();
            }
            case "JSON" -> {
                fileMonitor.monitor(JsonData.Group, new FileListener());
                fileMonitor.monitor(JsonData.User, new FileListener());
                JsonData.init();
            }
            case "Xml" -> {
                fileMonitor.monitor(XmlData.Group, new FileListener());
                fileMonitor.monitor(XmlData.User, new FileListener());
                XmlData.init();
            }
            case "Toml" -> {
                fileMonitor.monitor(TomlData.Group, new FileListener());
                fileMonitor.monitor(TomlData.User, new FileListener());
                TomlData.init();
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
     * 自动保存文件
     * 每隔10分钟保存一次
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
                System.out.println("不要重复调用自动保存方法");
                return;
            } else {
                init = true;
            }
            while(true) {
                try {
                    Thread.sleep(10*60*60);
                    switch (Permissions.type.getType()) {
                        case "YAML" -> YamlData.saveToFile();
                        case "JSON" -> JsonData.saveToFile();
                        case "Xml" -> XmlData.saveToFile();
                        case "Toml" -> TomlData.saveToFile();
                        default -> System.err.println("错误的存储种类");
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