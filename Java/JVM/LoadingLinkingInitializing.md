# Loading,Linking,and Initializing
[Loading,Linking,adn Initializing](https://docs.oracle.com/javase/specs/jvms/se12/html/jvms-5.html)

- Loading : It is the process of finding the binary representation of a class or 
interface type and creating a class or interface from it.
- Linking : It is the process of taking a class or interface and combing it into run
-time state of the JVM so that it can be executed.
- Initializing: It execute the init methods of the class.

- 总共三大阶段，加载，链接，初始化，链接分为三个小阶段，验证，准备，解析
- 加载

    将class字节码通过ClassLoader加载到内存中。JVM中内置了三个ClassLoader
    
    - BootstrapClassLoader：负责加载JVM核心类，根加载器 lib/rt.jar
    - ExtensionClassLoader：负责加载JVM扩展类 lib/ext/*.jar
    - AppClassLoader：加载我们自己编写的代码 classpath
    
    - 双亲委派：当程序运行时遇到未知的类 AppClassLoader 会先将类名交给 ExtensionClassLoader
    加载，ExtensionClassLoader会交给BootstrapClassLoader。
- 验证
    
    对加载进来的字节码进行验证，是否符合虚拟机规范
- 准备
    
    为类变量（静态变量）分配内存，赋予初始值（零值）
- 解析
    
    将常量池内符号引用变为直接引用（内存地址）
- 初始化
    
    对类变量初始化，执行构造器