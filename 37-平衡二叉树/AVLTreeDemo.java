package com.wndexx.tree.avl;

import org.junit.Test;

/**
 * 测试平衡二叉树
 *
 * @author wndexx 2022-04-09 13:56
 */
public class AVLTreeDemo {

    /**
     * 测试平衡二叉树
     */
    @Test
    public void test1() {

        // int[] arr = {4, 3, 6, 5, 7, 8};
        // int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        // int[] arr = {10, 8, 11, 7, 12, 6};

        // 创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree();

        // 添加结点
        for (int e : arr) {
            avlTree.add(new Node(e));
        }

        // 中序遍历
        System.out.println("中序遍历");
        avlTree.inOrder();
        System.out.println();

        System.out.println("平衡处理后");
        System.out.println("树的高度 " + avlTree.root.height()); // 4
        System.out.println("树的左子树的高度 " + avlTree.root.left.height()); // 4
        System.out.println("树的右子树的高度 " + avlTree.root.right.height()); // 4
        System.out.println("当前的根结点 " + avlTree.root.value);
        System.out.println("根结点的左子结点 " + avlTree.root.left.value);
    }
}
