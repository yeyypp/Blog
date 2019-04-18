public class Main {
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

    /**
     * 懒汉
     * 双锁检验
     * 1.变量需要用volatile修饰，因为singleton = new Singleton()这个操作不是原子的，可能在当声明引用时，被别的线程察觉到，
     * volatile保证对singleton的操作全部执行完，当别的再读取时即是指向new的
     */

    public class Singleton {
        private volatile static Singleton singleton;
        private Singleton() {}
        public Singleton getSingleton() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }

}