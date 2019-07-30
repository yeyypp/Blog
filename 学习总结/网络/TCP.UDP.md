# TCP UDP
- TCP

    - time_wait问题
        >一般情况下TIME_WAIT的时间是2MSL，如果在此时有链接复用了这个端口，则会出现无法同步的
        现象
        
        [TIME_WAIT过高，引起链接超时](https://zhuanlan.zhihu.com/p/61145243)