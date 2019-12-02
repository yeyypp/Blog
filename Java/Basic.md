# Basic
- Access modifiers
    
    - public: It can be accessed everywhere
    - protected: It can be accessed within current class,
    current package and outside the package through the child class
    - default : It can be accessed within current class and current package
    - private : It can be accessed within current class

- static Keyword
    
    - static fields: It means only one single copy of this field is created and used
    through all instance of this class. It can be accessed by using class name.
    - static methods: It can be accessed by using class instead of creating a object
    of the class first.
    - static block: It is used to initializing the instance. It will be called 
    when the JVM load the class, and only be called once. 1.static block(only once)
     => 2.constructor block 3. constructor method
     
- final Keyword
    
    - final class: It means the class can not be subclassed.
    - final methods: It means the methods can not be overridden.
    - final variables: It means the variable always contains the same value,
    if the variable is a primitive, then the value will never be changed. If
    the variable is a reference,then the address of the reference point will never
    be changed,but the content of the object may be changed.
    
- finally
    
    If there is a return in finally and try, the finally return will work.
    ```
    public static int test(int a) {
        try {
            return --a;
        } catch (Exception e) {
            return a + 2;
        } finally {
            return a + 3;
        }
    }
    
    test(1)
    Output
    3
    ```
    The --a will execute first, then the finally will use current a to return
- Reference
    - strong:It is an ordinary Java reference
    - weak:It isn't strong enough to remain in memory
    - sort: It is a stronger level of weak reference
    - phantom: