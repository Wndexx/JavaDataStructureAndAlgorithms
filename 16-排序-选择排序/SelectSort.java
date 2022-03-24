package com.wndexx.sort;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 选择排序
 *
 * @author wndexx
 * @create 2022-03-23 18:01
 */
public class SelectSort {


    /**
     * 测试 1
     */
    @Test
    public void test1() {

        int[] arr = {101, 34, 109, 1};

        System.out.println("排序前\n" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后\n" + Arrays.toString(arr));
    }

    /**
     * 测试选择排序的速度
     * 效率优于冒泡排序，因为交换次数少
     *
     */
    @Test
    public void test2() {
        // 测试选择排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数组成的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成 [0, 8000000) 之间的随机数
        }

        LocalDateTime before = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String beforeStr = pattern.format(before);
        System.out.println("排序前的时间是：" + beforeStr);

        selectSort(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    @Test
    public void testSelectSort0() {

        int[] arr = {101, 34, 119, 1};

        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        selectSort0(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 使用逐步推导的方式来讲解选择排序
     *
     * @param arr 原数组
     */
    public void selectSort0(int[] arr) {

        // // 原始的数组：101，34，119，1
        // // 算法 先简单 -> 再复杂，把复杂的算法拆分成简单的问题，逐步解决
        //
        // // 第一轮排序：1，34，119，101
        // int minIndex = 0;
        // int min = arr[minIndex];
        // for (int j = 0 + 1; j < arr.length; j++) {
        //     if (min > arr[j]) { // 说明假定的最小值，并不是最小的
        //         min = arr[j]; // 重置 min
        //         minIndex = j; // 重置 minIndex
        //     }
        // }
        //
        // // 将最小值，放在 arr[0]，即交换
        // if (minIndex != 0) {
        //     arr[minIndex] = arr[0];
        //     arr[0] = min;
        // }
        //
        // System.out.println("第 1 轮");
        // System.out.println(Arrays.toString(arr));
        //
        // // 第二轮
        // minIndex = 1;
        // min = arr[minIndex];
        // for (int j = 0 + 2; j < arr.length; j++) {
        //     if (min > arr[j]) { // 说明假定的最小值，并不是最小的
        //         min = arr[j]; // 重置 min
        //         minIndex = j; // 重置 minIndex
        //     }
        // }
        //
        // // 将最小值，放在 arr[0]，即交换
        // if (minIndex != 1) {
        //     arr[minIndex] = arr[1];
        //     arr[1] = min;
        // }
        //
        // System.out.println("第 2 轮");
        // System.out.println(Arrays.toString(arr));
        //
        // // 第二轮
        // minIndex = 2;
        // min = arr[minIndex];
        // for (int j = 0 + 3; j < arr.length; j++) {
        //     if (min > arr[j]) { // 说明假定的最小值，并不是最小的
        //         min = arr[j]; // 重置 min
        //         minIndex = j; // 重置 minIndex
        //     }
        // }
        //
        // // 将最小值，放在 arr[0]，即交换
        // if (minIndex != 2) {
        //     arr[minIndex] = arr[2];
        //     arr[2] = min;
        // }
        //
        // System.out.println("第 3 轮");
        // System.out.println(Arrays.toString(arr));

        // 在推导的过程中，发现规律，可以使用 for 来解决
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值，并不是最小的
                    min = arr[j]; // 重置 min
                    minIndex = j; // 重置 minIndex
                }
            }
            // 将最小值，放在 arr[0]，即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            // System.out.println("第 " + (i + 1) + " 轮");
            // System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 选择排序，时间复杂度 O(n^2)
     *
     * @param arr 原数组
     */
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = getMinIndex(arr, i);
            if (minIndex != i)
                reverse(arr, i, minIndex);
        }
    }

    /**
     * 获取从指定索引 index 到数组尾部最小元素的索引值
     *
     * @param arr   原数组
     * @param index 指定索引
     * @return 最小元素的索引值
     */
    private int getMinIndex(int[] arr, int index) {
        int min = index;
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] < arr[min])
                min = i;
        }
        return min;
    }

    /**
     * 数组内元素调换顺序
     *
     * @param arr 原数组
     * @param i   索引 i
     * @param j   索引 j
     */
    private void reverse(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
