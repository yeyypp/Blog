# Redis
[Redis data types](https://redis.io/topics/data-types-intro)
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
    
    - SET GET
        ```
        > set mykey somevalue
        OK
        > get mykey
        "somevalue"
        ```
        
        A value can't be bigger than 512 MB.
        
        Ask SET to fail if the key already exist.
        ```
        > set mykey newval nx
        ```
        Ask SET to succeed if the key already exist.
        ```
        > set mykey new val xx
        ```
    - INCR DECR
        
        The INCR command parse the string to integer, and increments it by one.
        They are all atomic operations.
    - GETSET
        
        It set a key to a new value and return the olf value as the result.
        
    - MSET MGET
        
        Using MSET to retrieve multiple keys.
        ```
        > mset a 10 b 20 c 30
        OK
        > mget a b c
        1) "10"
        2) "20"
        3) "30"
        ```
        
    - DEL EXISTS
        
        Using DEL to delete a key and EXISTS to see if a key exists.
    - keys with limited time
        
        We can set a timeout for a key by using expire
        ```
        > expire key 5
        OK
        
        > set key myValue ex 10
        OK
        ```
        Using ttl to check the expire time
        
- Redis Lists
    
    - LPUSH RPUSH LRANGE RPOP LPOP  
        
        The LPUSH put a element on the left of the list, the LRANGE extracts
         ranges of elements from lists.
         ```
         lpush list a
         rpush list b
         rpush list c
         
         lrange 0 -1
         lrange 0 2
         
         lpush list a b c d
         
         rpop list
         lpop list
         ```
    - LTRIM
        
        The LTRIM will removed elements outside the given range.
        
    - LLEN
        
        return the length of the list    
        
    - BLPOP BRPOP
        
        It will be blocked when the list is empty. They will return to the caller
        when a new element is added to the list.
        ```
        brpop tasks 5
        ```
        It means wait for elements in the list tasks, 
        but return if after 5 seconds no element is available
        
        It means wait forever if we set the timeout time to 0.
        
        1. The client are served in an ordered way.
        2. The return value is different compared to RPOP
        3. It will return NULL if timeout.
        4. The key no longer exists after all the elements are poped.
        
    - Common use cases
        
        1. Remember the latest updates posted by users into a social network.
        2. Communication between process.    
        
- Redis Hashes
    
    - HMSET HGET HGETALL
        
        ```
        hmset user:1000 name shuai age 27 experience 0 goal getAJob
        
        hget user:1000 name
        hget user:1000 age
        
        hgetall user:1000
        ```
    
    
- Redis Set
    
    - SADD SREM
        
        ```
        sadd set a
        sadd set b
        sadd set 1 2 3
        
        srem set a
        ```
        
    - SISMEMEBER SMEMBERS
        
        ```
        sismembers set a
        
        smembers set
        ```
        
    - SUNION SUNIONSTORE
        
        union multiple sets
        
        ```
        sunion set1 set2
        ```
        
        union multiple sets and store the elements to the former key
        ```
        sunionstore set1 set2
        ```
    - SINTER
        
        get the intersection between different sets
        
        ```
        sinter set1 set2
        ```
        
    - SPOP
     
     removes random elements from the set
     
- Redis Sorted sets

    Every element in the sorted set is associated with a floating point value,
    called score.
    
    - ZADD ZRANGE ZREVRANGE
    ```
    zadd set 1 best 2 person
    
    zrange set 0 -1
    
    zrevrange set 0 -1
    ```
    
    - ZRANGEBYSCORE
    ```
    zrangebyscore set 1 2
    ```
    