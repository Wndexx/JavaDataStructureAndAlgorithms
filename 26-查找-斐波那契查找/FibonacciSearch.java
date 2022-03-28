package com.wndexx.search;

import org.junit.Test;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * @author wndexx
 * @create 2022-03-28 17:24
 */
public class FibonacciSearch {
    /**
     * 测试斐波那契查找
     */
    @Test
    public void test1() {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 1234);
        System.out.println(index);
    }

    /**
     * 斐波那契查找
     *
     * @param arr 原数组,从小到大顺序
     * @param val 待查找数据
     * @return 存在, 返回索引;不存在,返回 -1
     */
    public int fibonacciSearch(int[] arr, int val) {
        int index = 1;
        while (arr.length > fibonacci(index) - 1)
            index++;
        int[] newArr = Arrays.copyOf(arr, fibonacci(index) - 1);
        Arrays.fill(newArr, arr.length, fibonacci(index) - 1, arr[arr.length - 1]);
        return Math.min(subSort(newArr, 0, newArr.length - 1, val, index), arr.length - 1);
    }

    /**
     * 斐波那契查找的递归方法
     *
     * @param arr   原数组,从小到大顺序
     * @param left  左索引
     * @param right 右索引
     * @param val   待查找数据
     * @param index 数组长度对应斐波那契数列中的位置
     * @return 存在，返回索引；不存在，返回 -1
     */
    private int subSort(int[] arr, int left, int right, int val, int index) {
        if (left > right || val < arr[left] || val > arr[right])
            return -1;
        int mid = left + fibonacci(--index) - 1;
        if (val < arr[mid])
            return subSort(arr, left, mid - 1, val, index);
        if (val > arr[mid])
            return subSort(arr, mid + 1, right, val, --index);
        return mid;
    }

    /**
     * 获得斐波那契数列索引为 i 的值
     *
     * @param i 索引，从 1 开始
     * @return 斐波那契数列索引为 i 的值
     */
    private int fibonacci(int i) {
        if (i == 1 || i == 2)
            return 1;
        return fibonacci(i - 2) + fibonacci(i - 1);
    }

    @Test
    public void test2() {
        int[] arr = new int[3];
        Arrays.fill(arr, 0, 1, 10);
        System.out.println(Arrays.toString(arr));
    }

    public int maxSize = 20;

    @Test
    public void test3() {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr, 189));
    }

    /**
     * 使用非递归的方法编写斐波那契查找算法
     *
     * @param a   数组
     * @param key 待查找关键码
     * @return 返回对应的下标，如果没有 -1
     */
    public int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 表示斐波那契数列恰好大于数组长度 + 1 的元素的下标
        int mid = 0; // 存放 mid 值
        int[] f = fib(); // 获取到斐波那契数列
        // 获取到斐波那契数列恰好大于数组长度 + 1 的元素的下标
        while (a.length > f[k] - 1) {
            k++;
        }
        // 因为 f[k] 可能大于 a 的长度，因为需要使用 Arrays 类，构造一个新的数组，并指向 temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(a, f[k] - 1);
        // 使用 a 最后的数填充 temp
        // 举例：temp = {1, 8, 10, 89, 1000, 1234, 0, 0, 0} => {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        // 使用 while 来循环处理，找到 key
        while (low <= high) { // 只要这个条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { // 说明应该继续向数组的前面查找（左边）
                high = mid - 1;
                // 为什么是 k--
                // 说明：
                // 1. 全部元素 = 前面元素 + 中间元素 + 后面元素
                // 2. f[k]-1 = （f[k-1]-1）+ 1 + (f[k-2]-1)
                // 3. 因为前面有 f[k-1]-1 个元素，所以可以继续拆分 (f[k-1]-1) = (f[k-2]-1)+ 1 + (f[k-3]-1)
                // 4. 即在 f[k-1] 的前面继续查找 k--
                // 即下次循环 mid = low + f[k-1-1] - 1
                k--;
            } else if (key > temp[mid]) { // 说明应该继续向数组的后面查找（右边）
                low = mid + 1;
                // 为什么是 k-=2
                // 说明
                // 1.  全部元素 = 前面元素 + 后面元素
                // 2. f[k]-1 = （f[k-1]-1）+ 1 + (f[k-2]-1)
                // 3. 因为后面右 f[k-2] 个元素，所以可以继续拆分 (f[k-2]-1) = (f[k-3]-1)+ 1 + (f[k-4]-1)
                // 4. 即在 f[k-2] 的卡面进行查找 k-=2
                // 5. 即下次循环 mid = low + f[k-2-1] -1
                k -= 2;
            } else { // 找到
                // 需要确定，返回的是那个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    // 因为 mid + F(k-1) -1，需要使用到斐波那契数列，因此需要获取一个斐波那契数列
    // 非递归方法得到一个斐波那契数列
    public int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
