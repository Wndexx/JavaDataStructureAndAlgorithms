package com.wndexx.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划算法--背包问题
 *
 * @author wndexx 2022-04-13 10:02
 */
public class KnapsackProblem {
    /**
     * 测试背包问题
     */
    @Test
    public void test1() {
        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] val = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 物品的个数
        int n = val.length;

        // 创建二维数组，v[i][j] 表示前 i 个物品中能够装入容量为 j 的背包的最大价值
        int[][] v = new int[n + 1][m + 1];

        // 记录商品的情况，path[i][j] 表示讨论前 i 个物品放入容量为 j 的背包时，第 i 个物品是否放入背包
        // 如果放入，为 1；否则，为 0
        int[][] path = new int[n + 1][m + 1];

        // 1. 初始化第一行和第一列，可以不处理，默认为 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; // 将第一列设置为 0
        }
        Arrays.fill(v[0], 0); // 将第一行设置为 0
        // for (int i = 0; i < v[0].length; i++) {
        //     v[0][i] = 0; // 将第一行设置为 0
        // }

        // 2. 动态规划处理
        for (int i = 1; i < v.length; i++) { // 不处理第一行
            for (int j = 1; j < v[0].length; j++) { // 不处理第一列
                // 公式
                if (j < w[i - 1]) {// 因为循环从 1 开始，但是 w[] 和 val[] 数组第一个元素从 0 开始
                    v[i][j] = v[i - 1][j];
                } else {
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录商品存放到背包的情况，不能直接使用上面的公式，需要使用 if-else 来处理
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 将当前的情况记录到 path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 输出 v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }

        // 输出最后放入的商品
        // for (int i = 0; i < path.length; i++) {
        //     for (int j = 0; j < path[i].length; j++) {
        //         if (path[i][j] == 1)
        //             System.out.printf("第 %d 个商品放入背包\n", i);
        //     }
        // }

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }

        int i = path.length - 1; // 行的最大下标
        int j = path[0].length - 1; // 列的最大下标
        while (i > 0 && j > 0) { // 从 path 的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第 %d 个商品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
