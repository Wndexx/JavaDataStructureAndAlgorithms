package com.wndexx.tree;

/**
 * 顺序存储二叉树
 *
 * @author wndexx 2022-04-01 13:07
 */
public class ArrBinaryTree {
    /**
     * 存储数据结点的数组
     */
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载 preOrder 方法，索引从 0 开始
     */
    public void preOrder() {
        preOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        // 如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        // 输出当前元素
        System.out.print(arr[index] + " ");
        // 向左递归遍历
        if ((2 * index + 1) < arr.length)
            preOrder(2 * index + 1);
        // 向右递归遍历
        if ((2 * index + 2) < arr.length)
            preOrder(2 * index + 2);
    }

    /**
     * 重载 inOrder 方法，索引从 0 开始
     */
    public void inOrder() {
        inOrder(0);
    }

    /**
     * 顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    public void inOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((2 * index + 1) < arr.length)
            inOrder(2 * index + 1);
        System.out.print(arr[index] + " ");
        if ((2 * index + 2) < arr.length)
            inOrder(2 * index + 2);
    }
    /**
     * 重载 postOrder 方法，索引从 0 开始
     */
    public void postOrder() {
        postOrder(0);
    }

    /**
     * 顺序存储二叉树的后序遍历
     *
     * @param index 数组的索引
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if ((2 * index + 1) < arr.length)
            postOrder(2 * index + 1);
        if ((2 * index + 2) < arr.length)
            postOrder(2 * index + 2);
        System.out.print(arr[index] + " ");
    }
}
