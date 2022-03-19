package com.wndexx.linklist3;

import org.junit.Test;

/**
 * @author wndexx
 * @create 2022-03-19 17:35
 */
/*
    Josephus  问题

        Josephus  问题为：设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数到 m 的那个人出列，它的下一位又从 1 开始报数，

        数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。

    提示

    用一个不带头结点的循环链表来处理 Josephus 问题：先构成一个有 n 个结点的单循环链表，然后由 k 结点起从 1 开始计数，计到 m 时，

    对应结点从链表中删除，然后再从被删除结点的下一个结点又从 1 开始计数，直到最后一个结点从链表中删除算法结束。

*/
public class Josephus {
    @Test
    public void test1() {

        // 测试构建环形链表，和遍历是否正确
        CircularSinglyLinkedList circularSinglyLinkedList = new CircularSinglyLinkedList();
        circularSinglyLinkedList.addBoy(5); // 加入 5 个小孩结点
        circularSinglyLinkedList.showBoy();
    }

    @Test
    public void test2() {

        CircularSinglyLinkedList circularSinglyLinkedList = new CircularSinglyLinkedList();
        circularSinglyLinkedList.addBoy(5); // 加入 5 个小孩结点
        // 测试小孩出圈是否正确
        circularSinglyLinkedList.countBoy(1,2,5);
    }


}
