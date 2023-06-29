package io.github.minecraftchampions.dodoopenjava.event.webhook.util;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * 开放秘密工具
 * @author Dodo
 */
public class OpenSecretUtil {
    /*
      初始化Provider，千万别漏了！
     */
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * WebHook解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     *
     * @return 解密后的字符串
     */
    public static String WebHookDecrypt(String payload, String secretKey) {
        try {
            return AESDecrypt(HexStringToBytes(payload), HexStringToBytes(secretKey), new byte[16], Cipher.getInstance("AES/CBC/PKCS7Padding"));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * AES解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     * @param iv        IV向量
     * @param cipher    AES配置
     *
     * @return 解密密过后的值
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
     * 十六进制字符串转字节数组
     *
     * @param hexStr 十六进制字符串
     *
     * @return 字节数组
     */
    private static byte[] HexStringToBytes(String hexStr) throws Exception {
        return Hex.decodeHex(hexStr.toCharArray());
    }
}
