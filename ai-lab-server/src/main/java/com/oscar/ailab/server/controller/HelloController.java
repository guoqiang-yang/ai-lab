package com.oscar.ailab.server.controller;

import com.oscar.ailab.server.service.DbTesterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    DbTesterService dbTesterService;

    @GetMapping("/")
    public String hello(){
        return "Hello Gay";
    }


    @GetMapping("/hi/db")
    public String hiBD(@RequestParam @Validated Integer id) {
        dbTesterService.get(id);
        return "Hi DB, Got It!";
    }
}
