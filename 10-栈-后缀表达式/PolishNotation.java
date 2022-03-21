package com.wndexx.stack3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author wndexx
 * @create 2022-03-21 12:22
 */
public class PolishNotation {

    @Test
    public void test1() {

        // 完成将一个中缀表达式转成后缀表达式（逆波兰表达式）的功能
        // 说明
        // 1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        // 2. 因为直接对字符串操作不方便，因此，先将 "1+((2+3)*4)-5" => 中缀表达式对应的 List
        //    即 "1+((2+3)*4)-5" => ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        // 3. 将得到的中缀表达式对应的 List => 后缀表达式对应的 List
        //    即 [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => [1, 2, 3, +, 4, *, +, 5, -]

        String expression = "1+((2+3)*4)-5";

        // 先定义一个逆波兰表达式
        // (3+4)*5-6 => 34+5*6-
        // 说明：为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";

        // 测试2：(30+4)*5-6 => 30 4 + 5 * 6 -
        suffixExpression = "30 4 + 5 * 6 -";

        // 测试3：4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        // 思路
        // 1. 先将 "3 4 + 5 * 6 - " 放到 ArrayList 中
        // 2. 将 ArrayList 传给一个方法，遍历 ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("list = " + list);

        int res = calculator(list);
        System.out.println("计算的结果是：" + res);
    }

    @Test
    public void test2() {
        String expression = "11+((2+3)*4)-51";
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println(list1);
    }

    @Test
    public void test3() {
        String expression = "11+((2+3)*4)-51";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        int res = calculator(suffixExpressionList);
        System.out.println(res);
    }

    // 将中缀表达式转成对应的 List
    public List<String> toInfixExpressionList(String s) {
        // 定义一个 List，存放中缀表达式对应的数据
        List<String> list = new ArrayList<>();

        int i = 0; // 这是一个指针，用于遍历中缀表达式字符串
        String str = ""; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到 c

        do {
            c = s.charAt(i);
            // 如果 c 是一个非数字，就需要计入到 list
            if (!isNumber(c)) {
                list.add(String.valueOf(c));
                continue;
            }
            // 如果是一个数，需要考虑多位数的问题
            str += c;
            if (i >= s.length() - 1 || !isNumber(s.charAt(i + 1))) {
                list.add(str);
                str = "";
            }
        } while (++i < s.length());

        return list;
    }

    // 判断一个字符是否是数字
    private boolean isNumber(char c) {
        return c >= 48 && c <= 57;
    }

    /*
       将得到的中缀表达式对应的 List => 后缀表达式对应的 List

       即 [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => [1, 2, 3, +, 4, *, +, 5, -]

        1. 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；

        2. 从左至右扫描中缀表达式；

            1) 遇到操作数时，将其压 s2；

            2) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：

                (1) 如果 s1 为空，或栈顶运算符为左括号 "("，则直接将此运算符入栈；

                (2) 否则，若优先级比栈顶运算符的高，也将运算符压入 s1；

                (3) 否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到 (1) 与 s1 中新的栈顶运算符相比较

            3) 遇到括号时：

                (1) 如果是左括号 "("，则直接压入 s1

                (2) 如果是右括号 ")"，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃

            4) 重复步骤 1) 至 3)，直到表达式的最右边

        3. 将 s1 中剩余的运算符依次弹出并压入 s2

        4. 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
    */
    public List<String> parseSuffixExpressionList(List<String> ls) {

        // 定义两个栈
        Stack<String> s1 = new Stack<>(); // 符号栈

        // 说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面还需要逆序输出，比较麻烦
        // 这里就没必要使用 Stack<String> ，可以直接使用 List<String>
        // Stack<String> s2 = new Stack<>(); // 存储中间结果的栈
        List<String> list = new ArrayList<>();

        // 遍历 ls
        for (String item : ls) {
            // 如果是一个数，加入到 list
            if (item.matches("\\d+")) {
                list.add(item);
                continue;
            }

            if (item.equals("(")) {
                s1.push(item);
                continue;
            }

            // 如果是右括号 ")"，则依次弹出 s1 栈顶的运算符，并压入 list，直到遇到左括号为止，此时将这一对括号丢弃
            if (item.equals(")")) {
                String str;
                while (!(str = s1.pop()).equals("("))
                    list.add(str);
                continue;
            }

            // 当 item 的优先级小于等于 s1 栈顶运算符的优先级，将 s1 栈顶的运算符加入到 list 中，再次转到 (1) 与 s1 中新的栈顶运算符相比较
            // 问题，缺少一个比较优先级高低的方法
            while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item))
                list.add(s1.pop());
            // 还需要将 item 压入栈中
            s1.push(item);
        }

        // 将 s1 中剩余的运算符依次弹出并加入 list
        while (s1.size() != 0)
            list.add(s1.pop());

        return list; // 注意因为是存放到 List，因此按顺序输出就是对应的后缀表达式对应的 List
    }

    // 将一个逆波兰表达式的数据和运算符依次放入到 ArrayList 中
    public List<String> getListString(String sufExpr) {
        // 将 sufExpr 分割
        return new ArrayList<>(Arrays.asList(sufExpr.split(" ")));
    }

    // 完成对逆波兰表达式的运算
    /*
        (1) 从左至右扫描，将3和4压入堆栈；
        (2) 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
        (3) 将5入栈；
        (4) 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
        (5) 将6入栈；
        (6) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    */
    public int calculator(List<String> list) {
        // 创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历 list
        for (String item : list) {
            // 这里使用正则表达式来取出数，匹配的是多位数
            if (item.matches("\\d+")) {
                stack.push(item);
                continue;
            }
            // pop 出两个数，并运算，再入栈
            int num2 = Integer.parseInt(stack.pop());
            int num1 = Integer.parseInt(stack.pop());

            int res = cal(num1, num2, item);
            // res 入栈
            stack.push(String.valueOf(res));
        }
        // 最后留在 stack 中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

    private int cal(int num1, int num2, String oper) {
        switch (oper) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new RuntimeException("无法运算");
        }
    }
}

// 编写一个类 Operation，可以返回一个运算符对应的优先级
class Operation {
    private static int AND = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    // 写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        switch (operation) {
            case "+":
                return AND;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            default:
                return -1;
        }
    }
}
