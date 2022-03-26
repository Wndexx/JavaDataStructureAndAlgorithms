package com.wndexx.sort;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author wndexx
 * @create 2022-03-24 14:47
 */
public class ShellSort {
    /**
     * 测试希尔排序
     */
    @Test
    public void test1() {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试希尔排序的速度
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

        shellSort(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    @Test
    public void testShelSort0() {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // shellSort0(arr);
        shellSort1(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test3() {
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

        // shellSort0(arr);
        shellSort1(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    /**
     * 使用逐步推导的方式编写希尔排序
     * 交换法
     *
     * @param arr 原数组
     */
    public void shellSort0(int[] arr) {

        // // 第 1 轮
        // // 因为第 1 轮，是将 10 个数据分成 5 组
        // for (int i = 5; i < arr.length; i++) {
        //     // 遍历各组中所有的元素（共有 5 组，每组有 2 个元素），步长 5
        //     for (int j = i - 5; j >= 0; j -= 5) {
        //         // 如果当前元素大于加上步长后的那个元素，说明交换
        //         if (arr[j] > arr[j + 5]) {
        //             int temp = arr[j];
        //             arr[j] = arr[j + 5];
        //             arr[j + 5] = temp;
        //         }
        //     }
        // }
        // System.out.println(Arrays.toString(arr));
        //
        // // 第 2 轮
        // // 因为第 2 轮，是将 10 个数据分成 5 / 2 = 2 组
        // for (int i = 2; i < arr.length; i++) {
        //     // 遍历各组中所有的元素（共有 5 组，每组有 2 个元素），步长 5
        //     for (int j = i - 2; j >= 0; j -= 2) {
        //         // 如果当前元素大于加上步长后的那个元素，说明交换
        //         if (arr[j] > arr[j + 2]) {
        //             int temp = arr[j];
        //             arr[j] = arr[j + 2];
        //             arr[j + 2] = temp;
        //         }
        //     }
        // }
        // System.out.println(Arrays.toString(arr));
        //
        // // 第 3 轮
        // // 因为第 3 轮，是将 10 个数据分成 2 / 2 = 1 组
        // for (int i = 1; i < arr.length; i++) {
        //     // 遍历各组中所有的元素（共有 5 组，每组有 2 个元素），步长 5
        //     for (int j = i - 1; j >= 0; j -= 1) {
        //         // 如果当前元素大于加上步长后的那个元素，说明交换
        //         if (arr[j] > arr[j + 1]) {
        //             int temp = arr[j];
        //             arr[j] = arr[j + 1];
        //             arr[j + 1] = temp;
        //         }
        //     }
        // }
        // System.out.println(Arrays.toString(arr));

        // 根据前面的逐步分析，使用循环处理
        int count = 0; // 计数器
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素（共 gap 组），步长 gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素小于等于加上步长后的那个元素，说明加上步长后的元素也大于该组前面的所有元素，可直接跳出内层循环
                    if (arr[j] <= arr[j + gap])
                        break;
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    int temp = arr[j];
                    arr[j] = arr[j + gap];
                    arr[j + gap] = temp;
                }
            }
            // System.out.println("第 " + (++count) + " 轮排序");
            // System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 使用逐步推导的方式编写希尔排序
     * 移动法
     * 对交换式的希尔排序进行优化 -> 移位法
     *
     * @param arr 原数组
     */
    public void shellSort1(int[] arr) {
        // 增量 gap ，并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    // 移动
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                // 当退出 while ，就给 temp 找到了插入的位置
                arr[j] = temp;
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param arr 原数组
     */
    public void shellSort(int[] arr) {
        int step = arr.length / 2; // 定义步长
        while (true) {
            if (step == 0)
                break;
            for (int i = 0; i < step; i++) {
                insertSortByStep(arr, i, step);
            }
            step = step / 2;
        }
    }

    /**
     * 对数组中指定步长的元素进行排序
     *
     * @param arr   原数组
     * @param index 起始索引
     * @param step  步长
     */
    private void insertSortByStep(int[] arr, int index, int step) {

        int insertVal; // 待插入数据
        int insertIndex; // 初始为有序部分最后一个数据的索引

        for (int i = index; i < arr.length; i += step) {

            insertVal = arr[i];
            insertIndex = i - step;

            // 将比待插入元素大的数据都后移 step 位
            while (insertIndex >= index && insertVal < arr[insertIndex]) {
                arr[insertIndex + step] = arr[insertIndex]; // 后移一位
                insertIndex -= step; // 索引前移
            }

            // 插入待插入数据
            arr[insertIndex + step] = insertVal;
        }
    }
}
