package com.wndexx.search;

import org.junit.Test;

/**
 * 顺序（线性）查找
 *
 * @author wndexx
 * @create 2022-03-27 14:47
 */
public class SeqSearch {
    /**
     * 测试线性查找
     */
    @Test
    public void test1() {
        int[] arr = {1, 9, 11, -1, 34, 89}; // 没有顺序的数组
        int index = seqSearch(arr, -89);
        if (index == -1) {
            System.out.println("没有找到 ");
        } else {
            System.out.println("找到，下标为 " + index);
        }
    }

    /**
     * 线性查找，线性查找是逐一比对，发现有相同的值时，就返回下标
     * 这里实现的线性查找是找到一个满足条件的值，就返回
     *
     * @param arr 原数组
     * @param val 待找数据
     * @return 目标所在位置
     */
    public int seqSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
