package com.wndexx.stack1;

import org.junit.Test;

/**
 * @author wndexx
 * @create 2022-03-20 15:21
 */
/*
    中缀表达式
*/
public class Calculator {

    @Test
    public void test1() {
        // 表达式
        String expression = "70+2*6-4";

        // 数栈
        ArrayStack numStack = new ArrayStack(10);

        // 符号栈
        ArrayStack operStack = new ArrayStack(10);

        // 定义需要的相关变量
        int index = 0; // 用于扫描表达式

        int num1 = 0;
        int num2 = 0;

        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到的 char 保存到 ch

        String keepNum = ""; // 用于拼接多位数

        // 开始 while 循环的扫描 expression
        while (true) {
            // 依次得到 expression 的每一个字符
            ch = expression.charAt(index);

            try {
                // 判断 ch 是什么，然后做相应的处理
                if (!operStack.isOper(ch)) {  // 如果是数，直接入数栈
                    // numStack.push(ch - 48); // '1' -48 = 1
                    // continue;

                    // 1. 当处理多位数时，不能发现是一个数就立即入栈，因为可能是多位数
                    // 2. 在处理数时，需要向 expression 的 index 后再看一位，如果是数就继续扫描，如果是符号才入栈
                    // 3. 因此我们需要定义一个字符串变量，用于拼接

                    // 处理多位数
                    keepNum += ch;

                    // 判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    // 注意是看后一位，不是 index++
                    // 如果 ch 已经是 expression 的最后一位，就直接入栈
                    if (index == expression.length() - 1 || operStack.isOper(expression.charAt(index + 1))) { //如果后一位是运算符，则入栈 keepNum = "123"
                        numStack.push(Integer.parseInt(keepNum));
                        // 重要！！！清空 keepNum
                        keepNum = "";
                    }
                    continue;
                }

                // 判断当前的符号栈是否为空
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                    continue;
                }

                if (operStack.priority(ch) > operStack.priority(operStack.peek())) {
                    operStack.push(ch);
                    continue;
                }

                // 如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中 pop 出两个数，
                // 再从符号栈中 pop 出一个符号，进行运算，将运算的结果加入数栈，把当前的操作符加入符号栈
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = numStack.cal(num1, num2, oper);
                // 把运算的结果入数栈
                numStack.push(res);
                // 把当前的操作符入符号栈
                operStack.push(ch);
            } finally {
                // 让 index + 1，并判断是否扫描到 expression 最后
                index++;
                if (index >= expression.length())
                    break;
            }
        }

        // 当表达式扫描完毕，就顺序地从数栈和符号栈 pop 出相应的数和符号，进行运算
        while (true) {
            // 如果符号栈为空，则计算结束，此时数栈中只有一个数字，即为结果
            if (operStack.isEmpty())
                break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);

            // 把运算的结果入数栈
            numStack.push(res);
        }

        // 将数栈的最后数，pop 出，就是结果
        res = numStack.pop();
        System.out.printf("表达式 %s = %d\n", expression, res);
    }
}
