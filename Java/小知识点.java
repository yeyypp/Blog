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
     * 7. hashcode() equals()
     *      一般情况下 equals相等，hashcode一定相等。
     *                hashcode相等，equals不一定相等
     *      在集合中时，首先判断hashcode是否相等，相等则查看equals是否相等
     *      所以当new一个对象，并需要判断通过其内容是否相等时，需要重写equals，当重写equals时必须
     *      重写hashcode，否则会造成相同的对象，不同的hashcode，在存入到set集合中时，会造成出现两个相同的对象。
     */
}