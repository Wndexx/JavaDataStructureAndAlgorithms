package com.wndexx.stack;

/**
 * @author wndexx
 * @create 2022-03-20 13:22
 */
public class LinkStack<T> {
    private Node top;

    public LinkStack() {

    }

    public void push(T t) {
        Node node = new Node(t);
        node.next = top;
        top = node;
    }

    public T pop() {
        if (isEmpty())
            throw new RuntimeException("空栈");
        Node temp = top;
        top = top.next;
        temp.next = null;
        return temp.t;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，没有数据");
            return;
        }
        Node temp = top;
        while (true) {
            if (temp == null)
                break;
            System.out.println(temp.t);
            temp = temp.next;
        }
    }

    private class Node {
        private T t; // 存储数据
        public Node next; // 指向下一个元素

        public Node(T t) {
            this.t = t;
        }
    }
}
