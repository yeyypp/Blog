# Redis
- Redis data types
    
    - Binary-safe strings.
    - Lists: They are basically linked lists.
    - Sets
    - Sorted sets : similar to sets but where every string element is 
    associated to a floating number value, called **score**. 
    - Hashes : maps composed of fields associated with values, both values
    are strings.
    - Bit arrays
    - HyperLogLogs : this is a probabilistic data structure which is used
    in order to estimate the cardinality of a set.
    - Streams
    
- Redis keys
    
    Keys are binary safe, you can use any binary sequence as a key.
    
    - Very long keys are not a good idea.
    - Very short keys are not a good idea.
    - "object-type:id" is a good idea
    
- Redis Strings
    
    
    