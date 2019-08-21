public class Main {
    /**
     * 什么是线程池：
     * 用一个实例来管理已有的线程，执行任务，并重复利用已有线程。
     *
     * 为什么用：
     * 并发请求非常多，为每一个请求使用一个线程，而线程执行时间又很短，这时就会在创建和销毁线程上浪费大量资源
     * 因为创建和销毁线程的开销很大，可能需要消耗过多内存，通过线程池有效管理，可以重复利用已经有的线程执行任务，提高效率，
     * 控制最大并发数。
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
     *  执行流程：
     *  提交任务 -》 当前线程数小于核心 ？ 创建新线程执行任务 ： 任务队列是否已满 ？ （当前线程数是否小于最大 ？ 创造新线程执行 ： 执行拒绝策略）：加入队列中
     *
     *  cpu密集型可以用线程较少的
     *  io密集型，因为会经常遇到阻塞，所以用线程较多的
     */
}