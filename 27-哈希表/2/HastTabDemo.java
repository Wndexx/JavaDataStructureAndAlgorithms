package com.wndexx.hashtable1;

import java.util.Scanner;

/**
 * 测试类
 *
 * @author wndexx 2022-03-30 11:56
 */
public class HastTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTable = new HashTab(7);

        // 菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：   添加雇员");
            System.out.println("find：  查找雇员");
            System.out.println("delete：删除雇员");
            System.out.println("list：  显示雇员");
            System.out.println("exit：  退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("输入 id：");
                    int id = scanner.nextInt();
                    System.out.print("输入 name：");
                    String name = scanner.next();
                    // 创建雇员
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "find":
                    System.out.print("输入 id：");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "delete":
                    System.out.print("输入 id：");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
