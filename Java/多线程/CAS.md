# CAS

CAS Compare and Swap,是队列同步器，原子类底层操作的实现

- 分析
    
    CAS操作中有三个数，内存值V，旧的预期值A，要更新的值B，当V与A相等时才会
    更新内存值为B。
    ```
    /**
     * Atomically updates Java variable to {@code x} if it is currently
     * holding {@code expected}.
     *
     * <p>This operation has memory semantics of a {@code volatile} read
     * and write.  Corresponds to C11 atomic_compare_exchange_strong.
     *
     * @return {@code true} if successful
     */
    @ForceInline
    public final boolean compareAndSwapInt(Object o, long offset,
                                           int expected,
                                           int x) {
        return theInternalUnsafe.compareAndSetInt(o, offset, expected, x);
    }
    ```
- CAS问题

    - 循环时间太长
        
        自旋CAS长时间不成功，会一直占用CPU
        
     - 只能保证一个共享变量
     - ABA问题
        用版本号解决
        