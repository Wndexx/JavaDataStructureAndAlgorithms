package com.wndexx.hashtable1;

/**
 * 哈希表，管理多条链表
 *
 * @author wndexx 2022-03-30 10:51
 */
public class HashTab {
    /**
     * EmpLinkedList 组成的数组
     */
    EmpLinkedList[] empLinkedListsArray;
    /**
     * 数组长度
     */
    int size;

    /**
     * 构造器
     */
    public HashTab(int size) {
        this.size = size;
        // 初始化 empLinkedListsArray
        empLinkedListsArray = new EmpLinkedList[size];
        // 分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     *
     * @param emp 待添加的雇员
     */
    public void add(Emp emp) {
        // 根据雇员的 id 得到该雇员应当添加到哪条链表
        int empLinkedListNO = hashFu(emp.id);
        // 将 emp 添加到对应的链表
        empLinkedListsArray[empLinkedListNO].add(emp);
    }

    /**
     * 根据 id 查找雇员
     *
     * @param id 输入的 id
     */
    public void findEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFu(id);
        Emp emp = empLinkedListsArray[empLinkedListNO].findEmpById(id);
        if (emp != null) { // 找到
            System.out.printf("在 %d 条链表中找到雇员 id = %d\n", empLinkedListNO + 1, id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    /**
     * 根据 id 删除雇员
     *
     * @param id 待删除雇员的 id
     */
    public void delete(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFu(id);
        boolean isDeleted = empLinkedListsArray[empLinkedListNO].delete(id);
        if (isDeleted) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    /**
     * 遍历 HashTable，即遍历所有的链表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list(i);
        }
    }

    /**
     * 散列函数，通过取模法获得雇员在链表的所在位置
     *
     * @param id 雇员 id
     * @return 雇员在链表的所在位置
     */
    private int hashFu(int id) {
        return id % size;
    }
}

/**
 * HastTable 的链表
 */
class EmpLinkedList {
    /**
     * 头指针，指向第一个雇员，即链表的 head 直接指向第一个 Emp
     * 默认为空
     */
    private Emp head;

    /**
     * 添加雇员到链表
     * 说明：假定当添加雇员时，id 是自增的，即 id 的分配总是从小到大。
     * 因此，将该雇员加入到本链表的最后即可
     *
     * @param emp 雇员
     */
    public void add(Emp emp) {
        // 如果添加的是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果添加的不是第一个雇员，则使用一个辅助的指针，帮助定位到链表的最后
        Emp curEmp = head;
        // 当 curEmp.next = null 时，说明到链表的最后
        while (curEmp.next != null) {
            curEmp = curEmp.next; // 后移
        }
        // 将 emp 加入链表
        curEmp.next = emp;
    }

    /**
     * 根据 id 查找雇员
     *
     * @param id 提供的 id
     * @return 满足条件的雇员；没有找到，返回 null
     */
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) { // 找到
                break; // 这时 curEmp 就指向要查找的雇员
            }
            // 退出
            if (curEmp.next == null) { // 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    /**
     * 根据 id 删除雇员
     *
     * @param id 待删除雇员的 id
     */
    public boolean delete(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return false;
        }
        if (head.id == id) {
            head = head.next;
            return true;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                return true;
            }
            curEmp = curEmp.next;
        }
        return false;
    }

    /**
     * 遍历链表的雇员信息
     *
     * @param i 链表索引
     */
    public void list(int i) {
        if (head == null) { // 说明链表为空
            System.out.println("第 " + (i + 1) + " 条链表为空");
            return;
        }
        System.out.print("第 " + (i + 1) + " 条链表信息为");
        Emp curEmp = head; // 辅助指针
        while (true) {
            System.out.printf(" => id = %d name = %s ", curEmp.id, curEmp.name);
            if (curEmp.next == null) // 说明 curEmp 已经是最后节点
                break;
            curEmp = curEmp.next; // 后移，遍历
        }
        System.out.println();
    }
}

/**
 * 雇员
 */
class Emp {
    public int id;
    public String name;
    public Emp next; // 默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}