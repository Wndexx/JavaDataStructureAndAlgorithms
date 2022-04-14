package com.wndexx.kmp;

import org.junit.Test;

/**
 * 暴力匹配算法
 *
 * @author wndexx 2022-04-13 14:14
 */
public class ViolenceMatch {
    /**
     * 测试暴力匹配算法
     */
    @Test
    public void test1() {

        String str1 = "Never put off what you can do today until tomorrow";
        String str2 = "you";

        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index); // index = 19

    }

    /**
     * 暴力匹配算法
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 匹配位置第一个索引，不存在返回 -1
     */
    public int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int len1 = s1.length;
        int len2 = s2.length;

        // i 用来遍历 s1
        int i = 0;
        // j 用来遍历 s2
        int j = 0;

        while (i < len1 && j < len2) { // 保证匹配时不越界，以及匹配成功时终止检索
            if (s1[i] == s2[j]) { // 匹配成功
                i++;
                j++;
            } else { // 没有匹配成功
                i = i - (j - 1);
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == len2) {
            return i - j;
        }
        return -1;
    }
}
