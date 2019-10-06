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