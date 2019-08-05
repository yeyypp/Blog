# ReentrantLock
    
ReentrantLock为可重入锁，实现了Lock接口，内部的具体实现是通过抽象静态类sync实现
同步，sync通过继承队列同步器，实现同步。

ReentrantLock可实现公平锁或者非公平锁
```
