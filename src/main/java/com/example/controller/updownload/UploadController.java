package com.example.controller.updownload;

import com.example.domain.CharWithPosition;
//import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// 快速测试： curl -F "file=@D:\\inspector-20250630.zip" http://localhost:8080/upload/zip
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @PostMapping("/ocrImg")
//    @ApiOperation(value = "上传安全键盘图片，识别，返回识别的所有元素的坐标", notes = "上传安全键盘图片，返回识别的所有元素的坐标")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    public List<CharWithPosition> uploadOcrImg(
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

//            ocr识别  todo
//            可以调用本地服务，也可以调用远程服务

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
        // 将上传的文件内容 写入到 tempFile
        file.transferTo(tempFile);
        return tempFile;
        // todo
        // 这里的 文件名是 bd49724c-5877-4943-894d-56a65181c8c82134659767787831625.png
        // 看到除了 UUID 和 文件名后缀 ，还有一个唯一的随机数，这足以保证唯一性
    }

    // 修改为你想要的解压目录
//    private static final String TARGET_DIR = "/opt/upload/unzip/";

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/zip")
//    @ApiOperation(value = "上传zip文件，并解压", notes = "上传zip文件，并解压")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Long")
    public ResponseEntity<String> uploadZip(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".zip")) {
            return ResponseEntity.badRequest().body("请上传 zip 文件");
        }

        // 创建解压目录
        File targetDir = new File(uploadDir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        try (InputStream zipInput = file.getInputStream();
             ZipInputStream zis = new ZipInputStream(zipInput)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(targetDir, entry.getName());

                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    // 确保目录存在
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }

        } catch (IOException e) {
            return ResponseEntity.status(500).body("解压失败：" + e.getMessage());
        }

        return ResponseEntity.ok("上传并解压成功");
    }
}