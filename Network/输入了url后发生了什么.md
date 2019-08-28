# [输入url后发生了什么](https://zhuanlan.zhihu.com/p/43369093)

1. 先查找DNS Cache中是否有地址，没有则查找本地host文件中，没有则会发送一个
DNS query message 到DNS服务器。DNS会通过迭代查询或者递归查询获得地址。

2. 获得地址后，浏览器通过系统调用Socket接口与服务器建立连接，进行TCP三次握手
建立连接前，服务器必须已经绑定并监听端口。

3. TCP三次握手 客户端A，服务器B
    - A 发送带有标志位SYN，以及随机序列号x到B
    - B 收到后，发送带有表示为SYN,ACK，以及随机序列号y，确认好x + 1，进入SYN_RCVD
    - A 收到后 进入ESTABLISHED状态，并发送带有标志位ACK，以及确认好y + 1
    - B 收到后进入ESTABLISHED状态，连接建立

4. 发送HTTP请求，返回响应，维持连接，在HTTP/1.1中 Connection: keep-alive
默认启用，表示持久连接

5. 断开连接，四次挥手 
    - 主动结束的一端会进入time_wait状态,被动发进入close_wait状态
    - [TIME_WAIT过高，引起链接超时](https://zhuanlan.zhihu.com/p/61145243)
    - [大量close_wait问题](https://juejin.im/post/5c0cf1ed6fb9a04a08217fcc)
    - A 发送标志位FIN,序列号为x的数据，表示没有数据要发送了，进入fin_wait1状态
    - B 收到后返回ACK表示，以及x + 1，进入close_wait
    - A 收到后进入fin_wait2状态
    - B 发送FIN，序列号y，进入LAST_ ACK状态
    - A 收到后，发送ack， y + 1， 进入time_wait状态
    - B 收到后 进入closed状态