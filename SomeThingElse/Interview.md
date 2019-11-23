#Interview
## Java
- HashMap
    
    - 1.7
        
        - 实现方式：数组加链表，冲突解决方式为链表法
        - capacity:当前数组容量，扩容大小为当前两倍
        - loadFactor: 负载因子 0.75
        - threshold: 阈值 capacity * loadFactor
        - 非线程安全
        - key value 可以为null
        
    - 1.8
        
        - 实现方式：数组加链表加红黑树
        - 当链表数据为8时转换为红黑树，获取元素时间logN
        - AVL数是严格平衡的，查找更快，但插入时需要消耗过多时间，红黑树在添加，删除，
        查找方面好于AVL数
        - hash = hashcode ^ hashcode >>> 16
    均为线程不安全
    
- ConcurrentHashMap
    
    - 1.7 采用分段锁，一个segment数组，每一个元素是一个链表数组
    - 1.8 采用数组加链表加红黑树
    - 在扩容时，判断sizeCtl是否小于1来确定是否有其他线程，当大于0时会尝试用CAS方法更新
    - 通过CAS 和 volatile 保证安全
    
- ArrayList LinkedList
    
    - ArrayList底层实现为数组，增删慢，获取元素的时间快，扩容时每次变为原数组1.5倍
    - LinkedList 底层为链表实现
- 基本类型 包装类型
    - 基本类型初始值为默认值，包装类型为null
- String StringBuffer StringBuilder
    - 只有StringBuffer是线程安全的
- 封装继承多态
    - 封装 把一段逻辑概念抽象出来，做到相对独立。比如操作系统，隐藏了具体的对磁盘的操作
    - 继承 使代码复用
    - 多态 一组对象表达同一个概念，但展现不同的行为
- 接口 抽象类
    - 接口 是对类的行为进行约束，可以要求不同的类具有相同的行为
    - 抽象类 是为了代码复用，继承的类可以有自己的方法
- 反射
    - 在运行时获得，修改类或者方法的信息
- synchronized
    - 修饰
        - 修饰实例方法：锁住的的是调用方法的对象
        - 修饰代码块：锁住的是代码块中的参数
        - 修饰静态方法：锁住的是当前类的class对象
    - 实现
        - 通过monitor 实现，而monitor会通过操作系统 mutex lock实现，意味着每次尝试
        获取琐时，都会切换到内核态，消耗过大
    - 优化
        - 偏向锁：锁长时间被同一线程访问，不用获取
        - 轻量级：在偏向锁时，有其他线程尝试获取锁，会通过CAS自旋，尝试获取
        - 重量级锁：当自旋一定次数，或者又有新的线程尝试，此时升级为重量级，等待的锁会进入阻塞
    - 锁消除
        - 在编译时，检测到共享数据不会出现竞争，就会执行锁消除
    - 锁粗化
        - 一系列操作都对某个对象重复加锁，则会粗化
- ReenTrantLock
    - 与synchronized相比
        - 可实现公平锁，syn是非公平
        - 需要主动释放，syn不需要 
        - 内部实现为队列同步器
- AQS
    - 维持一个队列，当当前线程未能获得锁，则加在队列后
- Java 8 9 10 11 12 13
    - Java 8
        - Lambda表达式，通过lambda简化函数式接口
        - 接口添加default方法，可以直接使用，不用实现类实现
        - 集合流式操作
        - HashMap实现加入红黑树，concurrenthashmap实现变为数组链表加红黑树
    - Java 9
        - 模块化
        - 集合上增加了List.of(), Set.of()工厂方法，创建不可变集合
        - Stream增加dropWhile, takeWhile
        - 添加反应式流
    - Java 10
        - 局部变量类型推断 var
        - 为G1引入多线程并行GC
    - Java 11
        - ZGC
    - Java 12
- JVM
    - JMM
        - Java内存模型，每个线程有自己的本地内存，所有共享变量存于主内存中，volatile
        使得每次读写变量都直接从主内存读取，使用。
        - happens before：表示a的执行结果对b可见
## Spring
- Spring framework vs Spring Boot
    - Spring is a framework which provides comprehensive infrastructure
     suppot for developing.
    - Spring Boot is an extension of Spring which eliminated the messive
    configurations. Provides spring-boot-starter-... to simplify build and
    configuration. Embedded server.
- Spring ioc
    - ioc 为控制反转，将设计好的对象交给容器控制，由容器负责控制，销毁
    - spring中容器是BeanFactory ApplicationContext
    - 通过ClassPathXmlApplicationContext加载配置文件
    - 通过依赖注入实现，set方法注入，构造其注入
    - 流程，初始化Beanactory，解析xml，得到BeanDefinition，BeanName
    注册到map中
    - 默认情况下允许循环引用，可以用set注入方法，不能用构造器注入
- Bean
    - 配置bean的方式：xml, 注解，java configuration（@Configuration）
    - 注入方式：构造起，setter
    - Scope：singleton, prototype, request, session, global session
    - 生命周期：
    - 循环依赖：
- Spring AOP
    - 使用jdk动态代理，cglib代理，一个需要目标类实现接口，一个不需要 
## Design Pattern
- Singleton
    - 为什么用volatile:保证内存可见性，禁止重排序，instance = new Singleton
    会先申请内存空间，执行构造方法，赋值引用，这两步可能发生重排序，此时，instance就
    不为null。
```
public class Singleton {
    private static volatile Singleton instance;

    public Singleton() {

    }

    public Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

```
    
        