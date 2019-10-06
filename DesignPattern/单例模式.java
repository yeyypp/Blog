public class Main {
    /**
     * 单例模式确保某一个类在系统中只有一个实例，并且自身创建这个实例
     * 只能有一个实例，自行创建这个实例，
     */

    /**
     * 饿汉
     * 写法简单，线程安全
     * 但无法延迟加载
     * 因为在加载类时，就会在方法区内创建相应的静态变量
     */



    public class Singleton {
        private static Singleton singleton = new Singleton();

        private Singleton() {}

        public Singleton getSingleton() {
            return singleton;
        }
    }

    /**
     * 懒汉
     * 可延迟加载，减少内存消耗
     * synchronized
     * 性能较差
     */

    public class Singleton {

        private static Singleton singleton;

        private Singleton() {}

        public synchronized Singleton getSingleton() {
            if (singleton == null) {
                singleton = new Singleton();
            }
            return singleton;
        }
    }

    public class Singleton {

        private volatile static Singleton uniqueInstance;

        private Singleton() {

        }

        /** 当两个线程同时调用这个方法
         * 看似简单的一段赋值语句：instance= new Singleton()，但是很不幸它并不是一个原子操作，其实际上可以抽象为下面几条JVM指令：
         *
         * 1
         * 2
         * 3
         * memory =allocate();    //1：分配对象的内存空间
         * ctorInstance(memory);  //2：初始化对象
         * instance =memory;     //3：设置instance指向刚分配的内存地址
         * 上面操作2依赖于操作1，但是操作3并不依赖于操作2，所以JVM是可以针对它们进行指令的优化重排序的，经过重排序后如下：
         *
         * 1
         * 2
         * 3
         * memory =allocate();    //1：分配对象的内存空间
         * instance =memory;     //3：instance指向刚分配的内存地址，此时对象还未初始化
         * ctorInstance(memory);  //2：初始化对象
         * 可以看到指令重排之后，instance指向分配好的内存放在了前面，而这段内存的初始化被排在了后面。
         *
         * 在线程A执行这段赋值语句，在初始化分配对象之前就已经将其赋值给instance引用，恰好另一个线程进入方法判断instance引用不为null，然后就将其返回使用，导致出错。
         * @return
         */
        public static Singleton getUniqueInstance() {
            if (uniqueInstance == null) {
                synchronized (Singleton.class) {
                    if (uniqueInstance == null) {
                        uniqueInstance = new Singleton();
                    }
                }
            }
            return uniqueInstance;
        }
    }

}