# Run-time Area

- Per-thread data areas
    - The pc Register
        
        each threads has its own pc (program counter) register.
        the pc contains the address of the JVM instruction
    
    - JVM Stacks
        
        each threads has a private JVM Stack, created at the same time 
        as the thread. The stack store frames.
        
        - Frames
            
            A frame is used to store data and partial results, as well as
            perform dynamic linking, return values for methods.  
            A new frame is created each time a method is invoked and 
            destroyed when its method invocation completes.  
            Each frame has its own array of local variables
        
        
        - StackOverflow
            
            If the computation in a thread requires a larger JVM Stack 
            than is permitted.
            
        - OutOfMemory
            
            If JVM Stack can be dynamically expanded, and there is not 
            enough memory for it to expand
    
    - Native Method Stacks(本地方法栈)
        
        Native methods are the methods written in a language other than Java
        
        It has the same problem which the JVM Stack has.
        
- Shared areas
    
    - Heap[heap](https://netjs.blogspot.com/2017/11/heap-memory-allocation-in-java.html)
        
        It is created on vm start-up and it storage for objects.
        
        - OutOfMemory
            
            If a computation requires more heap than can be made available
        
        [terminology](https://stackoverflow.com/questions/2129044/java-heap-terminology-young-old-and-permanent-generations)
        - Different part of Heap
            Before Java 8 there are Young Generation, Old Generation, PermGen.
            In Java 8 the PermGen is removed and replacing it with Metaspace which is not 
            in the Heap areas.
            
            - Young Generation
            The young generation contains eden and two survivor spaces, if the space is not enough
            , an attempt to allocate directly in old gen
            
            - Old Generation
            when objects residing in young gen and has been survived several minor GC, it will be moved to Old gen
            
            - Why is heap managed in generations
            In one line, it make GC more efficient.  
            When the Young Gen is full, it will start the minor gc.  
            When the Old Gen is full, it will start the major gc.
            
    - Method Area
        
        It stored per-class structures(类型信息，常量，静态变量，类中的方法等)
        
        - Run-Time Constant Pool
        
        
        