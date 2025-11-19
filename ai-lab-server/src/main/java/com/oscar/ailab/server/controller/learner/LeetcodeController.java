package com.oscar.ailab.server.controller.learner;

import com.oscar.ailab.server.service.learner.LeetcodeOfStringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lo")
public class LeetcodeController {

    @Resource
    private LeetcodeOfStringService leetcodeOfStringService;

    //最长不重复子串
    @GetMapping("/subString/longest/unrepeated")
    public String unrepeatedLongestSubString(String s, Integer method) {
        if (method == 1) {
            return leetcodeOfStringService.unrepeatedLongestSubString(s);
        } else if (method == 2) {
            return leetcodeOfStringService.unrepeatedLongestSubString2(s);
        } else {
            return "Method not found";
        }
    }

    //是否为"回字串"
    @GetMapping("/subString/isPalindrome")
    public String isPalindrome(Integer i) {
        List<String> list = Arrays.asList(
                "A man, a plan, a canal: Panama",
                "race a car",
                "abccba",
                "aaa",
                "   "
        );
        return leetcodeOfStringService.isPalindrome(list.get(i)) ? "true" : "false";
    }
}
