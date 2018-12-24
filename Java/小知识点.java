public class Main {
    /**
     * 1.& && 区别
     *      && 当第一个表达式值为false时，则不再计算第二个。
     *      & 两个都计算。
     *
     * 2. ^ 异或运算
     *      相同的为0，不同的为1
     *
     * 3. >>> 位运算
     *    如HashMap中计算hash， h >>> 16 向右移动16位
     *
     * 4. char int String
     *    int i = Integer.valueOf("12");
     *    int i = Integer.PaseInt("12");
     *    String str = String.valueOf(12);
     *
     *    char转换
     *      int i = str.charAt(index) - '0';
     *      或者
     *      String str = String.valueOf('12');
     *      int i = Integer.valueOf(str);
     *
     * 5. substring
     *      String str = "shuaiye";
     *      System.out.print(str.substring(4));
     *      System.out.print(str.substring(0, 3));
     *      包含起始索引，不包含结束索引
     *
     * 6. wait()需要放在while里而不是if里
     *      当某个wait的线程被唤醒后，会紧接着wait后边的代码执行程序，需要通过while重新判断是否
     *      符合运行条件。
     *
     * 7.
     */
}