# Interface

- 接口类型是一组方法签名的类型集合
```
type Abser interface {
    Abs() float64
    }
    
type MyFloat float64

func (m MyFloat) Abs() float64 {
    return -m
    }
```

- 接口值
```
type I interface {
    M()
    }

type T struct {
    S string
    }

func (t *T) M() {
    fmt.Println(t.S)
    }
    
func main() {
    var i I
    i = &T{"hello"}
    fmt.Println("(%v, %T)\n", i, i)
    }
```

得到的输出是
```
&{hello}, *main.T
```

- 类型断言
判断一个接口值是否保存了一个特定类型的值，用类型断言，返回两个值，一个是底层值，一个是断言是否成功的布尔值
```
package main

import "fmt"

func main() {
	var i interface{} = "hello"
	
	a, ok := i.(string)
	fmt.Println(a, ok)
}

```

- 类型选择
```
package main

import "fmt"

func do(i interface{}) {
	switch i.(type) {
	case int:
		fmt.Println("int")
	case string:
		fmt.Println("string")
	default:
		fmt.Println("default")
	}
}

func main() {
	do(20)
	do("hello")
}
```

