# Spring Security
Fuck the Spring Security!!!!
- [What's the “principal” in Spring Security?](https://stackoverflow.com/questions/37499307/whats-the-principal-in-spri)
    
- [Obtaining information about the current user](https://docs.spring.io/spring-security/site/docs/5.2.0.M4/reference/htmlsingle/#overall-architecture)
    
    8.1.2  
    Inside the SecurityContextHolder we store details of the principal currently
    interacting with the application. Spring Security uses an Authentication object
    to represent this information. 

- [Password encoder problem](https://stackoverflow.com/questions/46999940/spring-boot-how-to-specify-the-passwordencoder)

- [guide to form based authentication](https://stackoverflow.com/questions/549/the-definitive-guide-to-form-based-website-authentication)

- [Storing SecurityContext between requests](https://docs.spring.io/spring-security/site/docs/current/reference/html/overall-architecture.html)

    8.1.4  
    Depending on the type of application, there may need to be a strategy in place to store the security context between user operations. 
    In a typical web application, a user logs in once and is subsequently identified by their session Id. 
    The server caches the principal information for the duration session. 
    In Spring Security, the responsibility for storing the SecurityContext between requests falls to the SecurityContextPersistenceFilter, 
    which by default stores the context as an HttpSession attribute between HTTP requests. 
    It restores the context to the SecurityContextHolder for each request and, crucially, 
    clears the SecurityContextHolder when the request completes. 
    You shouldn’t interact directly with the HttpSession for security purposes. 
    There is simply no justification for doing so - always use the SecurityContextHolder instead.
    
    每个用户SecurityContext存在HttpSession
    