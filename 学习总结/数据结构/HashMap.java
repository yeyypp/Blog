public class Main {
    /**
     * HashMap 1.7 1.8
     * 相比hashtable，不同步，map的键值均可以为null
     * 放入的数据是无序的
     * 1.7采用头插法，在扩容时可能会造成死循环
     * 1.8采用尾插法，不会出现死循环
     * 1.8引入红黑树结构，默认在链表大小大于8时，自动转换成红黑树，保持logn查询复杂度
     * 1.8中的数组不会再new时分配，而是在第一次put时，resize（）生成
     * 当size大于数组长度乘以加载因子时，会扩容
     *
     * 1.8 扩容流程
     *
     * 定位流程
     * 先计算hash值，在用table.length() - 1 & hash得到下标
     * h = key.hashcode()
     * hash = h ^ h >>> 16
     * 为了让数据更分散
     */

    /**
     * LinkedHashMap
     * 放入的数据是有序的
     * 是因为实现了一个双向链表
     * 会根据添加顺序插入
     */

    /**
     * TreeMap
     *
     */

    /**
     * ConcurrentHashMap
     */
}