package com.wndexx.queue;

import java.util.Scanner;

/**
 * @author wndexx
 * @create 2022-03-17 11:21
 */
/*
    队列

        1. 介绍：

            (1) 队列是一个有序列表，可以用数组或是链表来实现。

            (2) 遵循先入先出的原则。即：先存入队列的数据，要先取出。后存入的要后取出

            (3) 数组模拟队列示意图：数组模拟队列1.jpg，数组模拟队列2.jpg

        2. 说明：

            (1) 队列的输出、输入是分别从前后端来处理，因此需要两个变量 front 及 rear 分别记录队列前后端的下标，front 会随着数据输出而改变，而 rear 则是随着数据输入而改变

            (2) maxSize 是该队列的最大容量

        3. 使用场景：银行排队

        4. 问题：

            (1) 目前数组只能使用一次，没有达到复用的效果

            (2) 将这个数组使用算法改进成一个环形队列  %(取模)

*/
public class ArrayQueueDemo {
    public static void main(String[] args) {

        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);

        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        l:
        while (true) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");

            key = scanner.next().charAt(0); // 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    break l;
            }
        }
        System.out.println("程序退出");

    }
}

// 使用数组模拟队列，编写 ArrayQueue 类
class ArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 该数组用以存放数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列第一个数据的前一个位置
        rear = -1; // 指向队列最后一个数据
    }

    // 判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，无法添加数据");
            return;
        }
        arr[++rear] = n; // rear 后移一位，在该位置上添加数据
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty())
            throw new RuntimeException("队列为空，无法取出数据");
        return arr[++front]; // front 后移一位，取出该位置的数据
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        // 遍历
        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空，没有数据");
        return arr[front + 1];
    }
}
