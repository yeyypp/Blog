
/**
 * 需要创建对象较少，客户端不关心创建过程
 * 一个工程负责所有对象的创建
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Shape s = new SimpleFactory().getShape("Tri");
        s.draw();
    }

    public Shape getShape(String str) {
        if (str.equals("Circle")) {
            return new Circle();
        }

        return new Tri();

    }
    interface Shape {
        public void draw();
    }

    class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("draw circle");
        }
    }

    class Tri implements Shape {
        @Override
        public void draw() {
            System.out.println("draw tri");
        }
    }
}

**
        * 工厂方法模式
        *  为每一个类型的对象创建一个工厂
        */

public interface Reader {
    public void read();
}

class JPGreader implements Reader {
    @Override
    public void read() {
        System.out.println("read jpg");
    }
}

class PDFreader implements Reader {
    @Override
    public void read() {
        System.out.println("read pdf");
    }
}

interface Factory {
    Reader getReader();
}

class JPG implements Factory {
    @Override
    Reader getReader() {
        return new JPGreader();
    }
}