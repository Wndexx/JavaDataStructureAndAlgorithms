package com.wndexx.tree;

import org.junit.Test;

/**
 * 顺序存储二叉树
 *
 * @author wndexx 2022-04-01 13:01
 */
public class ArrBinaryTreeDemo {
    /**
     * 测试顺序存储二叉树
     */
    @Test
    public void test1() {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        // 创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);

        arrBinaryTree.preOrder(); // 1 2 4 5 3 6 7
        System.out.println();

        arrBinaryTree.inOrder(); // 4 2 5 1 6 3 7
        System.out.println();

        arrBinaryTree.postOrder(); // 4 5 2 6 7 3 1
        System.out.println();
    }
}
