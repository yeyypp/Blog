import java.util.Stack;

public class Main {
    /**
     * wait,notify,notifyAll都是Object的final方法，无法被重写。
     * 使用的前提都是先获得锁，所以必须在synchronized中使用
     * wait释放当前锁，让出cpu，进入等待状态。
     * notify/notifyAll 唤醒一个或多个等待的线程，当线程获得锁之后，从wait后继续执行
     */

    class Producer implements Runnable {
        private Stack<String> repo;

        public Producer(Stack<String> repo) {
            this.repo = repo;
        }

        @Override
        public void run() {
            synchronized (repo) {
                while (true) {
                    if (repo.size() == 10) {
                        try {
                            repo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    repo.add("computer");
                    repo.notifyAll();
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
            synchronized (repo) {
                /........./
            }
        }
    }
}