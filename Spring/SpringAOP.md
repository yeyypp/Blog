# Spring AOP
- AOP
    
    把与主业务无关，提取出来
    
- 实现方式
    
    使用代理模式来实现，分为静态代理，及动态代理  
    静态代理：自己编写代理类，且一个代理类只能代理一个类型
    动态代理：利用反射在程序运行时动态创建代理类, 在程序运行时生成字节码并加载到
    class文件中.
    
    当被代理的对象实现接口时，SpringAOP会通过JDK动态代理实现代理类，当没有实现接口时
    会通过cglib实现代理类
- 术语
    
    - Advice：具体执行的逻辑
    - Pointcut：插入逻辑的位置
    - @Aspect：用来修饰AOP配置类
- filter（https://stackoverflow.com/questions/35856454/difference-between-interceptor-and-filter-in-spring-mvc）
    