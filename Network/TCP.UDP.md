# TCP UDP
### TCP

- TCP三次握手

    有a，b两台机器
    a向b发送SYN标志位为1,序列化x的数据包
    b收到后发送SYN,ACK标志位为1序列号为y，ack号为x + 1的数据包
    a收到后发送序列号x + 1,ack y + 1的数据包
    完成握手
    
    当server端接到了syn包后，client掉线，此时server端在一定时间没有收到ack后，
    会重发syn包，超过5次则会断开连接
    
    - 为什么需要三次握手
    
        因为当a，收到b的确认后，此时只是a与b的连接建立了，而b不知道a是不是可靠，
        三次握手是保证链接可靠的最小次数
    
- 四次挥手
    
    A发送报文包含FIN = 1 及序列号x 表示A不会再发送数据，但还可以接受数据，
    B 发送ack确认号x + 1表示收到，但可能此时B还有数据需要发送，
    当没有数据需要发送时B，发送Fin = 1 ACk = 1 序列号y，
    当A收到后发送确认号y + 1，B收到后关闭链接，
    此时A等待两个最大段生命周期，若没有收到ack，则关闭连接
    
    - 为什么需要四次
        
        两次握手之确认了一端没有数据发送，所以需要四次
        
- time_wait

    主动关闭的一方会进入time_wait状态，这种状态会持续2MSL，
    MSL是Maximum Segment LifeTime，
    报文最大生存时间，进入这个状态，
    
    1. 为了保证还在这个链接还在网络中的包全部消失，不会影响下一个链接
    2. 如果a发送的确认数据包，b没有收到，则b会重新发送fin数据包，
    如果a直接关闭，则会导致b的
    fin数据包被新的链接接受
    
    time_wait相关问题

    一般情况下TIME_WAIT的时间是2MSL，如果在此时有链接复用了这个端口，
    则会出现无法同步的现象。
    大量短链接会造成出现大量time_wait的情况
        
    [TIME_WAIT过高，引起链接超时](https://zhuanlan.zhihu.com/p/61145243)

- MTU MSS
    
    MTU包含了MSS以及40字节的tcpheader和ipheader，MSS为最大能传输的数据大小
    
- 超时重传

    tcp每发送一个数据，就会启动一个计时器，若超时还没有收到确认数据，则会重传，
    多次没有收到，则会关闭链接。
    计时器的时间RTO是根据RTT（数据在网络中一来一回的时间）来确定的,超过最大尝试次数
    则会断开链接。
    
        
    - 选择重传
        
        接收端会在tcp头中加一个sack字段，返回收到的包，这样发送端就知道那些包丢了

- 流量控制
    
    是为了防止发送端发送大量超过接收端能接受的数据，造成丢包，解决的是端到端
    接受方，发送方速度不匹配的问题
    
    - 滑动窗口
    
        tcp引入滑动窗口来做流量控制，tcp头中有一个window字段，
        是接受端用来告诉发送端，自己
        缓冲区还能接受多少数据，发送端会根据接收端返回的窗口大小发送相应的数据
        
        [具体流程](https://blog.csdn.net/mandagod/article/details/77883367)
        
        当发送端收到接受端信息得知窗口为0后，会启动一个计时器，
        并发送一个WindowProbe的包
        到接收端询问窗口大小。发送三次后还是0的话就会端口链接
        
    - 糊涂窗口综合症
        
        当发送窗口非常小时，发送端也会发送一些小包，造成资源浪费，不能充分利用带宽，
        此时有两种解决办法
        
        1. 在接收端使用clark方案，当窗口小于某个值时，ack窗口大小为0，直到处理了一些
        数据后，再进行响应
        2. 在发送端启用Nagle算法，定义是任何时刻只能有一个未被确认的小段在网络中，
        会发送
        数据大小为MSS的包

- Nagle算法
    
    任意时刻，网络中只能有一个未被确认的小包存在，Nagle算法可以减少网络拥塞的问题
    
    - 算法规则
        
        1. 包大小 大于等于MSS，允许发送
        2. 包含有FIN，允许发送
        3. 设置了TCP_NODELAY，允许发送
        4. 未设置TCP_CORK选项时，发出的小数据包均被确认，允许发送
        5. 上述条件都不满足，超时（一般为200ms），允许发送
        
    - TCP_NODELAY
        
        虽然提高了吞吐量，但影响了实时性，此选项可以禁止Nagle算法
        
    - TCP_CORK
    
        若有一个小数据包要发送，会尝试把小数据包拼接成大数据包发送
    
    - 延迟确认
        
        可以延迟ack确认包的发送，把多个ack合并为一个，减少网络中的包
        
        在于Nagle算法一起使用时可能会造成性能下降
        1. 在第一次发送数据时，网络中没有小包，直接发送，此时server端收到后，开启
        计时器，等待下一个包，而发送端因为没有收到确认，也会等待，造成两边都在等待
        对方。
        2. 一般write read write read模式不会造成影响，因为在接受端收到数据后需要
        进行处理
        ，然后再随ack发送回去，不会有影响。
        3. write write read模式，则会造成性能下降，接收端还在等待数据，发送端等待ack
        造成类似死锁的情况。
        
        解决办法，关闭Nagle，tcp nodelay选项
        
        [Nagle](https://my.oschina.net/xinxingegeya/blog/485643)
        
- 拥塞控制

    解决的是网络中的拥塞状况。[https://www.zhihu.com/question/38749788](https://www.zhihu.com/question/38749788)
    
    拥塞窗口：是用来确定能被发送出去的数据
    
    拥塞控制主要有以下算法
    1. 慢启动
    2. 拥塞避免
    3. 拥塞发生
    4. 快恢复
    
    - 慢启动
    
        慢启动采用拥塞窗口cwnd，初始化大小为1，即一个MSS（在linux3.0中为10），
        窗口大小呈指数增长（增加的大小为已经确认的数据）
        
        
    - 拥塞避免
        
        当窗口值大于等于阈值ssthresh时，窗口大小呈线性增长
        
    - 拥塞发生
        
        当出现丢包时，拥塞发生，此时会把阈值设为发生时窗口大小的一半，窗口
        大小设为1，开始慢启动。
    
    - 快恢复
    
        快恢复认为当收到三个重复的ack时，虽然丢包了，但网络拥塞并不是很严重，
        会把阈值减半，并把窗口设为阈值大小，开始拥塞避免算法
    
    - 快速重传
    
        当收到三个相同的ack时，会立刻重传，即使没有超时。
        
            
            


[TCP心跳](https://www.cnkirito.moe/tcp-talk/)

- 转发 重定向
    
    转发会将当前request转发到新的页面
    重定向会返回给客户端一个新的url，然后客户端会发送一个新的request到这个url
    


