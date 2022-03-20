package com.wndexx.stack1;

/**
 * @author wndexx
 * @create 2022-03-20 11:57
 */
// 数组模拟栈
public class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，存放数据
    private int top = -1; // 栈顶，初始化为 -1

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈 push
    public void push(int value) {
        // 判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    // 出栈 pop，将栈顶的数据返回
    public int pop() {
        // 判断栈是否空
        if (isEmpty())
            throw new RuntimeException("栈空");
        return stack[top--];
    }

    // 遍历
    // 从栈顶到栈底
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--)
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
    }

    // 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    // 数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/')
            return 1;
        if (oper == '+' || oper == '-')
            return 0;
        return -1; // 假定目前的表达式只有 + - * /
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

    // 返回当前栈顶数据，但不是 pop
    public int peek() {
        return stack[top];
    }
}
