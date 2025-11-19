package com.oscar.ailab.server.controller.tester;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curl")
public class CurlController {
    
    @PostMapping("/upload")
    public String receiveFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                String content = reader.lines().collect(Collectors.joining("\n"));
                return content;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "文件处理失败: " + e.getMessage();
        }
    }

    @PostMapping("/replay")
    public String replay(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> params) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
                String content = reader.lines().collect(Collectors.joining("\n"));
//                return JacksonUtils.toJSONString(Arrays.asList(content, params));
                return null;
            }
        } catch (IOException e) {
            return "文件处理失败: " + e.getMessage();
        }
    }
}
