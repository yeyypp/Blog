import java.util.Stack;

public class Main {
    /**
     * 需要注意两点
     * 1.wait 和 notify的使用必须是在synchronized代码块中
     * 2.wait必须在循环中使用，因为当wait的线程继续执行时，是从wait之后开始执行，必须重新判断条件是否符合
     *
     * wait,notify,notifyAll都是Object的final方法，无法被重写。
     * 使用的前提都是先获得锁，所以必须在synchronized中使用
     * wait释放当前锁，让出cpu，进入等待状态。
     * notify/notifyAll 唤醒一个或多个等待的线程，当线程获得锁之后，从wait后继续执行
     *
     * wait notifyAll 要加在锁的对象上
     *
     * 每一个对象都可以视为监视器，由一个锁，等待队列，进入队列组成
     * 当调用wait后，线程释放锁，进入等待队列，当调用notify后，将线程放入进入队列，竞争锁
     */

    public class Producer implements Runnable {

        private  Stack<String> repo;

        public Producer(Stack repo) {
            this.repo = repo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (repo) {
                        while (repo.size() == 10) {
                            System.out.println("the repo is full");
                            Thread.sleep(1000);
                            repo.wait();
                        }
                        repo.push("item");
                        System.out.println("add 1 item and the size is " + repo.size());
                        Thread.sleep(1000);

                        repo.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Consumer implements Runnable {

        private  Stack<String> repo;

        public Consumer(Stack repo) {
            this.repo = repo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (repo) {
                        while (repo.isEmpty()) {
                            System.out.println("the repo is empty");
                            Thread.sleep(1000);
                            repo.wait();
                        }
                        System.out.println("consume a item " + repo.pop() + "and the size is " + repo.size());
                        Thread.sleep(1000);

                        repo.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
