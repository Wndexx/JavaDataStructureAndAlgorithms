package com.wndexx.prim;

import org.junit.Test;

import java.util.Arrays;

/**
 * 普利姆算法
 *
 * @author wndexx 2022-04-16 20:11
 */
public class PrimAlgorithm {
    @Test
    public void test1() {
        // 测试图的创建
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertices = data.length;
        // 使用二维数组描述邻接矩阵，10000 表示两个点不连通
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        // 创建 MGraph 对象
        MGraph graph = new MGraph(vertices);
        // 创建一个 MinTree 对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertices, data, weight);
        // 输出
        minTree.showGraph(graph);
        // 测试 Prim 算法
        minTree.prim(graph, 0);
    }
}

/**
 * 最小生成树 -> 村庄的图
 */
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph    图对象
     * @param vertices 顶点个数
     * @param data     顶点数据
     * @param weight   邻接矩阵
     */
    public void createGraph(MGraph graph, int vertices, char[] data, int[][] weight) {
        for (int i = 0; i < vertices; i++) { // 顶点
            graph.data[i] = data[i];
            for (int j = 0; j < vertices; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     *
     * @param graph 图对象
     */
    public void showGraph(MGraph graph) {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * prim 算法，得到最小生成树
     *
     * @param graph 图
     * @param v     起始顶点
     */
    public void prim(MGraph graph, int v) {
        // visited[] 标记顶点是否被访问，默认元素均为 0，表示没有访问过
        int[] visited = new int[graph.vertices];
        // 把当前顶点标记为已访问
        visited[v] = 1;
        // h1 和 h2 记录所选边的两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        // 将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = 10000;
        // 普利姆算法结束后，有 graph.vertices - 1 条边
        for (int k = 1; k < graph.vertices; k++) {
            // 确定已访问过的顶点和未访问过的顶点的最小权值的边
            for (int i = 0; i < graph.vertices; i++) { // i 已访问过的顶点
                if (visited[i] == 1) {
                    for (int j = 0; j < graph.vertices; j++) { // j 未被访问过的顶点
                        if (visited[j] == 0 && graph.weight[i][j] < minWeight) {
                            // 替换 minWeight
                            minWeight = graph.weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
            }
            // 找到权值最小的那条边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            // 将当前边对应的未被访问过的顶点标记为已访问
            visited[h2] = 1;
            // 重置 minWeight
            minWeight = 10000;
        }
    }
}

/**
 * 图
 */
class MGraph {
    /**
     * 图的顶点个数
     */
    int vertices;
    /**
     * 存放顶点数据
     */
    char[] data;
    /**
     * 存放边，邻接矩阵
     */
    int[][] weight;

    public MGraph(int vertices) {
        this.vertices = vertices;
        data = new char[vertices];
        weight = new int[vertices][vertices];
    }
}
