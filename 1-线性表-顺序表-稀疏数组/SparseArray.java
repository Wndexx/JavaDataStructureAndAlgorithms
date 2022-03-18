package com.wndexx.sparsearray;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @author wndexx
 * @create 2022-03-16 18:24
 */
/*
    稀疏数组

        1. 基本介绍：当一个数组 arr 中大部分元素为 ０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组

            这里假设数组的大部分元素是 m

        2. 原数组 -> 稀疏数组：

                (1) 稀疏数组的第一行第一列记录原数组的行数，第一行第二列记录原数组的列数，第一行第三列记录原数组非 m 的个数

                (2) 从稀疏数组的第二行开始，每一行的第一列记录非 m 元素所在的行数，第二列记录所在的列数，第三列记录该元素的值

            稀疏数组 -> 原数组：

                (1) 原数组的行数为稀疏数组第一行第一列的元素值，列数为稀疏数组第一行第二列的元素值

                (2) 从稀疏数组的第二行开始，每一行的第一列记录非 m 元素所在的行数，第二列记录所在的列数，第三列记录该元素的值

        3. 说明：

            (1) 假设 arr 的非 m 的元素有 sum 个，则稀疏数组的初始化为 int[][] sparseArr = new int[sum + 1][3];

            (2) 使用稀疏数组可以缩小程序的规模

*/
public class SparseArray {

    /*
        案例：编写的五子棋程序中，有存盘退出和续上盘的功能

        一个棋盘尺寸为 11 * 11，在第二行第三列有一个黑子，第三行第四列有一个白子，利用稀疏数组存储当前的棋子布局

        假设 0 代表没有棋子，1 代表黑子，2 代表白子

    */

    @Test
    public void test1() {

        // 1. 创建普通的二维数组，存储当前的布局
        int len = 11;
        int[][] chessArr1 = new int[len][len];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 2. 创建稀疏数组
        // (1) 获取原数组中非 0 元素的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0)
                    sum++;
            }
        }

        // (2) 初始化稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        // (3) 根据普通数组 -> 稀疏数组的方法给稀疏数组的元素赋值
        // 稀疏数组的第一行第一列记录原数组的行数，第一行第二列记录原数组的列数，第一行第三列记录原数组非 m 的个数
        sparseArr[0][0] = sparseArr[0][1] = len;
        sparseArr[0][2] = sum;

        // 从稀疏数组的第二行开始，每一行的第一列记录非 m 元素所在的行数，第二列记录所在的列数，第三列记录该元素的值
        int count = 0; // count 用于记录是第几个非 0 数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = sparseArr[i][j];
                }
            }
        }

        // 3. 稀疏数组恢复成原数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <= sum; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
    }

    // @Test
    // public void test1() {
    //     // 创建一个原始的二维数组 11 * 11
    //     // 0：表示没有棋子，1：表示黑子，2：表示蓝子
    //     int chessArr1[][] = new int[11][11];
    //
    //     chessArr1[1][2] = 1;
    //     chessArr1[2][3] = 2;
    //     chessArr1[4][5] = 2;
    //
    //     // 输出原始的二维数组
    //     System.out.println("原始的二维数组：");
    //     for (int[] row : chessArr1) {
    //         for (int data : row) {
    //             // System.out.print(data + "\t");
    //             System.out.printf("%d\t", data);
    //         }
    //         System.out.println();
    //     }
    //
    //     // 将二维数组转稀疏数组
    //     // 1. 先遍历二维数组，得到非 0 数据的个数
    //     int sum = 0;
    //     for (int i = 0; i < chessArr1.length; i++) {
    //         for (int j = 0; j < chessArr1[i].length; j++) {
    //             if (chessArr1[i][j] != 0) {
    //                 sum++;
    //             }
    //         }
    //     }
    //     System.out.println("sum = " + sum);
    //
    //     // 2. 创建对应的稀疏数组
    //     int sparseArr[][] = new int[sum + 1][3];
    //     // 给稀疏数组赋值
    //     sparseArr[0][0] = chessArr1.length;
    //     sparseArr[0][1] = chessArr1[0].length;
    //     sparseArr[0][2] = sum;
    //
    //     // 遍历二维数组，将非 0 的值存放到 sparseArr 中
    //     int count = 0; // count 用于记录是第几个非 0 数据
    //     for (int i = 0; i < chessArr1.length; i++) {
    //         for (int j = 0; j < chessArr1[i].length; j++) {
    //             if (chessArr1[i][j] != 0) {
    //                 count++;
    //                 sparseArr[count][0] = i;
    //                 sparseArr[count][1] = j;
    //                 sparseArr[count][2] = chessArr1[i][j];
    //             }
    //         }
    //     }
    //
    //     // 输出稀疏数组
    //     System.out.println();
    //     System.out.println("得到的稀疏数组");
    //     for (int i = 0; i < sparseArr.length; i++) {
    //         System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
    //     }
    //
    //     // 将稀疏数组恢复成原始的二维数组
    //     // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
    //     int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
    //
    //     // 2. 再读取稀疏数组后几行的数据，并赋给原始的二维数组 即可
    //     for (int i = 1; i < sparseArr.length; i++) {
    //         chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
    //     }
    //
    //     // 输出恢复后的二维数组
    //     System.out.println();
    //     System.out.println("恢复后的二维数组");
    //     for (int[] row : chessArr2) {
    //         for (int data : row) {
    //             // System.out.print(data + "\t");
    //             System.out.printf("%d\t", data);
    //         }
    //         System.out.println();
    //     }
    // }


    @Test
    public void test2() {
        /*
            在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data
            恢复原来的数组时，读取 map.data 进行恢复
        */
        // 1. 创建普通的二维数组，存储当前的布局
        int len = 11;
        int[][] chessArr1 = new int[len][len];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 2. 创建稀疏数组
        // (1) 获取原数组中非 0 元素的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0)
                    sum++;
            }
        }

        // (2) 初始化稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        // (3) 根据普通数组 -> 稀疏数组的方法给稀疏数组的元素赋值
        // 稀疏数组的第一行第一列记录原数组的行数，第一行第二列记录原数组的列数，第一行第三列记录原数组非 m 的个数
        sparseArr[0][0] = sparseArr[0][1] = len;
        sparseArr[0][2] = sum;

        // 从稀疏数组的第二行开始，每一行的第一列记录非 m 元素所在的行数，第二列记录所在的列数，第三列记录该元素的值
        int count = 0; // count 用于记录是第几个非 0 数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 3. 将稀疏数组保存到磁盘 map.dat
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("map.dat"));
            oos.writeObject(sparseArr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3() {
        // 恢复原来的数组时，读取 map.data 进行恢复
        ObjectInputStream ois = null;
        int[][] sparseArr = new int[0][];
        try {
            ois = new ObjectInputStream(new FileInputStream("map.dat"));
            Object o = ois.readObject();
            sparseArr = (int[][]) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 稀疏数组恢复成原数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <= sparseArr[0][2]; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
