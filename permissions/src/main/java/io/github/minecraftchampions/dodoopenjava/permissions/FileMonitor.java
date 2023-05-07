package io.github.minecraftchampions.dodoopenjava.permissions;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileMonitor {

    private FileAlterationMonitor monitor;

    public FileMonitor(long interval) {
        monitor = new FileAlterationMonitor(interval);
    }

    /**
     * ���ļ���Ӽ���
     *
     * @param file     �ļ�
     * @param listener �ļ�������
     */
    public void monitor(File file, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(file);
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    public void stop() throws Exception {
        monitor.stop();
    }

    public void start() throws Exception {
        monitor.start();
    }
}
