package com.wndexx.sort;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 插入排序
 *
 * @author wndexx
 * @create 2022-03-24 10:26
 */
public class InsertSort {

    /**
     * 测试插入排序
     */
    @Test
    public void test1() {
        int[] arr = {101, 34, 119, 1};

        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        insertSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试插入排序的速度
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

        insertSort(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    @Test
    public void testInsertSort0() {
        int[] arr = {101, 34, 119, 1};
        insertSort0(arr);
    }

    /**
     * 使用逐步推导的方式来讲解插入排序
     *
     * @param arr 原数组
     */
    public void insertSort0(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 使用 for 循环简化代码
            // 定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1; // arr[1] 的前面数据的下标
            // 给 insertVal 找到插入的位置
            while (true) {

                // 保证在给 insertVal 找插入位置时，不越界，此时所要插入的位置索引为 0
                if (insertIndex < 0)
                    break;

                // 找到插入位置
                if (insertVal > arr[insertIndex])
                    break;

                arr[insertIndex + 1] = arr[insertIndex]; // arr[insertIndex] 后移
                insertIndex--;
            }
            // 当退出 while 循环时，说明插入的位置找到，insertIndex + 1
            arr[insertIndex + 1] = insertVal;

            System.out.println("第 " + i + " 轮插入");
            System.out.println(Arrays.toString(arr));
        }

        // // 第 1 轮：{101, 34, 119, 1} => {34, 101, 119, 1}
        //
        // // 定义待插入的数
        // int insertVal = arr[1];
        // int insertIndex = 1 - 1; // arr[1] 的前面数据的下标
        //
        // // 给 insertVal 找到插入的位置
        // while (true) {
        //     // 保证在给 insertVal 找插入位置时，不越界，此时所要插入的位置索引为 0
        //     if (insertIndex < 0)
        //         break;
        //     // 找到插入位置
        //     if (insertVal > arr[insertIndex])
        //         break;
        //     arr[insertIndex + 1] = arr[insertIndex]; // arr[insertIndex] 后移
        //     insertIndex--;
        // }
        //
        // // 当退出 while 循环时，说明插入的位置找到，insertIndex + 1
        // arr[insertIndex + 1] = insertVal;
        //
        // System.out.println("第 1 轮插入");
        // System.out.println(Arrays.toString(arr));
        //
        // // 第 2 轮：{34, 101, 119, 1} => {34, 101, 119, 1}
        //
        // // 定义待插入的数
        // insertVal = arr[2];
        // insertIndex = 2 - 1; // arr[1] 的前面数据的下标
        //
        // // 给 insertVal 找到插入的位置
        // while (true) {
        //     // 保证在给 insertVal 找插入位置时，不越界，此时所要插入的位置索引为 0
        //     if (insertIndex < 0)
        //         break;
        //     // 找到插入位置
        //     if (insertVal > arr[insertIndex])
        //         break;
        //     arr[insertIndex + 1] = arr[insertIndex]; // arr[insertIndex] 后移
        //     insertIndex--;
        // }
        //
        // // 当退出 while 循环时，说明插入的位置找到，insertIndex + 1
        // arr[insertIndex + 1] = insertVal;
        //
        // System.out.println("第 2 轮插入");
        // System.out.println(Arrays.toString(arr));
        //
        // // 第 3 轮：{34, 101, 119, 1} => {1, 34, 101, 119}
        //
        // // 定义待插入的数
        // insertVal = arr[3];
        // insertIndex = 3 - 1; // arr[1] 的前面数据的下标
        //
        // // 给 insertVal 找到插入的位置
        // while (true) {
        //     // 保证在给 insertVal 找插入位置时，不越界，此时所要插入的位置索引为 0
        //     if (insertIndex < 0)
        //         break;
        //     // 找到插入位置
        //     if (insertVal > arr[insertIndex])
        //         break;
        //     arr[insertIndex + 1] = arr[insertIndex]; // arr[insertIndex] 后移
        //     insertIndex--;
        // }
        //
        // // 当退出 while 循环时，说明插入的位置找到，insertIndex + 1
        // arr[insertIndex + 1] = insertVal;
        //
        // System.out.println("第 3 轮插入");
        // System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     *
     * @param arr 原数组
     */
    public void insertSort(int[] arr) {

        int insertVal; // 待插入数据
        int insertIndex; // 初始为有序部分最后一个数据的索引

        for (int i = 1; i < arr.length; i++) {

            insertVal = arr[i];
            insertIndex = i - 1;

            // 将比待插入元素大的数据都后移一位
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; // 后移一位
                insertIndex--; // 索引前移
            }

            // 插入待插入数据
            // if (insertIndex != i - 1) // 判断是否需要赋值
            arr[insertIndex + 1] = insertVal;
        }
    }
}
