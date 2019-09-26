# Proxy

- 静态代理
    
    优点：在不修改对象的前提下扩展对象功能，限制对目标对象的访问  
    缺点：需要代理对象和目标对象都实现接口，一个代理对象对应一个目标对象

- [Dynamic Proxies](https://www.baeldung.com/java-dynamic-proxies)
    
    Dynamic proxies allow one single class with one single method to service
    multiple method calls to arbitrary classes with an arbitrary number of 
    methods. It routes all method invocations to single handler - the invoke()
    method.  
    
    It implement by using the reflection.
    
```
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ShuaiYe
 * @date 2019/9/26 16:35
 */
public class ProxyFactory {

    private Object target;
    private InvocationHandler invocationHandler;

    public ProxyFactory(Object target, InvocationHandler invocationHandler) {
        this.target = target;
        this.invocationHandler = invocationHandler;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                invocationHandler);
    }
}

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuaiYe
 * @date 2019/9/26 16:16
 */
public class TimingDynamicInvocationHandler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;
        System.out.println("the method " + method.getName() + " finished in " + elapsed + " ns");
        return result;
    }
}

```

- cglib
    
    It implement the proxy without object implement the interface