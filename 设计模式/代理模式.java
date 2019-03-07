public class Main{
    /**
     * 远程代理(Remote Proxy)：对一个位于不同的地址空间对象提供一个局域代表对象，如RMI中的stub
     *
     * 虚拟代理(Virtual Proxy)：根据需要将一个资源消耗很大或者比较复杂的对象，延迟加 载，在真正需要的时候才创建
     *
     * 保护代理(Protect or Access Proxy)：控制对一个对象的访问权限。
     * 为其他对象提供一个代理以控制对某个对象的访问
     * 代理对象是对目标对象的扩展。
     */

    /**
     * 静态代理
     * 代理对象与被代理需要实现相同的接口或者父类
     * 缺点：会出现大量重复代码，如果接口修改，则代理类均要修改
     */

    public interface IUserDao {
        void save();
    }

    public class UserDao implements IUserDao {
        public void save() {
            System.out.print("save");
        }
    }

    public class UserDaoProxy implements IUserDao {
        private IUserDao target;

        public UserDaoProxy(IUserDao target) {
            this.target = target;
        }

        public void save() {
            System.out.print("something");
            target.save();
            System.out.print("something");
        }
    }

    /**
     * JDK代理
     * 运行时通过反射，创建代理类
     * 需要代理对象实现接口
     */

    public class ProxyFactory {

        private Object target;

        public ProxyFactory(Object target) {
            this.target = target;
        }

        public Object getProxyInstance() {
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("start something");
                            Object returnValue = method.invoke(target, args);
                            System.out.println("end something");
                            return returnValue;
                        }
                    }
            );
        }
    }

    /**
     * Cglib代理
     * 构建子类对象的方式
     * 要求代理类不能为final
     */
}