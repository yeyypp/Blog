import java.sql.*;
import java.util.Scanner;

public class Demo {
    /**
     * 数据库三范式
     * 1.每个属性都不可再分
     * 2.是表必须有一个主键；二是没有包含在主键中的列必须完全依赖于主键，而不能只依赖于主键的一部分
     * 3.非主属性不能依赖于其他非主属性。
     */
    public static final String url = null;
    public static final String user = null;
    public static final String password = null;

    public static void main(String[] args) {
        /**
         * 注册数据库驱动
         * 建立数据库连接
         * 创建statement
         * 执行sql语句
         * 处理结果
         * 关闭资源
         */

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection c = DriverManager.getConnection(url, user, password);
                Statement s = c.createStatement();
        ) {
            String sql = null;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * PreparedStatement使用参数设置，可读性好
         *
         */
        String sql2 = null;
        try (
                Connection c = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = c.prepareStatement(sql2);
        ) {
            ps.setArray(1,null);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * Statement执行10次，需要10次把SQL语句传输到数据库端
         * 数据库要对每一次来的SQL语句进行编译处理
         */
        for (int i = 0; i < 10; i++) {
            String sql0 = "insert into hero values(null," + "'提莫'" + ","
                    + 313.0f + "," + 50 + ")";
            s.execute(sql0);
        }
        s.close();

        /**
         * PreparedStatement 执行10次，只需要1次把SQL语句传输到数据库端
         * 数据库对带?的SQL进行预编译
         *
         * 每次执行，只需要传输参数到数据库端
         * 1. 网络传输量比Statement更小
         * 2. 数据库不需要再进行编译，响应更快
         */
        for (int i = 0; i < 10; i++) {
            ps.setString(1, "提莫");
            ps.setFloat(2, 313.0f);
            ps.setInt(3, 50);
            ps.execute();
        }
    }

    /**
     * 什么是事务：
     * 事务是指一系列对数据库的读写操作，当事务被提交时，需确保事务中的操作全部成功，否则则回滚到事务执行前的状态。
     *
     * 事务具有ACID性质
     * 原子性Atomic:
     *      一个事务包含多个操作，要么全执行，要么全不执行。
     * 一致性Consistency:
     *      事务使得系统从一个一致的状态到另一个一致的状态
     *      强一致性：读操作可以立即读到更新的操作。
     *      弱一致性：提交的更新操作，不一定立即会被读操作读到，此种情况会存在一个不一致窗口，指的是读操作可以读到最新值的一段时间。
     *    最终一致性：是弱一致性的特例。事务更新一份数据，最终一致性保证在没有其他事务更新同样的值的话，最终所有的事务都会读到之前事务更新的最新值。如果没有错误发生，不一致窗口的大小依赖于：通信延迟，系统负载等。
     *    其他一致性变体还有：
     *    单调一致性：如果一个进程已经读到一个值，那么后续不会读到更早的值。
     *    会话一致性：保证客户端和服务器交互的会话过程中，读操作可以读到更新操作后的最新值。
     * 隔离性Isolation
     *      并发事务之间互相影响的程度
     *      脏读：事务A修改了数据，还未提交，事务B读取到了修改的数据，若事务A提交失败，则事务B就产生了脏读。
     *      不可重复读：一个事务内两个相同的查询返回了不一样的数据。在事务A读取数据时，事务B修改了数据，造成了不可重复读。是因为并发时对原有的记录修改造成的。
     *      幻读：在一个事务内，同一个查询多次，返回结果不一样，事务A新增了一个记录，事务B在A提交前后查询的结果不一样。是因为并发时新增记录造成的。
     *      四种隔离级别
     *      Read uncommitted 无法保证任何情况
     *      Read committed 保证不会出现脏读
     *      Repeatable read 保证不会出现脏读，不可重复读。
     *      Serializable 避免脏读，不可重复读，幻读。
     *
     *
     * 存储过程是，一组完成特定功能的sql语句集，经过编译后存在数据库中，通过指定存储过程名，及参数调用
     * 因为是预编译的所以执行速度较快，还可以减轻网络流量，网络中传送的只是调用的存储过程的名字
     *
     *
     * 索引
     * 索引是对数据库表中一列或多列的值进行排序的一种存储结构
     * 一种是基于 B+ 树的索引，一种是基于哈希表的索引。基于哈希表的索引在等值查询上有绝对的优势，
     * 但其他方面就不是很好了。B+ 树是一种多分支的树结构，相比二叉树来说高度降低了很多，能够有效的减少磁盘 IO，所以我们平时使用的都是基于 B+ 树的索引
     *
     * 从数据结构角度
     *
     * 1、B+树索引(O(log(n)))：关于B+树索引，可以参考 MySQL索引背后的数据结构及算法原理
     *
     * 2、hash索引：
     * a 仅仅能满足"=","IN"和"<=>"查询，不能使用范围查询
     * b 其检索效率非常高，索引的检索可以一次定位，不像B-Tree 索引需要从根节点到枝节点，最后才能访问到页节点这样多次的IO访问，所以 Hash 索引的查询效率要远高于 B-Tree 索引
     * c 只有Memory存储引擎显示支持hash索引
     *
     *
     * 从物理存储角度
     *
     * 1、聚集索引（clustered index）
     *
     * 2、非聚集索引（non-clustered index）
     *
     * 从逻辑角度
     *
     * 1、主键索引：主键索引是一种特殊的唯一索引，不允许有空值
     *
     * 2、普通索引或者单列索引
     *
     * 3、多列索引（复合索引）：复合索引指多个字段上创建的索引，只有在查询条件中使用了创建索引时的第一个字段，索引才会被使用。使用复合索引时遵循最左前缀集合
     *
     * 4、唯一索引或者非唯一索引
     * 指索引的值是否可以重复
     *
     * 优点：有效加速查询；
     * 缺点：操作数据时需要对索引进行更新，效率上稍微差一点,因为每次改变数据，都要同时改变索引结构
     * ；索引需要占用一定的空间。
     *
     * B树 b+树区别
     * 是B+树只有叶节点存放数据，其余节点用来索引，而B-树是每个索引节点都会有Data域。
     * 但是B-树的每个节点都有data域（指针），这无疑增大了节点大小，说白了增加了磁盘IO次数（磁盘IO一次读出的数据量大小是固定的，单个数据变大，
     *
     * 为什么用数据库连接池
     * 频繁的建立、关闭连接，会极大的减低系统的性能，使得一个数据库连接可以得到高效、安全的复用，避免了数据库连接频繁建立、关闭的开销。
     */

    /**
     * 创建索引
     * create index index_name on table_name（column_name);
     */

    /**
     * mysql引擎
     * InnoDB
     * 支持事务，支持行级锁
     * MyISAM
     * 主要用于插入和读取，支持表级锁，不支持事务
     * MEMORY
     * 如果只是临时存放数据，数据量不大，并且不需要较高的数据安全性，可以选择将数据保存在内存中的Memory引擎，
     * MySQL中使用该引擎作为临时表，存放查询的中间结果。数据的处理速度很快但是安全性不高。
     */


}