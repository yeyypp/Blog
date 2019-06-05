public class Main {
    /**
     * udp是数据报通信，tcp是六通信
     * tcp位于传输层上的协议，同样的还有udp
     * 一个tcp连接通过四个元素确定，源ip源端口，目的ip目的端口
     * 一个udp连接由两个元素确定，目的ip，目的端口
     *
     * java中Server端
     * 创建ServerSocket server = new ServerSocket(port)绑定端口
     * Socket client = server.accept()阻塞，直到收到连接
     * 打开in打开out 收到数据 写入数据 关闭in 关闭out
     *
     */
}