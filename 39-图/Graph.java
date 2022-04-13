package com.wndexx.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 *
 * @author wndexx 2022-04-11 15:14
 */
public class Graph {
    /**
     * 存储顶点的集合
     */
    ArrayList<String> vertexList;

    /**
     * 存储图对应的邻接矩阵
     */
    int[][] edges;

    /**
     * 边的数目
     */
    private int numOfEdges;

    /**
     * 定义数组 boolean[] ，记录某个结点是否被访问
     */
    boolean[] isVisited;

    public Graph(int n) {
        // 初始化 vertexList 和 edges
        // n 代表底层创建了长度是 n 的 Object[] 数组 elementData
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        // 可以不写，默认为 0
        numOfEdges = 0;
    }

    /**
     * 插入结点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个顶点的下标，也就是在 vertexList 的索引
     * @param v2     第二个顶点的下标
     * @param weight 边的权值，0 代表 v1 和 v2 没有相连，1 代表相连
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 图中常用的方法

    /**
     * @return 返回顶点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * @return 返回边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 0 -> 'A'，1 -> 'B'，2 -> 'C'
     *
     * @param i 顶点下标
     * @return 顶点 i 对应的数据
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * @param v1 第一个顶点的下标
     * @param v2 第二个顶点的下标
     * @return 两顶点对应的边的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    int count = 0;

    /**
     * 遍历所有的结点都进行广度优先遍历
     * 因为可能是非连通图
     */
    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(i);
            }
        }
    }

    /**
     * 广度优先遍历
     *
     * @param i 结点索引
     */
    private void bfs(int i) {
        int u; // 表示队列的头结点对应的下标
        int w; // 邻接结点的下标
        // 队列，记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问结点，输出结点的信息
        System.out.print(getValueByIndex(i) + "->");
        // 标记为已访问
        isVisited[i] = true;
        // 将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = queue.removeFirst();
            // 得到位于 u 后面的第一个邻接结点的下标
            // w = getNextNeighbor(u, u);
            w = getFirstNeighbor(u);
            while (w != -1) { // 找到了
                // 是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 找到 u 的下一个邻接结点
                w = getNextNeighbor(u, w); // 体现出广度优先
            }
        }
    }

    /**
     * 深度优先遍历
     * 对 dps 方法重载，遍历所有的顶点
     * 因为可能存在非连通图 比如 ABC 三个顶点连通，DE 两个顶点连通，但是不存在一条边连通 ABC 与 DE
     */
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param i 第一次是 0
     */
    private void dfs(int i) {
        // 访问该结点
        System.out.print(getValueByIndex(i) + "->");
        // 将结点设置为已访问
        isVisited[i] = true;
        // 查找顶点 i 的第一个邻接顶点 w
        int w = getFirstNeighbor(i);
        while (w != -1) { // 说明存在邻接顶点
            count++;
            if (!isVisited[w]) {
                dfs(w); // 体现出深度优先
            }
            // 如果 w 顶点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * @param index 当前顶点的下标
     * @return 返回当前顶点的第一个邻接顶点的下标；不存在，返回 -1
     */
    public int getFirstNeighbor(int index) {
        return getNextNeighbor(index, -1);
    }

    /**
     * @param prev 前一个邻接顶点的下标获得下一个邻接顶点的下标
     * @return 返回当前邻接顶点的下一个邻接顶点的下标；不存在，返回 -1
     */
    public int getNextNeighbor(int index, int prev) {
        for (int i = prev + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }
        return -1;
    }

    // /**
    //  * 深度优先遍历
    //  */
    // public void depthFirstSearch() {
    //     List<String> list = new ArrayList<>(getNumOfVertex());
    //     for (int i = 0; i < vertexList.size(); i++) {
    //         if (!list.contains(vertexList.get(i))) {
    //             dfs(i, list);
    //         }
    //     }
    //     for (String s : list) {
    //         System.out.print(s + " ");
    //     }
    // }
    //
    // /**
    //  * 深度优先遍历递归方法
    //  * 这样写是错误的，比如 A->D->B->C->E 遍历出的结果是 A->D
    //  */
    // private void dfs(int i, List<String> list) {
    //     list.add(vertexList.get(i));
    //     int j = getNextNeighbor(i, i);
    //     while (j != -1 && j < getNumOfVertex()) {
    //         count++;
    //         if (list.contains(getValueByIndex(j))) {
    //             j = getNextNeighbor(i, j);
    //             continue;
    //         }
    //         dfs(j, list);
    //         j = getNextNeighbor(i, j);
    //     }
    // }
}
