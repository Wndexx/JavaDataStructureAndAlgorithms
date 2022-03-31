package com.wndexx.tree;

import org.junit.Test;

/**
 * 测试二叉树的遍历
 *
 * @author wndexx 2022-03-31 9:24
 */
public class BinaryTreeDemo {
    /**
     * 测试二叉树的遍历
     */
    @Test
    public void testBinaryTreeTraversal() {

        // 1. 创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();

        // 2. 创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 3. 说明：手动创建二叉树，后面以递归的方式创建二叉树
        binaryTree.root = root;
        root.left = node2;
        root.right = node3;
        node3.left = node5;
        node3.right = node4;

        // 4. 测试
        System.out.println("前序遍历"); // 1，2，3，5，4
        binaryTree.preOrder();

        System.out.println("中序遍历"); // 2，1，3，5，4
        binaryTree.inOrder();

        System.out.println("后序遍历"); // 2，5，4，3，1
        binaryTree.postOrder();
    }

    /**
     * 测试二叉树的遍历查找
     */
    @Test
    public void testBinaryTreeSearch() {
        // 1. 创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();

        // 2. 创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 3. 说明：手动创建二叉树，后面以递归的方式创建二叉树
        binaryTree.root = root;
        root.left = node2;
        root.right = node3;
        node3.left = node5;
        node3.right = node4;

        // 4. 测试
        // 前序遍历查找：
        // 前序遍历的次数：4
        System.out.println("前序遍历查找");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no = %d name = %s\n", resNode.no, resNode.name);
        } else {
            System.out.printf("没有找到 no = %d 的英雄\n", 5);
        }

        // 中序遍历查找
        // 中序遍历的次数：3
        System.out.println("中序遍历查找");
        resNode = binaryTree.inOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no = %d name = %s\n", resNode.no, resNode.name);
        } else {
            System.out.printf("没有找到 no = %d 的英雄\n", 5);
        }

        // 后序遍历查找
        // 后序遍历的次数：2
        System.out.println("后序遍历查找");
        resNode = binaryTree.postOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no = %d name = %s\n", resNode.no, resNode.name);
        } else {
            System.out.printf("没有找到 no = %d 的英雄\n", 5);
        }
    }
}
