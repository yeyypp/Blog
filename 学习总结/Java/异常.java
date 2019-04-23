public class Main {
    /**
     * 分为运行时异常，编译器异常
     * 运行时异常不需要try，catch
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