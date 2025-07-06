package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "图片服务", description = "图片")
@RestController
@RequestMapping("/image")
public class ImageController {

    @ApiOperation(value = "根据ID获取用户信息", notes = "传入用户ID，返回用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    @PostMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        // 这里只是示例，实际应返回User对象
        return "用户ID：" + id;
    }
}
