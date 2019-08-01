# Practice
### 循环与函数
```
package main

import (
	"fmt"
)

func Sqrt(x float64) float64 {
	z := float64(1)
	for i := 0; i < 9; i++ {
		z -= (z * z - x) / (2 * z)
		fmt.Println(z);
	}
	return z
}

func main() {
	fmt.Println(Sqrt(2))
}
```

### 映射
```
package main

import (
	"golang.org/x/tour/wc"
	"strings"
)

func WordCount(s string) map[string]int {
	m := make(map[string]int)
	for _, v := range strings.Fields(s) {
		count, exist := m[v]
		if exist {
			m[v] = count + 1
		} else {
			m[v] = 1
		}
	}
		return m
		
}

func main() {
	wc.Test(WordCount)
}
```

### 斐波那契闭包
```
package main

import "fmt"

// 返回一个“返回int的函数”
func fibonacci() func() int {
	a, b := -1, 1
	return func() int {
		a, b = b, a + b
		return b
	}
}

func main() {
	f := fibonacci()
	for i := 0; i < 10; i++ {
		fmt.Println(f())
	}
}
```