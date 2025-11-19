package com.oscar.ailab.server.controller.learner;


import com.oscar.ailab.server.domain.ResultWrapper;
import com.oscar.ailab.server.service.learner.JavaGrammarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/j/g")
public class JavaGrammarController {

    @Resource
    private JavaGrammarService javaGrammarService;

    @GetMapping("/do/string")
    public ResultWrapper<?> doString() {
        javaGrammarService.doString();
        return ResultWrapper.success();
    }

}
