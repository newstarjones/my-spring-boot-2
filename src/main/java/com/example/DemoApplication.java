package com.example;


//import com.example.config.MyConfigProperties;
import com.example.jasypt.JasyptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

//@EnableConfigurationProperties(MyConfigProperties.class)
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private JasyptUtil jasyptUtil;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String rawPassword = "1234";

        // 加密
        String encrypted = jasyptUtil.encrypt(rawPassword);
        System.out.println("加密后: ENC(" + encrypted + ")");

        // 解密
        String decrypted = jasyptUtil.decrypt(encrypted);
        System.out.println("解密后: " + decrypted);
    }

    // 禁用 Swagger 相关组件
    // 有时需要禁用，如下是禁用办法，但是 编译异常
//    @Bean
//    @ConditionalOnProperty(name = "swagger.enabled", havingValue = "false")
//    public io.springfox.documentation.spi.DocumentationType documentationType() {
//        return null;
//    }
}