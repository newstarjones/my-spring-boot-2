package com.example.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * StandardPBEStringEncryptor
 */
public class EncryptExample2 {
    public static String algorithm = "PBEWithMD5AndDES";
//    public static String algorithm = "PBEWithHmacSHA512AndAES_256";
    public static void main(String[] args) {
        System.out.println(de(en()));
    }

    public static String en() {
        String plaintext = "1234";
        String password = "mySecretKey"; // 与 Spring 配置中保持一致

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(algorithm);
        encryptor.setStringOutputType("base64");

        String encrypted = encryptor.encrypt(plaintext);
        System.out.println("加密后密文: ENC(" + encrypted + ")");
        return encrypted;
    }

    public static String de(String pwd) {
        String encrypted = pwd; // 去掉 ENC()
        String password = "mySecretKey";

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(algorithm);
        encryptor.setStringOutputType("base64");

        String decrypted = encryptor.decrypt(encrypted);
        System.out.println("解密后明文: " + decrypted);
        return decrypted;
    }
}
