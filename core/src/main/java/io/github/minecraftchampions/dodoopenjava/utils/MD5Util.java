package io.github.minecraftchampions.dodoopenjava.utils;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * ����md5��һЩ����
 */
public class MD5Util {
    /**
     * ��ȡMD5ֵ
     * @param path �ļ�
     * @return md5
     */
    public static String getMd5(File path) {
        try {
            return String.format("%032x", new BigInteger(1, MessageDigest.getInstance("MD5").digest(Files.readAllBytes(path.toPath())))).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ��ȡMD5ֵ
     * @param is ������
     * @return md5
     */
    public static String getMd5(InputStream is) {
        try {
            return String.format("%032x", new BigInteger(1, new DigestInputStream(is, MessageDigest.getInstance("MD5")).getMessageDigest().digest())).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }
}
