# volatile
- 被volatile修饰的变量，保证其他线程在读取这个变量时，读到的都是最新的数据。
被修饰的变量在写时会直接写入主存，读时也会直接从主存中读取。volatile不可以用在修饰自增操作的变量上，
因为自增操作，是多个操作。

- 对被修饰变量的操作前后，禁止重排序，（重排序是指不影响程序运行结果的前提下对指令重排序）

- volatile底层是使用内存屏障来实现

- [Example of usage of volatile](https://stackoverflow.com/questions/106591/what-is-the-volatile-keyword-useful-for)

    1. 一个线程创建了实例后，其他线程立马可见
    2. 因为可能重排序会导致一个引用先创建出来，但还没有初始化，此时可能返回一个错误，volatile禁止重排序，防止这种情况产生
```
Example of usage of volatile

public class Singleton {
    private static volatile Singleton singleton;
    
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
 
```