package com.wndexx.divideandconquer;

import org.junit.Test;

/**
 * 分治算法-汉诺塔问题
 *
 * @author wndexx 2022-04-12 17:25
 */
public class HanoiTower {
    /**
     * 测试汉诺塔问题
     */
    @Test
    public void test1() {
        hanoiTower(5, 'A', 'B', 'C');
        System.out.println("共走了 " + count + " 步");
    }

    int count;

    /**
     * 使用分治算法解决汉诺塔问题
     *
     * @param num 盘的数量
     * @param a   初始柱子
     * @param b   过渡柱子
     * @param c   目标柱子
     */
    public void hanoiTower(int num, char a, char b, char c) {
        count++;
        // 1. 如果只有一个盘
        if (num == 1) {
            System.out.println("第 1 个盘从 " + a + " -> " + c);
            return;
        }
        // 2. 如果 n >= 2，总是可以看作是 上面的盘 和 最下面的盘 两个盘
        // (1) 把上面的盘 A -> B，移动过程会使用到 C
        hanoiTower(num - 1, a, c, b);
        // (2) 把最下面的盘 A -> C
        System.out.println("第 " + num + " 个盘从 " + a + " -> " + c);
        // (3) 把 B 塔的所有的盘从 B -> C，移动过程使用到 A
        hanoiTower(num - 1, b, a, c);
    }
}
