package com.example.file;

public class FIleOperator {
    public static void main(String[] args) {
        String tempDir = System.getProperty("java.io.tmpdir");
        System.out.println("默认临时目录：" + tempDir);
    }
}
