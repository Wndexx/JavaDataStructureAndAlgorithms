package com.wndexx.tree.huffmancode;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 * 这里压缩后的字节数组最后一位存放的是压缩后的二进制字符串最后一个 byte 高位 0 的个数
 *
 * @author wndexx 2022-04-05 12:37
 */
public class HuffmanCode {

    /**
     * 测试文件解压
     */
    @Test
    public void testUnZip() {
        String zipFile = "3.zip";
        String destFile = "3.bmp";
        unZipFile(zipFile,destFile);
        System.out.println("解压成功");
    }

    /**
     * 文件解压
     *
     * @param zipFile  准备解压的文件
     * @param destFile 将文件解压到哪个路径
     */
    public void unZipFile(String zipFile, String destFile) {
        // 定义文件的输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取 byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取 HuffmanCodes
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将 bytes 数组写出到目标文件
            os = new FileOutputStream(destFile);
            // 写出数据到 destFile 文件
            os.write(bytes);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 测试文件压缩
     */
    @Test
    public void testZipFile() {
        String srcFile = "src.bmp";
        String destFile = "3.zip";
        zipFile(srcFile, destFile);
        System.out.println("压缩文件成功");
    }

    /**
     * 文件压缩
     * 当文件中字节出现的次数越接近，压缩效率越低
     *
     * @param srcFile  传入的希望压缩的文件的全路径
     * @param destFile 将压缩后的文件放入的路径
     */
    public void zipFile(String srcFile, String destFile) {
        // 创建输出流
        OutputStream fos = null;
        ObjectOutputStream oos = null;
        // 创建文件的输入流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的 byte[]
            // fis.available() 返回文件大小
            byte[] b = new byte[fis.available()];
            // 读取文件
            int len = fis.read(b);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            fos = new FileOutputStream(destFile);
            // 创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(fos);
            // 把赫夫曼编码后的字节数组写出到压缩文件
            oos.writeObject(huffmanBytes);
            // 这里以对象流的方式写出赫夫曼编码，便于恢复源文件
            // 注意一定要把赫夫曼编码写出到压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试解压
     */
    @Test
    public void test5() {
        // String str = byteToBitString((byte) 124);
        // System.out.println(str);
        // int i = Integer.parseInt("110", 2);
        // System.out.println(i);
        String cont = "i ike like like java do you like a javal";
        byte[] contBytes = cont.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contBytes);

        System.out.println(huffmanCodes);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串 = " + new String(sourceBytes));
    }

    // 如何将数据进行解压（解码）
    // 思路
    // 1. 将 huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //    先转成赫夫曼编码对应的二进制的字符串 "10101000101111111100100010111111110010001011..."
    // 2. 将赫夫曼编码对应的二进制的字符串 "10101000101111111100100010111111110010001011..." => 对照赫夫曼编码 => "i like like like java do you like a java"

    /**
     * 步骤一：
     * 将一个 byte 转成一个二进制的字符串
     * int 型的数对应的二进制的补码直接截断就可以得到强转后的 byte 型的数对应的补码
     * 字节数组的字节如果是正数，需要考虑补高位的问题；数组的最后一个字节需要另外考虑，例如 原来二进制字符串最后只剩下 001，则需要补两个 0；如果是1，则不需要补0；如果是负数，一定是 8 位，不需要
     *
     * @param flag 表示是否需要补高位，如果是 true ，表示需要补高位；如果是 false，表示不补
     * @param b    给定字节
     * @return 该 b 对应的二进制字符串，注意是按补码返回
     */
    private String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b; // 将 b 转成 int
        // 如果是正数，需要补高位
        if (flag) {
            temp |= 256; // 按位与 256 => 1 0000 0000 | 0000 0001 =>  1 0000 0001  如果是负数，例如 -5 => 1111 1011 | 1 0000 0000 => 1 1111 1011  可以保证后 8 位没有问题
        }
        String str = Integer.toBinaryString(temp); // 返回的是 temp 对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 步骤二：对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码压缩后的字节数组
     * @return 原来的字符串对应的字节数组
     */
    private byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到 huffmanBytes 对应的二进制的字符串，形式 "10101000101111111100100010111111110010001011..."
        StringBuilder stringBuilder = new StringBuilder();
        // 将 byte 数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length - 1; i++) {
            // 判断是不是倒数第二个字节
            if (i == huffmanBytes.length - 2 && huffmanBytes[i] >= 0) {
                for (int j = 0; j < huffmanBytes[huffmanBytes.length - 1]; j++) {
                    stringBuilder.append(0);
                }
                String s = byteToBitString(false, huffmanBytes[huffmanBytes.length - 2]);
                stringBuilder.append(s);
                break;
            }
            String s = byteToBitString(true, huffmanBytes[i]);
            stringBuilder.append(s);
        }
        // System.out.println("赫夫曼字节数组对应的二进制字符串 = " + stringBuilder.toString());

        // 2. 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换，因为要反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // System.out.println("map = " + map);

        // 创建一个集合，存放 byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解成就是索引，扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 连续取出一个或多个 '1' 或 '0'
                String key = stringBuilder.substring(i, i + count); // i 不动，让 count 移动
                b = map.get(key);
                if (b == null) { // 说明没有匹配到
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; // i 向左移动 count 位
        }
        // 当 for 循环结束后，list 中就存放了所有的字符 "i like like like java do you like a java"
        // 把 list 中的数据放入到 byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 测试压缩
     */
    @Test
    public void testHuffmanZip() {
        String cont = "i like like like java do you like a java";
        byte[] contBytes = cont.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contBytes);
        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodeBytes) + "， 长度= " + huffmanCodeBytes.length); // 17
    }

    /**
     * 将上述步骤整合封装成一个利用赫夫曼编码进行数据压缩的方法
     *
     * @param bytes 原始的字节数组
     * @return 经过赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    public byte[] huffmanZip(byte[] bytes) {
        // 步骤1：将字节数组中的数据包装成 Node ，封装到 list 中
        List<Node> nodes = getNodes(bytes);
        // 步骤2：根据 nodes 创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        // 步骤3：根据赫夫曼树生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        // 步骤4：根据赫夫曼编码对原始的字节数组进行压缩
        return zip(bytes, huffmanCodes);
    }

    /**
     * 测试赫夫曼编码
     */
    @Test
    public void test1() {

        String cont = "i like like like java do you like a java";
        byte[] contBytes = cont.getBytes();
        System.out.println(contBytes.length); // 40

        List<Node> nodes = getNodes(contBytes);
        System.out.println(nodes);

        // 测试赫夫曼树的创建
        System.out.println("赫夫曼树");
        Node root = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        preOrder(root);

        // 测试是否生成了对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        System.out.println("生成的赫夫曼编码表" + huffmanCodes);

        // 测试
        byte[] huffmanCodeBytes = zip(contBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes)); // 17

        // 发送 huffmanCodeBytes 数组
    }

    /**
     * 步骤一：接收一个字节数组，获得包含 Node 的 List
     *
     * @param bytes 字节数组
     * @return 返回包含 Node 的 List
     */
    private List<Node> getNodes(byte[] bytes) {
        // 1. 创建一个 ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        // 2. 遍历 bytes，统计每个 byte 出现的此时次数 => map[key,value]
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            // map 还没有这个字符数据
            map.merge(b, 1, Integer::sum);
        }
        // for (byte b : bytes) {
        //            Integer count = map.get(b);
        //            if (count == null) { // map 还没有这个字符数据
        //                map.put(b, 1);
        //            } else {
        //                map.put(b, count + 1);
        //            }
        //        }
        // 3. 把每个键值对转成一个 Node 对象，并加入到 nodes 集合
        // 遍历 map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 步骤二：创建赫夫曼树
     *
     * @param nodes 给定的 List
     * @return 赫夫曼树的根结点
     */
    private Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 从小到大
            Collections.sort(nodes);
            // 取出第一棵最小的二叉树
            Node left = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node right = nodes.get(1);
            // 创建一颗新的二叉树，它的根结点没有 data，只有权值
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            // 将已经处理过的两棵二叉树从 nodes 删除
            nodes.remove(left);
            nodes.remove(right);
            // 将新的二叉树加入到 nodes
            nodes.add(parent);
        }
        // nodes 最后的结点就是赫夫曼树的根结点
        return nodes.get(0);
    }

    // 步骤三： 生成赫夫曼树对应的赫夫曼编码
    // 思路：
    // 1. 将赫夫曼编码存放在 Map<Byte,String> 中，形式 32 -> 01 等等
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2. 在生成赫夫曼编码表时，需要去拼接路径，定义一个 StringBuilder 存放某个叶子结点的路径
    static StringBuilder sb = new StringBuilder();

    /**
     * 重载 getCodes() 方法
     *
     * @param root 传入的结点
     * @return 赫夫曼编码
     */
    private Map<Byte, String> getCodes(Node root) {
        if (root == null)
            return null;
        // 处例 root
        getCodes(root, "", sb);
        return huffmanCodes;
    }

    /**
     * 得到传入的 node 结点的所有叶子结点的赫夫曼编码，存放到 huffmanCodes
     *
     * @param node 传入的结点
     * @param code 路径：左子结点是 0，右子结点是 1
     * @param sb   拼接路径
     */
    private void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder builder = new StringBuilder(sb);
        // 将 code 加入到 builder
        builder.append(code);
        if (node != null) { // 如果 node == null 不处理
            // 判断当前 node 是叶子结点还是非叶子结点
            if (node.data == null) { // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", builder);
                // 向右递归
                getCodes(node.right, "1", builder);
            } else { // 叶子结点
                // 就表示找到了某个叶子结点的最后
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    /**
     * 步骤四：
     * 将字符串对应的 byte[] 数组，通过生成的赫夫曼编码表，返回压缩后的 byte[]
     * 举例：String cont = "i like like like java do you like a java"; => byte[] contBytes = cont.getBytes();
     * 返回的是字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes，即 8 位对应一个 byte，放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000（补码） => byte [推导 10101000 => 10101000 -1 => 10100111（反码） => 11011000（原码）=> -88]
     *
     * @param bytes        字符串对应的 byte[] 数组
     * @param huffmanCodes 赫夫曼编码表
     * @return 压缩后的 byte[]
     */
    private byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1. 利用 huffmanCodes 将 bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历 bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        // System.out.println("测试 stringBuilder = " + stringBuilder);

        // 将 "10101000101111111100100010..." 转成 byte[]

        // 统计返回的 byte[] huffmanCodeBytes 长度
        // 或 int len = (stringBuilder.length() + 7) / 8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        len += 1; // 这里最后增加一个 byte，用于记录最后一个 byte 高位 0 的个数，如果全是 0，不包括最后一位

        // 创建存储压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录是第几个 byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为是每 8 位对应一个 byte，所以步长 + 8
            String strByte;
            if (i + 8 >= stringBuilder.length()) {
                byte zeroCount = getZeroCount(stringBuilder.substring(i)); // 获得最后一个 byte 高位 0 的个数
                huffmanCodeBytes[len - 1] = zeroCount;
            }
            if (i + 8 > stringBuilder.length()) { // 剩余位数，不够 8 位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将 strByte 转成一个 byte，放入到 huffmanCodeBytes
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    @Test
    public void testGetZeroCount() {
        byte zeroCount = getZeroCount("001");
        System.out.println(zeroCount);
    }

    /**
     * 获得一个二进制字符串高位 0 的个数
     * 如果全是 0，不包括最后一位
     *
     * @param s 二进制字符串
     * @return 高位 0 的个数
     */
    private byte getZeroCount(String s) {
        byte count = 0;
        int i = 0;
        while (s.charAt(i) == '0' && i++ < s.length() - 1) {
            count++;
        }
        return count;
    }

    @Test
    public void test2() {
        String strByte = "10101000";
        System.out.println((byte) Integer.parseInt(strByte, 2)); // -88
    }

    @Test
    public void test4() {
        // String str = "110";
        // StringBuilder sb = new StringBuilder(str);
        // String s = str.substring(0, 8); // 字符串角标越界
        // System.out.println(s);
    }

    /**
     * 前序遍历
     *
     * @param root 根结点
     */
    public void preOrder(Node root) {
        if (root == null) {
            System.out.println("赫夫曼树为空，不能遍历");
            return;
        }
        root.preOrder();
    }
}

/**
 * Node 结点
 */
class Node implements Comparable<Node> {
    Byte data; // 存放数据（字符）本身，'a' => 97 ' ' => 32
    int weight; // 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }
}