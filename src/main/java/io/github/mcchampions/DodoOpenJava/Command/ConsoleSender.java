package io.github.mcchampions.DodoOpenJava.Command;

import java.io.IOException;

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

}
