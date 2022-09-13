package io.github.mcchampions.DodoOpenJava.Command;

import java.io.IOException;

/**
 * 控制台发送者
 */
public class ConsoleSender extends CommandSender{
    /**
     * 回复发送者发送的消息
     * @param Message 消息
     * @throws IOException 发送失败时抛出
     */
    @Override
    public void referencedMessage(String Message) throws IOException {
        System.out.println(Message);
    }

    /**
     * 判断是否有权限
     * @param permission 权限
     * @return true成功，false失败
     */
    @Override
    public Boolean hasPermission(String permission) {
        return true;
    }
}
