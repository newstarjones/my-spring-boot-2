package com.example.controller;

import jdk.internal.org.objectweb.asm.Handle;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @PostMapping("/{type}/{key}")
    public String getMap(@PathVariable("type") String type, @PathVariable("key") String key) {
        // 这里只是示例，实际应返回User对象
        switch (type) {
            case "map":
                RMap<String, String> rMap = redissonClient.getMap(key);
//                转成java 的map
//                new HashMap<>(rMap);
                rMap.put("name", "123");
                return "用户名字：" + rMap.get("name");
            case "list":
                RList<String> rList = redissonClient.getList(key);
                return "" + new ArrayList<>(rList);
            default:
                RBucket<String> rBucket = redissonClient.getBucket(key);
                return rBucket.get();
        }

    }
}
