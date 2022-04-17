package com.wndexx.kruskal;

import org.junit.Test;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 *
 * @author wndexx 2022-04-17 10:16
 */
public class KruskalAlgorithm {
    public static void main(String[] args) {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, -1, INF, INF, INF, 16, -2},
                {-1, 0, 10, INF, INF, -3, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, -3, 6, INF, 2, 0, 9},
                {-2, INF, INF, INF, 8, 9, 0}
        };

        // 创建 KruskalAlgorithm 对象实例
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertices, matrix);
        // // 输出构建的图的邻接矩阵
        // kruskalAlgorithm.print();

        // EData[] edges = kruskalAlgorithm.getEdges();
        // System.out.println("排序前 = " + Arrays.toString(edges));
        // kruskalAlgorithm.sortEdges(edges);
        // System.out.println("排序后 = " + Arrays.toString(edges));

        kruskalAlgorithm.kruskal();

    }

    /**
     * 边的个数
     */
    int edgeNum; // 边的个数
    /**
     * 顶点数组
     */
    char[] vertices;
    /**
     * 邻接矩阵
     */
    int[][] matrix;

    /**
     * 使用 INF 表示两个顶点不连通
     */
    static final int INF = Integer.MAX_VALUE;

    public KruskalAlgorithm(char[] vertices, int[][] matrix) {
        // 初始化顶点数和边的个数
        int vLen = vertices.length;

        // 初始化顶点，复制拷贝的方式，避免 vertices 发生改变时，this.vertices 也发生改变
        this.vertices = new char[vLen];
        for (int i = 0; i < vertices.length; i++) {
            this.vertices[i] = vertices[i];
        }
        // this.vertices = vertices;

        // 初始化边，复制拷贝
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 打印邻接矩阵
     */
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 1. 获取图中的边，放到 EData[] 数组
     * 通过 matrix 邻接矩阵来获取
     * EData[] [['A','B',12],['B','F','7'],...]
     *
     * @return 包含图的边的 EData[] 数组
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertices[i], vertices[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 2. 对边进行排序处理，冒泡排序
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) { // 交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 3. 返回顶点对应的下标
     *
     * @param ch 顶点的值， 'A'
     * @return 返回顶点对应的下标，如果找不到，返回 -1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == ch)
                return i;
        }
        // 找不到，返回 -1
        return -1;
    }

    /**
     * 4. 获取下标为 i 的顶点的终点，用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点的终点，ends 数组是在遍历过程中，逐步形成的
     * @param i    传入的顶点对应的下标
     * @return 下标为 i 的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 克鲁斯卡尔算法
     */
    public void kruskal() {
        int index = 0; // 表示结果数组的索引
        int[] ends = new int[vertices.length]; // 用于保存已有最小生成树中的每个顶点在最小生成树中的终点
        // 创建结果数组，保存最小生成树
        EData[] results = new EData[edgeNum];

        // 获取图中所有的边的集合，一共有 12 条边
        EData[] edges = getEdges();
        // System.out.println("图的边的集合 = " + Arrays.toString(edges) + " 共 " + edges.length);

        // 按照边的权值大小进行排序（从小到大）
        sortEdges(edges);

        // 遍历 edges，将边添加到最小生成树中前，判断准备加入的边是否形成了回路
        // 如果没有，就加入到 results，否则，不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第 i 条边的起始顶点
            int p1 = getPosition(edges[i].start);
            // 获取到第 i 条边的终止顶点
            int p2 = getPosition((edges[i].end));

            // 获取 p1 这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取 p2 这个顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);

            // 是否构成回路
            if (m != n) { // 不构成回路
                // 如果两个顶点的终点不相同，只需要将其中一个顶点的终点指向另一个顶点的终点，则该顶点所在通路上的所有顶点也都指向另一个顶点的终点
                // 注意：如果 ends[n] = 0，则 getEnds(n) = n，所以不用加 ends[n] = n
                ends[m] = n; // 设置 m 在已有最小生成树中的终点
                results[index++] = edges[i]; // 有一条边加入到 results 数组
            }
            System.out.println(Arrays.toString(ends));
        }
        // 统计并打印最小生成树，输出 results
        System.out.println("最小生成树 = ");
        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }
    }
}

/**
 * 对象实例表示一条边
 */
class EData {
    /**
     * 边的起点
     */
    char start;
    /**
     * 边的终点
     */
    char end;
    /**
     * 边的权值
     */
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    /**
     * @return 边的信息
     */
    @Override
    public String toString() {
        return "<" + start +
                "," + end +
                "," + weight +
                '>';
    }
}
