# I\O Streams

[Basic IO](https://docs.oracle.com/javase/tutorial/essential/io/index.html)

## I/O Streams

### Byte Streams

    ```
    public static void copyByte(String inTxt, String outTxt) {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(inTxt);
            out = new FileOutputStream(outTxt);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```
### Character Streams

```
    public static void copyCharacter(String inTxt, String outTxt) {
        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader(inTxt);
            out = new FileWriter(outTxt);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```
当需要将字节流转换为字符流时可以使用InputStreamReader,OutputStreamWriter
    在用BufferReader包裹，提高效率
    
InputStreamReader and OutputStreamWriter. 
     Use them to create character streams 
     when there are no prepackaged character stream classes that meet your needs.
     
     
在处理字符时一般都是以行为单位来处理     
 ```
public static void readFile(String inTxt) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inTxt));
                ) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
```

### Buffered Stream
如果不使用buffer，每一次的读写都会调用系统调用，频繁的用户内核空间转换，
使用buffer后，一次性读取大量数据，会写入大量数据，减少系统调用
```
public static void writeFile(String outTxt) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(outTxt));
                ) {
            String line = "hello";
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

### Object Streams

用于读写实现了序列化的对象

