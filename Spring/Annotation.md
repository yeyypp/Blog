# Annotation

- @Controller
    
    is a class which is responsible for preparing a model
    Map with data to be displayed by the view.
    It can also directly write into response stream by using 
    @ResponseBody
    返回的是view对象，加了@ResponseBody后返回数据
    
- @RestController
    
    is a combination of @Controller and @ResponseBody
    REST API return data in form of JSON or XML

- @RequestParam
    
    extract query parameters or form parameters or files from request.
    
- @PathVariable
    
    also can extract data from URI and it is used for dynamic values(pattern)

- @SpringBootApplication
    
   @SpringBootApplication is a convenience annotation that adds all of the following:

    @Configuration tags the class as a source of bean definitions for the application context.

    @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, 
    other beans, and various property settings. For example, 
    if spring-webmvc is on the classpath this flags the application as a web application 
    and activates key behaviors such as setting up a DispatcherServlet.

    @ComponentScan tells Spring to look for other components, configurations, 
    and services in the package, allowing it to find the controllers. 
    
- @ModelAttribute
    
    indicates the argument should be retrieved from the model
    @ModelAttribute(name = "object") Greeting greeting

- @Scope
    
    [Bean scope](https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s04.html)  
    [The difference between singleton and prototype](https://stackoverflow.com/questions/16058365/what-is-difference-between-singleton-and-prototype-bean)
    
- Spring Boot Jpa
    
    - @JoinColumn
        
        It is used to create foreign key in the entity.
        [JoinColumn](https://www.baeldung.com/jpa-join-column)
        [mappedBy](https://www.baeldung.com/jpa-joincolumn-vs-mappedby)