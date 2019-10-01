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
    
    Create a selector by ``Selector selector = Selector.open()``  
    
    Registering Channels with the Selector
    ```
    channel.configureBlocking(false);
    
    SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
    ```
    The channel must be non-blocking mode to be used with Selector.
    The FileChannel is not non-blocking.  
    
    There four event that the selector is listening for channel
    - Connect
    - Accept
    - Read
    - Write  
    
    SelectionKey, when register a channel to the selector, it returned a 
    SelectionKey. It contains a few properties:
    - interest set
        
        It is the set of event that the channel is interested.
    - ready set
        
        It is the set of event that the channel is ready for
    - channel
        
        It is the channel which the SelectionKey contains.
    - selector
    - attached object(optional)  
    
    Selecting channels via a Selector, the select() method will return how
    many channels are ready. It tells how many channels that became ready since 
    last time call select().  
    
    Selecting Channels
    - int select() block until get one ready channel
    - int select(long timeout) block timeout
    - int selectNow() non-blocking will return 0 if no channel is ready
    
    wakeUp()
    - make the select() return immediately.
    
    
- Channels
    
    We can both write and read to a channel, stream can't.
    Channels can be read and written asynchronously.
    Channels always read to, or write from a Buffer.
    
- Buffer
    
    4 step to use a buffer
    - write data into buffer
    - call buffer.flip() change to read mode
    - read data out of buffer
    - call buffer.clear() or buffer.compact() to clear buffer  
    
    
    The Buffer has three properties.
    - capacity
    - position
    - limit  
    
    In write mode
    - the position point to the next cell to insert data into.
    - limit means how much data can be written into the buffer  
    
    In read mode
    - the position change to zero
    - the limit means how much data can be read  
    
    To obtain a buffer, we need to first allocate it. We can allocate non-direct
    or direct buffer. The difference is the direct buffer is outside the heap, 
    if we don't allocate direct buffer, the jvm will automatically create a direct
    buffer for us because the non direct buffer is in the heap, and the GC may move
    the address of the buffer, we need the direct buffer to store data.
    [Direct vs. non-direct buffers](https://docs.oracle.com/javase/7/docs/api/java/nio/ByteBuffer.html)  
    
    The flip() method change the buffer from writing mode to reading mode. It sets
    the position back to 0 and sets the limit to where the position was. 
    ```
    ByteBuffer buffer = ByteBuffer.allocate(48);
    
    int byteRead = channel.read(buffer);
    
    while (byteRead != -1) {
        buffer.flip();
        
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        
        buffer.clear();
        byteRead = channel.read(buffer);
    }
    
    
    ``` 
    
    Writing Data to Buffer
    - int bytesRead = channel.read(buf) or buf.put(127)  
    
    Reading Data from a Buffer
    - int byteWritten = channel.write(buf) or buf.get()
    - rewind() method can sets the position to 0, so we can reread all the data.  
    
    clear() and compact()
    - clear() will set the position to 0, and limit to capacity,the data is not clear
    ed only the marker telling where we can write the data.
    - compact() will copy unread data to beginning and set position to the last empty cell.
    - [Convert ByteBuffer to String](https://stackoverflow.com/questions/17354891/java-bytebuffer-to-string)
- [Non-blocking](http://tutorials.jenkov.com/java-nio/non-blocking-server.html)
- [NIO Examples](https://docs.oracle.com/javase/8/docs/technotes/guides/io/example/index.html)


```
Server

public class Server {
    public static void main(String[] args) {
        initServer();
    }

    public static void initServer() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(9000));
            server.configureBlocking(false);
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(256);

            while (true) {
                if (selector.select() != 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                        }

                        if (key.isReadable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            client.read(buffer);
                            String message = new String(buffer.array()).trim();
                            System.out.println("receive " + message);

                            buffer.flip();
                            client.write(buffer);
                            buffer.clear();
                        }
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

Client

public class Client {
    public static void main(String[] args) {
        initClient();
    }

    public static void initClient() {
        try {
            SocketChannel client = SocketChannel.open();
            client.connect(new InetSocketAddress("127.0.0.1", 9000));
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(256);

            buffer.put("hello".getBytes());
            buffer.flip();
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            System.out.println(new String(buffer.array()).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
    
    
    

