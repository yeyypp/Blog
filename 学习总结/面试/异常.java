public class Main {
    /**
     * java异常，erroe exception 继承自throwable，分为检查异常，非检查异常
     *
     * 检查异常是在编译时会进行检查，是程序正确但可能会因为外部原因而在运行时造成错误，在代码中需要try-catch捕获处理，或者
     * 在方法名中throws抛出
     * 如：IOException
     *     SQLException
     *     DataAccessException
     *     ClassNotFoundException
     * 非检查异常是由程序本身错误引起，所有的非检查异常均是RuntimeException子类，不需要处理
     * 如：NullPointerException
     *     ArrayIndexOutOfBound
     *     ArithmeticException
     *
     * 在设计异常时，如果这个客户端可以从这个异常中恢复，则设为checked异常，不能则为unchecked异常
     *
     * 1.try中和finally中return执行顺序:无论如何finally中的语句都会执行，如果try finally中都有return语句，则会以finally中为准
     * 避免在finally中使用return语句。
     *
     * 2.运行时异常，编译时异常，前者为非检查异常，后者为检查异常，检查异常是指程序本身没有错误，但可能因为外部原因出现运行错误
     * 需要进行try-catch捕获处理，非检查异常指本身程序有问题，在运行时会有java抛出，不需要处理
     * 检查异常例子：ClassNotFound，IOException， SQLException，DataAcessException
     * 非检查异常例子：NullPointerException， ArithmeticException，ArrayIndexOutOfBound
     *
     *
     */
}