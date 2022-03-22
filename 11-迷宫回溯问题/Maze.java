package com.wndexx.recursion;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 迷宫问题
 * 说明:
 * (1) 小球得到的路径，和程序员设置的找路策略有关即：找路的上下左右的顺序相关
 * (2) 在得到小球路径时，可以先使用(下右上左)，再改成(上右下左)，看看路径是不是有变化
 * (3) 测试回溯现象
 * 思考: 如何求出最短路径? 改变策略，存储路径，比较大小
 *
 * @author wndexx
 * @create 2022-03-22 13:23
 */
public class Maze {
    @Test
    public void testMaze() {
        // 1. 初始化地图
        // (1) 利用 HashMap 记录挡板位置
        HashMap<Integer, Integer[]> hashMap = new HashMap<>();
        hashMap.put(1, new Integer[]{3, 1});
        hashMap.put(2, new Integer[]{3, 2});
        // hashMap.put(3, new Integer[]{1, 2});
        // hashMap.put(4, new Integer[]{2, 2});

        // (2) 生成地图
        int[][] map = InitMap(8, 7, hashMap);

        // 2. 输出地图
        System.out.println("地图的情况");
        showMap(map);

        // 3. 使用递归回溯给小球找路
        setWay(map, 1, 1);
        // setWay2(map, 1, 1);

        // 3. 再次输出地图
        System.out.println("地图的情况");
        showMap(map);
    }

    /**
     * 初始化地图（利用二维数组模拟）
     *
     * @param r 地图的行数
     * @param c 地图的列数
     * @param m 存储挡板，key 代表当前挡板是第几个，value[0] 代表行数，value[1] 代表列数
     * @return 二维数组模拟的地图
     */
    public int[][] InitMap(int r, int c, Map<Integer, Integer[]> m) {
        // 1. 地图
        int[][] map = new int[r][c];

        // 2. 使用 1 表示墙
        // 上下全部置为 1
        for (int i = 0; i < c; i++) {
            map[0][i] = 1;
            map[r - 1][i] = 1;
        }
        // 左右全部置为 1
        for (int i = 0; i < r; i++) {
            map[i][0] = 1;
            map[i][c - 1] = 1;
        }

        // 3. 设置挡板，1 表示
        Set<Map.Entry<Integer, Integer[]>> entrySet = m.entrySet();
        for (Map.Entry<Integer, Integer[]> entry : entrySet) {
            map[entry.getValue()[0]][entry.getValue()[1]] = 1;
        }

        return map;
    }

    /**
     * 输出地图
     *
     * @param map 地图
     */
    public void showMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路：
     * 小球从 (1,1) 出发，如果能到 (6,5)，则说明通路找到
     * 约定：当 map[i][j] 为 0，表示该点没有走过；1 表示墙；2 表示通路，可以走；3 表示该位置已经走过，但是走不通
     * 在走迷宫时，需要确定一个策略：下 -> 右 -> 上 -> 左，如果该点走不通，再回溯
     *
     * @param map 表示地图
     * @param i   位置的行索引
     * @param j   位置的列索引
     * @return 找到通路，返回 true；否则，返回 false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) // 通路已经找到
            return true;
        if (map[i][j] == 0) { // 如果当前这个点还没有走过
            // 按照策略 "下 -> 右 -> 上 -> 左" 走
            map[i][j] = 2; // 假定该点是可以走通
            if (setWay(map, i + 1, j)) // 向下走
                return true;
            if (setWay(map, i, j + 1)) // 向右走
                return true;
            if (setWay(map, i - 1, j)) // 向上走
                return true;
            if (setWay(map, i, j - 1)) // 向左走
                return true;
            map[i][j] = 3; // 该点走不通，是死路
            return false;
        }
        // 如果 map[i][j] != 0，可能是 1，2，3
        return false;
    }

    // 修改找路的策略，改成 "上 -> 右 -> 下 -> 左"
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) // 通路已经找到
            return true;
        if (map[i][j] == 0) { // 如果当前这个点还没有走过
            // 按照策略 "下 -> 右 -> 上 -> 左" 走
            map[i][j] = 2; // 假定该点是可以走通
            if (setWay2(map, i - 1, j)) // 向上走
                return true;
            if (setWay2(map, i, j + 1)) // 向右走
                return true;
            if (setWay2(map, i + 1, j)) // 向下走
                return true;
            if (setWay2(map, i, j - 1)) // 向左走
                return true;
            map[i][j] = 3; // 该点走不通，是死路
            return false;
        }
        // 如果 map[i][j] != 0，可能是 1，2，3
        return false;
    }
}

































