```yaml
title: Go 容器
author: samin
date: 2021-09-08
```

# 数组

```go
var arrT [3]string = [3]string{"a","b","c"}
```

# slice

```go
var sliceT []string
sliceT = append(sliceT, "a")
sliceT = append(sliceT, "b")
sliceT = append(sliceT, "c")
```

# map

```go
var mapT map[int]string = map[int]string{1:"a",2:"b",3:"c"}
```

# Tips

1. 其中数组和slice能够使用cap()方法来计算长度，map则报错

```go
fmt.Println(cap(arrT))  
fmt.Println(cap(sliceT))
```  

2. 数组一定要指定长度，也可以使用以下方法，让系统自己指定  

```go
var arrT = [...]string{"a","b","c"}
```

3. len()和cap()的区别

  - len()方法求的是实际长度   
  - cap()方法求的是容量  

  容量的用处：在与当你用append()扩展长度时，如果新的长度小于容量，不会更换底层数组，否则，go会新申请一个底层数组，拷贝这边的值过去，把原来的数组丢掉。也就是说，容量的用途是：在数据拷贝和内存申请的消耗与内存占用之间提供一个权衡。  
  而长度，则是为了帮助你限制切片可用成员的数量，提供边界查询的。所以用 make 申请好空间后，需要注意不要越界

4. make()方法  

用于声明slice、map、channel，当声明slice时，可以有3个参数，如：

var slice = make([]int,2,4) // 声明了一个长度为2，容量为4的切片，长度不能大于容量，否则运行报错

5. append()方法

slice使用append()方法操作的时候，底层数组的容量会**成倍**增加。如： 
```go
var slice = make([]int, 2)  
fmt.Println(cap(slice)) // 2  
slice = append(slice, 1);  
fmt.Println(cap(slice)) // 4  
slice = append(slice, 1);  
slice = append(slice, 1);  
fmt.Println(cap(slice)) // 8
```  

6. 内置函数delete()按照指定的键将元素从映射中删除。若map为nil或无此元素，delete()不进行操作

```go
mapT := make(map[int]string)  
mapT[1] = "a"  
mapT[2] = "b"  
mapT[3] = "c"  
mapT[4] = "d"  
fmt.Printf("mapT：%v ， len(mapT)：%v\n",mapT, len(mapT)) // mapT：map[1:a 2:b 3:c 4:d] ， len(mapT)：4  
delete(mapT, 2)  
fmt.Printf("mapT：%v ， len(mapT)：%v",mapT, len(mapT)) // mapT：map[3:c 4:d 1:a] ， len(mapT)：3  
//map 是无序的,每次打印出来的 map 都会不一样,所以它不能通过 index 获取,而必须通过 key 获取 
```