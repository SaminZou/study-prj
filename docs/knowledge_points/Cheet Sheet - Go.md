```yaml
title: Cheet Sheet - Go
author: samin
date: 2021-10-29
```

# hello world

```go
package main

import "fmt"

func main(){
    fmt.Println("hello world")
}
```

# 字符串连接

字符串：+

与其他类型：不能直接拼接，需要转换，或者调用字符串拼接方法，如 fmt.Sprintf

# 变量

var 变量名 变量类型 = 初始值

```go
var a string = "initial"
var b,c int = 1,2
var d = true
var e int
f := "short"
```

# 常量

```go
const s string = "constant"

// 可作枚举
const (
    Unknown = 0
    Female = 1
    Male = 2
)
```

# 循环

```go
i := 1

for i <= 3 {
    i = i + 1
}

for j := 7; j <= 9; j++ {}

for {}

```

# 分支

1. if / else

```go
if true {

} else {

}


```

2. switch ( 不需要 break )

```go
switch i {
    case 1,2:
    case 3:
    default:
}
```

# 数组

```go
var a [5]int

b := [5]int{1,2,3,4,5}
len(b) = 5

var c [10][5]int
```

# Slices

```go
a := make([]string, 3)

b := make([][]int, 3)

c := []string{"a","b","c"}

// 添加元素
a = append(a, "e","f")

// 切片操作
l := a[2:5]
l := a[:5]
l := a[2:]
```

# Map

```go
m := make(map[string]int)
n := map[string]int{"foo":1, "bar":2}

delete(n, "bar")

value, is_exist := n["bar"]
```

# Range

```go
array := []int{1,2,3,4,5}

for index,value := range array {
}
```

# 函数

```go
func plus(a int,b int) int {}
func plusPlus(a,b,c int) int {}

// 返回多个结果
func vals() (int,int) {}

func sum(nums ...int) {

    for k,v := range nums {}
}
```

# Closures ( 闭包 )

```go
func intSeq() func() int {
    i := 0
    return func() int {
        i += 1
        return i
    }
}

nextInt := intSeq()
```

# 指针

# 结构体

```go
type person struct {
    name string
    age int
}

person{"Bob",20}
person{name: "Alice",age: 30}
person{name: "Fred"}

s := person{name: "Sean",age: 40}
s.name

sp := &s
```