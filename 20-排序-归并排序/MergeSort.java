package com.wndexx.sort1;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 归并排序
 *
 * @author wndexx
 * @create 2022-03-26 14:19
 */
public class MergeSort {
    /**
     * 测试归并排序
     */
    @Test
    public void test1() {

        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr);
        System.out.println("归并排序后 = " + Arrays.toString(arr));
    }

    /**
     * 测试归并排序速度
     */
    @Test
    public void test2() {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成 [0, 8000000) 之间的随机数
        }

        LocalDateTime before = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String beforeStr = pattern.format(before);
        System.out.println("排序前的时间是：" + beforeStr);

        mergeSort(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    /**
     * 归并排序
     *
     * @param arr 原数组
     */
    public void mergeSort(int[] arr) {
        subSort(arr, 0, arr.length - 1, new int[arr.length]); // 归并排序需要一个额外空间
    }

    /**
     * 归并排序中的递归方法
     *
     * @param arr   原数组
     * @param left  左边索引
     * @param right 中间索引
     * @param temp  右边索引
     */
    public void subSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; // 中间的索引
            // 向左递归进行分解
            subSort(arr, left, mid, temp);
            // 向右递归进行分解
            subSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   原数组
     * @param left  左边索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // System.out.println("<-_->");
        int i = left; // 初始化 i，左边有序序列的初始索引
        int j = mid + 1; // 初始化 j，右边有序序列的初始索引
        int t = 0; // 指向 temp 数组的当前索引

        // 1. 先把左右两边（有序）的数据按照规则填充到 temp 数组，直到左右两边的有序序列有一边处理完毕为止
        while (i <= mid && j <= right) { // 继续
            // 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素，
            // 将左边的当前元素，拷贝到 temp 数组
            // 然后 t 后移，i 后移
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                // 反之，将右边有序序列的当前元素，填充到 temp 数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 2. 把有剩余数据的一边的数据依次填充到 temp
        while (i <= mid) { // 左边的有序序列还有剩余的元素，就全部填充的 temp
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) { // 右边的有序序列还有剩余的元素，就全部填充的 temp
            temp[t] = arr[j];
            t++;
            j++;
        }

        // 3. 将 temp 数组的元素拷贝到 arr
        // 注意，并不是每次都拷贝所有的
        t = 0;
        int tempLeft = left;
        // 第一次合并时 tempLeft = 0，right = 1 ；第二次合并时 tempLeft = 2，right = 3 ； 第三次合并时 tempLeft = 0，right = 3；
        // 最后一次合并时 tempLeft = 0，right = 7
        // System.out.println("tempLeft = " + tempLeft + " right = " + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
















