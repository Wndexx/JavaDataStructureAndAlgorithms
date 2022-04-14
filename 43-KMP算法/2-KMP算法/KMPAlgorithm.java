package com.wndexx.kmp;

import org.junit.Test;

import java.util.Arrays;

/**
 * KMP 算法
 *
 * @author wndexx 2022-04-13 15:33
 */
public class KMPAlgorithm {
    /**
     * 测试 KMP 算法
     */
    @Test
    public void test1() {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        // int[] next = kmpNext("ABCDABD");
        // System.out.println(Arrays.toString(next));

        int index = kmpSearch(str1, str2);
        System.out.println("index = " + index);
    }

    /**
     * KMP 搜索算法
     *
     * @param str1 字符串
     * @param str2 字符串
     * @return 返回 str2 在 str1 中第一个匹配子串的第一个字符索引；不存在，返回 -1
     */
    public int kmpSearch(String str1, String str2) {
        // str2 的部分匹配值表
        int[] next = kmpNext(str2);

        // 遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 当 str1.charAt(i) != str2.charAt(j)，调整 j 的大小
            // KMP 算法的核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取到一个字符串（子串）的部分匹配值表
     *
     * @param dest 目标字符串
     * @return 部分匹配值表
     */
    public int[] kmpNext(String dest) {
        // 1. 创建一个 next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        // 如果字符串长度为 1，则该字符串的部分匹配值为 0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当 dest.charAt(i) != dest.charAt(j)，需要从 next[j-1] 获取新的 j
            // 直到 dest.charAt(i) == dest.charAt(j) 满足时退出
            // 这是 KMP 算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当 dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值 + 1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
