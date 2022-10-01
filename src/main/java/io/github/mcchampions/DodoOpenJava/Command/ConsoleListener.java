package io.github.mcchampions.DodoOpenJava.Command;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 控制台输入监听
 * @author qscbm187531
 */
public class ConsoleListener {
    HashMap<String, Action> answers = new HashMap<>();
    Scanner scanner;
    Action defaultAction;

    /**
     * 增加动作（输入指定消息）
     * @param message 消息
     * @param action 动作
     */
    public void addAction(String message, Action action) {
        answers.put(message.toLowerCase(), action);
    }

    /**
     * 初始化
     * @param scanner 键盘
     * @param defaultAction 默认动作
     */
    public ConsoleListener(Scanner scanner, Action defaultAction) {
        this.scanner = scanner;
        this.defaultAction = defaultAction;

        if (scanner == null || defaultAction == null) {
            throw new NullPointerException("null params for ConsoleListener");
        }
    }

    /**
     * 移除动作
     * @param message 消息
     * @param action 动作
     */
    public void removeAction(String message, Action action) {
        answers.remove(message, action);
    }

    /**
     * 替换动作
     * @param message 消息
     * @param action 动作
     */
    public Action replaceAction(String message, Action action) {
        return answers.replace(message, action);
    }

    /**
     * 监听（创建新的线程）
     */
    public void listenInNewThread() {
        Thread t = new Thread(this::listen);
        t.start();
    }

    /**
     * 监听
     */
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
     * 收到之后做的处理
     */
    public interface Action {
        /**
         * 处理
         * @param msg 传入的控制台的输入
         */
        void act(String msg);
    }
}
