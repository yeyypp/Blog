# ReentrantLock
    
ReentrantLock为可重入锁，实现了Lock接口，内部的具体实现是通过抽象静态类sync实现
同步，sync通过继承队列同步器，实现同步。

ReentrantLock可实现公平锁或者非公平锁

### 方法
    
- void lock();
        
    获取锁，若没有获得锁则会阻塞
        
- void lockInterruptibly() throws InterruptedException;
    
    在锁的获取中可以中断
        
- boolean tryLock();
        
    尝试获取锁，并立即返回，不会阻塞
- boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
        
    在一定时间内尝试获取锁，超时返回false

- void unlock();
    
    解锁
- Condition newCondition();
        
    获取等待通知组件，该组件和当前所绑定，只有获得了锁才能调用wait（）方法，
        调用后释放锁。

### 于synchronized的区别
    
1. 更灵活，可以在获取不到锁时返回。
2. 提供了条件Condition，灵活的控制线程等待，唤醒
3. 可实现公平锁
4. lock需要主动释放锁，synchronize不用

### Condition
用来阻塞唤醒线程

>一个线程获取锁后，通过调用 Condition 的 #await() 方法，
会将当前线程先加入到当前条件的队列中，
然后释放锁，最后通过 #isOnSyncQueue(Node node) 方法，
不断自检看节点是否已经在 CLH 同步队列了，
如果是则尝试获取锁，否则一直挂起。

>当线程调用 #signal() 方法后，程序首先检查当前线程是否获取了锁，
然后通过#doSignal(Node first) 方法唤醒CLH同步队列的首节点。
被唤醒的线程，将从 #await() 方法中的 while 循环中退出来，
然后调用 #acquireQueued(Node node, int arg) 方法竞争同步状态。

每个condition有自己的一个队列，当调用await时，将创建根据当前线程信息的节点入队，
在调用signal后，会将第一个节点加到aqs队尾，尝试获取锁

```
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShuaiYe
 * @date 2019/8/7 21:56
 */
public class Buffer {
    private ReentrantLock lock = new ReentrantLock();
    private ArrayList<String> repo = new ArrayList<>(10);
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void put(String message) {
        try {
            lock.lock();
            while (repo.size() == 10) {
                System.out.println("the buffer is full");
                notFull.await();
            }
            System.out.println(Thread.currentThread().getName() + " put 1 message");
            repo.add(message);
            System.out.println("the current size is " + repo.size());
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        try {
            lock.lock();
            while (repo.size() == 0) {
                System.out.println("the buffer is empty");
                notEmpty.await();
            }
            System.out.println(Thread.currentThread().getName() + " take 1 message");
            System.out.println("the message is: " + repo.remove(repo.size() - 1));
            System.out.println("the current size is " + repo.size());
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.put("you are the best");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


### Reentreadwritelock