package com.wndexx.linkedlist;

/**
 * @author wndexx
 * @create 2022-03-17 17:00
 */
/*
    线性结构和非线性结构

    线性结构

        线性表

            1. 概念：零个或多个元素的有限序列

            2. 特点：

                (1) 存在唯一的一个被称作 "第一个" 的数据元素

                (2) 存在唯一个一个被称作 "最后一个" 的数据元素

                (3) 除 "第一个" 之外，每一个数据元素均只有一个前驱

                (4) 除 "最后一个" 之外，每一个数据元素均只有一个后继

                即：线性表是有序的

            3. 存储结构

                (1) 顺序存储结构，顺序存储的线性表称为顺序表：用一组地址连续的存储单元依次存储线性表的数据元素

                    即：顺序表中的存储元素是连续的

                    高级程序设计语言中通常用 数组 来描述数据结构中的顺序存储结构

                    优点：

                        - 无须为表示表中元素之间的逻辑关系而增加额外的存储空间

                        - 可以快速的存取表中任一位置的元素

                    缺点：

                        - 插入和删除操作需要移动大量元素

                        - 当线性表长度变化较大时，难以确定存储空间的容量

                        - 造成存储空间的 "碎片"

                (2) 链式存储结构，链式存储的线性表称为链表：用一组任意的存储单元存储线性表的数据元素（这组存储单元可以是连续的，也可以是不连续的）

                    即：链表中的存储元素不一定是连续的

                    数据域：存储一个数据元素信息的域

                    指针域：存储直接后继存储位置的域。指针域中存储的信息称作指针或链

                    结点（node）：数据域和指针域组成结点

                    n 个结点链接成一个链表

            4. 分类：线性表（数组，链表）、栈、队列

    非线性结构

        非线性结构包括：二维数组，多维数组，广义表，树结构，图结构

    2. 链表（Linked List）

        链式存储的线性表称为链表

        (1) 单链表（又叫线性链表，Singly Linked List）：只包含一个指针域（存储直接后继存储位置的域）的链表，称作单链表

            头结点：有时，我们为了更加方便的对链表进行操作，会在单链表的第一个结点前附设一个结点，称为头结点

                   头结点的数据域可以不存储任何信息

                   头结点的指针域存储指向第一个结点的指针

*/
public class SinglyLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        // 先创建结点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        // 将结点加入链表
        // singlyLinkedList.add(hero1);
        // singlyLinkedList.add(hero2);
        // singlyLinkedList.add(hero3);
        // singlyLinkedList.add(hero4);

        // 按照编号的顺序将结点加入链表
        singlyLinkedList.addByOrder(hero1);
        singlyLinkedList.addByOrder(hero4);
        singlyLinkedList.addByOrder(hero2);
        singlyLinkedList.addByOrder(hero3);
        singlyLinkedList.addByOrder(hero3);

        // 显示
        singlyLinkedList.list();

        // 获取指定编号的结点
        System.out.printf("编号为 %d 的结点信息\n", 1);
        HeroNode heroNode = singlyLinkedList.get(1);
        System.out.println(heroNode);

        // 测试修改结点的代码
        HeroNode newHeroNode = new HeroNode(2, "花荣", "小李广");
        singlyLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况");
        singlyLinkedList.list();

        // 删除一个结点
        singlyLinkedList.delete(1);
        singlyLinkedList.delete(4);
        singlyLinkedList.delete(2);
        singlyLinkedList.delete(3);
        System.out.println("删除后的链表情况");
        singlyLinkedList.list();
    }
}

// 定义一个 SinglyLinkedList 管理结点
class SinglyLinkedList {
    // 1. 初始化头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

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

    // 5. 获取指定位置的结点
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


    // 6. 显示链表【遍历】
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
