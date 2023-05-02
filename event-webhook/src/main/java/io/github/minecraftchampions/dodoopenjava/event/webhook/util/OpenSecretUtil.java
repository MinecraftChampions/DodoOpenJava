package io.github.minecraftchampions.dodoopenjava.event.webhook.util;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * �������ܹ���
 * @author Dodo
 */
public class OpenSecretUtil {
    /*
      ��ʼ��Provider��ǧ���©�ˣ�
     */
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * WebHook����
     *
     * @param payload   ������Ϣ
     * @param secretKey ������Կ
     *
     * @return ���ܺ���ַ���
     */
    public static String WebHookDecrypt(String payload, String secretKey) {
        try {
            return AESDecrypt(HexStringToBytes(payload), HexStringToBytes(secretKey), new byte[16], Cipher.getInstance("AES/CBC/PKCS7Padding"));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * AES����
     *
     * @param payload   ������Ϣ
     * @param secretKey ������Կ
     * @param iv        IV����
     * @param cipher    AES����
     *
     * @return �����ܹ����ֵ
     *
     */
    private static String AESDecrypt(byte[] payload, byte[] secretKey, byte[] iv, Cipher cipher) throws Exception {
        var sKeySpec = new SecretKeySpec(secretKey, "AES");
        var params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
        var result = cipher.doFinal(payload);
        return new String(result);
    }

    /**
     * ʮ�������ַ���ת�ֽ�����
     *
     * @param hexStr ʮ�������ַ���
     *
     * @return �ֽ�����
     */
    private static byte[] HexStringToBytes(String hexStr) throws Exception {
        return Hex.decodeHex(hexStr.toCharArray());
    }
}
