public class Main {
    /**
     * Resource接口的设计采用了策略模式
     * 是用来访问资源
     * 策略模式，将一系列算法单独封装起来，可以互相替换，独立于使用它们的用户
     * 算法通常被封装在context类中，让客户端自己选择算法，或者context提客户端选择
     *
     * 在spring中通过applicationContext的具体实现类，来选择相应的resource
     *
     * https://www.ibm.com/developerworks/cn/java/j-lo-spring-resource/index.html
     * 当我们创建 ApplicationContext 对象时，通常可以使用如下三个实现类：
     * ClassPathXmlApplicatinContext：对应使用 ClassPathResource 进行资源访问。
     * FileSystemXmlApplicationContext：对应使用 FileSystemResoure 进行资源访问。
     * XmlWebApplicationContext：对应使用 ServletContextResource 进行资源访问。
     * 从上面说明可以看出，当使用 ApplicationContext 的不同实现类时，就意味着 Spring 使用相应的资源访问策略。
     */
}