/**
 创建，开启，销毁一个线程的开销很大，如果每有一个任务就单独创建一个线程，会消耗很多资源
 放入线程池中可以让一个线程被重复利用

 为线程栈分配内存，保存每个线程方法调用的栈帧。
 每个栈帧包括本地变量数组、返回值、操作栈和常量池
 一些 JVM 支持本地方法，也将分配本地方法栈
 每个线程获得一个程序计数器，标识处理器正在执行哪条指令
 系统创建本地线程，与 Java 线程对应
 和线程相关的描述符被添加到 JVM 内部数据结构
 线程共享堆和方法区

 参数 核心线程大小，最大线程大小，线程空闲时间及单位，任务缓存队列

 **/
public class Main {
    public class test {
        Executor service = new ThreadPoolExecutor(5, 5, 100, TimeUnit.MILLISECONDS, new
                ArrayBlockingQueue<>(5));
    }
}

