package com.wndexx.stack2;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author wndexx
 * @create 2022-03-20 17:39
 */
public class Calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表达式：");
        String expression = scanner.next();

        // 数栈
        ArrayStack numStack = new ArrayStack(expression.length());
        // 符号栈
        ArrayStack operStack = new ArrayStack(expression.length());

        // 相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        // 用于拼接多位数
        StringBuilder keepNum = new StringBuilder();

        // 循环扫描 expression
        while (true) {
            ch = expression.charAt(index);
            try {
                // 如果是左括号，直接入栈
                if (isLeftParenthesis(ch)) {
                    operStack.push(ch);
                    continue;
                }

                // 右括号，循环判断栈顶是否是左括号，如果不是，出栈参与运算
                if (isRightParenthesis(ch)) {
                    while (!isLeftParenthesis(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = cal(num1, num2, oper);
                        // 把运算的结果入数栈
                        numStack.push(res);
                    }
                    operStack.pop();
                    continue;
                }

                if (!isOper(ch)) {  // 如果是数，直接入数栈
                    keepNum.append(ch);
                    if (index == expression.length() - 1 || isOper(expression.charAt(index + 1)) || isRightParenthesis(expression.charAt(index + 1))) {
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        // 重要！！！清空 keepNum
                        keepNum = new StringBuilder();
                    }
                    continue;
                }

                if (operStack.isEmpty()) {
                    operStack.push(ch);
                    continue;
                }

                if (priority(ch) > priority(operStack.peek())) {
                    operStack.push(ch);
                    continue;
                }

                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = cal(num1, num2, oper);
                // 把运算的结果入数栈
                numStack.push(res);
                // 把当前的操作符入符号栈
                operStack.push(ch);
            } finally {
                index++;
                if (index >= expression.length())
                    break;
            }
        }

        while (true) {
            // 如果符号栈为空，则计算结束，此时数栈中只有一个数字，即为结果
            if (operStack.isEmpty())
                break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = cal(num1, num2, oper);

            // 把运算的结果入数栈
            numStack.push(res);
        }

        // 将数栈的最后数，pop 出，就是结果
        res = numStack.pop();
        System.out.printf("表达式 %s = %d\n", expression, res);

    }

    // 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    // 数字越大，则优先级越高
    public static int priority(int oper) {
        if (oper == '*' || oper == '/')
            return 1;
        if (oper == '+' || oper == '-')
            return 0;
        if (oper == '(')
            return -1;
        return -2; // 假定目前的表达式只有 + - * /
    }

    // 判断是不是一个运算符
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public static int cal(int num1, int num2, int oper) {
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

    // 判断是不是左括号
    public static boolean isLeftParenthesis(int c) {
        return c == '(';
    }

    // 判断是不是右括号
    public static boolean isRightParenthesis(int c) {
        return c == ')';
    }
}
