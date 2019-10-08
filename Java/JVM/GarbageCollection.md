# Garbage Collection
- What is a Garbage Collector
The garbage collection (GC) automatically manages the applications's dynamic
memory allocation requests.

    - Allocates from and gives back memory to the OS
    - Hands out that memory to the application as it requests it
    - Determines which parts of that memory is still in use by the application
    - Reclaims the unused memory for reuse by the application

- Generations
The memory is managed in generations because if we don't use this strategy, the GC
will iterate all areas of heap every time.

    - Young Generation
        
        When young generation is full, it will start minor GC.
        The young generation consists of eden and two survivor spaces. Most 
        objects are initially allocated in eden. One survivor space is empty at
        any time,after GC, the alive objects will moved to another survivor space.
        Then there are still one empty survivor.When a object survive several time
        of GC, it will be moved to old generation.
    
    - Old Generation
        
        When old generation is full, it will start major GC.

- Collector
    
    - Serial Collector: It uses a single thread to perform all garbage collection
    it suited for single processor.
    - Parallel Collector: It has multiple threads to speed up gc.
    - G1 Collector : the mostly concurrent collectors
    - CMS Collector: It is for application that prefer shorter gc pauses.(
    It has been deprecated since JDK 9)
    - Z Collector: It is a low latency garbage collector. ZGC performs all expensive 
    work concurrently, without stopping the execution of application threads.
    
- GC Algorithms
    - Mark and sweep: marking live objects, remove unreachable objects.
    - Mark and sweep, compact: After all alive objects has been marked, they will
    be moved to the beginning of the memory space.
    - Mark copy: divided the heap into two areas, mark the alive objects and copy 
    them to another areas, then clear the current areas.
- How to tell if a object is alive or not
    - Root search
    - Reference count