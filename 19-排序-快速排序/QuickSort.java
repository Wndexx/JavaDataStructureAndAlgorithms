package com.wndexx.sort1;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 快速排序
 *
 * @author wndexx
 * @create 2022-03-25 15:18
 */
public class QuickSort {

    /**
     * 测试快速排序
     */
    @Test
    public void test1() {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param arr 原数组
     */
    public void quickSort(int[] arr) {
        subSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序中的递归方法
     *
     * @param arr   原数组
     * @param start 数组中的一部分数据的起始索引
     * @param end   数组中的一部分数据的终止索引
     */
    private void subSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int pivot = arr[start];
        int low = start + 1;
        int high = end;

        while (true) {
            while (low < end && arr[low] <= pivot) {
                low++;
            }
            while (high > start && arr[high] >= pivot) {
                high--;
            }
            if (low >= high)
                break;
            reverse(arr, low, high);
        }

        reverse(arr, start, high);

        subSort(arr, start, high - 1);
        subSort(arr, high + 1, end);
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


    @Test
    public void test0() {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        // int[] arr = {-3, 7, 4, 6, 8, 10, 9};
        quickSort0(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test00() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成 [0, 8000000) 之间的随机数
        }

        LocalDateTime before = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String beforeStr = pattern.format(before);
        System.out.println("排序前的时间是：" + beforeStr);

        quickSort0(arr, 0, arr.length - 1);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    public void quickSort0(int[] arr, int left, int right) {
        if (left > right)
            return;

        int l = left; // 左下标
        int r = right; // 右下标
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; // 临时变量，作为交换时使用

        // while 循环的目的是让比 pivot 值小的放到左边，比 pivot 值大的放到右边
        while (l < r) {
            // 在 pivot 的左边一直找，找到大于等于 pivot 的值
            while (arr[l] < pivot) {
                l++;
            }
            // 在 pivot 的右边一直找，找到小于等于 pivot 值
            while (arr[r] > pivot) {
                r--;
            }

            // 如果 l >= r 成立，说明 pivot 的左右两边的值，已经按照左边全部小于等于 pivot 的值；右边全部是大于等于 pivot 的值
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            /*
               不能合成一块，比如 2，6，4，6，7，8，9

             */
            // if (arr[l] == pivot && arr[r] == pivot) {
            //     l++;
            //     r--;
            // }

            // 如果交换完后，发现 arr[l] == pivot ，r--
            if (arr[l] == pivot) {
                r--;
            }

            // 如果交换完后，发现 arr[r] == pivot ，l++
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r，必须 l++，r--，否则会出现栈溢出；
        // 这个判断可以省略，直接进行 l++，r-- 即可
        if (l == r) {
            l++;
            r--;
        }

        // 向左递归
        quickSort0(arr, left, r);

        // 向右递归
        quickSort0(arr, l, right);
    }
}
