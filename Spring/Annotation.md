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
