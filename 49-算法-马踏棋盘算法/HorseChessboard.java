package com.wndexx.horse;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘算法（骑士周游问题）
 *
 * @author wndexx 2022-04-20 16:52
 */
public class HorseChessboard {
    @Test
    public void test1() {
        X = 8;
        Y = 8;
        int row = 1; // 马初始位置的行 从 1 开始编号
        int column = 1; // 马初始位置的列 从 1 开始编号
        // 创建棋盘
        int[][] chessboard = new int[Y][X];
        visited = new boolean[X * Y]; // 初始值都是 false
        // 测试耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + " 毫秒");
        // 输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 棋盘的列数
     */
    private static int X;

    /**
     * 棋盘的行数
     */
    private static int Y;

    /**
     * 标记棋盘的各个位置是否被访问过
     */
    private static boolean[] visited;

    /**
     * 标记棋盘的所有位置是否都被访问过，如果为 true，表示成功
     */
    private static boolean finished;

    /**
     * 马踏棋盘算法（骑士周游问题）
     *
     * @param chessboard 棋盘
     * @param row        马当前位置行，从 0 开始
     * @param column     马当前位置列，从 0 开始
     * @param step       马当前走的第几步，初始位置就是第 1 步
     */
    public void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        // row = 4   X = 8   column = 4  4 * 8 + 4 = 36
        visited[row * X + column] = true; // 标记该位置已经访问
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> points = next(new Point(column, row));
        sort(points);
        // 遍历 points
        while (!points.isEmpty()) {
            Point point = points.remove(0); // 取出下一个可以走的位置
            // 判断该点是否已经访问过
            if (!visited[point.y * X + point.x]) { // 说明还没有访问过
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        // 判断马是否完成了任务 ，使用 step 和应该走的步数比较，如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        // 说明：step < X * Y 成立的情况有两种
        // 1. 棋盘到目前为止，仍然没有走完
        // 2. 棋盘处于回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置（Point 对象），计算马还能走哪些位置（Point），将结果放入到一个集合中（ArrayList），最多有 8 个位置
     *
     * @param curPoint 当前位置
     * @return 从当前位置还能走哪些位置（ArrayList<Point>）
     */
    private ArrayList<Point> next(Point curPoint) {
        // 创建一个 ArrayList
        ArrayList<Point> points = new ArrayList<>();
        // 创建一个 Point
        Point point = new Point();
        // 判断马是否可以走 5 位置
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 6 位置
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 7 位置
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 0 位置
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 1 位置
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 2 位置
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 3 位置
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        // 判断马是否可以走 4 位置
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }
        return points;
    }

    /**
     * 根据当前这一步的所有的下一步的下一步的集合数量，对当前这一步的所有的下一步，进行非递减排序，减少回溯的次数
     *
     * @param ps 当前位置的下一步可走的位置的集合
     */
    private void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }
}
