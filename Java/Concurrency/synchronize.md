### wait notify notifyAll
wait和notify只能在synchronized中使用，调用wait后，线程会释放锁并阻塞，
调用notifyAll后会唤醒所有阻塞线程，浸润runnable状态