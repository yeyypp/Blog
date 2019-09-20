# Servlet

- Life Cycle

    1. Load Servlet Class.
    2. Create Instance of Servlet.
    3. Call the servlets init() method.
    4. Call the servlets service() method.
    5. Call the servlets destroy() method.

- 是否单例

是单例的，需要根据具体实现判断是否线程安全

- 运行流程
    1. 用户输入一个URL，服务器收到请求后，将其匹配到相应的servlet
    2. 如果没有创建servlet实例，则加载该类并初始化
    3. 调用init(), service()
    4. 1如有必要调用destroy()清除

- request中信息获取
request.getHeader() request.getUrl()

- Listener
[Listener](https://www.journaldev.com/1945/servletcontextlistener-servlet-listener-example)
The Listener is implemented with Observer pattern.

- Filter
a filter is not a servlet and it has to implements the methods in the 
javax.servlet.Filter
    1. init()
    2. doFilter()
    3. destroy()