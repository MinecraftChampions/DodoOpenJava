package io.github.mcchampions.DodoOpenJava.Command;

/**
 * ����̨������
 * @author qscbm187531
 */
public class ConsoleSender extends CommandSender{
    /**
     * �ظ������߷��͵���Ϣ
     * @param message ��Ϣ
     */
    @Override
    public void referencedMessage(String message) {
        System.out.println(message);
    }

    /**
     * �ж��Ƿ���Ȩ��
     * @param permission Ȩ��
     * @return true�ɹ���falseʧ��
     */
    @Override
    public Boolean hasPermission(String permission) {
        return true;
    }
}
