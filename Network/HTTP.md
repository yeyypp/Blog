# HTTP
- [HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview)
- http status
    
    [http status](https://httpstatuses.com/)  
    - 200 OK
    - 201 Created
    - 301 Moved Permanently
    - 400 Bad Request
    - 401 Unauthorized
    - 403 Forbidden
    - 404 Not Found
    
    
    - 403 forbidden
        
        访问没有授权的url
        
- Cookie vs Session
    
    [session cookie](https://www.zhihu.com/question/19786827)  
    [session cookie2](https://zhuanlan.zhihu.com/p/63061864)  
    1. session存在服务端，cookie存在客户端
    2. session依赖session id，session id 存在cookie里，如果cookie被
    禁用，则session失效，但可以通过其他方式实现如重写url  
    3. 服务端会创建一个session对象，并赋予一个session ID 发送给客户端
    在客户端请求其他资源时会在请求头添加session ID
    
    - session
        
        Session can be achieved by
            
            1. Cookies
            2. URL Rewriting
            3. HttpSession
        Usually the session is stored in the server memory. By using 
        Spring session, we can store it in the MySQL database, which
        make it 

- HTTP/2
    
    [HTTP/2](https://developers.google.com/web/fundamentals/performance/http2)
    
- HTTP Request
    
    - Request Line
        
        Methods URL The version of the protocol
    
    - Request Header
        
    - Request Body
- Difference between HTTP 1.0 and 1.1
    
    - 1.1 allows to have a persistent connection, client can send more than one
    request on one connection. 1.0 need to open a new connection for a new request

    
    