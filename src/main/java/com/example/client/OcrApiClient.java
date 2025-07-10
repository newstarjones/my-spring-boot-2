package com.example.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class OcrApiClient {
    public static void main(String[] args) {
        // API 端点 URL
//        String apiUrl = "http://localhost:8080/api/ocr";

//        ocr nginx proxy
        String apiUrl = "http://localhost:9778/upload/uploadOcrImg";

        // 图片文件路径
        String imagePath = "D:\\code_sina\\my-spring-boot-2\\screenshots\\screen_1749201090289.png";

        File fileToUpload = new File(imagePath);

        // 创建 HTTP 客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 POST 请求
            HttpPost httpPost = new HttpPost(apiUrl);

            // 构建 multipart/form-data 请求体
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "image",                          // 表单字段名
                    fileToUpload,              // 文件
                    ContentType.APPLICATION_OCTET_STREAM,  // 内容类型
                    fileToUpload.getName()     // 文件名
            );

            // 设置请求体
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 获取响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("状态码: " + statusCode);

                if(statusCode == HttpStatus.SC_OK) {
                    // 获取响应实体
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity != null) {
                        // 读取响应内容
                        String responseBody = EntityUtils.toString(responseEntity);
                        System.out.println("响应内容: " + responseBody);

                        // 释放响应实体
                        EntityUtils.consume(responseEntity);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("请求执行失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}