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

    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
    Statement s = c.createStatement();) {

        // 有事务的前提下
        // 在事务中的多个操作，要么都成功，要么都失败

        c.setAutoCommit(false);

        // 加血的SQL
        String sql1 = "update hero set hp = hp +1 where id = 22";
        s.execute(sql1);

        // 减血的SQL
        // 不小心写错写成了 updata(而非update)

        String sql2 = "updata hero set hp = hp -1 where id = 22";
        s.execute(sql2);

        // 手动提交
        c.commit();

    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public class Test {
    public final static String url = null;
    public final static String user = null;
    public final static String password = null;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection c = DriverManager.getConnection(url, user, password);
                Statement s1 = c.createStatement();
                Statement s2 = c.createStatement();
                Scanner in = new Scanner(System.in);
        ) {
            c.setAutoCommit(false);
            ResultSet rs = s1.executeQuery("select * from tableName order by id asc limit 0,10");
            while (rs.next()) {
                int id = rs.getInt(1);
                s2.execute("delete from tableName where id = " + id);
            }

            while (true) {
                String str = in.nextLine();
                if (str.equals("Y")) {
                    c.commit();
                    break;
                }
                if (str.equals("N")) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
public class Hero {
    private String name;
    private int hp;

    public Hero(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }
}
//DAO data access object
public interface DAO {
    Hero get(int id);
    public void add(Hero h);
    public void update(int id);
    public void delete(int id);

}

public class HeroDAOImpl implements DAO {

    public HeroDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://123.123.123.123:3306/demo", "root", "password");
    }

    @Override
    public Hero get(int id) {
        Hero h = new Hero("shuai", 100);
        try (
                Connection c = getConnection();
                Statement s = c.createStatement();
                ) {
            String sql = "select * from tableName where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return h;
    }

    @Override
    public void add(Hero h) {
        String sql = "insert into hero values(null, ?, ?)";
        try (
                Connection c = getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {
            ps.setString();
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}