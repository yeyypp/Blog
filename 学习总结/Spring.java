public class Main {
    /**
     *  Spring是一个轻量级的IoC和AOP容器框架。目的是解决企业应用开发的复杂性
     *
     * java反射
     * 在程序运行时动态的加载一些之前没有用到的类
     * 在运行时判断一个对象是否属于一个类，生成一个类的对象，调用对象的方法，改变类的成员变量或方法
     *
     * Class.forName.getInstance();
     * 优点：灵活度高
     * 缺点：性能较慢，破坏封装性
     *
     * Spring IOC
     * spring ioc 控制反转，采用了依赖倒置的设计思路，通过依赖注入的方式实现
     * 在spring中通过反射的方式，通过解析xml配置文件中bean的信息，获得类名，及对象名，利用反射创建对象
     * 所谓依赖注入，就是把底层类作为参数传入上层类，实现上层类对下层类的“控制
     * 由ioc容器来实现对象的创建，
     *
     *
     * Spring AOP
     * 面向切面编程，这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程
     * 在spring中我认为就是把一些非核心业务的，但是会在多个模块中重复使用的功能，抽象，封装起来，通过代理模式实现
     *
     * Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理：
     *
     *         ①JDK动态代理通过反射来接收被代理的类，并且要求被代理的类必须实现一个接口。
     * JDK动态代理的核心是InvocationHandler接口和Proxy类。生成的代理对象的方法调用都会委托到InvocationHandler.invoke()方法，
     * 当我们调用代理类对象的方法时，这个“调用”会转送到invoke方法中，代理类对象作为proxy参数传入，
     * 参数method标识了我们具体调用的是代理类的哪个方法，args为这个方法的参数。
     *
     *         ②如果目标类没有实现接口，那么Spring AOP会选择使用CGLIB来动态代理目标类。
     * CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成指定类的一个子类对象，
     * 并覆盖其中特定方法，覆盖方法时可以添加增强代码，从而实现AOP。CGLIB是通过继承的方式做的动态代理，
     * 因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。
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
     * bean作用域
     * 通过scope属性来设置
     * singleto容器中只会有一个bean
     * prototype每次请求该bean时都会创建一个新的bean
     * request 创建的bean只在request中有效
     *
     */

    /**
     *Spring MVC是一个基于MVC架构的用来简化web应用程序开发的应用开发框架，它是Spring的一个模块,
     * web模型中，通过把Model，View，Controller分离，把较为复杂的web应用分成逻辑清晰的几部分，简化开发，减少出错
     *
     * （1）用户发送请求至前端控制器DispatcherServlet；
     * （2） DispatcherServlet收到请求后，调用HandlerMapping处理器映射器，请求获取Handle；
     * （3）处理器映射器根据请求url找到具体的处理器，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet；
     * （4）DispatcherServlet通过HandlerAdapter处理器适配器调用处理器；
     * （5）执行处理器(Handler，也叫后端控制器)；
     * （6）Handler执行完成返回ModelAndView；
     * （7）HandlerAdapter将Handler执行结果ModelAndView返回给DispatcherServlet；
     * （8）DispatcherServlet将ModelAndView传给ViewResolver视图解析器进行解析；
     * （9）ViewResolver解析后返回具体View；
     * （10）DispatcherServlet对View进行渲染视图（即将模型数据填充至视图中）
     * （11）DispatcherServlet响应用户。
     *
     * spring事务
     *
     * spring拦截器
     */


    /**
     * post不支持
     * 表单如何提交数据
     *
     */
}