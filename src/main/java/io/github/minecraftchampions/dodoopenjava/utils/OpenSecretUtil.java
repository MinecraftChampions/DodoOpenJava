package io.github.minecraftchampions.dodoopenjava.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;

/**
 * 开放秘密工具
 *
 * @author imdodo
 * @author qscbm187531
 */
@Slf4j
public class OpenSecretUtil {
    /**
     * WebHook解密
     *
     * @param payload   加密消息
     * @param secretKey 解密密钥
     * @return 解密后的字符串
     */
    public static String webHookDecrypt(String payload, String secretKey) {
        try {
            return aesDecrypt(hexToBytes(payload), hexToBytes(secretKey), new byte[16], Cipher.getInstance("AES/CBC/PKCS5Padding"));
        } catch (Exception e) {
            log.error("WebHook数据解密时错误", e);
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
     * @return 解密密过后的值
     */
    private static String aesDecrypt(byte[] payload, byte[] secretKey, byte[] iv, Cipher cipher) throws Exception {
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
            int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
