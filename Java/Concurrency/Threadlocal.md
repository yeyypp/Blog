# Threadlocal
Threadlocal 为提供了线程的局部变量，使得访问每个线程得局部变量都有一个自己的副本

```
Java

public class userID implements Runnable {
    private static final ThreadLocal<SimpleDateFormat> local = new ThreadLocal<>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + local.get().format(new Date()));
    }
}

```