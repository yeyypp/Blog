# CyclicBarrier

- 用于在一个有固定线程数的程序中，多个线程必须互相等待，直到所有线程到达某一
个共同点，才能继续执行。并且可以在线程被释放后重复使用。

- CountDownLatch
    
    与CyclicBarrier相似，但不可以重复设置，可以使一个或几个线程等待某一个或几个
    线程中的任务先执行
```
<p>Another typical usage would be to divide a problem into N parts,
 * describe each part with a Runnable that executes that portion and
 * counts down on the latch, and queue all the Runnables to an
 * Executor.  When all sub-parts are complete, the coordinating thread
 * will be able to pass through await. (When threads must repeatedly
 * count down in this way, instead use a {@link CyclicBarrier}.)
 *
 * <pre> {@code
 * class Driver2 { // ...
 *   void main() throws InterruptedException {
 *     CountDownLatch doneSignal = new CountDownLatch(N);
 *     Executor e = ...
 *
 *     for (int i = 0; i < N; ++i) // create and start threads
 *       e.execute(new WorkerRunnable(doneSignal, i));
 *
 *     doneSignal.await();           // wait for all to finish
 *   }
 * }
 *
 * class WorkerRunnable implements Runnable {
 *   private final CountDownLatch doneSignal;
 *   private final int i;
 *   WorkerRunnable(CountDownLatch doneSignal, int i) {
 *     this.doneSignal = doneSignal;
 *     this.i = i;
 *   }
 *   public void run() {
 *     try {
 *       doWork(i);
 *       doneSignal.countDown();
 *     } catch (InterruptedException ex) {} // return;
 *   }
 *
 *   void doWork() { ... }
 * }}</pre>
 ```