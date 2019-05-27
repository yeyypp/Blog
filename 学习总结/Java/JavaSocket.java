public class Main {
    /**
     * 应用进程要进行网络链接时会通过socket系统调用，请求操作系统为其创建一个套接字，就是操作系统把网络通信的资源分配给应用进程
     * 资源的总和叫套接字描述符socket descriptor
     *
     * 服务端调用socket函数，bind绑定端口，listen监听
     * 客户端调用socket函数，connect函数连接，发送syn，connect阻塞
     * 服务端收到请求后，调用accept函数，返回syn ack，accept阻塞
     * 客户端收到后，connect返回，发送ack
     * 服务端收到后accept返回，连接建立
     */

    /**
     * Client socket 创建时会自动绑定一个本地端口，在new Socket(address, port)中的port为目标端口
     */

    /**
     * 当长时间没有数据发送时，
     */

    /**
     * 监听到了一个客户的连接请求。管理客户连接请求的任务是由操作系统来完成的。操作系统把这些连接请求存储在一个先进先出的队列中。
     * 许多操作系统限定了队列的最大长度，一般为50。当队列中的连接请求达到了队列的最大容量时，服务器进程所在的主机会拒绝新的连接请求。
     * 只有当服务器进程通过ServerSocket的accept()方法从队列中取出连接请求，使队列腾出空位时，队列才能继续加入新的连接请求。
     *
     * ServerSocket的accept()方法从连接请求队列中取出一个客户的连接请求，然后创建与客户连接的Socket对象，并将它返回。
     * 如果队列中没有连接请求，accept()方法就会一直等待，直到接收到了连接请求才返回。
     *
     * SO_TIMEOUT表示ServerSocket的accept()方法等待客户连接的超时时间,超时会抛出sockettimeoutexception
     *SO_REUSEADDR 指在状态处于time wait 重启服务器，重新绑定相同的端口
     */
}