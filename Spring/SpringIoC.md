# Spring IoC
[Spring doc](https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/core.html)  
- Ioc
    Ioc-Inversion of Control 即控制反转，将设计好的对象交给
    容器控制，由容器控制对象的创建。
- DI
    Dependency Injection 依赖注入，是实现控制反转的一种方式。  
    Set注入，构造器注入
    
- The container
    
    The beans and context package are the basis container.  
    The ApplicationContext interface represents the Spring IoC container and 
    is responsible for instantiating, configuring and assembling the beans.
    It reads the metadata which can be represented in XML, Java annotations, or Java
    code.
    
    