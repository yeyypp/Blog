public class Main {
    /**
     * 多线程的开销
     * 除了CPU执行上下文切换的消耗以外，线程的执行还将有其他一些资源的消耗，
     * 保存线程A的执行线现场，切换到B
     * 比如:内存同步的开销（线程需要一些内存在维持线程本地栈,每个线程都有本地独立的栈用以存储线程专用数据），
     * 上下文切换的开销（前面已经讲过），线程创建和消亡的开销，以及调度的开销（占用操作系统的一些资源来管理和协调线程），
     *
     * java中线程安全的类
     * hashtable vector juc包下的类
     */
    /**
     * 线程的状态
     * 线程运行状态：创建，start后变为就绪状态，此时并不代表线程已经运行，在获得cpu时间片时运行，变为运行状态
     *              ，阻塞状态，死亡状态。
     *              阻塞状态分为三种：
     *              1.等待阻塞：运行的线程执行wait()方法后，JVM会把该线程放入等待池中。
     *              2.同步阻塞：运行的线程在获取对象的同步锁时，该同步锁已被别的线程占用，JVM会把该线程放入锁池中。
     *              3.其他阻塞：运行的线程执行sleep或join方法时，或遇到i、I/O阻塞时
     */

    /**
     * 线程实现方式
     * 1.继承Thread 2.实现Runnable 3.实现callable
     *
     * 实现Runnable接口比继承Thread类所具有的优势：
     * 1）：适合多个相同的程序代码的线程去处理同一个资源
     * 2）：可以避免java中的单继承的限制
     *
     * 实现Callable类，可以有返回值
     */

    /**
     * 线程间通信
     * 通过全局变量
     * 通过wait,notify
     * 通过condition
     */

    /**
     * 原子性
     * 线程中执行的最小单元，不可中断，不可分割的操作
     * 基本类型的赋值中除long double外，其他都可以保证原子性
     *
     *
     *
     * 保证变量，被修饰时立即刷新回主存，且其他线程中缓存的此变量值无效，使用时必须从主存读取
     *
     * volatile修饰的变量要求在工作内存中，每次使用这个值之前先从主内存刷新到工作内存，保证每次获得的值是最新值
     *                     并保证在修改这个变量后，会立刻刷回到主内存，保证对其他线程可见
     * read load use assign store write
     * volatile可以保证可见性，但不能保证原子性
     * 只能修饰不依赖自身的变量 如boolean
     * 不能修饰自家类 i++ 因为 这一操作并不是原子的，先读取i，赋值加一，在写入i，在读取i时可能有别的线程也在读取，而此时获得的就是最新值
     *
     * 有序性
     * 保证了程序是按代码顺序运行的，比如 if (instance != null) {
     *     return instance;
     * }
     *  1.分配对象空间
     *  2，初始化对象
     *  3，instance指向对象空间
     *  2，3是依赖于1，但2，3，互不依赖，可能造成3先发生，这样在别的线程使用他时，就会出现错误
     *
     *  禁止指令重排序
     *  1.当进行到对volatile变量的读或写时，保证前面的操作已经全部进行完毕
     *
     *  不能把前面的或者后边的语句放到后面或前面执行
     *
     *
     *
     *
     * synchronized 同步
     * 1.确保线程互斥的访问同步的代码 2.保证共享变量的修改可以及时可见（数据直接刷新到主存） 3.解决重排序，
     * 保证获得锁的线程的操作先于之后获得
     * 实现方法
     * 1.修饰普通方法 锁为调用此方法的对象
     * 2.修饰代码块 锁synchronized（）参数里的对象
     * 3.修饰静态方法 锁为类锁，针对一切此类的实例
     *
     * 原理
     * 会在同步快的前后加入两条字节码，一个monitorenter 一个monitorexit
     * 会锁住一个对象，每次执行monitorenter时会将锁计数器加1，同样的执行monitorexit时会将锁减1，0的时候释放锁
     *
     * 偏向锁，轻量级锁，重量级锁
     * 偏向锁：在只有一个线程执行同步代码块时，不会释放这个锁，每次执行时不用重新获得，当出现竞争时才会释放，并升级成轻量级锁
     * 轻量锁：在轻量级锁时，认为在某一时刻，只有一个线程在竞争，他并不会阻塞，而是通过自旋，循环尝试获得这个锁
     * （线程挂起和恢复都需要从用户态转为内核态，这些操作给系统的并发性能带来了很大的压力）
     * 重量级锁：当同时多个线程竞争时，就会升级成重量级锁，此时其他竞争的线程就会阻塞
     *
     * 重量级锁是悲观锁：认为当一个线程执行代码块时，其他线程必定会修改
     * 乐观锁：认为当线程读数据时，其他线程不会修改
     *
     *
     * Lock
     * ReentrantLock ReentrantReadWriteLock
     * 可以实现读写锁
     *
     * synchronized与lock区别
     * synchronized 是java关键字，lock是java内的接口
     * 都是可重入锁：在子类改写父类synchronized时，并执行super.synchron方法，如果不是可重入，则会产生死锁
     * lock可以实现公平锁,synch不行
     * syn在发生异常时会自动释放锁,而lock不会,必须在finally中显示释放
     * syn在没有获取到锁时会发生阻塞,而lock可以立即返回,不会发生阻塞
     *
     *
     *
     *
     * yield()使当前运行的线程转为就绪状态
     * 当某个线程调用join（）方法时，可以使当前线程阻塞，先执行调用join的线程
     *
     *
     * 线程安全的实现方法
     * 临界区 互斥量 信号量
     */

    /**
     * ThreadLocal
     */

    /**
     * https://juejin.im/entry/58fada5d570c350058d3aaad
     * 线程池
     * 线程池主要由四部分实现
     * 管理线程的方法，任务队列，工作线程，任务
     *
     * Executor接口：
     * 只有一个execute方法
     * ExecutorService接口：
     * 继承了Executor，提供了终止线程池的方法
     * ScheduleExecutor扩展了ExecutorService，提供了延时处理任务的方法
     * ThreadPoolExecutor实现了ExecutorService并继承AbstractExecutorService，是主要使用的线程池
     *
     * 常用的四种ThreadPool 底层实现都是ThreadPool
     * newSingleThreadPool：核心，最大都是1，任务队列为无界的linkedblockingqueue的池，适用于逻辑上单线程处理的任务
     * newFixThreadPool：核心等于最大的池子
     * newCachedThreadPool：核心为0，最大为Integer.MAX_VALUE,任务队列只有1，超时时间为60秒，是一个可以随时变化的线程池
     *
     *
     * 为什么使用线程池：
     * 因为创建和销毁线程都需要消耗很多资源，通过线程池可以重复利用已有的线程，如果任务过多使创造的线程过多，可能会用完内存
     * 通过复用线程，提升响应速度，节约资源
     *
     * 参数解释：
     * ctl是对线程池状态和有效线程数控制的一个字段
     *
     * 线程池运行状态：
     * RUNNING：能接受提交的任务，也能处理阻塞队列中的任务
     * SHUTDOWN:关闭状态，不再接受新提交的任务，但可以处理阻塞队列中的任务，线程池调用shutdown()会进入该状态
     * STOP:不接受新任务，不能处理阻塞队列中的任务，会中断正在处理任务的线程，在running或shutdown时调用shutdownNow会进入
     * TIDYING:如果所用任务都已经终止了，线程进入改状态后会调用terminated()进入TERMINATED状态
     * TERMINATED:线程池终止状态
     *
     * 构造方法参数：
     *
     * corePoolSize：核心线程数量
     *  1.如果线程数量小于corePoolSize，则创建新线程处理任务，即使有空闲线程
     *  2.如果线程数量大于corePoolSize 小于 maxumumPoolSize，当workQueue满时才会创建新的线程
     *  3.如果设置core等于max，则线程池大小固定
     *
     *  maximumPoolSize：最大核心数量
     *
     *  workQueue：等待队列，当任务提交时，线程数大于core时，把任务封装成worker对象
     *
     *  keepAliveTime：当线程数超过core但没有新任务提交时，空闲线程不会立即销毁，直到超过keepAliveTime
     *
     *  threadFactory：用来创建新线程，创建的线程具有同等优先级
     *
     *  handler：线程池饱和策略
     *  1.AbortPolicy：直接抛出异常，默认策略
     *  2.CallerRunsPolicy：调用者线程执行任务
     *  3.DiscardOldestPolicy：丢弃阻塞队列中最靠前的任务，并执行当前任务
     *  4.DiscardPolicy：直接丢弃任务
     *
     *  线程池执行流程：
     *  提交任务，如果线程数小于核心线程数，则创建新线程执行任务，不是则判断任务队列是否未满，未满则添加到队列中，满，则判断
     *  线程数是否小于最大，是，则添加新线程执行，不是，则拒绝策略处理
     *
     *
     *  jdk文档建议使用四种线程池
     *  Executors.newFixThreadPool
     *
     */
}