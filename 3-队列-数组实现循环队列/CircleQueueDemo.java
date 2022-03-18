package com.wndexx.queue;

import java.util.Scanner;

/**
 * @author wndexx
 * @create 2022-03-17 14:41
 */
/*
    循环队列

        1. 队列的头尾相接的顺序存储结构称为循环队列

        2. 约定：front 指向队头元素，rear 指向队尾元素的下一个位置，这样当 front == rear 时，此队列不是还剩一个元素，而是空队列。初始时，front = rear = 0。

        3. 数组模拟环形队列示意图：（数组模拟环形队列1.jpg，数组模拟环形队列2.jpg）

            说明：

                (1) 当队列满时，条件是 (rear + 1) % maxSize == front

                (2) 当队列为空时，条件是 rear == front

                (3) 队列中有效的数据的个数为：(rear + maxSize - front) % maxSize

                    分析：- 当 rear > front 时，队列的长度为 rear - front

                          - 当 rear < front 时，队列长度分为两段，一段是 (maxSize-1) - front + 1 = maxSize - front；

                            另一段是 rear - 0 = rear。加在一起，队列长度为 rear + maxSize - front

                          - 因此通用的计算队列长度公式为：(rear + maxSize - front) % maxSize

*/
public class CircleQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例");
        // 创建一个队列
        CircleQueue queue = new CircleQueue(4); // 说明：这里设置 4，其队列的有效数据最大是 3
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

class CircleQueue {
    private int maxSize; // 表示数组的最大容量
    // front 指向队头元素，初始值为 0
    private int front;
    // rear 指向队尾元素的下一个位置，初始值为 0
    private int rear;
    private int[] arr; // 该数组用以存放数据，模拟队列

    public CircleQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    // 1. 判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 2. 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 3. 添加数据到队列
    public void addQueue(int n) {
        // (1) 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，无法添加数据");
            return;
        }
        // (2) 直接将数据加入
        arr[rear] = n;
        // (3) 将 rear 后移，这里必须考虑取模 %
        rear = (rear + 1) % maxSize;
    }

    // 4. 获取队列的数据，出队列
    public int getQueue() {
        // (1) 判断队列是否空
        if (isEmpty())
            throw new RuntimeException("队列为空，无法取出数据");
        // (2) 先把 front 对应的值保存到一个临时变量
        int value = arr[front];
        // (3) 将 front 后移
        front = (front + 1) % maxSize;
        // (4) 将临时保存的变量返回
        return value;
    }

    // 5. 显示队列的所有数据
    public void showQueue() {
        // (1) 判断队列是否空
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        // (2) 遍历：从 front 开始遍历，遍历多少个元素
        for (int i = front; i < front + getSize(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 6. 求出当前队列有效数据的个数
    public int getSize() {
        return (rear + maxSize - front) % maxSize;
    }

    // 7. 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空，没有数据");
        return arr[front];
    }
}





























