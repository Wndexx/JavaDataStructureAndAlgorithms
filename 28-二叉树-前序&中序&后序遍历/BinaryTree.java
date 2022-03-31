package com.wndexx.tree;

/**
 * 二叉树
 *
 * @author wndexx 2022-03-31 9:24
 */
public class BinaryTree {
    public HeroNode root;

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        this.root.preOrder();
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        this.root.inOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root == null) {
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        this.root.postOrder();
    }
}

/**
 * HeroNode 结点
 */
class HeroNode {
    public int no;
    public String name;
    /**
     * 左子结点，默认 null
     */
    public HeroNode left;
    /**
     * 右子结点，默认 null
     */
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 1. 先输出父结点
        System.out.println(this);
        // 2. 递归向左子树前序遍历
        if (this.left != null)
            this.left.preOrder();
        // 3. 递归向右子树前序遍历
        if (this.right != null)
            this.right.preOrder();

    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        // 1. 递归向左子树中序遍历
        if (this.left != null)
            this.left.inOrder();
        // 2. 输出父结点
        System.out.println(this);
        // 3. 递归向右子树中序遍历
        if (this.right != null)
            this.right.preOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 1. 递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 2. 递归向右子树后序遍历
        if (this.right != null)
            this.right.postOrder();
        // 3. 输出父结点
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}





















