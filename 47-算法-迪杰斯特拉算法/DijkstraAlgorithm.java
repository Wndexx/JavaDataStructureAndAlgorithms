package com.wndexx.dijkstra;

import org.junit.Test;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 *
 * @author wndexx 2022-04-18 17:16
 */
public class DijkstraAlgorithm {
    @Test
    public void test1() {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // N 表示不可连接
        final int N = 65535;
        // 邻接矩阵
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        // 创建 Graph 对象
        Graph graph = new Graph(vertices, matrix);
        // 测试
        graph.showGraph();
        graph.dsj(6);
        graph.show();
    }
}

/**
 * 图
 */
class Graph {
    /**
     * 顶点数组
     */
    char[] vertices;
    /**
     * 邻接矩阵
     */
    int[][] matrix;

    /**
     * 已访问集合
     */
    VisitedVertex visitedVertex;

    public Graph(char[] vertices, int[][] matrix) {
        this.vertices = vertices;
        this.matrix = matrix;
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发顶点对应的下标
     */
    public void dsj(int index) {
        visitedVertex = new VisitedVertex(vertices.length, index);
        // 更新出发顶点到周围顶点的距离及对应的前驱顶点
        update(index);
        for (int i = 1; i < vertices.length; i++) {
            // 选择并返回新的访问顶点
            index = visitedVertex.updateArr();
            update(index);
        }
    }

    /**
     * 比较当前出发顶点到周围顶点的距离和当前出发顶点经过 index 顶点到周围顶点的距离，
     * 如果后者小，更新出发顶点到周围顶点的距离以及周围顶点的前驱顶点
     *
     * @param index 访问顶点
     */
    private void update(int index) {
        int len = 0;
        // 遍历 matrix[index]
        for (int j = 0; j < matrix[index].length; j++) {
            // 出发顶点到 index 的距离 + index 顶点到 j 顶点的距离
            len = visitedVertex.getDis(index) + matrix[index][j];
            // 如果 j 未被访问过，且 len 小于出发顶点到 j 顶点的距离，就需要更新
            if (!(visitedVertex.in(j)) && len < visitedVertex.getDis(j)) {
                // 更新出发顶点到 j 顶点的距离
                visitedVertex.updateDis(j, len);
                // 更新 j 顶点的前驱顶点
                visitedVertex.updatePre(j, index);
            }
        }
    }

    /**
     * 显示结果
     */
    public void show() {
        visitedVertex.show();
    }
}

/**
 * 已访问顶点集合
 */
class VisitedVertex {
    /**
     * 记录各个顶点是否访问过，1 表示访问过，0 表示未访问过，会动态更新
     */
    int[] already_arr;
    /**
     * 每一个下标对应的值为前驱顶点的下标，会动态更新
     */
    int[] pre_visited;
    /**
     * 记录出发顶点到其它所有顶点的距离，
     * 比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求得最短距离
     */
    int[] dis;

    /**
     * @param length 顶点的个数
     * @param index  对应的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 设置出发顶点被访问过
        this.already_arr[index] = 1;
        // 初始化 dis 数组
        Arrays.fill(dis, 65535);
        // 设置出发顶点的访问距离为 0
        this.dis[index] = 0;
    }

    /**
     * 判断 index 顶点是否被访问过
     *
     * @param index 顶点的下标
     * @return 如果访问过，返回 true；否则，返回 false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * @param index 顶点的下标
     * @return 出发顶点到 index 顶点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 更新出发顶点到 index 顶点的距离
     *
     * @param index 顶点的下标
     * @param len   当前出发顶点到 index 顶点的更短距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新 index 顶点的前驱顶点为 index
     *
     * @param index 顶点的下标
     * @param pre   index 的前驱顶点的下标
     */
    public void updatePre(int index, int pre) {
        pre_visited[index] = pre;
    }

    /**
     * 选取新的访问顶点（不是出发顶点）
     *
     * @return 新的访问顶点
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (!(in(i)) && getDis(i) < min) {
                min = getDis(i);
                index = i;
            }
        }
        // 更新 index 顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    /**
     * 显示最后的结果，即将三个数组的情况输出
     */
    public void show() {
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535)
                System.out.print(vertices[count] + "(" + i + ")");
            else
                System.out.print("N");
            count++;
        }
    }
}
