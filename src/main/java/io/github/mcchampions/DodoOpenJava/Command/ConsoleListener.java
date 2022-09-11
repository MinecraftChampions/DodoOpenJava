package io.github.mcchampions.DodoOpenJava.Command;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 控制台输入监听
 */
public class ConsoleListener {
    HashMap<String, Action> answers = new HashMap<String, ConsoleListener.Action>();
    Scanner scanner;
    Action defaultAction;

    public void addAction(String message, Action action) {
        answers.put(message.toLowerCase(), action);
    }

    public ConsoleListener(Scanner scanner, Action defaultAction) {
        this.scanner = scanner;
        this.defaultAction = defaultAction;

        if (scanner == null || defaultAction == null) {
            throw new NullPointerException("null params for ConsoleListener");
        }
    }

    public void removeAction(String message, Action action) {
        answers.remove(message, action);
    }

    public Action replaceAction(String message, Action action) {
        return answers.replace(message, action);
    }

    public void listenInNewThread() {
        Thread t = new Thread() {
            public void run() {
                listen();
            }
        };
        t.start();
    }

    public void listen() {
        while (true) {
            String line = scanner.nextLine();
            String msg = line.replaceAll("[\\s]+", " ");
            msg = msg.trim().toLowerCase();
            Action action = answers.get(msg);
            if (action == null) {
                action = defaultAction;
            }

            action.act(line);

        }
    }

    public static interface Action {
        public void act(String msg);
    }
}
