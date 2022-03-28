package com.wndexx.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 *
 * @author wndexx
 * @create 2022-03-27 16:05
 */
public class BinarySearch {
    /**
     * 测试二分查找
     */
    @Test
    public void test1() {
        // int[] arr = {1, 8, 10, 89, 1000, 1234};
        int[] arr = {2, 2, 3, 3, 4, 5, 6};
        int index = binarySearch(arr, 2);
        System.out.println(index);
    }

    /**
     * 递归实现二分查找
     * 注意：使用二分查找的前提是该数组有序; 返回的不一定是第一个该元素
     *
     * @param arr 原数组，从小到大有序排列
     * @param val 待找数据
     * @return 如果找到就返回下标; 如果没有找到，就返回 -1
     */
    public int binarySearch(int[] arr, int val) {
        return subSearch(arr, 0, arr.length - 1, val);
    }

    /**
     * 二分查找中的递归方法
     *
     * @param arr   原数组，从小到大有序排列
     * @param left  左索引
     * @param right 右索引
     * @param val   待找数据
     * @return 如果找到就返回下标; 如果没有找到，就返回 -1
     */
    private int subSearch(int[] arr, int left, int right, int val) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        if (val > arr[mid]) // 向右递归
            return subSearch(arr, mid + 1, right, val);
        if (val < arr[mid]) // 向左递归
            return subSearch(arr, left, mid - 1, val);
        return mid;
    }

    /**
     * 测试 binarySearchAll
     */
    @Test
    public void test2() {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};
        List<Integer> all = binarySearchAll(arr, 1001);
        // 直接赋值给没有泛型的 list 相当于将泛型擦除，当作一个普通的 ArrayList，不会报错
        // 但是不能赋值给泛型为 Object 的 list，因为不同泛型的引用之间不能相互赋值
        // List all = binarySearchAll(arr, 1000);
        System.out.println(all);
    }

    /**
     * 二分查找所有匹配元素
     * 思路分析：
     * 1. 找到 mid 值时，不要返回
     * 1.1 向 mid 索引值的左边扫描，将所有匹配的元素的下标加入到集合 ArrayList
     * 1.2 向 mid 索引值的右边扫描，将所有匹配的元素的下标加入到集合 ArrayList
     * 2. 将 ArrayList 返回
     *
     * @param arr 数组
     * @param val 待查数据
     * @return 索引的 list 集合
     */
    public List<Integer> binarySearchAll(int[] arr, int val) {
        ArrayList<Integer> list = new ArrayList<>();
        subSearch0(arr, 0, arr.length - 1, val, list);
        return list;
    }

    /**
     * 二分查找中的递归方法
     *
     * @param arr   原数组，从小到大有序排列
     * @param left  左索引
     * @param right 右索引
     * @param val   待找数据
     */
    private void subSearch0(int[] arr, int left, int right, int val, List<Integer> list) {
        if (left > right)
            return;
        int mid = (left + right) / 2;
        if (val > arr[mid]) {// 向右递归
            subSearch0(arr, mid + 1, right, val, list);
        } else if (val < arr[mid]) {// 向左递归
            subSearch0(arr, left, mid - 1, val, list);
        } else {
            // 向 mid 索引值的左边扫描，将所有匹配的元素的下标加入到集合 ArrayList
            subSearch0(arr, left, mid - 1, val, list);
            list.add(mid);
            // 向 mid 索引值的右边扫描，将所有匹配的元素的下标加入到集合 ArrayList
            subSearch0(arr, mid + 1, right, val, list);

            // 或
            // list.add(mid);
            // // 向 mid 索引值的左边扫描，将所有匹配的元素的下标加入到集合 ArrayList
            // int temp = mid - 1;
            // while (true) {
            //     if (temp < left || arr[temp] != val) // 退出
            //         break;
            //     // 否则，将 temp 放入 list
            //     list.add(temp);
            //     temp--; // temp 左移
            // }
            // // 向 mid 索引值的右边扫描，将所有匹配的元素的下标加入到集合 ArrayList
            // temp = mid + 1;
            // while (true) {
            //     if (temp > right || arr[temp] != val) // 退出
            //         break;
            //     // 否则，将 temp 放入 list
            //     list.add(temp);
            //     temp++; // temp 右移
            // }
        }
    }


}
