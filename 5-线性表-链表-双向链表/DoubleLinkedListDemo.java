package com.wndexx.linkedlist1;

import org.junit.Test;

/**
 * @author wndexx
 * @create 2022-03-19 13:03
 */
/*
    双向链表

        单链表的缺点：

            1. 单向链表，查找的方向只能是一个方向

            2. 单向链表不能自我删除，需要靠辅助节点

        概念：在双向链表的节点中有两个指针域，其一指向直接后继，另一指向直接前继

        示意图：双向链表1.jpg 双向链表2.jpg 双向链表3.jpg

*/
public class DoubleLinkedListDemo {

    @Test
    public void testAdd() {

        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

    }

    @Test
    public void testUpdate() {

        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        // 修改
        HeroNode newHeroNode = new HeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);

        doubleLinkedList.list();
    }

    @Test
    public void testDelete() {

        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        // 删除
        doubleLinkedList.delete(3);

        doubleLinkedList.list();
    }

    @Test
    public void testAddByOrder() {

        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        // 测试按照顺序添加
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);

        doubleLinkedList.list();

    }

}
