public class Main {
    /**
     * Bean配置的三种方式
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
     *
     * 基于XML的配置主要使用场景：
     * 第三方
     * 第三方类库，如DataSource、JdbcTemplate等；
     * 命名空间，如aop、context等；
     *
     * 基于注解的配置主要使用场景：
     * 自己开发的类
     * Bean的实现类是当前项目开发的，可直接在Java类中使用注解配置
     *
     * 基于Java类的配置主要使用场景：
     * 对于实例化Bean的逻辑比较复杂，则比较适合用基于Java类配置的方式
     *
     * 在日常的开发中我们主要是使用XML配置和注解配置方式向结合的开发方式，一般不推荐使用基于Java类的配置方式。

     */

    /**
     * Bean生命周期
     * https://blog.csdn.net/ysjian_pingcx/article/details/8968591
     * http://wiki.jikexueyuan.com/project/spring/dependency-injection.html
     *
     * 生命周期回调方法
     * 在XML文件中使用<bean>的init-method 和 destory-method 属性，
     * 指定初始化之后和回调之前的回调方法。这两个属性的取值是bean中相应的初始化和销毁方法的名称。方法名称任意，
     * 但是方法不能有参数。
     * 通过注解设定的话就是
     *
     */

    /**
     * applicationcontext 初始化
     */
}