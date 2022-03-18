package com.wndexx.linkedlist;

/**
 * @author wndexx
 * @create 2022-03-18 17:39
 */
// 定义一个 SinglyLinkedList 管理结点
public class SinglyLinkedList {
    // 1. 初始化头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    // 返回头结点
    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    // 2. 不考虑顺序添加结点到单向链表
    public void add(HeroNode heroNode) {
        // (1) 找到当前链表的最后结点
        // 因为 head 结点不能动，因此我们需要一个辅助变量 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null)
                break;
            // 如果没有找到最后，就将 temp 后移
            temp = temp.next;
        }
        // 当 退出 while 循环时，temp 就指向了链表的最后
        // (2) 将最后这个结点的 next 指向新的结点
        temp.next = heroNode;
    }

    // 2+. 按照顺序添加结点到单向链表
    public void addByOrder(HeroNode heroNode) {
        // 因为头结点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        // 因为是单向链表，因此我们找的 temp 是位于添加位置的前一个结点，否则无法添加
        HeroNode temp = head;
        boolean flag = false; // flag 标志添加的编号是否存在，默认为 false
        while (true) {
            if (temp.next == null) // 说明 temp 已经在链表的最后
                break;
            if (temp.next.no > heroNode.no) // 位置找到，就在 temp 的后面插入
                break;
            if (temp.next.no == heroNode.no) { // 说明希望添加的 heroNode 的编号已经存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移，遍历当前链表
        }
        // 判断 flag 的值
        if (flag) { // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", heroNode.no);
            return;
        }
        // 插入到链表中
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    // 3. 修改结点的信息，根据 no 编号来修改，即 no 编号不能修改
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 根据 newHeroNode 的 no 找到需要修改的结点
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该结点
        while (true) {
            if (temp == null)
                break; // 已经遍历完链表
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据 flag 判断是否找到要修改的结点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
            return;
        }
        // 没有找到
        System.out.printf("没有找到编号 %d 的结点，不能修改\n", newHeroNode.no);
    }

    // 4. 删除结点
    // 思路
    // 1. head 结点不能动，因此需要一个 temp 辅助结点找到待删除结点的前一个结点
    // 2. 在比较时，是 temp.next.no 和需要删除的结点的 no 比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标识是否找到待删除结点
        while (true) {
            if (temp.next == null)  // 已经到链表的最后
                break;
            if (temp.next.no == no) { // 找到待删除结点的前一个结点 temp
                flag = true;
                break;
            }
            temp = temp.next; // temp 后移
        }
        // 判断 flag
        if (flag) { // 找到，可以删除
            temp.next = temp.next.next;
            return;
        }
        System.out.printf("要删除的 %d 结点不存在\n", no);
    }

    // 5. 获取指定编号位置的结点
    public HeroNode get(int no) {
        // 判断链表是否为空
        if (head.next == null)
            return null;
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null)
                break;
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            return temp;
        }
        return null;
    }

    // 6. 获取指定索引位置的结点
    public HeroNode getNodeByIndex(int index) {
        if (index < 0)
            throw new RuntimeException("索引不能为负");
        // 判断链表是否为空
        if (head.next == null)
            return null;
        HeroNode temp = head.next;
        for (int i = 0; i++ < index && temp != null; )
            temp = temp.next;
        return temp;
    }

    // 7. 显示链表【遍历】
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null)
                break;
            // 输出结点的信息
            System.out.println(temp);
            // 将 temp 后移
            temp = temp.next;
        }
    }
}

// 1. 定义 HeroNode，每一个 HeroNode 对象就是一个结点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个结点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

