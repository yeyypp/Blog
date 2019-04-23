public class Main {
    /**
     * java nio
     * 是面向buffer channels
     *
     * buffer
     * 数据的容器
     *
     * channel
     * 代表与能执行io操作实体的连接
     *
     * selector and selection keys
     * 用于选择可以执行io操作的channel
     */

    /**
     * 作者：stevenczp
     * https://www.cnblogs.com/stevenczp/p/7506280.html
     * 1. 先申请一块临时的direct memory
     *
     * 2. 调用native的FileDispatcherImpl.pread0或者FileDispatcherImpl.read0，将step1中申请的direct memory的地址传进去
     *
     * 3. jvm调用Linux提供的read或者pread系统调用，传入direct memory对应的内存空间指针，以及正在操作的fd
     *
     * 4. 触发中断，进程从用户态进入到内核态（1-3步全是在用户态中完成）
     *
     * 5. 操作系统检查kernel中维护的buffer cache是否有数据，如果没有，给磁盘发送命令，让磁盘将数据拷贝到buffer cache里
     *
     * 6. 操作系统将buffer cache中的数据复制到step3中传入的指针对应的内存里
     *
     * 7. 触发中断，进程从内核态退回到用户态（5-6步全在内核态中完成）
     *
     * 8. FileDispatcherImpl.pread0或者FileDispatcherImpl.read0方法返回，此时临时创建的direct memory中已经有用户需要的数据了
     *
     * 9. 将direct memory里的数据复制到heap memory中（这中间又要调用Unsafe里的一些方法，例如copyMemory）
     *
     * 10. 现在heap memory中终于有我们想要的数据了。
     *
     * 总结一下，数据的流转过程是：hard disk -> kernel buffer cache -> direct memory -> heap memory
     *
     * 中间调用了一次系统调用，触发了两次中断。
     *
     * 流程看起来相当复杂，有优化的办法吗？当然是有的：
     *
     * a. 可以直接使用direct memory作为缓冲区，这样就砍掉了direct memory -> heap memory的耗费
     *
     * b. 也可以使用内存映射文件，也就是FileChannel.map，砍掉中间的kernel buffer cache这一段
     */

    /**
     * Buffer
     * position limit capacity
     * capacity：buffer的大小，创建buffer时指定
     * limit：第一个不应该写或者读的索引
     * position：应该写或者读的索引
     * mark：the index to which its position will be reset when the reset method is invoked
     *
     * allocateDirect() allocate a new direct buffer
     * allocate() allocate new buffer
     * clear() clear the buffer limit = capacity, position = 0
     * flip() prepare for reading limit = position, position = 0
     * rewind() prepare for re-reading position = 0
     * mark() mark = position
     * reset() position = mark
     * wrap() wrap a array in a buffer
     * get() put()
     * hasRemaining()
     */

    /**
     * Channels
     * can be blocking or non-blocking
     *
     * For TCP connection
     * SocketChannel
     * ServerSocketChannel
     *
     * For UDP
     * DatagramChannel
     *
     * For file
     * FileChannel
     */

    /**
     * SocketChannel
     * each SocketChannel is associated with a Socket object
     *
     * SocketChannel sc = SocketChannel.open(new InetSocketAddress(host, port));
     *
     * SeverSocketChannel
     * eahc SeverSocketChannel is associated with a SeverSocket
     *
     * SeverSocketChannel ssc = SeverSocketChannel.open();
     * SeverSocket severSocket = ssc.socket();
     * severSocket.bind(new InetSocketAddress(port));
     */

    /**
     * Selectors
     * is used to select a channel which is ready to communicate
     *
     * select() blocking select, return a set of keys whose channels is ready
     * selectNow() non-blocking select,returns zero if no channels are ready
     *
     */
}