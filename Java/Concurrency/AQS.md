# AbstractQueueSynchronizer
队列同步器：是构建锁及其他同步工具的基础，支持独占和共享两种模式
[https://www.javadoop.com/post/AbstractQueuedSynchronizer](https://www.javadoop.com/post/AbstractQueuedSynchronizer)

- 实现
    
    aqs内部通过一个状态位state和一个fifo队列实现。一般通过继承，实现继承的方法实现同步。
    state 的设置都是通过CAS方法设置  
    
    head表示当前持有锁的线程，阻塞队列不包含head


- 同步状态

    使用一个int类型的变量state来表示同步状态
    - 当 state > 0 时表示获取了锁
    - 当 state = 0 时表示释放了锁
    
- 同步队列

    - 当某个线程获取锁失败后，AQS会将线程的信息构成一个 Node 放在队列里，并阻塞线程
    - 当同步状态释放时，会尝试唤醒头节点的下一个节点，如果下一个节点为空或者
    其waitStatus == 1 既取消了等待，则从tail开始找可以唤醒的节点
    ，唤醒。
    - 队列中的addWaiter和enq内部都是使用Unsafe的CAS方法来实现，使用CAS方法来添加节点
    
    
- 流程
    - 当有线程尝试获取锁时，首先进行accquire（1）方法
    - if(!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSive), arg))
    首先tryAcquire尝试获取锁，若获得锁后则不会进入阻塞队列，如果没有获得
    - 则进行addWaiter，把当前线程包装成Node，如果队列不为空，则用CAS将节点添加到最后一个
    - 如果队列为空或者CAS添加节点失败，则进行enq（）通过死循环添加节点
    - 然后执行accquiredQueued，再次如果当前节点为阻塞队列第一个，并且尝试获取锁成功了
    则将此节点设为head，如果没有获得锁，或者不是第一个节点，则进入
    shouldParkAfterFailedAccquire判断是否需要阻塞
    - 如果前驱节点waitStatus < 0 说明，一切正常，挂起当前线程。如果 > 0 说明
    前驱节点取消了排队，则往前寻找waitStatus < 0 的节点，设为父节点
    如果 == 0，则将前驱节点状态设为-1
    
    - 唤醒流程，tryReleas成功，且节点状态为-1，则唤醒下一个节点

