package com.wndexx.stack;

import java.util.Scanner;

/**
 * @author wndexx
 * @create 2022-03-20 13:38
 */
public class LinkStackDemo {
    public static void main(String[] args) {

        LinkStack<Integer> stack = new LinkStack<>();

        String key = "";
        boolean loop = true; // 控制是否退出菜单

        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：数据入栈");
            System.out.println("pop：数据出栈");

            System.out.println("输入操作");
            key = scanner.next();

            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
            }
        }
        System.out.println("程序退出");
    }
}
