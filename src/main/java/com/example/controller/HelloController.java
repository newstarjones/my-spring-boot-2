package com.example.controller;


//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

//@Api(tags = "用户相关接口", description = "用户管理相关的增删改查接口")
@RestController
@RequestMapping("/hello")
public class HelloController {

//    @ApiOperation(value = "根据ID获取用户信息", notes = "传入用户ID，返回用户信息")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        // 这里只是示例，实际应返回User对象
        System.out.println("getUserById enter");
        return "用户ID：" + id;
    }

//    @ApiOperation(value = "创建用户", notes = "根据用户名和年龄创建用户")
//    @PostMapping("/create")
//    public String createUser(
//            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
//            @ApiParam(name = "age", value = "年龄", required = false) @RequestParam(required = false) Integer age) {
//        // 这里只是示例，实际应处理创建逻辑
//        return "创建用户：" + username + "，年龄：" + age;
//    }
}