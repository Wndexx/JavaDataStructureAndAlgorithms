package com.wndexx.tree.binarysorttree;

import org.junit.Test;

/**
 * @author wndexx 2022-04-07 19:00
 */
public class BinarySortTreeDemo {
    /**
     * 测试二叉排序树
     */
    @Test
    public void testBST() {

        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();

        // 循环的添加结点到二叉排序树
        for (int e : arr) {
            binarySortTree.add(new Node(e));
        }

        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.inOrder(); // 1 3 5 7 9 10 12

    }

    /**
     * 测试二叉排序树删除结点
     */
    @Test
    public void testDelete() {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的添加结点到二叉排序树
        for (int e : arr) {
            binarySortTree.add(new Node(e));
        }

        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.inOrder();

        System.out.println();

        // 测试删除叶子结点
        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(1);

        // 删除结点后
        System.out.println("删除结点后");
        binarySortTree.inOrder();
    }
}
