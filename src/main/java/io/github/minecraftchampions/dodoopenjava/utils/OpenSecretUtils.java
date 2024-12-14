package io.github.minecraftchampions.dodoopenjava.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * 开放秘密工具
 *
 * @author imdodo
 * @author qscbm187531
 */
@Slf4j
public class OpenSecretUtils {
    /**
     * WebHook解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     * @return 解密后的字符串
     */
    public static String webHookDecrypt(String payload, String secretKey) {
        try {
            return aesDecrypt(hexToBytes(payload), hexToBytes(secretKey), Cipher.getInstance("AES/CBC/PKCS5Padding"));
        } catch (Exception e) {
            log.error("WebHook数据解密时错误", e);
            return null;
        }
    }

    public static final AlgorithmParameters AES_PARAMS;

    static {
        try {
            AES_PARAMS = AlgorithmParameters.getInstance("AES");
            AES_PARAMS.init(new IvParameterSpec(new byte[16]));
        } catch (NoSuchAlgorithmException | InvalidParameterSpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     * @param cipher    AES配置
     * @return 解密密过后的值
     */
    private static String aesDecrypt(byte[] payload, byte[] secretKey, Cipher cipher) throws Exception {
        SecretKeySpec sKeySpec = new SecretKeySpec(secretKey, "AES");

        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, AES_PARAMS);
        byte[] result = cipher.doFinal(payload);
        return new String(result);
    }

    /**
     * 十六进制字符串转字节数组
     *
     * @param hex 十六进制字符串
     * @return 字节数组
     */
    private static byte[] hexToBytes(String hex) {
        int hexLen = hex.length();
        int arrayLen = hexLen / 2;
        boolean isOdd = hexLen % 2 == 1;
        byte[] result;
        if (isOdd) {
            arrayLen++;
            result = new byte[arrayLen];
            hex = "0" + hex;
        } else {
            result = new byte[arrayLen];
        }
        for (int i = 0; i < arrayLen; i++) {
            int high = Integer.parseInt(hex.substring(i << 1, (i << 1) + 1), 16);
            int low = Integer.parseInt(hex.substring((i << 1) + 1, (i << 1) + 2), 16);
            result[i] = (byte) ((high << 4) + low);
        }
        return result;
    }
}