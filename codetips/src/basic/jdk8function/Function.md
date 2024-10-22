# 说明

这几个接口都在 java.util.function 包下的，分别是Consumer（消费型）、supplier（供给型）、predicate（谓词型）、function（功能性）

# Consumer

consumer 接口就是一个消费型的接口，通过传入参数，然后输出值

# Supplier

Supplier 接口是一个供给型的接口，类似一个容器，可以用来存储数据，然后可以供其他方法使用的这么一个接口

# Predicate

Predicate 接口是一个谓词型接口，类似于 bool 类型的判断的接口

# Function

Function 接口是一个功能型接口，它的一个作用就是转换作用，将输入数据转换成另一种形式的输出数据

# 使用 java.util.function 可以帮助理解 stream 中的 peek 和 map

peek 传入的是一个 Consumer 实现类，可以做一些输出，外部处理等，可以当 forEach 使用，区别在于 forEach 是终止操作

map 传入一个有返回值的 Function 实现类，表示将一种类型输入，返回转换后的类型输出
