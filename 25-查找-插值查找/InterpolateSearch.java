package com.wndexx.search;

import org.junit.Test;

/**
 * 插值查找
 *
 * @author wndexx
 * @create 2022-03-28 15:38
 */
public class InterpolateSearch {
    /**
     * 测试插值查找
     */
    @Test
    public void test1() {
        // int[] arr = new int[100];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = i + 1;
        // }
        // int[] arr = {1, 8, 10, 89, 1000, 1234};
        int[] arr = {1, 2, 3, 4, 99, 100};
        int index = interpolateSearch(arr, 4);
        System.out.println(index);
    }

    /**
     * 插值查找
     * 说明：插值查找算法，要求数组有序
     *
     * @param arr 原数组，从小到大排序
     * @param val 待查找数
     * @return 存在，返回索引；不存在，返回 -1
     */
    public int interpolateSearch(int[] arr, int val) {
        return subSearch(arr, 0, arr.length - 1, val);
    }

    /**
     * 插值查找的递归方法
     *
     * @param arr   原数组，从小到大排序
     * @param left  左索引
     * @param right 右索引
     * @param val   待查找数
     * @return 存在，返回索引；不存在，返回 -1
     */
    private int subSearch(int[] arr, int left, int right, int val) {
        System.out.println("ヾ(•ω•`)o");
        // 注意：val < arr[left] 和 val > arr[right] 必须需要，否则得到的 mid 可能越界，主要是 arr[0] 和 arr[arr.length - 1]
        if (left > right || val < arr[left] || val > arr[right])
            return -1;
        // 求出 mid，自适应
        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        if (val > arr[mid]) // 向右递归
            return subSearch(arr, mid + 1, right, val);
        if (val < arr[mid]) // 向左递归
            return subSearch(arr, left, mid - 1, val);
        return mid;
    }
}
