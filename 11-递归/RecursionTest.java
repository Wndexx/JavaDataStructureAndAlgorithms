package com.wndexx.recursion;

import org.junit.Test;

/**
 * @author wndexx
 * @create 2022-03-22 11:16
 */
public class RecursionTest {
    @Test
    public void test1() {
        // 通过打印问题，回顾递归的调用机制
        show(4);
    }

    public void show(int n) {
        if (n > 2) {
            show(n - 1);
        }
        System.out.println("n = " + n);
    }

    @Test
    public void test2() {
        int factorial = factorial(3);
        System.out.println(factorial);
    }

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }

}
