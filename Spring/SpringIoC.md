# Spring IoC
[Spring doc](https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/core.html)  
- The core Spring Framework
    It provides the core container and dependency injection framework.
- Ioc
    Ioc-Inversion of Control 即控制反转，将设计好的对象交给
    容器控制，由容器控制对象的创建。
- DI
    Dependency Injection 依赖注入，是实现控制反转的一种方式。  
    对象间的引用，都是由容器来注入。
    Set注入，构造器注入
    
- The container
    
    The beans and context package are the basis container.  
    The ApplicationContext interface represents the Spring IoC container and 
    is responsible for instantiating, configuring and assembling the beans.
    It reads the metadata which can be represented in XML, Java annotations, or Java
    code.

- ioc 实例化过程
    - 通过ClassPathXmlApplicationContext构造方法中的refresh（）方法开始，此方法
    可以用于销毁当前ApplicationContext
    - 在refresh（）方法里通过obtainFreshBeanFactory（）方法初始化BeanFactory，
    加载Bean，注册Bean，此时Bean还没有初始化。
    - 在obtainFreshBeanFactory（）方法中通过loadBeanDefinitions（）加载Bean
    并放到BeanFactory中。
    - 会此方法中初始化一个XmlBeanDefinitionReader，通过它解析加载xml配置，创建
    BeanDefinition，将BeanDefinition和beanName放到map中。
    - 

- Circular dependencies
    
    One possible solution is to edit the source code of some classes to be configured 
    by setters rather than constructors. Alternatively, avoid constructor injection 
    and use setter injection only. In other words, although it is not recommended, 
    you can configure circular dependencies with setter injection.
    