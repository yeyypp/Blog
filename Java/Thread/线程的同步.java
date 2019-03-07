public class ThreadTest implements Runnable {
    private int numberOfTickets = 50;

    /**
     *
     * synchronized 同步
     * 1.确保线程互斥的访问同步的代码 2.保证共享变量的修改可以及时可见（数据直接刷新到主存） 3.解决重排序，
     * 保证获得锁的线程的操作先于之后获得
     * 实现方法
     * 1.修饰普通方法 锁为调用此方法的对象
     * 2.修饰代码块 锁synchronized（）参数里的对象
     * 3.修饰静态方法 锁为类锁，针对一切此类的实例
     *
     * volatile
     * 线程在具体执行时，会先从主存拷贝数据到线程本地，当操作完数据后再刷到主存
     * 修饰的共享变量，保证了变量的可见性，是指当修改了主内存的数据后，会直接刷新主内存的数据，
     * 并告知其他线程中工作内存的此数据的拷贝无效，但不保证原子性，原子性是此操作不可分割
     *
     * 禁止指令重排序
     *
     * 用在对变量的写操作不依赖于当前值的情况，如boolean
     * 当出现i++这种情况时就不能保证
     *
     * Lock
     * ReentrantLock ReentrantReadWriteLock
     * 可以实现读写锁
     *
     * synchronized 是java关键字，lock是java内的接口
     *
     * yield()使当前运行的线程转为就绪状态
     * 当某个线程调用join（）方法时，可以使当前线程阻塞，先执行调用join的线程
     *
     *
     * 线程安全的实现方法
     * 1.互斥同步
     * 信号量或者synchronized
     */
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                System.out.println("I'm locked");
            }
        }
    }
}