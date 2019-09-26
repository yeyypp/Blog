# Spring MVC
- [How Spring MVC Work](https://stackify.com/spring-mvc/)
    
    - DispatcherServlet 
        
        It is the heart of Spring MVC. It receives all request for the 
        application.  
        
        DispatcherServlet -> FrameworkServlet -> HttpServletBean -> HttpServlet
        -> GenericServlet  
        
        It unifying the request processing and enriching the request.  
        
        The core responsibility of a DispatcherServlet is to dispatch incoming 
        HttpRequests to the correct handlers specified with the @Controller or
        @RestController annotations.
     
    - HandlerAdapter
        
        It is used in conjunction with the **HandlerMapping**, which maps a method
        to a URL.  
        
        The DispatcherServlet then uses a HandlerAdapter to invoke this method.
        
        The RequestMappingHandlerAdapter makes sure the arguments of the method are
        resolved from the HttpServletRequest and it creates the ModelAndView object
        from the method's return value.
    
    - HandlerMapping
        
        It maps a method to a specific URL.  
        
        A controller is a POJO with several @RequestMapping annotations, a handler
        is basically a method of this class wrapped in a HandlerMethod instance.
        To adapt to this handler type, Spring uses the RequestMappingHandlerAdapter
        class.
      
    - ViewResolver
        
        Spring has processed the HTTP request and received a ModelAndView object,
        it has to render the HTML page. 
        
- Conclusion
    
    When a request come to the application, the DispatcherServlet invoke the handler
    through HandlerAdapter, the HandlerAdapter through the HandlerMapping to find the
    handler. Then the HandlerAdapter creates a ModelAndView object from the handler's
    return value and return it to DispatcherServlet. Finally it invoke the ViewResolver
    to render the HTML page
        
        