public class Test {
    /**
     *  Throwable顶层
     *  分为error 及 exception类 和他们的子类
     *  error代表jvm发生的错误
     *  exception代表
     *
     * 非检查异常（unckecked exception）：Error 和 RuntimeException 以及他们的子类。
     * javac在编译时，不会提示和发现这样的异常，不要求在程序处理这些异常。
     * 所以如果愿意，我们可以编写代码处理（使用try…catch…finally）这样的异常，。这样的异常发生的原因多半是代码写的有问题。
     * 如除0错误ArithmeticException，错误的强制类型转换错误ClassCastException，
     * 数组索引越界ArrayIndexOutOfBoundsException，使用了空对象NullPointerException等等。
     *
     * 检查异常（checked exception）：除了Error 和 RuntimeException的其它异常。
     * （使用try…catch…finally或者throws）。
     * 否则编译不会通过。
     * 这样的异常一般是由程序的运行环境导致的。因为程序可能被运行在各种未知的环境下，
     *
     * 如SQLException , IOException,ClassNotFoundException 等。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(method());

    }

    /**
     * finally 语句必定执行，并且会在try或者catch中return 之前执行。
     * @return
     */
    public static int method() {

        try {
            System.out.println("try");
            return 10;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
            return 10;

        }
    }

}
        /**
         * 自定义异常
         */
public class EnemyDeadException extends Exception {
    public EnemyDeadException() {}
    public EnemyDeadException(String msg) {
        super(msg);
    }
}

 public class Hero {

    private String name;
    private int hp;

    public Hero(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public void attack(Hero h, int amount) throws EnemyDeadException {
        if (h.hp <= 0) {
            throw new EnemyDeadException("the hp is 0");
        } else {
            h.hp = h.hp - amount;
            if (h.hp < 0) {
                throw new EnemyDeadException("hp is below 0");
            }
        }
    }

    public static void main(String[] args) {
        Hero g = new Hero("gai", 200);
        Hero t = new Hero("ti", 100);

        try {
            g.attack(t, 150);
        } catch (EnemyDeadException e) {
            System.out.println(e.getMessage());
        }


    }
}

public class Account {
    /**
     * 银行取钱异常
     */
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public double deposit(double amount) {
        this.balance = this.balance + amount;
        return balance;
    }

    public double withdraw(double amount) throws OverDraftException {
        if (amount > this.balance) {
            throw new OverDraftException("out of money");
        } else {
            this.balance = this.balance - amount;
            return this.balance;
        }
    }

    public static void main(String[] args) {
        Account a1 = new Account("shuai", 1000);
        System.out.println(a1.balance);
        System.out.println(a1.deposit(1000));
        try {
            System.out.println(a1.withdraw(3000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class OverDraftException extends Exception {
    public OverDraftException() {}
    public OverDraftException(String msg) {
        super(msg);
    }
}
