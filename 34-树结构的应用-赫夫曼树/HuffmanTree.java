package com.wndexx.tree.huffmantree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 * @author wndexx 2022-04-04 14:43
 */
public class HuffmanTree {
    /**
     * 测试赫夫曼树
     */
    @Test
    public void testHuffmanTree() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = creatHuffmanTree(arr);

        // 测试
        preOrder(root);
    }

    // 编写一个前序遍历的方法
    public void preOrder(Node root) {
        if (root == null) {
            System.out.println("树是空树，不能遍历");
            return;
        }
        root.preOrder();
    }


    /**
     * 创建赫夫曼树
     *
     * @param arr 待处理的数组
     * @return 创建好的赫夫曼树的 root 结点
     */
    public Node creatHuffmanTree(int[] arr) {
        // 第一步， 为了操作方便
        // 1. 遍历 arr 数组
        // 2. 将 arr 的每个元素构建成一个 Node
        // 3. 将 Node 放入 ArrayList 中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        // 循环处理
        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 第二步，取出根结点权值最小的两棵二叉树
            // (1) 取出权值最小的结点（二叉树）
            Node left = nodes.get(0);
            // (2) 取出权值第二小的结点（二叉树）
            Node right = nodes.get(1);
            // (3) 构建一棵新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            // (4) 从 ArrayList 删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            // (5) 将 parent 加入到 nodes
            nodes.add(parent);
        }

        // 返回赫夫曼树的 root 结点
        return nodes.get(0);
    }
}

/**
 * 结点类
 * 为了让 Node 对象支持 Collections 集合排序，可以让 Node 类实现 Comparable 接口
 */
class Node implements Comparable<Node> {
    int value; // 结点权值
    Node left; // 指向左子结点
    Node right; // 指向右子结点

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.print(this.value + " ");
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return Integer.compare(this.value, o.value);
    }
}
