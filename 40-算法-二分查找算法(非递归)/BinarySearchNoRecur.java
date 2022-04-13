package com.wndexx.binarysearchnorecursion;

import org.junit.Test;

/**
 * 二分查找非递归算法
 *
 * @author wndexx 2022-04-12 16:46
 */
public class BinarySearchNoRecur {

    /**
     * 测试二分查找非递归算法
     */
    @Test
    public void test1() {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, -8);
        System.out.println(index);
    }

    /**
     * 二分查找的非递归实现
     *
     * @param arr    带查找的数组，arr 是升序排列
     * @param target 需要查找的数
     * @return 返回对应下标，-1 表示没有找到
     */
    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) { // 说明可以继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                right = mid - 1; // 需要向左边查找
            else
                left = mid + 1; // 需要向右边查找
        }
        return -1;
    }
}
