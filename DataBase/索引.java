public class Main {
    /**
     * 为什么用索引
     * 使用索引可以加快查询的速度，避免全表扫描
     * 缺点，会占用一定空间，且降低修改表的效率，因为增加删除都需要修改索引
     *
     *
     * 创建表示添加索引
     * create table user(
     *  uid INT not null,
     *  userName VARCHAR(16) not null,
     *  INDEX [nameIndex](userName(此处可以输入数字代表选择列名的前几个字符作为索引))
     *  );
     *
     *
     * 索引的种类
     * 1.从逻辑角度：
     * 主键索引：不能重复，不能空值，创建表时自动在主键创建
     * alter table user add PRIMARY KEY pk_index(uid);
     * 唯一索引：不能重复，但可以为空
     * alter table user add unique unName（userName）；
     * 普通索引：
     * alter table user add index 。。。
     * 组合索引：多个列构建的索引，列中不能有空值
     * alter table user add index mutilpyIndex(uid, userName);
     *
     * 2.从物理存储角度：
     * 聚簇索引：InnoDB主键使用的索引，在b+tree的叶子节点上存储了主键与行数据，二级索引存储了主键值
     * 非聚簇索引：MYIsam使用的索引，在叶子节点上存储的都是对应数据的地址
     * 覆盖索引：即查找的数据就在索引上
     *
     *
     * 注意最左前缀原则，当在col1 col2 col3上建立索引时，在查找时，首先会按照建立的顺序进行匹配
     * 加入现在 a b c d 四列， 建立了 (a, b, c, d)联合索引，相当于建立了
     * (a) (a,b) (a,b,c) (a,b,c,d) 四个索引，如果查找的列中没有a 则不会使用索引
     * 查找语句中顺序可以变 比如  a = 1 and b = 2 和 b = 2 and a = 1 都会使用索引
     * 索引会按a b, c, 顺序依次查找，当碰到（>, <, between, like)时停止匹配
     *
     * 3.从数据结构角度：
     * 索引的实现
     *
     * 采用B+TREE实现
     * 每个非叶子节点存储索引，叶子节点存储主键值及行数据，而在某些优化的b+tree实现中，叶子节点还有指针指向下一个叶子节点
     * 提高区间查找效率
     * 相比于b-tree优点，因为b树每个节点还需要存储数据，而b+tree只有叶子节点会存有数据，
     * 索引结构的节点通常被设计成以页为大小，每次读取时将整个节点的数据读取到内存中，因为采用b+tree的数据结构，所以可以有效减少io的
     * 次数，因为树的高度较低，只需要较少次数的io，就可以找到需要的数据
     *
     * 什么时候使用索引：
     * 经常作为查询条件where中出现
     *
     * 什么时候会失效：
     * 组合索引时，不能有列的值为null
     * like操作中 不能有%aaa%，但aaa%会使用
     * 在查询条件使用 < , > , !=
     *
     * 尽量不要在值重复率较高的列上建立索引，会增加负担
     */
}