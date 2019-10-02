# interrupted
```
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
当被阻塞的线程被中断后，它会停止阻塞,继续运行之后的代码

Java中线程中断一般有三个方法

1. thread.interrupt()
    
    给目标线程发一个中断信号，线程被打上中断标记
    
2. thread.isInterrupted()
    
    判断目标线程是否被中断，不会清楚标记
    
3. thread.interrupted()

    判断目标线程是否中断，会清楚中断标记
    
一般在使用时需要线程中有处理中断的逻辑，才能中断线程
```
public void run() {
    if (Thread.isInterrupted()) {
        return;
        }
    }
```

线程阻塞时，当收到中断信号后会停止阻塞，消除中断标记，继续执行代码，
所以当需要停止阻塞的线程时，需要重新设置中断标记
```
try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
     }
```

- join
```
Thread t  = new Thread();
t.join()
```

t.join() 将会先等待t执行完

- Simple Threads
```
/**
 * @author Java Tutorials
 * @date 2019/10/2 11:23
 */
public class SimpleThreads {

    public static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " : " + message);
    }

    private static class MessageLoop implements Runnable {

        @Override
        public void run() {
            String[] info = {"hello", "shuai", "you", "are", "the", "best"};

            try {
                for (int i = 0; i < info.length; i++) {
                    Thread.sleep(4 * 1000);
                    threadMessage(info[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long patience = 60 * 60 * 1000;

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop to finish");

        while (t.isAlive()) {
            threadMessage("Still waiting....");
            t.join(1000);
            if ((System.currentTimeMillis() - startTime) > patience && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}
```
