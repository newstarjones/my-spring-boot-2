package com.example.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JasyptUtil {

    @Autowired
    private StringEncryptor encryptor;

    public String encrypt(String input) {
        return encryptor.encrypt(input);
    }

    public String decrypt(String encrypted) {
        return encryptor.decrypt(encrypted);
    }
}
