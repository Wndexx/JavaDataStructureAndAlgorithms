package com.wndexx.tree.threadedbinarytree;

import org.junit.Test;

/**
 * @author wndexx 2022-04-01 15:58
 */
public class ThreadBinaryTreeDemo {
    HeroNode root = new HeroNode(1, "tom");
    HeroNode node2 = new HeroNode(3, "jack");
    HeroNode node3 = new HeroNode(6, "smith");
    HeroNode node4 = new HeroNode(8, "mary");
    HeroNode node5 = new HeroNode(10, "king");
    HeroNode node6 = new HeroNode(14, "dim");

    ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();

    {
        // 二叉树，后面递归创建，目前手动创建
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        threadedBinaryTree.root = root;
    }

    /**
     * 测试中序线索化二叉树
     */
    @Test
    public void test1() {
        // 线索化
        threadedBinaryTree.threadedNodes();

        // 测试中序线索化，以 10 号结点测试
        HeroNode leftNode = node5.left;
        HeroNode rightNode = node5.right;

        System.out.println("10 号结点的前驱结点是 " + leftNode);
        System.out.println("10 号结点的后继结点是 " + rightNode);

        // 线索化二叉树后，不能再使用原来的遍历方法
        // threadedBinaryTree.inOrder();
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();
    }

    /**
     * 测试前序线索化二叉树
     */
    @Test
    public void test2() {
        threadedBinaryTree.preThreadedNodes();

        // 测试中序线索化，以 10 号结点测试
        HeroNode leftNode = node5.left;
        HeroNode rightNode = node5.right;

        System.out.println("10 号结点的前驱结点是 " + leftNode);
        System.out.println("10 号结点的后继结点是 " + rightNode);

        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.preThreadList();
    }

    /**
     * 测试后序线索化二叉树
     */
    @Test
    public void test3() {
        threadedBinaryTree.postThreadedNodes();

        // 测试中序线索化，以 10 号结点测试
        HeroNode leftNode = node5.left;
        HeroNode rightNode = node5.right;

        System.out.println("10 号结点的前驱结点是 " + leftNode);
        System.out.println("10 号结点的后继结点是 " + rightNode);

        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.postThreadList();
    }
}
