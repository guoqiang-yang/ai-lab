package com.oscar.ailab.server.controller.learner;


import com.oscar.ailab.server.service.learner.CompletableFutureService;
import com.oscar.ailab.server.service.learner.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/thread")
public class ThreadController {
    @Resource
    private ThreadService threadService;
    @Resource
    private CompletableFutureService completableFutureService;


    @GetMapping("/raw")
    public void raw(int num) {
        threadService.rawThread(num);
    }

    @GetMapping("/sync")
    public void sync(String f) {
        if (f.equals("2")) {
            threadService.reentrantLock();
        } else if (f.equals("rw")) {
            threadService.readWriteLock();
        } else if (f.equals("stamped")) {

        } else {
            threadService.syncThread();
        }
    }

    @GetMapping("/concurrent")
    public void concurrent() {
        completableFutureService.hisFunc();
    }

    @GetMapping("/asyncList")
    public void asyncList(int c) {
        completableFutureService.asyncList(c);
    }

    @GetMapping("/then")
    public void then() {
        completableFutureService.then();
    }
}
