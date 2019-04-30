public class Main {
    /**
     * 分为collection map
     * collection下有list set queue
     * map 下有hashmap hashtable linkedhashmap treemap
     * list 下有 linkedlist ArrayList
     * set 下有 hashset treeset
     *
     *
     * HashMap 1.7 1.8
     * https://blog.csdn.net/qq_36520235/article/details/82417949
     * 相比hashtable，不同步，map的键值均可以为null
     * 放入的数据是无序的
     * 1.7采用头插法，在扩容时可能会造成死循环
     * 1.8采用尾插法，不会出现死循环
     * 1.8引入红黑树结构，默认在链表大小大于8时，自动转换成红黑树，保持logn查询复杂度
     * 1.8中的数组不会再new时分配，而是在第一次put时，resize（）生成
     * 当size大于数组长度乘以加载因子时，会扩容
     * 1.7 是扩容后插入，1.8是扩容前插入
     *
     *
     * 定位流程
     * 先计算hash值，在用table.length() - 1 & hash得到下标
     * h = key.hashcode()
     * hash = h ^ h >>> 16
     * 为了让数据更分散
     *
     * put 流程
     * 计算好hash值后，table是否初始化，否，则通过resize（）初始化，然后定位，是则直接进行定位
     * h = key.hashcode();
     * hash = h ^ h >>> 16;
     * 下标 = table.length() - 1 & hash;
     * 若不存在，则直接新建节点，存在则比较key是否相等，相等则更新，否则，则判断当前是为链表还是红黑树
     * 是红黑树则遍历，判断key是否相等，相等则更新，遍历完后，没有相等的，则插入节点。
     * 是链表则遍历，判断key是否相等，相等则更新，遍历完后，没有相等的，则链表尾部插入节点，此时判断链表大小是否大于默认值，大于则转为红黑树
     *
     * 扩容流程
     * 当键值对大小大于阈值时（数组长度乘以加载因在）扩容
     * 创建一个原数组大小两倍的新数组
     * 遍历旧数组元素，计算在新数组的位置，转移到新数组上
     * 将新数组引用到table属性上
     * 重新设置扩容阈值
     * 结束
     */

    /**
     * LinkedHashMap
     * 放入的数据是有序的
     * 是因为实现了一个双向链表
     * 会根据添加顺序插入
     */

    /**
     * TreeMap
     * 底层由红黑树实现
     * 保证数据按自然顺序排序
     */

    /**
     * ConcurrentHashMap
     * 线程安全的hashmap
     *
     */

    /****************************************************************************************************************************/

    /**
     * HashSet,LinkedHashSet,TreeSet
     * 底层的实现是相应的map
     * set存储不相同的元素
     * set中put是put 一个key 以及一个 Object
     *
     */

    /****************************************************************************************************************************/

    /**
     * LinkedList ArrayList Vector
     *
     * linkedlist底层实现为链表，插入，删除速度较快，但获取元素较慢
     * arraylist底层实现为数组，获取元素速度快
     *
     * ArrayList扩容
     * 当size == elementData数组大小时，就会发生扩容
     * 会扩容为现在大小的1.5倍
     *
     *
     * 用的最多的为linkedlist，可以作为队列或者栈使用
     * 作为栈
     * Deque<Integer> stack = new LinkedList<>();
     *         stack.push(1);
     *         stack.push(2);
     * 作为队列
     * Deque<Integer> queue = new LinkedList<>();
     *         queue.offer(1);
     *         queue.offer(2);
     *         queue.offer(3);
     *         System.out.println(queue.poll());
     *         System.out.println(queue.pollLast());
     */

    /****************************************************************************************************************************/

    /**
     * PriorityQueue
     * 底层实现是一个最小堆
     * 也可以在构造器中传入Comparator改变排序规则
     */


}