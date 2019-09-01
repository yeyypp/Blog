# Pring ABC
```
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShuaiYe
 * @date 2019/8/16 21:21
 */
public class Main {
    public volatile static int state = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition printA = lock.newCondition();
        Condition printB = lock.newCondition();
        Condition printC = lock.newCondition();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (state != 2) {
                        printC.await();
                    }
                    Thread.sleep(1000);
                    System.out.println("C");
                    state = 0;
                    printA.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (state != 0) {
                        printA.await();
                    }
                    Thread.sleep(1000);
                    System.out.println("A");
                    state = 1;
                    printB.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (state != 1) {
                        printB.await();
                    }
                    Thread.sleep(1000);
                    System.out.println("B");
                    state = 2;
                    printC.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();


    }
}
```