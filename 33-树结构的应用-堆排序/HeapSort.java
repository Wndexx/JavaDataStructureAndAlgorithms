package com.wndexx.tree.heapsort;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author wndexx 2022-04-03 10:18
 */
public class HeapSort {

    /**
     * 测试堆排序
     */
    @Test
    public void testHeapSort0() {
        // int[] arr = {4, 6, 8, 5, 9};
        int[] arr = {4, 6, 8, 5, 9};
        heapSort0(arr);
    }

    /**
     * 测试堆排序速度
     * O(nlogn)
     */
    @Test
    public void testHeapSort00() {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成 [0, 8000000) 之间的随机数
        }

        LocalDateTime before = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String beforeStr = pattern.format(before);
        System.out.println("排序前的时间是：" + beforeStr);

        heapSort0(arr);

        LocalDateTime after = LocalDateTime.now();
        String afterStr = pattern.format(after);
        System.out.println("排序后的时间是：" + afterStr);
    }

    /**
     * 堆排序
     */
    public void heapSort0(int[] arr) {
        // System.out.println("堆排序");

        // 分布完成
        // adjustHeap(arr, 1, arr.length);
        // System.out.println("第 1 次 " + Arrays.toString(arr)); // 4 9 8 5 6
        // adjustHeap(arr,0, arr.length);
        // System.out.println("第 2 次 " + Arrays.toString(arr)); // 9 6 8 5 4

        // 完成最终代码
        // 1. 将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 2. 将堆顶元素与末尾元素交换，将最大元素 "沉" 到数组末端
        // 3. 重新调整结构，使其满足对定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整 + 交换步骤，直到整个序列有序
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // ！！！经过第一次构建大顶堆，后面只需调整第一个元素就可以了，因为其他位置是符合大顶堆的要求的
            adjustHeap(arr, 0, j);
        }

        // System.out.println("数组 =  " + Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * 功能：将 i 对应的二叉树调整成大顶堆
     * 举例：{4, 6, 8, 5, 9} => i = 1 => {4, 9, 8, 5, 6}
     * 如果再次调用 adjustHeap，传入的是 i = 0 => {9, 6, 8, 5, 4}
     *
     * @param arr 待调整的数组
     * @param i   表示非叶子结点在数组中的索引
     * @param len 表述对多少个元素进行调整，len 是在逐渐减少
     */
    public void adjustHeap(int[] arr, int i, int len) {
        // 先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        // 开始调整
        // 1. k = i * 2 + 1，k 是 i 结点的左子结点
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len && arr[k] < arr[k + 1]) { // 说明左子结点的值小于右子结点的值
                k++; // k 指向右子结点
            }
            if (arr[k] > temp) { // 如果子结点大于父结点
                arr[i] = arr[k]; // 把较大的值赋给当前结点
                i = k;  // !!! i 指向 k ，继续循环比较
            } else {
                break; // 因为这里非叶子结点是从左至右，从下至上进行调整的，所以可以直接 break
            }
        }
        // 当 for 循环结束后，已经将以 i 为根结点的树的最大值放在了根结点的位置（局部）
        arr[i] = temp; // 将 temp 值放到调整后的位置
    }

    // ======================================= 以下写法极度耗时 ===========================================

    /**
     * 测试堆排序
     */
    @Test
    public void testHeapSort() {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序，升序排列，选择大顶堆
     *
     * @param arr 待排序数组
     */
    public void heapSort(int[] arr) {
        // System.out.println("堆排序");
        int len = arr.length;
        while (len > 1) {
            maxHeap(arr, len);
            // System.out.println(Arrays.toString(arr));
            reverse(arr, 0, len - 1);
            len--;
        }
    }

    /**
     * 构造大顶堆
     *
     * @param arr 顺序存储二叉树的数组
     * @param len 表明当前只考虑数组从 0 到 len-1 的元素
     */
    private void maxHeap(int[] arr, int len) {
        int k = 1;
        int j = 0;
        // ！！！！经过第一次构建大顶堆，后面只需调整第一个元素就可以了，因为其他位置是符合大顶堆的要求的
        // ！！！！所以这里的循环从第二次构建大顶堆开始就会出现很多的无用操作，极度耗时
        while (j <= len / 2 - 1) {
            int i = len / 2 - 1;
            while (i >= j) {
                int subMaxIndex = getSubMaxIndex(arr, i, len);
                reverse(arr, i, subMaxIndex);
                i--;
            }
            j = k++ * 2 - 1;
        }
    }

    /**
     * 获取一个结点和左右结点的最大值的索引
     *
     * @param arr 顺序存储二叉树的数组
     * @param i   指定结点的索引
     * @return 指定结点和左右结点的最大值的索引
     */
    private int getSubMaxIndex(int[] arr, int i, int len) {
        int maxIndex = i;
        int leftIndex = 2 * i + 1;
        if (leftIndex < len && arr[maxIndex] < arr[leftIndex])
            maxIndex = leftIndex;
        int rightIndex = 2 * i + 2;
        if (rightIndex < len && arr[maxIndex] < arr[rightIndex])
            maxIndex = rightIndex;
        return maxIndex;
    }

    /**
     * 交换数组的两个元素的位置
     *
     * @param arr 指定数组
     * @param i   索引
     * @param j   索引
     */
    private void reverse(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
