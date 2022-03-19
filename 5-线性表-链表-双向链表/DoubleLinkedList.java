package com.wndexx.linkedlist1;

/**
 * @author wndexx
 * @create 2022-03-19 11:45
 */
/*
    创建一个双向链表的类
*/
public class DoubleLinkedList {

    // 1. 初始化头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    // 2. 返回头结点
    public HeroNode getHead() {
        return head;
    }

    // 3. 遍历双向链表
    public void list() {

        // (1) 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // (2) 因为头结点不能动，因此我们需要一个辅助变量来遍历
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

    // 4. 添加结点到双向链表的最后
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

        // (2) 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 4. 修改双向链表的结点（可以看到双向链表的结点内容修改和单链表，只是结点类型改变）
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

    // 5. 删除双向链表的结点
    // 说明
    // (1) 对于双向链表，我们可以直接找到要删除的结点
    // (2) 找到后，自我删除即可
    public void delete(int no) {

        // 判断当前链表是否为空
        if (head.next == null) { // 空链表
            System.out.println("链表为空，无法删除");
        }

        // 定义辅助变量（指针）
        HeroNode temp = head.next;

        boolean flag = false; // 标识是否找到待删除结点
        while (true) {
            if (temp == null)  // 已经到链表的最后结点的 next
                break;
            if (temp.no == no) { // 找到待删除结点的前一个结点 temp
                flag = true;
                break;
            }
            temp = temp.next; // temp 后移
        }
        // 判断 flag
        if (flag) { // 找到，可以删除
            temp.pre.next = temp.next;
            // 这里代码有问题
            // 如果是最后一个结点，就不需要执行下面的这句话，否则会出现空指针异常
            if (temp.next != null)
                temp.next.pre = temp.pre;
            return;
        }
        System.out.printf("要删除的 %d 结点不存在\n", no);
    }

    // 6. 按照顺序添加结点到双向链表
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
        if (temp.next != null)
            temp.next.pre = heroNode;
        heroNode.next = temp.next;
        temp.next = heroNode;
        heroNode.pre = temp;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;

    public HeroNode next; // 指向下一个结点，默认为 null
    public HeroNode pre; // 指向前一个结点，默认为 null

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
