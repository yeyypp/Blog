# Exception
There are two kinds of exception in java, checked exception and unchecked exception.
Both of them are under throwable. Error and RuntimeException are unchecked exception,
everything else are checked exception.

- checked exception
    
    The exceptions are checked at compile time, it must be handled by try-catch or 
    throw.  
    FileNotFoundException,IOException
    
- unchecked exception
    
    the exception that are not checked at compile time, it is not forced by the 
    compiler to handle, the JVM will deal with it. The Error and RuntimeException 
    are unchecked exception.  
    NullPointerException,ArrayIndexOutOfBoundException,ClassCastException.
    
- If a client can reasonably be expected to recover from an exception, make it a 
checked exception. If a client cannot do anything to recover from the exception, make
it an unchecked exception.