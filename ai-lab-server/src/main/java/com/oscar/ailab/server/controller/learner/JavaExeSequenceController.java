package com.oscar.ailab.server.controller.learner;


import com.oscar.ailab.server.service.learner.JavaExeSequenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/j/e/s")
public class JavaExeSequenceController {

    @Resource
    private JavaExeSequenceService javaExeSequenceService;

    @GetMapping("/static")
    public void testStatic() {
        javaExeSequenceService.testStatic();
    }

    @GetMapping("/extend")
    public void testExtend() {
        javaExeSequenceService.testExtend();
    }

    @GetMapping("/book")
    public void testBook() {
        javaExeSequenceService.testBook();
    }
}
