d    /**
     * 双重校验单例
     */

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

    /**
     * 静态饿汉
     */

    public class Singleton {
        private static final Singleton instance = new Singleton();

        private Singleton();

        public static Singleton getInstance() {
            return instance;
        }
    }

    /**
     * 静态内部,内部类只有在使用时才会加载
     */

    public class Singleton {
        private Singleton() {

        }

        private static class SingletonHolder {
            private static final Singleton instance = new Singleton();
        }

        public static Singleton getInstance() {
            return SingletonHolder.instance;
        }
    }

    /**
     * 代理模式
     * 给某一个对象创建一个代理对象
     * 通过代理对象访问原对象的功能
     * 控制对象的访问
     *
     * 装饰器模式
     * 增强原对象的某一个功能
     */
}