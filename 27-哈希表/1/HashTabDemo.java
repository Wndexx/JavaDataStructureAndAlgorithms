package com.wndexx.hashtable;

import org.junit.Test;

/**
 * @author wndexx 2022-03-29 15:46
 */
public class HashTabDemo {
    @Test
    public void test1() {
        HashTable<Employee> table = new HashTable<>();
        table.add(new Employee(1, "张三"));
        table.add(new Employee(2, "李四"));
        table.list();
        System.out.println();
        table.delete(new Employee(1, "张三"));
        table.list();
    }
}
