package com.wndexx.floyd;

import org.junit.Test;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 *
 * @author wndexx 2022-04-19 16:36
 */
public class FloydAlgorithm {
    @Test
    public void test1() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertex.length, vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

/**
 * 图
 */
class Graph {
    /**
     * 存放顶点
     */
    char[] vertex;
    /**
     * 保存从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组中
     */
    int[][] dis;
    /**
     * 保存到达目标顶点的前驱顶点，实际存放的是与终点直接连接的顶点下标，但初始化时不是
     */
    int[][] pre;

    /**
     * 构造器
     *
     * @param length 顶点的数量
     * @param matrix 邻接矩阵
     * @param vertex 顶点集合
     */
    public Graph(int length, char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对 pre 数组初始化
        for (int i = 0; i < length; i++) {
            // 初始时，每个顶点到另一个顶点的前驱顶点存放的都是起始顶点
            // 在后续遍历的过程中，如果两个顶点直接相连的边不是这两个顶点的最短路径，则一定会进行更新，
            // 只有当直接相连的边就是这两个顶点的最短路径时路径时，才不会更新
            // 所以主要是保证了直接相连的边就是这两个顶点的最短路径时，其终点的前驱顶点就是起始顶点
            // 方便确定路径
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        int len = 0;
        for (int k = 0; k < dis.length; k++) { // 遍历中间顶点
            for (int i = 0; i < dis.length; i++) { // 遍历初始顶点
                for (int j = 0; j < dis.length; j++) { // 遍历终点
                    len = dis[i][k] + dis[k][j]; // 从 i 顶点出发经过 k 中间顶点，到达 j 顶点的距离
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j]; // 更新前驱顶点
                    }
                }
            }
        }
    }

    /**
     * 显示 pre 数组和 dis 数组
     */
    public void show() {
        char[] v = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.printf("%10s %10s %10s %10s %10s %10s %10s %10s\n", ' ', v[0], v[1], v[2], v[3], v[4], v[5], v[6]);
        for (int k = 0; k < vertex.length; k++) {
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.printf("%10s ", v[k]);
            for (int i = 0; i < vertex.length; i++) {
                System.out.printf("%10d ", pre[k][i]);
            }
            System.out.println();
            System.out.printf("%10s ", ' ');
            for (int i = 0; i < vertex.length; i++) {
                System.out.printf("%10d ", dis[k][i]);
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }
}
