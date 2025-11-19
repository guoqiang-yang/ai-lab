package com.oscar.ailab.server.service.impl.learner;

import com.oscar.ailab.server.service.learner.JavaGrammarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JavaGrammarServiceImpl implements JavaGrammarService {

    public void doString() {
        newString();
    }

    private void newString() {
        String s1 = new String("bbb");
        String s2 = new String("bbb");

        String s3 = "bbb";
        String s4 = "bbb";

        log.info("{}, {}, {}, {}", s1==s2, s1.equals(s2), s1==s3, s3==s4);
    }
}
