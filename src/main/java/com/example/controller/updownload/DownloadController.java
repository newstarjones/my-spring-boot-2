package com.example.controller.updownload;

//import io.swagger.annotations.Api;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//@Api(tags = "文件上传下载", description = "文件上传下载")
@RestController
@RequestMapping("/file")
public class DownloadController {

    @GetMapping("/download/{who}")
    public ResponseEntity<Resource> downloadLog(@PathVariable String who) throws IOException {
        System.out.println("who:" + who);
//        Path filePath = Paths.get(logDirPath).resolve(file).normalize();
        Path filePath = Paths.get("D:\\code\\my-spring-boot-2\\src\\main\\java\\com\\example\\controller\\HelloController.java").normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
