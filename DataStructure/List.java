public class Main {
    /**
     * 1.数组与ArrayList区别
     * 数组:最高效,但容量固定
     * ArrauList:容量可动态增长,但牺牲效率
     * 因为在每次执行add方法时都要检查容量是否够用,不够则扩容
     *
     *
     *2.ArrayList和LinkedList都实现了List接口，有以下的不同点： 
     * 1、arraylist基于数组实现,linkedlist基于链表实现
     * 随机访问时array优于linkedlist,因为linked需要挨个遍历元素,array可通过下标直接获取 
     * 2、一般说相对于ArrayList，LinkedList的插入，添加，删除操作速度更快，因为当元素被添加到集合任意位置的时候，
     * 不需要像数组那样重新计算大小或者是更新索引。 
     *
     *  linkedlist存储需要的空间更大,因为需要存两个指向前后节点的变量
     *
     *  linkedlist 可用座双向队列 poll()拿出队顶 pollLast()拿出队尾的元素
     */


}