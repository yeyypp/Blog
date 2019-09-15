# Kernel Thread and User Thread
- Implementing Threads in User Space
    
    The first method is to put the package entirely in user space. As far
    as the kernel concerned, it is managing ordinary, single-threaded process.
    It can be implemented on an OS that does not support threads.  
    
    When threads are managed in user space, each process needs its own private
    **thread table** to keep track of the threads in that process. it stores the 
    program counter, stack pointer, registers, state and so on.  
    
    When a thread does something that may cause it to become blocked, it calls 
    run-system procedure to check if it should be put into blocked state. If so
    it stores its context into the thread table and looks in the table for a ready
    thread to run.  
    
    Problem with only using user level thread is when the current thread is blocked,
    the whole process is blocked

- Implementing Threads in the Kernel
    
    The kernel has a thread table that keeps track of all the threads in the system.
    When a thread is blocked, the kernel at its option, can run either another thread
    from the same process or another process.
    
- Hybrid Implementations
    
    With this approach, the kernel is aware of only the kernel level threads and schedule
    those. Some of those threads may have multiple user level threads. These user level threads
    are created, destroyed and scheduled just like user level threads in a process.
    
    