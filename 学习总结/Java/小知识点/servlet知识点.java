/**
 1.当引用dependencey时需要在scope标签处加入provided表示只在编译和测试是使用，因为tomcat中也包含servlet会产生冲突
 <dependency>
 <groupId>javax.servlet</groupId>
 <artifactId>javax.servlet-api</artifactId>
 <version>3.1.0</version>
 <scope>provided</scope>
 </dependency>

 2.当不支持post方法时，可以在get方法里调用post方法

 3.客户端重定向与服务器请求转发
    服务器跳转，地址内容不变客户端请求到达服务器后，服务器在内部请求另一个资源，属于一次request
    request.getRequestDispaatcher("....html").forward(request, response);
    客户端重定向，请求到达服务器后，服务器返回一个响应，客户端再根据相应中的url，发送请求，服务器依此回应
 response.sendRedirect("....html");

 4.session cookie 区别
    session保存在服务端，cookie保存在客户端
 Session是在服务端保存的一个数据结构，用来跟踪用户的状态，这个数据可以保存在集群、数据库、文件中；
 Cookie是客户端保存用户信息的一种机制，用来记录用户的一些信息，也是实现Session的一种方式

 WEB容器默认的是用Cookie机制保存SessionID到客户端，并将此Cookie设置为关闭浏览器失效

 般这种情况下，会使用一种叫做URL重写的技术来进行会话跟踪，即每次HTTP交互，URL后面都会被附加上一个诸如 sid=xxxxx 这样的参数，服务端据此来识别用户。

 4.MVC
 model view controler
 model就是数据
 view是网页，用来显示数据
 controler把不同数据显示在网页上

5. /**
 * servlet 是单例，当多个客户端请求发出请求时，都会调用同一个servlet对象，其是否安全由它的实现来确定，
 * 若实现中定义了实例变量，或静态变量则线程不安全
 */






