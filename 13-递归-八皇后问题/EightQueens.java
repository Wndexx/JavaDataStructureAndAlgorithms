package com.wndexx.recursion;

import org.junit.Test;

/**
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。
 * 该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 *
 * @author wndexx
 * @create 2022-03-22 17:48
 */
public class EightQueens {
    /**
     * 测试
     */
    @Test
    public void test() {
        check(0);
        System.out.printf("一共有 %d 种解法\n",count);
        System.out.printf("一共判断冲突 %d 次\n",judgeCount);
    }

    /**
     * 表示共有多少个皇后
     */
    private int max = 8;

    /**
     * 记录解法个数
     */
    private int count = 0;

    /**
     * 记录 judge 次数
     */
    private int judgeCount = 0;

    /**
     * 保存皇后存放位置的结果，比如 arr = {0, 4, 7, 5, 2, 6, 1, 3}
     */
    private int[] array = new int[max];

    /**
     * 编写一个方法，放置第 n 个皇后
     * 特别注意：check 是每一次递归时，进入到 check 中都有  for (int i = 0; i < max; i++)，因此会有回溯
     *
     * @param n 表示第 n 个皇后，从 0 开始
     */
    private void check(int n) {
        if (n == max) {// n = 8时，其实 8 个皇后已经放好
            print();
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后，放到该行的第 1 列
            array[n] = i;
            // 判断当放置第 n 个皇后到 i 列时，是否冲突
            if (judge(n)) { // 不冲突
                // 接着放 n+1 个皇后，即开始递归
                check(n + 1);
            }
            // 如果冲突，就继续执行 array[n] = i; 即将第 n 个皇后放置在本行的下一列
        }
    }

    /**
     * 判断第 n 个皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第 n 个皇后，从 0 开始
     * @return true 不冲突；false 冲突
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++)
            // 1. array[i] == array[n] 表示判断第 n 个皇后是否和前面的 n-1 个皇后在同一列
            // 2. Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第 n 个皇后是否和第 i 个皇后是否在同一斜线
            // 3. 判断是否在同一行，没有必要判断，n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i]))
                return false;
        return true;
    }

    /**
     * 输出皇后摆放的位置
     */
    private void print() {
        count++;
        for (int ele : array) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
}
