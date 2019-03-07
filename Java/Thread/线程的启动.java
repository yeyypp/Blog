import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 线程的实现方式
 * 线程运行状态：创建，start后变为就绪状态，此时并不代表线程已经运行，在获得cpu时间片时运行，变为运行状态
 *              ，阻塞状态，死亡状态。
 *              阻塞状态分为三种：
 *              1.等待阻塞：运行的线程执行wait()方法后，JVM会把该线程放入等待池中。
 *              2.同步阻塞：运行的线程在获取对象的同步锁时，该同步锁已被别的线程占用，JVM会把该线程放入锁池中。
 *              3.其他阻塞：运行的线程执行sleep或join方法时，或遇到i、I/O阻塞时
 *
 */
public class ThreadTest{

    /**
     * 继承Thread类
     */

    static class threadTest extends Thread {
        private String name;
        private Object obj;

        public threadTest(String name, Object obj) {
            this.name = name;
            this.obj = obj;
        }

        @Override
        public void run() {
            synchronized (obj) {

                for (int i = 0; i < 10; i++) {
                    System.out.println(name);
                }
            }

        }
    }

    /**
     * 实现Runnable
     *
     * 实现Runnable接口比继承Thread类所具有的优势：
     * 1）：适合多个相同的程序代码的线程去处理同一个资源
     * 2）：可以避免java中的单继承的限制
     */

    static class threadTest2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Method 2");
            }
        }
    }

    /**
     * 实现Callable类，可以有返回值
     */

    static class threadTest3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 10; i++) {
                System.out.println("Method 3");
            }
            return "finish";
        }
    }

    public static void main(String[] args) throws Exception {
        Object obj = new Object();

        practice.ThreadTest.threadTest t1 = new practice.ThreadTest.threadTest("t1",obj);
        practice.ThreadTest.threadTest t2 = new practice.ThreadTest.threadTest("t2", obj);
        Thread tg = new Thread(new practice.ThreadTest.threadTest2());
        FutureTask<String> task = new FutureTask<>(new practice.ThreadTest.threadTest3());
        Thread t3 = new Thread(task);
        /**
         *  System.out.println(task.get());
         *  此时get() 会一直等待返回值，造成阻塞
         */

        t2.start();

        t1.start();
        /**
         *  t2.yield()会让t2变为runnable状态，不会释放锁
         *  t1.join（）会让t1先执行，但如果没有获得锁，则被阻塞
         */



    }


}
