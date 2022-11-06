package io.github.mcchampions.DodoOpenJava.Command;

import okio.ByteString;

/**
 * ����Ľӿ�
 * @author qscbm187531
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
