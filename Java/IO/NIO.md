# NIO
[NIO](http://tutorials.jenkov.com/java-nio/channels.html)
- The key of NIO
    
    In standard IO, we work with byte stream and character stream. In NIO we work
    with **channels** and **buffers**. Data is always read from a channel into a buffer, or 
    written from a buffer to a channel.  
    The primary Channel:
    - FileChannel
    - DatagramChannel
    - SocketChannel
    - ServerSocketChannel
    The buffer:
    - ByteBuffer
    - CharBuffer
    - DoubleBuffer
    - FloatBuffer
    - IntBuffer
    - MappedByteBuffer

- Selectors
    
    A selectors allows a single thread to handle multiple channels.
    To uses a selector, register a channel to it first. Then call the 
    select() method. **This method will block** until there is an event
    ready for the registered channels.
    
- Channels
    
    We can both write and read to a channel, stream can't.
    Channels can be read and written asynchronously.
    Channels always read to, or write from a Buffer.
    

