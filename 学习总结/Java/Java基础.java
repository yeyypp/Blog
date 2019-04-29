public class Main {
    /**
     * java基本类型
     *
     * byte：8位，最大存储数据量是255，存放的数据范围是-128~127之间。
     *
     * char：16位，存储Unicode码，用单引号赋值。
     *
     * short：16位，最大数据存储量是65536，数据范围是-32768~32767之间。
     *
     * int：32位，最大数据存储容量是2的32次方减1，数据范围是负的2的31次方到正的2的31次方减1。
     *
     * long：64位，最大数据存储容量是2的64次方减1，数据范围为负的2的63次方到正的2的63次方减1。
     *
     * float：32位，数据范围在3.4e-45~1.4e38，直接赋值时必须在数字后加上f或F。
     *
     * double：64位，数据范围在4.9e-324~1.8e308，赋值时可以加d或D也可以不加。
     *
     * boolean：1位。
     *
     * 将"大"数据转换为"小"数据时，你可以使用强制类型转换。即你必须采用下面这种语句格式： int n=(int)3.14159/2;
     */

    /**
     * static
     * inner class cannot have static declarations
     * 静态变量是放在方法区中，但方法区是否在堆中要根据jvm实现判断
     */

    /**
     * 接口可以多继承接口
     * 类只能单继承类
     */

    /**
     * 对象实例化过程
     * 静态变量，静态代码块，变量，构造块，构造函数
     * 继承中
     * 父静变，静块，子静变，静块，父变，父块，子变，子块，父构造函数，子构造函数
     * 当通过子类静态方法调用父类方法时，会初始化父类，但不会初始化子类。
     * 只有当实例化时，才会执行构造块
     *
     * java对象实例化过程
     * 实例变量指的是类的属性,局部变量指的是方法中的变量
     * 对象实例化时,会先检查该类是否有父类,有则实例化父类,而在每个类中初始化顺序是
     * 静变,静块,子静变,子静块,父变,父块
     */

/**
 Java中的访问控制符
 访问控制符   该成员所在类   该成员所在包   其他包子类   任何地方
 private         √            ×            ×           ×
 default         √            √            ×           ×
 protected       √            √            √           ×
 public                            全部可以


 /* *
 * java 三大特性
 * 继承 多态 封装
 * 继承：方便复用之前的代码，提高效率。
 * 多态：不修改程序代码，就可以改变程序运行时绑定的程序代码。父类可以引用不同的子类
 *       在运行时确定调用的方法。
 * 封装：将数据和基于数据的操作封装在一起，只提供有限的接口供外界使用
 *
 * 泛型：参数化类型，泛指一种类型，灵活性更高，减少不必要重复代码，比如arraylist不用string Integer单独写处理方法，只要在参数表明就好
 *       可以在编译器检查出是否添加的一种类型
 *       但运行时就检查不出了
 */

/**
 * 接口 抽象类
 *
 *
 * 抽象方法必须用abstract关键字进行修饰
 * 抽象类可以提供成员方法的实现细节，而接口中只能存在public abstract 方法；
 *
 * 　　2）抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是public static final类型的；
 *
 * 　　3）接口中不能含有静态代码块以及静态方法，而抽象类可以有静态代码块和静态方法；
 *
 * 　　4）一个类只能继承一个抽象类，而一个类却可以实现多个接口。
 * 接口可以继承多个接口，但不能继承类
 *
 * 使用抽象类，也就是继承，是一种强耦合的设计，用来描述“A is a B” 的关系，即如果说A继承于B，那么在流程中将A当做B去使用应该完全没有问题
 * 而接口则多用于描述各个类型的对象间所共有的行为，表示“所有实现了这个接口的类的对象都能这么干
 */



/**
 * 重写
 * 三同一大一小，方法名，返回值，参数一样，访问权限>=, 抛出异常<=
 *
 * 重载
 *重载的时候，方法名要一样，但是参数类型和个数不一样，返回值类型可以相同也可以不相同。无法以返回型别作为重载函数的区分标准
 */

/**
 * final
 * 可以声明成员变量，类，方法，本地变量
 * 声明成员变量，表示是一个常量，赋值后不可变，但如果是一个引用，只能保证地址不变
 * 声明方法表示这个方法不可以被子类重写
 * 声明类，表示这个类不能被继承
 * 变量可以安全的在多线程内共享，提高性能，因为声明了final的变量会被缓存
 *
 * finally
 * try语句在返回前，将其他所有的操作执行完，保留好要返回的值，而后转入执行finally中的语句，而后分为以下三种情况：
 *
 *     情况一：如果finally中有return语句，则会将try中的return语句”覆盖“掉，直接执行finally中的return语句，得到返回值，这样便无法得到try之前保留好的返回值。
 *
 *     情况二：如果finally中没有return语句，也没有改变要返回值，则执行完finally中的语句后，会接着执行try中的return语句，返回之前保留的值。
 *
 *     情况三：如果finally中没有return语句，但是改变了要返回的值，这里有点类似与引用传递和值传递的区别，分以下两种情况，：
 *
 *         1）如果return的数据是基本数据类型或文本字符串，则在finally中对该基本数据的改变不起作用，try中的return语句依然会返回进入finally块之前保留的值。
 *
 *         2）如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，try中的return语句返回的就是在finally中改变后的该属性的值。
 * finalize
 * 是object的方法，gc在回收对象前会调用该方法
 *
 * 变量可以安全的在多线程内共享，提高性能，因为声明了final的变量会被缓存
 *
 * static
 * 声明方法，静态方法中不可调用非静态方法和变量。相反的可以，静态方法可以直接通过类调用
 * 声明变量，静态变量被所有对象共享
 * 声明代码块，在类初次加载时会执行，只会执行一次
 *
 *
 * 序列化是这个过程的第一部分，将数据分解成字节流，以便存储在文件中或在网络上传输。反序列化就是打开字节流并重构对象。
 */

/**
 * String相关
 *
 * String final修饰，String 使用 final char value[] 来存放字符序列，不可继承线程安全，StringBuffer线程安全，方法中被synchronized修饰 StringBuilder
 *
 * 字符串常量池存在于JVM的方法去中
 * 面试题：String str4 = new String(“abc”) 创建多少个对象？
 * 1.在常量池中查找是否有“abc”对象
 *   有则返回对应的引用实例
 *   没有则创建对应的实例对象
 * 2.在堆中 new 一个 String("abc") 对象
 *   将对象地址赋值给str4,创建一个引用
 *   所以，常量池中没有“abc”字面量则创建两个对象，否则创建一个对象，以及创建一个引用
 * 根据字面量，往往会提出这样的变式题：
 * String str1 = new String("A"+"B") ; 会创建多少个对象?
 * String str2 = new String("ABC") + "ABC" ; 会创建多少个对象?
 *
 * str1：
 * 字符串常量池："A","B","AB" : 3个
 * 堆：new String("AB") ：1个
 * 引用： str1 ：1个
 * 总共 ： 5个
 *
 * str2 ：
 * 字符串常量池："ABC" : 1个
 * 堆：new String("ABC") ：1个
 * 引用： str2 ：1个
 * 总共 ： 3个
 */

    /**
     * 数字进制转换
     * Integer.parseInt("10", 2);
     * 表示将二进制数10，转换为十进制
     * 十进制转成十六进制：
     *
     * Integer.toHexString(int i)
     *
     * 十进制转成八进制
     *
     * Integer.toOctalString(int i)
     *
     * 十进制转成二进制
     *
     * Integer.toBinaryString(int i)
     */


    String s = "Test";


    Character c = s.charAt(1);

    // i == 0; 如果s中没有Te则返回-1；
    int i = s.indexOf("Te");

    // 包含0 不包含2
    String s1 = s.substring(0,2);

    char[] chars = s1.toCharArray();
    // 需要计算字母
    int[] array = new int[26];
    array[s.charAt(i) - 'a']++
            //需要计算数字
            s.charAt(i) - '0'

    //字符串分割
    String delimeter = " ";
    String s = "shuai ye is a good";
    String[] s1 = s.spilt(delimeter);

    //分割多个空格
            s.split("\\s+");



/**
 * 在String的题中常用到sliding window 的方法解题
 *
 * 无重复字符的最长子串
 *
 * class Solution {
 *     public int lengthOfLongestSubstring(String s) {
 *
 *     窗口时[i,j),用set不包含重复元素的性质
 *
 *         Set<Character> set = new HashSet<>();
 *         int ans = 0, i = 0, j = 0;
 *         while (i < s.length() && j < s.length()) {
 *             if (!set.contains(s.charAt(j))) {
 *                 set.add(s.charAt(j));
 *                 j++;
 *                 ans = Math.max(ans, j - i);
 *             } else {
 *                 set.remove(s.charAt(i));
 *                 i++;
 *             }
 *         }
 *         return ans;
 *     }
 * }
 */

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
 *    int i = Integer.parseInt("12");
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
 *
 * 8. 异常处理
 *     throw throws
 *     throws 出现在方法声明上，表示可能出现的异常。
 *     throw 一般出现在方法体内，一定会抛出异常。
 *
 * 9. next nextLine
 *      next后一定跟一个nextLine，因为next以Enter为分隔符，不读取Enter，而nextLine会读取next省略的Enter。
 */


    /**
     内存溢出
     表示用户数据实际大小超过了申请的内存大小
     内存泄漏
     表示申请了的内存，在使用完后并没有释放
     */

    /**
     * 对象实例化顺序
     *
     * 父类--静态变量
     * 父类--静态初始化块
     * 子类--静态变量
     * 子类--静态初始化块
     * 子类main方法
     * 父类--变量
     * 父类--初始化块
     * 父类--构造器
     * i=9, j=0
     * 子类--变量
     * 子类--初始化块
     * 子类--构造器
     * i=9,j=20
     *
     * 如果只是调用子类的变量，则没有实例化子类，不会出现子类的静态代码块等
     */
}

