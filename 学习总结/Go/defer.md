# defer

defer语句会将函数推迟到外层函数返回之后执行,推迟的函数会被压入一个栈中，当外层函数返回后
推迟的函数会按顺序调用
```
package main

import "fmt"

func main() {
	defer fmt.Println("shuai")
	for i := 0; i < 5; i++ {
		defer fmt.Println(i)
	}
	defer fmt.Println("hello")
	fmt.Println("world")
}
```
返回
```
world
hello
4
3
2
1
0
shuai
```

defer中的函数的参数，记录的是调用defer前的
```
package main

import "fmt"

type Book struct {
	name string
}

func main() {
	b := Book{"Go"}
	fmt.Println(b)
	defer fmt.Println(b)
	a := &b
	a.name = "c"
	fmt.Println(b)
}
```
返回
```
{Go}
{c}
{Go}
```