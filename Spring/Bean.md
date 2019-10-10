# Bean
- Scope
    
    singleton: single object instance per IoC container
    prototype: new Instance every time the prototype is referenced
    request: single object instance per request
    session: single object instance per session
    global session:
    
- Bean life cycle
    - instantiation
    - populate properties
    - implement BeanNameAware and BeanFactoryAware to get
    Bean ID and BeanFactory
    - postProcessBeforeInitialization to enhance bean
    - afterPropertySet
    - postProcessAfterInitialization to enhance bean
    After all above actions, the bean is ready to use.
    - DisposableBean and destroy-method 
    Then the bean is destroyed.