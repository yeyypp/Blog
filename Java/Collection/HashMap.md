# HashMap
- Difference between HashMap, TreeMap, LinkedHashMap
    
    HashMap: unordered, it is implemented by array and linkedlist.key and value can be null.  
    TreeMap: ordered by key, it is implemented by red black tree. only value can be null.  
    LinkedHashMap: ordered by inserting, it is implemented linkedlist and HashMap.
    
- HashMap
    
    - 1.7 
        
        It is implemented by linkedlist and array.
    - 1.8 
        
        It is implemented by linkedlist, array and red black tree.
        when the size of linkedlist reach to 8, it will be changed to
        red black tree.