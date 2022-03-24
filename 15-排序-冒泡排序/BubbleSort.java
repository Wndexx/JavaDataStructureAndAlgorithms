package com.wndexx.sort;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author wndexx
 * @create 2022-03-23 14:34
 */
public class BubbleSort {

    /**
     * 测试 1
     */
    @Test
    public void test1() {

        int[] arr = {3, 9, -1, 10, 20};

        System.out.println("排序前\n" + Arrays.toString(arr));

        // bubbleSort(arr);
        bubbleSort1(arr);

        System.out.println("排序后\n" + Arrays.toString(arr));


        // // 冒泡排序的演示过程
        // // 1. 第一趟排序，就是将最大的数排在最后
        // int temp; // 临时变量
        // for (int j = 0; j < arr.length - 1 - 0; j++) {
        //     // 如果前面的数比后面的数大，则交换
        //     if (arr[j] > arr[j + 1]) {
        //         temp = arr[j];
        //         arr[j] = arr[j + 1];
        //         arr[j + 1] = temp;
        //     }
        // }
        // System.out.println("第一趟排序后的数组");
        // System.out.println(Arrays.toString(arr));
        //
        // // 2. 第二趟排序，就是将第二大数排在倒数第二位
        // for (int j = 0; j < arr.length - 1 - 1; j++) {
        //     // 如果前面的数比后面的数大，则交换
        //     if (arr[j] > arr[j + 1]) {
        //         temp = arr[j];
        //         arr[j] = arr[j + 1];
        //         arr[j + 1] = temp;
        //     }
        // }
        // System.out.println("第二趟排序后的数组");
        // System.out.println(Arrays.toString(arr));
        //
        // // 3. 第三趟排序，就是将第三大数排在倒数第三位
        // for (int j = 0; j < arr.length - 1 - 2; j++) {
        //     // 如果前面的数比后面的数大，则交换
        //     if (arr[j] > arr[j + 1]) {
        //         temp = arr[j];
        //         arr[j] = arr[j + 1];
        //         arr[j + 1] = temp;
        //     }
        // }
        // System.out.println("第三趟排序后的数组");
        // System.out.println(Arrays.toString(arr));
        //
        // // 4. 第四趟排序，就是将第四大数排在倒数第四位
        // for (int j = 0; j < arr.length - 1 - 3; j++) {
        //     // 如果前面的数比后面的数大，则交换
        //     if (arr[j] > arr[j + 1]) {
        //         temp = arr[j];
        //         arr[j] = arr[j + 1];
        //         arr[j + 1] = temp;
        //     }
        // }
        // System.out.println("第四趟排序后的数组");
        // System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试 2
     */
    @Test
    public void test2() {

        // 测试冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数组成的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000); // 生成 [0, 80000) 之间的随机数
        }

        LocalDateTime before = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String beforeStr = pattern.format(before);
        System.out.println("排序前的时间是：" + beforeStr);

        bubbleSort1(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    /**
     * 冒泡排序，时间复杂度 O(n^2)   T(n) = 1/2 * n * n - 1/2 * n = O(n^2)
     *
     * @param arr 原数组
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1])
                    reverse(arr, j, j + 1);
                // System.out.println("第 " + (i + 1) + " 趟排序\n" + Arrays.toString(arr));
            }
        }
    }

    /**
     * 冒泡排序优化
     *
     * @param arr 原数组
     */
    public void bubbleSort1(int[] arr) {
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    reverse(arr, j, j + 1);
                }
            }
            // System.out.println("第 " + (i + 1) + " 趟排序\n" + Arrays.toString(arr));
            if (!flag) // 在一趟排序中，一次交换都没有发生
                break;
            flag = false; // 重置 flag，进行下次判断
        }
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
