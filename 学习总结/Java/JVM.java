public class Main {
    /**
     * bootstrap 加载jdk核心类库，随着jvm启动而启动
     * extension 负责加载java的扩展类
     * app 系统类加载器，负责加载classpath下的jar包或者class
     * custom
     *
     * 加载时先在本加载器查看是否已经加载了此类，通过查看classloader的classes字段，有的话返回类的实例对象
     * 最后从顶层加载器，加载不成功则子类尝试加载
     *
     * 好处是防止重复加载相同类
     */


    /**
     * 类加载过程
     * 加载   验证 准备 解析   初始化
     * 类加载过程主要是将Class文件加载到虚拟机内存中
     *
     * 加载：通过类的全限定名获得类的二进制字节流，类的信息存放到方法区，在堆中生成类的对象
     *
     * 验证：通过文件格式，元数据，字节码，符号引用四种验证，保证class文件中的二进制信息符合虚拟机规范
     * 准备：会为类变量分配内存设置初始值，数据类型默认的0值
     * 解析：将常量池中的符号引用替换为直接引用
     *
     * 初始化：根据类中所写的程序代码，初始化类变量及其他资源。
     *
     *  class NetworkClassLoader extends ClassLoader {
     *          String host;
     *          int port;
     *
     *          public Class findClass(String name) {
     *              byte[] b = loadClassData(name);
     *              return defineClass(name, b, 0, b.length);
     *          }
     *
     *          private byte[] loadClassData(String name) {
     *              // load the class data from the connection
     *               . . .
     *          }
     *      }
     *
     *      自定义classloader
     *      loadclass，查找是否被加载过，在父类查找
     *      都不行了通过findclass找到相应字节码
     *      通过defineclass加载
     *
     * java 热部署
     * 在不重启虚拟机的情况下，侦测到class文件的变化，更新class的行为
     *
     */

    /**
     * JVM在执行java程序时会把其管理的区域分成几个不同的区域
     * 线程共有
     * 堆，方法区
     * 堆中存放着对象实例
     * 方法区中存放类的具体信息，及常量
     *
     * 线程独有的
     * 虚拟机栈，本地方法栈，程序计数器
     * 程序计数器记录当前线程字节码执行的行号
     * 虚拟机栈
     * 存放局部变量表，方法出口等信息
     * 本地方法栈
     * 存放native方法
     *
     *  垃圾收集，判断
     *
     *  垃圾收集分为minor gc/ full gc
     *  对象分配时会先在eden，大对象会直接进入老年代，eden区满了后，会进行minor gc 通过复制算法将存活的对象放入survivor区，放不了时
     *  直接放入老年代。
     *  存在于survivor区的对象每经历过一次minor gc 会有一个age计数器，到达15时会把这个对象放到老年代。
     *
     *  当老年代空间不足时，会进行full gc，老年代采用采用标记整理法，将活着的对象标记出来，并按内存地址一次排列，在统一清楚死掉的对象。
     *
     *
     * 引用计数法，根搜索法判断对象
     * 什么可以为root，虚拟机栈中引用的对象，方法区中类静态变量引用的对象，常量引用的对象
     *
     * 收集法
     * 标记清楚，先标记不需要的对象，然后统一清楚，会造成大量内存碎片
     * 标记整理，标记出不需要的对象，将其移动到一端，再统一整理
     * 复制算法，将内存分为两块，使用其中一块，将其中活着的对象复制到另一块中，再统一清理
     * 分代收集
     *
     * 垃圾收集器
     * serial
     * 单线程收集器，收集时，需要stw，暂停用户线程，直到收集完毕
     * parNew
     * 多线程收集器，与serial差不多，但是是多线程，可配合cms
     * CMS
     * 最短回收停顿时间
     * 初始标记 stw
     * 并发标记 此时会与用户线程一起执行
     * 重新标记 stw
     * 并发清楚
     * 但采用的标记清楚，可能会造成大量内存碎片，不适合分配大对象
     *
     *
     * System.gc() 是Runtime.gc()的简写，使用后不会立即gc而是告诉虚拟机尽快执行gc
     *
     *
     */

    /**
     * gc root 为虚拟机栈中指向堆中对象的引用
     *
     * 为什么gc要分代
     * 因为大部分gc时需要stop the world 所以一次gc整个堆花的时间太长，不如只收集其中一部分
     *
     *
     */








}