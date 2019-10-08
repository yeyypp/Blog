# Spring 事务
在Spring中一般使用声明式事务，可以与具体逻辑解耦，不影响业务代码，实现方式有两种
xml配置，注解配置。

- @Transactional
    
    - name：用此属性选择哪个事务管理器
    - propagation：事务传播行为，默认为REQUIRED
        - REQUIRED 如果当前存在事务，则加入该事务，不存在，则创建一个新事务
        - SUPPORTS 存在则加入，否则以非事务方式继续运行
        - MANDATORY 存在则加入，否则抛出异常
        - REQUIRES_NEW 重新创建一个新事务，如果当前存在事务，则暂停当前事务
        - NOT_SUPPORTED 以非事务方式运行，当前存在事务则暂停当前事务
        - NEVER 以非事务方式运行，如果当前存在事务，则抛出异常
        - NESTED 与REQUIRED一样
    - isolation：事务隔离级别，默认DEFAULT
        - READ_UNCOMMITTED 什么都防止不了
        - READ_COMMITTED 可以防止脏读（A 读到B未提交的数据）
        - REPEATABLE_READ 防止不可重复读（事务中两次读到的数据不一样），脏读
        - SERIALIZABLE 防止幻读（多次读到的数据不一样）串行化执行
    - rollbackFor：指定回滚的异常类型