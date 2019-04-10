import java.util.Stack;

public class Demo {
    /**
     * wait,notify,notifyAll都是Object的final方法，无法被重写。
     * 使用的前提都是先获得锁，所以必须在synchronized中使用
     * wait释放当前锁，让出cpu，进入等待状态。
     * notify/notifyAll 唤醒一个或多个等待的线程，当线程获得锁之后，从wait后继续执行
     *
     * wait notifyAll 要加在锁的对象上
     */
    public static void main(String[] args) {
        Stack<String> repo = new Stack<>();
        Producer p = new Producer(repo);
        Consumer c = new Consumer(repo);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);
        Thread t3 = new Thread(p);
        Thread t4 = new Thread(c);


        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }



}

class Producer implements Runnable {
    private Stack<String> repo;

    public Producer(Stack<String> repo) {
        this.repo = repo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized(repo) {
                    while (repo.size() > 10) {
                        System.out.println("the P has to wait");
                        repo.wait();
                    }
                    repo.push("item");
                    System.out.println("produce 1 item");
                    Thread.sleep(1000);
                    repo.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Stack<String> repo;

    public Consumer(Stack<String> repo) {
        this.repo = repo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized(repo) {
                    while (repo.size() == 0) {
                        repo.wait();
                    }
                    System.out.println("consume " + repo.pop());
                    Thread.sleep(1000);
                    repo.notifyAll();


                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}