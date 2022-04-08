package com.wndexx.tree.binarysorttree;

/**
 * 二叉排序树
 *
 * @author wndexx 2022-04-07 18:01
 */
public class BinarySortTree {
    private Node root;

    /**
     * 添加结点
     *
     * @param node 待添加的结点
     */
    public void add(Node node) {
        if (root == null) {
            // 如果 root 为空，直接让 root 指向 node
            root = node;
            return;
        }
        root.add(node);
    }

    /**
     * 删除结点
     *
     * @param value 希望删除的结点的值
     */
    public void deleteNode(int value) {
        if (root == null)
            return;
        // 1. 找到要删除的结点
        Node targetNode = search(value);
        // 如果没有找到要删除的结点
        if (targetNode == null)
            return;
        // 如果当前这棵二叉排序树只有一个结点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 2. 找到要删除的结点的父结点
        Node parent = searchParent(value);
        // (1) 如果要删除的结点是叶子结点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断 targetNode 是父结点的左子结点，还是右子结点
            if (parent.left != null && parent.left.value == value) { // 左子结点
                parent.left = null;
                return;
            }

            // 这里的 if 语句没有必要写，因为当 targetNode 是叶子结点且不是 parent 的左子结点时，一定是右子结点
            // 注意前面已经确定了 targetNode 存在，且当前二叉排序树结点大于等于 2，此时又因为 targetNode 是叶子结点，则 parent 一定存在
            // 当 parent 存在时，targetNode 要么是 parent 的左子结点，要么是 parent 的右子结点
            // 所以当 targetNode 是叶子结点且不是 parent 的左子结点时，一定是右子结点

            // if (parent.right != null && parent.right.value == value) { // 右子结点
            //     parent.right = null;
            // }

            parent.right = null;
            return;
        }

        // (2) 删除有两棵子树的结点
        if (targetNode.left != null && targetNode.right != null) {
            // targetNode.value = deleteRightTreeMin(targetNode.right);
            targetNode.value = deleteLeftTreeMax(targetNode.left);
            return;
        }

        // (3) 删除只有一棵子树的结点
        // 如果删除的结点是根结点
        if (parent == null) {
            // 如果要删除的结点有左子结点
            if (targetNode.left != null) {
                // root 指向 targetNode.left
                root = targetNode.left;
                return;
            }
            // 如果要删除的结点有右子结点
            root = targetNode.right;
            return;
        }
        // 如果要删除的结点有左子结点
        if (targetNode.left != null) {
            // 如果 targetNode 是 parent 的左子结点
            // 这里要判断 parent.left 是否为 null，
            if (parent.left != null && parent.left.value == value) {
                parent.left = targetNode.left;
                return;
            }
            // 如果 targetNode 是 parent 的右子结点
            parent.right = targetNode.left;
            return;
        }
        // 如果 targetNode 是 parent 的右子结点
        // 如果 targetNode 是 parent 的左子结点
        if (parent.left != null && parent.left.value == value) {
            parent.left = targetNode.right;
            return;
        }
        // 如果 targetNode 是 parent 的右子结点
        parent.right = targetNode.right;
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到，返回该结点；否则，返回 null
     */
    public Node search(int value) {
        if (root == null)
            return null;
        return root.search(value);
    }

    /**
     * 查找要删除结点的父结点
     *
     * @param value 希望删除的结点的值
     * @return 返回的是要删除的结点的父结点；如果没有，返回 null
     */
    public Node searchParent(int value) {
        if (root == null)
            return null;
        // 当 root.value == value 时，root.searchParent(value) 返回的也是 null
        // 当 root 结点和其右子结点均等于 value 时，如果不加该条件，返回的是 root 结点，但实际应该返回 null ，会出错
        if (root.value == value)
            return null;
        return root.searchParent(value);
    }

    /**
     * 1. 返回以 node 为根结点的二叉排序树的最小结点的值
     * 2. 删除以 node 为根结点的二叉排序树的最小结点
     *
     * @param node 传入的结点（当作二叉排序树的根结点）
     * @return 返回以 node 为根结点的二叉排序树的最小结点的值
     */
    public int deleteRightTreeMin(Node node) {
        // 循环的查找左结点，就会找到最小值
        while (node.left != null)
            node = node.left;
        // 这时 node 就指向了最小结点
        // 删除最小结点
        deleteNode(node.value);
        // 返回最小结点对应的值
        return node.value;
    }

    /**
     * 1. 返回以 node 为根结点的二叉排序树的最大结点的值
     * 2. 删除以 node 为根结点的二叉排序树的最大结点
     *
     * @param node 传入的结点（当作二叉排序树的根结点）
     * @return 返回以 node 为根结点的二叉排序树的最大结点的值
     */
    public int deleteLeftTreeMax(Node node) {
        // 循环的查找右结点，就会找到最大值
        while (node.right != null)
            node = node.right;
        // 这时 node 就指向了最大结点
        // 删除最大结点
        deleteNode(node.value);
        // 返回最大结点对应的值
        return node.value;
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空，不能遍历");
            return;
        }
        root.inOrder();
    }
}

/**
 * 结点
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 递归添加结点，需要满足二叉排序树的要求
     *
     * @param node 待添加的结点
     */
    public void add(Node node) {
        if (node == null)
            return;
        // 判断传入的结点的值，和当前子树的根结点的值的关系
        if (node.value < this.value) {
            // 如果当前结点左子结点为 null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        } else { // 添加的结点的值大于等于当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到，返回该结点；否则，返回 null
     */
    public Node search(int value) {
        if (value == this.value) // 找到
            return this;
        if (value < this.value) { // 如果查找的值小于当前节点，继续向左子树递归查找
            // 如果左子结点为空
            if (this.left == null)
                return null;
            return this.left.search(value);
        }
        // 如果查找的值大于当前节点，继续向右子树递归查找
        if (this.right == null)
            return null;
        return this.right.search(value);
    }

    /**
     * 查找要删除结点的父结点
     *
     * @param value 希望删除的结点的值
     * @return 返回的是要删除的结点的父结点；如果没有，返回 null
     */
    public Node searchParent(int value) {
        // Node node;
        // if (this.left != null) {
        //     node = this.left;
        //     if (node.value == value)
        //         return this;
        //     if (value < this.value)
        //         return node.searchParent(value);
        // }
        // if (this.right != null) {
        //     node = this.right;
        //     if (node.value == value)
        //         return this;
        //     if (value > this.value)
        //         return node.searchParent(value);
        // }

        // 或

        // 如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value))
            return this;
        // 如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
        if (value < this.value && this.left != null)
            return this.left.searchParent(value);
        // 这里没有必要写 value >= this.value，后面在判断的时候，会先判断 root 的值是否等于 value ，如果等于，返回 null
        // 如果不等于，从 root 开始调用此方法，因为 第一个 if 已经判断左右子结点均不为 value，所以其左右结点执行此方法到该步时，不需要判断是否等于
        if (value > this.value && this.right != null)
            return this.right.searchParent(value);

        return null; // 没有找到父结点
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        if (this.left != null)
            this.left.inOrder();
        System.out.print(this.value + " ");
        if (this.right != null)
            this.right.inOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
