# Semaphore

- 信号量，通常用来限制对某个资源的访问线程数量,当信号量数量设为1时就是互斥锁
```
Semaphotr a = new Semaphore(3);
a.acquire（）
a.release（）
```