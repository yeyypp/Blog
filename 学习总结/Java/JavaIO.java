public class Main {
    /**
     * 流可以看作是数据的序列
     * InPutStream:代表着从流中读取数据
     * OutPutStream：代表将数据输出到流中
     *
     * 一般用BufferedInputStream，
     * 当不用时，意味着每一次io都需要经过操作系统直接处理，
     * 当使用了之后会在内存区域开辟一个缓冲区
     * 当读数据时，会从buffer读，为空时才会触发系统调用，从硬盘中读取
     * 当写数据时，只有buffer满时，才会写入硬盘
     * PrintWriter pw = new PrintWriter(client.getOutputStream(), true); true代表autoflush
     */

    // BufferedReader
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String lines = null;
            while ((lines = br.readLine()) != null) {
                if (lines.equals("quit")) {
                    break;
                }
                System.out.println(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 直接io，缓冲io
     * 缓冲io
     * 在内核中维护一个缓冲区，无论用户读取的数据多小，都从磁盘中加载一段数据到缓冲区中，下次可以直接从缓冲区中读取
     * 可以减少磁盘读取的次数
     *
     *  1.用户调用read方法
     *
     * 2. 调用系统调用，触发中断，进程从用户态进入内核态
     *
     * 3. 从硬盘中读取数据并复制到kernel缓冲区
     *
     * 4. 将数据从kernel缓存区复制到用户提供的byte数组中
     *
     * 5. 进程从内核态返回到用户态
     *
     * 直接io
     * 从磁盘直接读入数据
     */

    /**
     * 当直接使用inputstream.close时socket也会关闭
     * 用shutinputstram关闭流
     */

    /**
     * 传统bio
     * 一个线程负责一个连接，采用线程池，复用线程
     * 但线程之间的切换对于操作系统来说是昂贵的，因为需要保存现在线程的状态，再切换到另一个线程
     *
     * 代理 代理的对象是客户端 反向代理 代理的对象是服务器
     */



    public class Test {
        public static void main(String[] args) {
            try (FileInputStream fis = new FileInputStream("C:\\Users\\18610\\Desktop\\in.txt")) {
                //字节时
                byte[] buffer = new byte[1024];
                int end = 0;
                while ((end = fis.read(buffer)) != -1) {
                    System.out.println(new String(buffer, 0, end));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (FileReader fr = new FileReader("C:\\Users\\18610\\Desktop\\in.txt")) {
                char[] buffer = new char[10];
                int end = 0;
                while ((end = fr.read(buffer)) != -1) {
                    System.out.println(new String(buffer, 0, end));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
    }
}