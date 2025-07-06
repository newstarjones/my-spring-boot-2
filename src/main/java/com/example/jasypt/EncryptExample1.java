package com.example.jasypt;

import com.sun.javafx.geom.transform.GeneralTransform3D;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * BasicTextEncryptor
 */
public class EncryptExample1 {
    // 命令行执行
    // java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI \
    //   input="mysecret123" password="mykey" algorithm="PBEWithMD5AndDES"
    // 输出结果类似：ENC(Hj4iN8s7vA7LDkp1LaNFcw==)
    public static void main(String[] args) {
        en();
        de();
    }

    public static void en() {
        // 1. 设置加密用的密钥（你在 Spring Boot 中也会用这个密钥来解密）
        String secretKey = "mySecretKey";

        // 2. 明文密码
        String plaintextPassword = "1234";

        // 3. 初始化加密器
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(secretKey); // 设置密钥

        // 4. 执行加密
        String encrypted = encryptor.encrypt(plaintextPassword);

        // 5. 输出加密后的字符串
        System.out.println("加密后的密码是：ENC(" + encrypted + ")");
    }

    public static void de() {
        String encryptedText = "SERRty8KyJnmIvL0wEpQAQ=="; // 去掉 ENC(...)
        String password = "mySecretKey"; // 加密/解密密钥

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password); // 设置密钥

        String decrypted = textEncryptor.decrypt(encryptedText);
        System.out.println("解密后的内容: " + decrypted);
    }
}
