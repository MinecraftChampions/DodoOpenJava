package io.github.minecraftchampions.dodoopenjava.command;

import okio.ByteString;

/**
 * ����Ľӿ�
 */
public interface CommandExecutor {
    /**
     * ������������б�ˣ�
     * @return ������
     */
    ByteString MainCommand();

    /**
     * ��Ҫ��Ȩ��
     * @return Ȩ��
     */
    String Permission();

    /**
     * �����
     * @param sender ������
     * @param args ����
     */
    void onCommand(CommandSender sender, String[] args);
}
