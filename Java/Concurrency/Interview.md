# Interview
- waiting/blocked
(https://stackoverflow.com/questions/28378592/java-thread-state-transition-waiting-to-blocked-or-runnable)

- 
- Print Odd and Even
Design a class as the flag
```
public class OddEvenFlag {
    private static boolean isOdd;

    public OddEvenFlag() {
        this.isOdd = true;
    }

    public void setIsOdd(boolean flag) {
        this.isOdd = flag;
    }

    public boolean getIsOdd() {
        return isOdd;
    }
}

public class Odd implements Runnable {
    private OddEvenFlag flag;
    private int start;

    public Odd(OddEvenFlag flag) {
        this.flag = flag;
        this.start = 1;
    }

    @Override
    public void run() {
        try {
            while (start <= 99) {
                synchronized (flag) {
                    while (!flag.getIsOdd()) {
                        flag.wait();
                    }
                    System.out.println(start);
                    start += 2;
                    flag.setIsOdd(false);
                    flag.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Even implements Runnable {
    private OddEvenFlag flag;
    private int start;

    public Even(OddEvenFlag flag) {
        this.flag = flag;
        this.start = 2;
    }

    @Override
    public void run() {
        try {
            while (start <= 100) {
                synchronized (flag) {
                    while (flag.getIsOdd()) {
                        flag.wait();
                    }
                    System.out.println(start);
                    start += 2;
                    flag.setIsOdd(true);
                    flag.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

- Print ABC
```
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shuai
 * @date 2019/10/22
 */

public class PrintABC {
    private static Lock lock = new ReentrantLock();
    private static Condition isA = lock.newCondition();
    private static Condition isB = lock.newCondition();
    private static Condition isC = lock.newCondition();
    private static volatile int STATE = 1;
    private int outSideState;

    public PrintABC(int outSideState) {
        this.outSideState = outSideState;
    }

    public void print() {
        try {
            while (true) {
                Thread.sleep(1000);
                lock.lock();
                if (STATE == outSideState) {
                    switch (outSideState) {
                        case 1:
                            System.out.println("A");
                            STATE = 2;
                            isB.signal();
                            break;
                        case 2:
                            System.out.println("B");
                            STATE = 3;
                            isC.signal();
                            break;
                        case 3:
                            System.out.println("C");
                            STATE = 1;
                            isA.signal();
                            break;
                    }
                } else {
                    switch (outSideState) {
                        case 1:
                            isA.await();
                            break;
                        case 2:
                            isB.await();
                            break;
                        case 3:
                            isC.await();
                            break;
                    }
            }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author shuai
 * @date 2019/10/22
 */

public class abc implements Runnable {
    private PrintABC printABC;

    public abc(int outSideState) {
        this.printABC = new PrintABC(outSideState);
    }

    @Override
    public void run() {
        printABC.print();
    }
}

/**
 * @author shuai
 * @date 2019/10/15
 */

public class Main {
    public static void main(String[] args) {

        new Thread(new abc(1)).start();
        new Thread(new abc(2)).start();
        new Thread(new abc(3)).start();
    }
}
```

- Producer/Consumer
Producer will work when the buffer is empty, Consumer will work when the buffer is full
```
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shuai
 * @date 2019/10/22
 */

mport java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shuai
 * @date 2019/12/9
 */

public class Buffer {
    private static final int CAPACITY = 10;
    private ReentrantLock lock;
    private Queue<Integer> queue;
    private Condition notFull;
    private Condition notEmpty;

    public Buffer() {
        this.lock = new ReentrantLock();
        this.queue = new LinkedList<>();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void put() {
        try {
            lock.lock();
            while (queue.size() >= CAPACITY) {
                System.out.println("it is full");
                notEmpty.await();
            }
            System.out.println("put 1" + " the current size is " + queue.size());
            queue.offer(1);
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        try {
            lock.lock();
            while (queue.size() == 0) {
                System.out.println("it is empty");
                notFull.await();
            }
            System.out.println("take " + queue.poll() + " the current size is " + queue.size());
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author shuai
 * @date 2019/10/22
 */

public class Put implements Runnable {
    private Buffer buffer;

    public Put(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.put();
    }
}

/**
 * @author shuai
 * @date 2019/10/22
 */

public class Take implements Runnable {
    private Buffer buffer;

    public Take(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.take();
    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuai
 * @date 2019/10/15
 */

public class Main {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);
        Buffer buffer = new Buffer();

        for (int i = 0; i < 2; i++) {
           service.execute(new Take(buffer));
           service.execute(new Put(buffer));
        }

        try {
            Thread.sleep(10 * 1000);
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

- Multi Thread Sorting/Sum
```
import java.util.concurrent.Callable;

/**
 * @author shuai
 * @date 2019/10/22
 */

public class SortingWorker implements Callable<int[]> {
    private String line;

    public SortingWorker(String line) {
        this.line = line;
    }

    @Override
    public int[] call() {
        String[] tem = line.split(" ");
        int[] nums = new int[tem.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.valueOf(tem[i]);
        }

        quickSort(nums);
        return nums;
    }

    private void quickSort(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    private void partition(int[] nums, int start, int end) {
        if (start < end) {
            int pivot  = findPivot(nums, start, end);
            partition(nums, start, pivot);
            partition(nums, pivot + 1, end);
        }
    }

    private int findPivot(int[] nums, int start, int end) {
        int key = nums[start];

        while (start < end) {
            while (start < end && nums[end] >= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author shuai
 * @date 2019/10/22
 */

public class MultiThreadSorting {
    public void sorting(String file) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))
                ) {
            ExecutorService service = Executors.newFixedThreadPool(2);
            String line = null;
            Queue<Future<int[]>> queue = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                Future<int[]> future = service.submit(new SortingWorker(line));
                queue.offer(future);
            }

            int[] ans = null;

            while (!queue.isEmpty()) {
                Future<int[]> future = queue.peek();
                if (future.isDone()) {
                    ans = mergeArray(ans, future.get());
                    queue.poll();
                }
            }
            service.shutdown();
            System.out.println(Arrays.toString(ans));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[] mergeArray(int[] a, int[] b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        int i = 0, j = 0, k = 0;
        int[] ans = new int[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                ans[k++] = a[i++];
            } else {
                ans[k++] = b[j++];
            }
        }

        while (i < a.length) {
            ans[k++] = a[i++];
        }

        while (j < b.length) {
            ans[k++] = b[j++];
        }

        return ans;
    }
}

/**
 * @author shuai
 * @date 2019/10/15
 */

public class Main {
    public static void main(String[] args) {
        String file = "/home/shuai/123.txt";
        MultiThreadSorting multiThreadSorting = new MultiThreadSorting();
        multiThreadSorting.sorting(file);
    }
}
```