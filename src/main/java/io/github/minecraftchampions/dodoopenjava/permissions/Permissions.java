package io.github.minecraftchampions.dodoopenjava.permissions;

import io.github.minecraftchampions.dodoopenjava.permissions.data.*;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;

/**
 * 权限系统核心
 */
public class Permissions {
    public static PermData permData = null;

    @Getter
    private static DataType type;

    private static boolean initialized;

    /**
     * 初始化
     *
     * @param type 存储类型
     * @return true成功，false失败
     */
    public static boolean init(@NonNull DataType type) {
        if (initialized) {
            return false;
        } else {
            initialized = true;
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

    private static final Runnable target = new AutoSaveC();

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
            while (true) {
                try {
                    Thread.sleep(10 * 60 * 60);
                    permData.saveToFile();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}