# ThreadPool
- Why we use the ThreadPool
    
    - It reduces the cost of creating thread and destroying thread.
    - If we didn't use the threadpool, the system may create a large number
    of threads and consume the system memory.

- Under what circumstances do ThreadPool be used
    
    - processing short time tasks.
    - processing a lot of tasks.
    
- The core parameters of ThreadPool
    - corePoolSize: the number of threads to keep in the pool, even if they are 
    idle
    - maximumPoolSize: the maximum number of threads allow in the pool. When the 
    number of threads == coreSize and workQueue is full, the ThreadPool will 
    create
    new threads.
    - keepAliveTime: when the number of threads is greater than the core, this is 
    the maximum time that excess idle threads will wait for new tasks before 
    terminating
    - unit: time unit for keepAliveTime
    - workQueue: the queue will hold the task. ArrayBlockingQueue, 
    LinkedBlockingQueue
    SynchronousQueue.
    - (Optional)ThreadFactory: used to create threads.
    - (Optional)Rejected Execution Handler:
        
        - when the number of threads has reached the maxPoolSize and the 
        workQueue is
        full, new tasks will be rejects.
        - when the ThreadPool is called shutdown(), it waits the current 
        tasks to finish
        and rejects new tasks.

- Denial Strategy
    
    - DiscardPolicy : discard the new task
    - DiscardOldestPolicy : discard the oldest task in the queue
    - AbortPolicy : it will throw an exception
    - CallerRunsPolicy : as long as the ThreadPool is running, it will make the 
    caller to run the task
    
- the working procedure
    
    ```
    submit new task
    if (the number of threads < coreSize) {
        create new thread to execute task
    } else if (the working queue is not full) {
        add task to queue
    } else if (threads < maxSize) {
        create new thread to execute task
     } else {
        discard
        }
    ```
- how big should the thread pool be
    
    - CPU n(cpu) + 1
    - IO 2 * n(cpu)

- 4 kinds of ThreadPool
    - newFixThreadPool : at point, at most n threads will active, the new task will
    wait in the unbound queue if the number of threads reached n. The threads will
    exist until shutdown.
    - newWorkStealingPool : Creates a work-stealing thread pool using the number of
     {@linkplain Runtime#availableProcessors available processors} 
     as its target parallelism level.
    - newSingleThreadExecutor : Creates an Executor that uses a single worker thread operating
    off an unbounded queue.
    - newCachedThreadPool : it coreSize is 0, maxSize is Integer.MAX_VALUE. it suitable
    for executing multiple short asynchronous task
    - newScheduledThreadPool : It can run task after a short delay.

- ExecutorService methods
    - execute and submit : the difference is submit can return a future object.
    - invokeAll and invokeAny : former return a list of Future object
    