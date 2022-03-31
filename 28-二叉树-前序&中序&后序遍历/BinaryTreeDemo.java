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

        // 3. 说明：手动创建二叉树，后面以递归的方式创建二叉树
        binaryTree.root = root;
        root.left = node2;
        root.right = node3;
        node3.right = node4;

        // 4. 测试
        System.out.println("前序遍历"); // 1，2，3，4
        binaryTree.preOrder();

        System.out.println("中序遍历"); // 2，1，3，4
        binaryTree.inOrder();

        System.out.println("后序遍历"); // 2，4，3，1
        binaryTree.postOrder();
    }
}
