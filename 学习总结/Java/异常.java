public class Main {
    /**
     * 所有的异常都是Throwable的子类
     *                     Throwable
     *            Error                                         Exception
     *  OutOfMemory(OOM) StackOverFlow          IOException                 RunntimeException
     *                                    FileNotFouondException
     *                                                                     NullPointerException
     *                                                                     ArrayIndexOutOf。。。
     *
     * Exception 异常 也可以分为RunntimeException 运行时异常（Unchecked）意味着编译时没有检查
     * 和 compileException 编译时异常（checked）编译时检查
     *
     * 运行时异常因为其不可查性，由java自动抛出，程序存在bug
     * 编译异常必须进行捕捉或者抛出，程序是正确的但因为外在环境不满足而引发的异常。
     *
     * 通常处理异常方法
     * 　try 监听可能发生异常的代码
     *   catch 捕获异常
     *   finally 总是执行，用来回收try中打开的资源
     *   throw 抛出异常
     *   throws方法命中抛出异常，声明该方法可能抛出的异常
     *
     *   Java 文档中对如何创建不同的异常的定义是
     *   If a client can reasonably be expected to recover from an exception,
     *   make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception
     *   如果用户能从该异常中恢复，则设定其为checked exception，否则 unchecked exception
     */

    /**
     * 分为运行时异常，编译器异常
     *
     *
     * error exception 继承Throwable
     * 运行时异常与非运行时异常继承exception
     *
     * runtimeexception
     * unchecked
     * 是不可恢复的
     * 如，ClassCastException，NullPointerException、IndexOutOfBoundsException
     *
     * checked
     * 是可恢复的，可能是由于用户输入等原因造成
     *
     * spring事务中碰到运行时异常会直接回滚，碰到checked异常会默认提交
     *
     * throw/throws
     * throw 一般在方法中 throw应用于运行时异常
     * throws在方法声明处，throws应用于编译时异常
     */
}