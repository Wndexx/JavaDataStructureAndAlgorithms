package com.wndexx.linkedlist;

import org.junit.Test;

import java.util.Stack;

/**
 * 单链表面试题
 *
 * @author wndexx
 * @create 2022-03-18 17:40
 */
public class SinglyLinkedListDemo1 {

    // 问题一： 获取单链表的结点的个数（如果是带头结点的链表，需要去掉头结点）
    @Test
    public void testGetLength() {
        // 先创建结点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        // 按照编号的顺序将结点加入链表
        singlyLinkedList.add(hero1);
        singlyLinkedList.add(hero4);
        singlyLinkedList.add(hero2);
        singlyLinkedList.add(hero3);

        // 显示
        singlyLinkedList.list();
        System.out.println();

        // 单链表有效结点的个数
        System.out.println("单链表有效结点的个数");
        int length = getLength(singlyLinkedList.getHead());
        System.out.println(length);
    }
    // 1

    /**
     * @param head 链表的头结点
     * @return 返回有效结点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) // 空链表
            return 0;
        int len = 0;
        HeroNode cur = head.next; // 定义一个辅助指针
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    // 2
    // public static int getLength(HeroNode head) {
    //     if (head.next == null) // 空链表
    //         return 0;
    //     int len = 0;
    //     HeroNode cur = head; // 定义一个辅助指针
    //     while ((cur = cur.next) != null)
    //         len++;
    //     return len;
    // }

    // 问题二：查找单链表中的倒数第 k 个结点 【新浪面试题】
    // 思路
    // 1. 编写一个方法，接收 head 结点，同时接收一个 index
    // 2. index 表示是倒数第 index 个结点
    // 3. 先把链表从头到尾遍历，得到链表的总的长度
    // 4. 得到 length 后，从链表的第一个开始遍历 (length - index) 个，就可以得到
    // 5. 如果找到了，则返回该结点，否则返回 null

    @Test
    public void testFindLastIndexNode() {
        // 先创建结点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        // 按照编号的顺序将结点加入链表
        singlyLinkedList.add(hero1);
        singlyLinkedList.add(hero4);
        singlyLinkedList.add(hero2);
        singlyLinkedList.add(hero3);

        // 显示
        singlyLinkedList.list();
        System.out.println();

        // 测试查找单链表中的倒数第 k 个结点
        HeroNode res = findLastIndexNode(singlyLinkedList.getHead(), 1);
        System.out.println(res);
    }

    // 1
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 判断如果链表为空，返回 null
        if (head.next == null)
            return null;
        // 第一次遍历，得到链表的长度（结点个数）
        int length = getLength(head);
        // 第二次遍历 length - index 位置，就是倒数第 index 个结点
        // index 数据校验
        if (index <= 0 || index > length) {
            return null;
        }
        // 定义一个辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 2
    // public static HeroNode getNodeOfLast(SinglyLinkedList list, int k) {
    //     int index = getLength(list.getHead()) - k;
    //     if (index < 0)
    //         return null;
    //     return list.getNodeByIndex(index);
    // }

    @Test
    public void testReverseList() {
        // 先创建结点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        // 按照编号的顺序将结点加入链表
        singlyLinkedList.add(hero1);
        singlyLinkedList.add(hero4);
        singlyLinkedList.add(hero2);
        singlyLinkedList.add(hero3);

        // 显示
        singlyLinkedList.list();
        System.out.println();

        // 测试单链表的反转
        reverseList(singlyLinkedList.getHead());
        singlyLinkedList.list();
    }

    // 问题三：单链表的反转【腾讯面试题】
    // 1
    public static void reverseList(HeroNode head) {

        // (1) 如果当前链表为空，或者只有一个结点，无需反转，直接返回
        if (head.next == null || head.next.next == null)
            return;

        // (2) 定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前结点[cur]的下一个结点
        HeroNode reverseHead = new HeroNode(0, "", "");

        // (3) 从头到尾遍历原来的链表，每遍历一个结点，就将其取出，并放在新的链表的最前端
        while (cur != null) {
            next = cur.next;// 暂时保存当前结点的下一个结点，后面需要使用
            cur.next = reverseHead.next; // 将 cur 的下一个结点指向新的链表的最前端
            reverseHead.next = cur; // 新链表的头结点指向 cur
            cur = next; // 让 cur 后移
        }

        // (4) 将 head.next 指向 reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    // 2
    // public static void reverse(HeroNode head) {
    //     // 如果当前链表为空，或者只有一个结点，无需反转，直接返回
    //     if (head.next == null || head.next.next == null)
    //         return;
    //     HeroNode reverseHead = new HeroNode(0, "", "");
    //     HeroNode cur = head.next;
    //     while (cur != null) {
    //         HeroNode temp = cur;
    //         cur = cur.next;
    //         temp.next = reverseHead.next;
    //         reverseHead.next = temp;
    //     }
    //     head.next = reverseHead.next;
    // }

    // 3
    // public static void reverse(SinglyLinkedList list) {
    //     if (list.getHead().next == null)
    //         return;
    //     HeroNode head1 = new HeroNode(0, "", "");
    //     head1.next = findLastIndexNode(list.getHead(), 1);
    //     HeroNode cur = head1.next;
    //     int len = getLength(list.getHead());
    //     for (int i = 2; i <= len; i++) {
    //         cur.next = findLastIndexNode(list.getHead(), 2);
    //         cur = cur.next;
    //         cur.next = null;
    //     }
    //     list.setHead(head1);
    // }

    // 4
    // public static void reverse(SinglyLinkedList list) {
    //     SinglyLinkedList newList = new SinglyLinkedList();
    //     HeroNode cur = findLastIndexNode(list.getHead(), 1) ;
    //     newList.add(cur);
    //     int len = getLength(list.getHead());
    //     for (int i = 2; i <= len; i++) {
    //         cur = findLastIndexNode(list.getHead(), 2);
    //         cur.next = null;
    //         newList.add(cur);
    //     }
    //     list.setHead(newList.getHead());
    // }

    @Test
    public void testPrintList() {
        // 先创建结点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        // 按照编号的顺序将结点加入链表
        singlyLinkedList.add(hero1);
        singlyLinkedList.add(hero4);
        singlyLinkedList.add(hero2);
        singlyLinkedList.add(hero3);

        // 显示
        singlyLinkedList.list();
        System.out.println();

        // 测试逆序打印单链表，没有改变链表本身的结构
        // printList(singlyLinkedList.getHead());
        reversePrint(singlyLinkedList.getHead());
    }

    // 问题四：从尾到头打印单链表 【百度，要求：方式1：反向遍历；方式2：Stack 栈】
    // 1. 利用栈，将各个结点压入到栈中，利用栈的先进后出的特点，就实现了逆序打印的效果
    public void reversePrint(HeroNode head) {
        if (head.next == null)
            return; // 空链表，不能打印

        // 创建一个栈，将各个结点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;

        // 将链表的所有结点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; // cur 后移，这样就可以压入下一个结点
        }

        // 将栈中的结点进行打印，pop()
        while (stack.size() > 0)
            System.out.println(stack.pop()); // stack 的特点是先进后出
    }

    // 2
    // public void printList(HeroNode head) {
    //     if (head.next == null)
    //         System.out.println("null");
    //     int len = getLength(head);
    //     for (int i = 1; i <= len; i++) {
    //         HeroNode node = findLastIndexNode(head, i);
    //         System.out.println(node);
    //     }
    // }

    // 问题五：合并两个有序的单链表，合并之后的链表依然有序
    @Test
    public void testMergeList() {
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SinglyLinkedList list1 = new SinglyLinkedList();
        SinglyLinkedList list2 = new SinglyLinkedList();

        list1.addByOrder(hero3);
        list1.addByOrder(hero1);
        list1.list();
        System.out.println();

        list2.addByOrder(hero4);
        list2.addByOrder(hero2);
        list2.list();
        System.out.println();

        mergeList(list1, list2);
        list1.list();

    }

    public void mergeList(SinglyLinkedList list1, SinglyLinkedList list2) {

        HeroNode head2 = list2.getHead();

        HeroNode cur = head2.next;
        HeroNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = null;
            list1.addByOrder(cur);
            cur = next;
        }
    }
}



























