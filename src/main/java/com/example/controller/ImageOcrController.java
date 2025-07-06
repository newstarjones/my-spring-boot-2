package com.example.controller;

import com.example.domain.CharWithPosition;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ImageOcrController {

    @PostMapping("/ocr")
    @ApiOperation(value = "上传图片", notes = "上传手机截图")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    public List<CharWithPosition> recognizeTextCoordinates(
            @RequestParam("image") MultipartFile imageFile) {

        List<CharWithPosition> charWithPositions = new ArrayList<>();
        CharWithPosition charWithPosition = new CharWithPosition();
        charWithPosition.setC("1");
        charWithPosition.setX(10);
        charWithPosition.setY(100);
        charWithPositions.add(charWithPosition);

        try {
            // 保存上传的图片到临时文件
            File tempImageFile = convertMultipartFileToFile(imageFile);

            // 删除临时文件
//            if (tempImageFile.exists()) {
//                tempImageFile.delete();
//            }
            System.out.println(tempImageFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return charWithPositions;
    }

    // 辅助方法：将MultipartFile转换为File
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {

        String prefix = UUID.randomUUID().toString();
        System.out.println("prefix:" + prefix);
        File tempFile = File.createTempFile(prefix,
                "." + file.getOriginalFilename().split("\\.")[1]);
        file.transferTo(tempFile);
        return tempFile;
        // todo
        // 这里的 文件名是 bd49724c-5877-4943-894d-56a65181c8c82134659767787831625.png
        // 看到除了 UUID 和 文件名后缀 ，还有一个唯一的随机数，这足以保证唯一性
    }
}