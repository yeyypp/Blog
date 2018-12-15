public class ThreadTest implements Runnable {
    private int numberOfTickets = 50;

    /**
     * synchronized 同步
     * 1.确保线程互斥的访问同步的代码 2.保证共享变量的修改可以及时可见 3.有效解决重排序
     * 实现方法
     * 1.修饰普通方法 锁为调用此方法的对象
     * 2.修饰代码块 锁synchronized（）参数里的对象
     * 3.修饰静态方法 锁为类锁，针对一切此类的实例
     */
    @Override
    public void run() {
        while (true) {
            synchronized (this) {

            }
        }
    }
}