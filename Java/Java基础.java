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
 * 封装：将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立实体，只提供必要的接口，方法给外部调用
 */

/**
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
 *
 * 变量可以安全的在多线程内共享，提高性能，因为声明了final的变量会被缓存
 *
 * static
 * 声明方法，静态方法中不可调用非静态方法和变量。相反的可以，静态方法可以直接通过类调用
 * 声明变量，静态变量被所有对象共享
 * 声明代码块，在类初次加载时会执行，只会执行一次
 *
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