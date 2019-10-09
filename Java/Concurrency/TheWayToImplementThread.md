# The Way To Implement Thread
- Inherit Thread
```
public class task extends Thread {
    @Override
    public void run() {}
    }
```

- Implements Runnable
```
public class task implements Runnable {
    @Override
    public void run() {}
    }
```

- Implements Callable
```
private static class task implements Callable<Integer> {
        private int input;

        public task(int input) {
            this.input = input;
        }

        @Override
        public Integer call() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input * input;
        }
    }
    
Future<Integer> future = service.submit(new task(4));
        while (!future.isDone()) {
            try {
                System.out.println("It is still working....");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
```

- [CompletableFuture](https://www.callicoder.com/java-8-completablefuture-tutorial/)