public class Main {
    /**
     * 什么是spring
     * spring是一个轻量级的企业开发框架，目的是减少企业开发的复杂性
     */

    /**
     * Container Overview：
     * applicationContext 接口代表了Spring ioc 容器，负责初始化，设定，管理beans，通过读取设定来初始化bean，
     * 这个设定可以是xml文件，可以是注解，也可以是java编码。
     * 一般有两种实现ClassPathXmlApplicationContext，在实现中一般通过web.xml设定需要解析的xml。
     * applicationContext包含了beanfactory的所有功能,并支持aop等功能
     *
     * ioc实例化过程:
     * 创建一个ClassPathXmlApplicationContext对象，
     * ApplicatinContext间接实现了ResourceLoader，BeanDefinitionReader
     * 通过ResourceLoader加载xml
     * 通过BeanDefinitionReader解析xml，解析出为BeanDefinition对象
     * 通过BeanDefinitionRegistry注册到beanDefinitionMap中完成初始化
     *
     */

    /**
     * BeanFactory 和 ApplicationContext 有什么区别
     * context继承factory，在原有功能上添加了别的功能
     * 创建bean工厂类，创建bean实例对象
     * 提供了支持国际化的文本消息
     * 统一的资源文件读取方式
     */

    /**
     * Bean：
     * Bean生命周期：
     * 实例化bean -> 注入属性 -> 如果实现了了BeanNameAware，BeanFactoryAware则调用setBeanName，setBeanFactory为了获得容器
     * -> 实现了ApplicationContextAware则调用setApplicationContext传入应用上下文 -> 调用BeanPostProcess的postProcessBefore
     * Initialization方法 作用是对创建后的Bean的功能进行增强 -> 调用bean自己的init初始化方法 ->
     * 调用postProcessAfterInitialization,作用同样是增强bean的功能,只不过是在初始化之后,此时bean就已经可以被使用了 ->
     * 当应用关闭时,也就会关闭ioc 容器,如果bean实现了DisposableBean,则调用destroy()方法,再调用bean自己的destroy方法销毁.
     *
     * Bean依赖的三种方式:
     * 1.使用setter方法注入,需要在配置类中设定注入方法,并在xml中设定<property>属性</property>
     * 2.构造器,需要在配置类中设置构造器,并在xml中配置<construct>属性</construct>
     * 3.使用注解依赖注入,通过@Autowire@Resource注入,前者是通过在应用上下文中找到相应类型的对象注入,
     * 后者是通过找到相应名字的对象注入,没有找到的话,再通过类型寻找.
     *
     * 循环依赖如何解决:
     * 如果是构造器注入,则会报错,创建A需要实例B,而创建B需要实例A,会将创建中的bean标识符放在一个池子中,创建完毕后,取出,当检测到
     * A已经在池子中时就会报错.
     * 如果是setter注入,会在创建时首先根据无参构造器创造bean,暴露一个ObjectFactory用于暴露一个创建中的bean,
     * 这样在set A时注入的就是提前暴露的bean
     *
     * Bean配置的三种方式:
     * xml配置，通过注解，基于java类的方式
     * 1.xml配置
     * 在xml中配置
     *
     * 2.通过注解
     * Bean的定义注解
     * Bean的生命周期注解
     * Bean的依赖检查注解
     * Bean的自动装配注解
     * 以下为定义
     * @Component：一个泛化的概念，表示一个组件（Bean），可作用在任何层次
     * @Controller：用于对Controller实现类进行标注，目前该功能与Component相同
     * @Repository：用于对DAO实现类进行标注
     * @Service：用于对Service实现类进行标注，目前该功能与Component相同
     * 以下为生命周期
     * @PostConstruct：初始化之后的执行的回调方法
     * @PreDestroy：销毁之前的回调方法
     *
     * 3.基于java 不推荐
     * 在日常的开发中我们主要是使用XML配置和注解配置方式向结合的开发方式，一般不推荐使用基于Java类的配置方式。
     */


    /**
     * The difference between spring-web and spring-webmvc
     * spring-web provides core HTTP integration, including some handy Servlet filters, Spring HTTP Invoker,
     * infrastructure to integrate with other web frameworks and HTTP technologies e.g. Hessian, Burlap.
     *
     * spring-webmvc is an implementation of Spring MVC. spring-webmvc depends on on spring-web,
     * thus including it will transitively add spring-web. You don't have to add spring-web explicitly.
     *
     * You should depend only on spring-web if you don't use Spring MVC but want to
     * take advantage of other web-related technologies that Spring supports.
     */

    /**
     * annotation-config, annotation-driven, compont-scan 区别
     * https://blog.csdn.net/catoop/article/details/50068573
     *
     * <context:annotation-config/>
     * 隐式地向Spring容器中注册AutowiredAnnotationBeanPostProcessor等
     * 如果需要使用@Autowired @Resource
     *
     * <context:component-scan/>
     * <context:component-scan/> 配置项不但启用了对类包进行扫描以实施注释驱动 Bean 定义的功能，
     * 同时还启用了注释驱动自动注入的功能
     * （即还隐式地在内部注册了 AutowiredAnnotationBeanPostProcessor 和 CommonAnnotationBeanPostProcessor）
     *
     * <mvc:annotation-driven/>
     * 相当于注册了DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter两个bean，
     * 配置一些messageconverter。即解决了@Controller注解的使用前提配置
     *
     * <context:annotation-config/>
     * Looks for annotations on beans in the same application context
     * it is defined and declares support for all the general annotations like @Autowired, @Resource,
     * @Required, @PostConstruct etc etc.
     * <context:annotation-config> does NOT search for @Component, @Controller, etc.
     * <context:component-scan> DOES search for those @Component annotations,
     * as well as the annotations that <context:annotation-config/> does.
     *
     */



}