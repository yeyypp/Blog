public class Main {
    /**
     * java 集合类
     * ArrayList LinkedList
     * HashSet TreeSet
     * HashMap TreeMap
     * Stack
     */

/**
 * 1. HashMap是非线程安全的，key，value均可以为null,hashtable线程安全,但key,value不能为null
 * 当两个线程同时put两个key，而刚好经过计算后key的hash值定位到数组中同一位置，此时一个线程在准备put时暂停，另一个
 * 线程完成put，阻塞的线程再次运行时，完成put操作，会导致覆盖掉另一个key。
 *
 * 当两个线程同时put数据时,容量超过大小,进行resize,一个线程进行到transfer时阻塞,另一个线程完成resize操作,此时线程1在进行操作.会造成数组中产生循环链表
 * java8 不会产生死循环,但还是会产生数据丢失的问题,将链表分成两个新链表.一次保存数据
 *
 * 2.内部是数组 + 链表 + 红黑树实现的
 *      当数组内链表长度大于8时，链表变为红黑树结构
 *
 * 3. 取hash值
 * static final int hash(Object key) {   //jdk1.8 & jdk1.7
 *      int h;
 *      // h = key.hashCode() 为第一步 取hashCode值
 *      // h ^ (h >>> 16)  为第二步 高位参与运算
 *      return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
 * }
 *
 * 4.  int threshold;             // 所能容纳的key-value对极限
 *      final float loadFactor;    // 负载因子
 *      int modCount;
 *      int size;
 *      threshold = loadFactor * length
 * 5.put过程
 *  对key去hash值，后计算下标
 *  如果没有发生碰撞，则存储
 *  若发生了则以链表的方式放到最后面，若节点超过8则将链表转换为红黑树
 *  当容量超过threshold时resize
 *
 *  6.resize过程,先是计算新的数组大小,一般为原数组的两倍,然后计算数据的新的hash值
 *
 *  7.hash方法,链地址法,开放定址法(对hash值再求hash)
 *
 *  concurrenthashmap 底层实现是一个segment数组 其中每一个元素是一个hashentry的数组，其中每一个元素是一个链表
 *  通过对单独的segment加锁，实现锁分离，可以实现多个线程共享访问
}