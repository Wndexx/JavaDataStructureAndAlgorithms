package com.wndexx.linkedlist;

import java.util.Stack;

/**
 * @author wndexx
 * @create 2022-03-18 19:16
 */
// 演示栈 Stack 的基本使用
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        // 出栈
        while (stack.size() > 0)
            System.out.println(stack.pop()); // pop() 就是将栈顶的数据取出

    }
}
