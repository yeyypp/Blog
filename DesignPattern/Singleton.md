# Singleton
- Eager initialization
the drawback is the singleton will be created at the time
the class get loaded even the object won't be use.

```
public class Singleton {
    private static Singleton singleton = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        return singleton;
    }
}
```

- Lazy initialization
(not safe with multi threads)
```
public class Singleton {
    private static Singleton singleton;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
```

```
Thread safe version
public class Singleton {
    private static Singleton singleton;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}

```

- Double check initialization
the reason we use volatile is that the singleton = new Singleton()
action is not atomic. By using volatile to forbidden the instruction
reorganize.
```
public class Singleton {
    private static volatile Singleton singleton;

    private Singleton() {}

    public static  Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

```