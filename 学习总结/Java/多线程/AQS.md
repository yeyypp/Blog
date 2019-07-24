# AbstractQueueSynchronizer
队列同步器，是构建锁及其他同步工具的基础，支持独占和共享两种模式

## 实现
aqs内部通过一个状态位state和一个fifo队列实现。

1. 使用一个int值state来实现共享状态，子类通过重写tryAcquire(),tryAcquireShared()