public class Main {
    /**
     * 什么是spring
     * spring是一个轻量级的企业开发框架，目的是减少企业开发的复杂性
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

    /**
     * 配置是需要配置dtd解析
     */
}