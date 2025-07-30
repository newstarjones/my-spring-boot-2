package com.example.json.fastjson;

import com.alibaba.fastjson2.JSON;

public class Main {
    public static void main(String[] args) {
        Integer value = JSON.parseObject("10", Integer.class);
        System.out.println(value); // 输出 10
    }
}
