# type关键字
- 定义结构体
- 定义接口
- 类型定义
- 类型别名
- 类型查询

### 定义结构体

```
type Book struct {
    name string
    id int
    }
```

### 定义接口

```
type Car interface {
    run() string
    }
type SportCar struct {
    name string
    }
    
func (c SportCar) run() string {
    return c.name
    }
```

### 类型定义

```
type name string

```

### 类型别名
类型别名与类型定义的不同之处在于，类型别名定义出的类型与原类型一样

```
type name = string
```

### 类型查询
使用type类型查询时，只能在switch中使用，且查询类型必须是interface{}

```
var a interface{} = "abc"

switch v := a.(type) {
.........
```