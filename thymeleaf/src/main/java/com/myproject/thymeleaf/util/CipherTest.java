package com.myproject.thymeleaf.util;
 
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
 
/**
 * 常用对称加密算法性能测试
 */
public class CipherTest {
 
    private static final int LOOP = 50000;
 
    public static String ALGORITHM_DES = "DES";
    public static String ALGORITHM_3DES = "DESede"; // 3DES
    public static String ALGORITHM_BLOWFISH = "Blowfish";
    public static String ALGORITHM_AES = "AES";
 
    public static Key keyGenerator(String algorithm, int n) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }
 
    // 加密方法
    public static byte[] encrypt(Key key, String text, String algorithm) throws Exception {
        // ECB是分组模式，PKCS5Padding 是补全策略
        Cipher cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }
 
    // 解密方法
    public static byte[] decrypt(Key key, byte[] data, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
 
    // 测试加密速度
    public static void testEncrypt(String text, String algorithm, int bit) throws Exception {
        System.out.println("text = " + text);
        Key key = keyGenerator(algorithm, bit);
        System.out.println("key = " + key);
//        long startTs = System.currentTimeMillis();
 
//        for (int i = 0; i < LOOP; i++) {
        byte[] encrypt = encrypt(key, text, algorithm);

        String s = byte2hex(encrypt);
        // 9B18DB7D7CDB45368E7D460F2F49883D
        System.out.println("s = " + s);
//        }
//        long endTs = System.currentTimeMillis();
//        System.out.println(algorithm + " (" + bit + "位秘钥) 加密平均耗时 " + ((endTs - startTs) / (double) LOOP) + " ms");
    }
 
    public static void testDecrypt(String text, String algorithm, int bit) throws Exception {
        Key key = keyGenerator(algorithm, bit);
//        long startTs = System.currentTimeMillis();
 
        byte[] encrypted = encrypt(key, text, algorithm);

//        for (int i = 0; i < LOOP; i++) {
        byte[] decrypt = decrypt(key, encrypted, algorithm);
        String s = byte2hex(decrypt);
        System.out.println("s = " + s);

//        }
//        long endTs = System.currentTimeMillis();
//        System.out.println(algorithm + " (" + bit + "位秘钥) 解密平均耗时 " + ((endTs - startTs) / (double) LOOP) + " ms");
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
 
    public static void main(String[] args) throws Exception {
        String passwordText = "XC2021060334172";
 
       /* // DES 算法仅支持56位定长秘钥
        testEncrypt(passwordText, ALGORITHM_DES, 56);
        testDecrypt(passwordText, ALGORITHM_DES, 56);
 
        // 3DES 算法仅支持112位和168位秘钥
        testEncrypt(passwordText, ALGORITHM_3DES, 112);
        testDecrypt(passwordText, ALGORITHM_3DES, 112);
 
        testEncrypt(passwordText, ALGORITHM_3DES, 168);
        testDecrypt(passwordText, ALGORITHM_3DES, 168);
 
        // Blowfish 算法支持32 到 448位的变长秘钥
        testEncrypt(passwordText, ALGORITHM_BLOWFISH, 32);
        testDecrypt(passwordText, ALGORITHM_BLOWFISH, 32);
 
        testEncrypt(passwordText, ALGORITHM_BLOWFISH, 256);
        testDecrypt(passwordText, ALGORITHM_BLOWFISH, 256);
 
        testEncrypt(passwordText, ALGORITHM_BLOWFISH, 448);
        testDecrypt(passwordText, ALGORITHM_BLOWFISH, 448);
 */
        // AES 算法支持 128，192，256 三种定长秘钥
        testEncrypt(passwordText, ALGORITHM_AES, 128);
//        testDecrypt(passwordText, ALGORITHM_AES, 128);
 
//        testEncrypt(passwordText, ALGORITHM_AES, 192);
//        testDecrypt(passwordText, ALGORITHM_AES, 192);
 
//        testEncrypt(passwordText, ALGORITHM_AES, 256);
//        testDecrypt(passwordText, ALGORITHM_AES, 256);
 
    }
}