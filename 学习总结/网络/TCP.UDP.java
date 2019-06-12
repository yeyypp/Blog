public class Main {
    /**
     * udp是数据报通信，tcp是六通信
     * tcp位于传输层上的协议，同样的还有udp
     * 一个tcp连接通过四个元素确定，源ip源端口，目的ip目的端口
     * 一个udp连接由两个元素确定，目的ip，目的端口
     *
     * tcp可靠的基本要素
     * 确认号，确认数据
     * 超时重传，重传丢失的数据
     * 快速重传
     *
     * tcp三次握手建立连接
     * client发送一个syn标志位为1，且带有一个随机序号的数据到服务端，此时client从closed状态进入syn_sent状态
     * server收到数据，会发送带有syn为1，ack号，以及一个随机序列号到client，server进入
     *
     *
     */
}