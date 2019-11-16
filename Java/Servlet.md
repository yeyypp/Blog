# Servlet

### Life Cycle

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

### Listener
[Listener](https://www.journaldev.com/1945/servletcontextlistener-servlet-listener-example)
The Listener is implemented with Observer pattern.

### Filter
a filter is not a servlet and it has to implements the methods in the 
javax.servlet.Filter
    1. init()
    2. doFilter()
    3. destroy()
    
### Session
- Cookies
    
    - 2 types of cookies
        - Non-persistent: It is valid for single session. It is
        removed when user closed the browser.
        - Persistent: It is valid for multiple session.
    - Advantage of cookies
        - Simplest to maintain the state.
        - Stored on client side.
    - Disadvantage
        - It will not work if the browser disable the cookie
- Hidden Form Field
- URL Rewriting
- HttpSession
    the container creates a session id for each user. An object of HttpSession
    can be used to perform two tasks.
    
    - bind objects
    - view and manipulate information about a session.