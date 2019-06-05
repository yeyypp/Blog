public class Main {
    /**
     * Decorator
     * 是一种结构型模式
     * 装饰模式可以在不增加子类的情况下，对对象的功能进行扩展。增加了子类意味着增加了耦合性
     * ，遵循了面向对象的原则，对扩展开放，对修改关闭。
     * 使用装饰者，可以在运行期动态改变被装饰者的功能
     *
     * 什么时候使用：
     * 需要动态的扩展一个类的功能
     * 需要动态的给一个对象增加功能
     *
     * 总共四部分组成
     * 抽象构件：一个接口
     * 具体构建：一个具体的类
     * 抽象装饰类：实现接口的装饰类
     * 具体装饰类：继承了抽象装饰类的类
     *
     * java中io使用了此模式
     */

    public interface Car {

        void assemble();
    }

    public class BasicCar implements Car {

        @java.lang.Override
        public void assemble() {
            System.out.println("Basic Car");
        }
    }

    public class CarDecorator implements Car {

         Car car;

        public CarDecorator(Car car) {
            this.car = car;
        }

        @java.lang.Override
        public void assemble() {
            this.car.assemble();
        }
    }

    public class SportsCar extends CarDecorator {

        public SportsCar(Car car) {
            super(car);
        }

        @java.lang.Override
        public void assemble() {
            super.assemble();
            System.out.println("add features of sports car");
        }
    }

    public static void main(String[] args) {
        Car car = new SportsCar(new BasicCar());
    }
}