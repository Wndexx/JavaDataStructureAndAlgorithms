package com.wndexx.graph;

import org.junit.Test;

/**
 * 测试图
 *
 * @author wndexx 2022-04-11 15:49
 */
public class GraphDemo {
    /**
     * 测试图的创建
     */
    @Test
    public void test1() {

        int n = 8; // 顶点的个数
        // String[] vertices = {"A", "B", "C", "D", "E"};
        String[] vertices = {"1", "2", "3", "4", "5", "6", "7"};

        // 创建图对象
        Graph graph = new Graph(n);

        // 循环的添加顶点
        for (String vertex : vertices) {
            graph.insertVertex(vertex);
        }

        // 添加边
        // A - B
        // graph.insertEdge(0, 1, 1);
        // graph.insertEdge(0, 2, 1);
        // graph.insertEdge(1, 2, 1);
        // graph.insertEdge(1, 3, 1);
        // graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示
        graph.showGraph();

        // 深度优先遍历
        System.out.println("深度优先遍历");
        // graph.depthFirstSearch();
        graph.dfs(); // 10
        System.out.println();
        // System.out.println(graph.count);

        // 广度优先遍历
        System.out.println("广度优先遍历");
        graph.bfs();
    }
}
