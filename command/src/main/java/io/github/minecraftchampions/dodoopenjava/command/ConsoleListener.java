package io.github.minecraftchampions.dodoopenjava.command;

import java.util.HashMap;
import java.util.Scanner;

/**
 * ����̨�������
 */
public class ConsoleListener {
    HashMap<String, Action> answers = new HashMap<>();
    Scanner scanner;
    Action defaultAction;

    /**
     * ���Ӷ���������ָ����Ϣ��
     * @param message ��Ϣ
     * @param action ����
     */
    public void addAction(String message, Action action) {
        answers.put(message.toLowerCase(), action);
    }

    /**
     * ��ʼ��
     * @param scanner ����
     * @param defaultAction Ĭ�϶���
     */
    public ConsoleListener(Scanner scanner, Action defaultAction) {
        this.scanner = scanner;
        this.defaultAction = defaultAction;

        if (scanner == null || defaultAction == null) {
            throw new NullPointerException("null params for ConsoleListener");
        }
    }

    /**
     * �Ƴ�����
     * @param message ��Ϣ
     * @param action ����
     */
    public void removeAction(String message, Action action) {
        answers.remove(message, action);
    }

    /**
     * �滻����
     * @param message ��Ϣ
     * @param action ����
     */
    public Action replaceAction(String message, Action action) {
        return answers.replace(message, action);
    }

    /**
     * �����������µ��̣߳�
     */
    public void listenInNewThread() {
        Thread t = new Thread(this::listen);
        t.start();
    }

    /**
     * ����
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void listen() {
        while (true) {
            String line = scanner.nextLine();
            String msg = line.replaceAll("\s+", " ");
            msg = msg.trim().toLowerCase();
            Action action = answers.get(msg);
            if (action == null) {
                action = defaultAction;
            }

            action.act(line);

        }
    }

    /**
     * �յ�֮�����Ĵ���
     */
    public interface Action {
        /**
         * ����
         * @param msg ����Ŀ���̨������
         */
        void act(String msg);
    }
}
