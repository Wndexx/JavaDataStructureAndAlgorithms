package com.wndexx.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 哈希表
 *
 * @author wndexx
 * @create 2022-03-29 15:51
 */
public class HashTable<T> {
    List<LinkedList<T>> lists = new ArrayList<>();

    public HashTable() {
        for (int i = 0; i < 7; i++) {
            lists.add(new LinkedList<>());
        }
    }

    /**
     * 添加元素
     *
     * @param t 要添加的元素
     */
    public void add(T t) {
        lists.get(hash(t)).add(t);
    }

    /**
     * 删除元素
     *
     * @param t 要删除的元素
     */
    public void delete(T t) {
        LinkedList<T> list = lists.get(hash(t));
        list.delete(t);
    }

    public void find() {

    }

    /**
     * 遍历哈希表
     */
    public void list() {
        for (LinkedList<T> l : lists) {
            l.list();
        }
    }

    /**
     * 散列函数，获取添加数据在数组中的位置
     *
     * @return 数据在数组中的位置
     */
    public int hash(Object obj) {
        return obj.hashCode() % 7;
    }

    static class LinkedList<T> {
        Node<T> head = new Node<>();

        void add(T t) {
            Node<T> node = new Node<>(t);
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }

        void delete(T t) {
            Node<T> temp = head;
            boolean flag = false;
            while (temp.next != null) {
                if (temp.next.equals(new Node<>(t))) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next = temp.next.next;
            }
        }

        void list() {
            if (head.next == null) {
                return;
            }
            Node<T> temp = head.next;
            while (temp != null) {
                // 判断是否到链表最后
                // 输出结点的信息
                System.out.println(temp);
                // 将 temp 后移
                temp = temp.next;
            }
        }

        static class Node<T> {
            T t;
            Node<T> next;

            Node() {

            }

            Node(T t) {
                this.t = t;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node<?> node = (Node<?>) o;
                return Objects.equals(t, node.t);
            }

            @Override
            public int hashCode() {
                return Objects.hash(t, next);
            }

            @Override
            public String toString() {
                return t.toString();
            }
        }
    }
}





