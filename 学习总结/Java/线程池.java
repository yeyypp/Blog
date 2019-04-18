public class Main {
    /**
     * 为什么需要线程池
     * 因为创建，开启，销毁一个线程的开销很大，如果每有一个任务就单独创建一个线程，会消耗很多资源
     * 放入线程池中可以让一个线程被重复利用
     * 比如分配每个线程独有的区域
     *
     * Executor顶层接口，只有一个方法
     * void execute（Runnable a）
     */
}
