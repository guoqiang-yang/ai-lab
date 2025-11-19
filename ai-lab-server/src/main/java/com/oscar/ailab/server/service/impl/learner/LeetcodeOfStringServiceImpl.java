package com.oscar.ailab.server.service.impl.learner;

import com.oscar.ailab.server.service.learner.LeetcodeOfStringService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LeetcodeOfStringServiceImpl implements LeetcodeOfStringService {

    //不含重复字符的最长子字符串（1）

    /**
     * 不含重复字符的最长子字符串
     *  - "abcabcbb" -> "abc" -> 3
     *  - "bbbbb" -> "b" -> 1
     *  - "" -> 0
     */
    @Override
    public String unrepeatedLongestSubString(String s) {
        String subString = "";
        String tmpSubString;
        Map<String, Integer> occupied = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            String cc = s.charAt(i) + "";
            tmpSubString = cc;
            occupied.put(cc, i);

            for(int j=i+1; j<s.length(); j++) {
                String c = s.charAt(j) + "";
                if (occupied.get(c) != null && occupied.get(c) ==i) {
                    break;
                }
                tmpSubString += c;
                occupied.put(c, i);
            }
            if (tmpSubString.length() > subString.length()) {
                subString = tmpSubString;
            }
        }
        return subString;
    }

    public String unrepeatedLongestSubString2(String s) {
        String subString = "";
        String lastSubString = "";
        String tmpSubString;
        Map<String, Integer> occupied = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            int j = i+1;
            String cc = s.charAt(i) + "";
            occupied.put(cc, 1);

            if (lastSubString.length() > 1) {
                tmpSubString = lastSubString.substring(1, lastSubString.length());
                j += tmpSubString.length()-1;
            } else {
                tmpSubString = cc;
            }

            for(; j<s.length(); j++) {
                String c = s.charAt(j) + "";
                if (occupied.get(c) != null && occupied.get(c+ "") ==1) {
                    break;
                }
                tmpSubString += c;
                occupied.put(c, 1);
            }

            occupied.put(cc, 0);
            if (tmpSubString.length() > subString.length()) {
                subString = tmpSubString;
            }
            lastSubString = tmpSubString;
        }
        return subString;
    }

    /**
     * 回文串
     *  如果在将所有大写字符转换为小写字符
     *      并移除所有非字母数字字符之后，
     *      短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     *
     *  s = "A man, a plan, a canal: Panama" => amanaplanacanalpanama => Yes
     *  s = "race a car" => racar => No
     *  s = " " =>  => Yes
     */
    @Override
    public Boolean isPalindrome(String s) {
        if (s==null || s.length()==0) {
            return true;
        }
        for (int i=0, j=s.length()-1; ; ) {
            if (i>=j) {
                return true;
            }
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!Character.isLetterOrDigit(ci)) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(cj)) {
                j--;
                continue;
            }
            if (Character.toLowerCase(ci) != Character.toLowerCase(cj)) {
                return false;
            }
            i++;
            j--;
        }
    }
}
