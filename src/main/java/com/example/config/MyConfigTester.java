package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyConfigTester {

    @Value("${my.config.apiKey}")
    private String apiKey;

    private final MyConfigProperties props;

    public MyConfigTester(MyConfigProperties props) {
        this.props = props;
    }

    @PostConstruct
    public void print() {
        System.out.println("API Key: " + props.getApikey());
    }

    @PostConstruct
    public void print2() {
        System.out.println("API Key2: "+ apiKey);
    }
}
