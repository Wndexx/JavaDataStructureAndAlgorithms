package com.wndexx.linkedlist;

import org.junit.Test;

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

        System.out.println();
        // reverse(singlyLinkedList);
        // reverse(singlyLinkedList.getHead());
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
        // singlyLinkedList.delete(2);
        // singlyLinkedList.delete(3);
        System.out.println("删除后的链表情况");
        singlyLinkedList.list();

        // 获取指定索引位置的结点
        System.out.printf("索引位置为 %d 处的结点\n", 0);
        HeroNode node = singlyLinkedList.getNodeByIndex(0);
        System.out.println(node);
    }
}
