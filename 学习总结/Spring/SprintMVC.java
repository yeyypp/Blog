public class Main {
    /**
     * https://www.cnblogs.com/winterfells/p/8476759.html
     * https://blog.csdn.net/eson_15/column/info/spring-mvc
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
     *
     */
}