# 1 使用场景

用来对超过 16 位有效位的数进行精确的运算。

一般情况下，对于那些不需要准确计算精度的数字，我们可以直接使用 Float 和 Double 处理，但是 Double.valueOf(String) 和 Float.valueOf(String) 会丢失精度。

# 2 构造函数

- BigDecimal(int)
创建一个具有参数所指定整数值的对象

- BigDecimal(double)
创建一个具有参数所指定双精度值的对象

- BigDecimal(long)
创建一个具有参数所指定长整数值的对象

- BigDecimal(String)
创建一个具有参数所指定以字符串表示的数值的对象

> 虽然提供了多种构造函数，建议还是使用 String 入参的构造函数，可以预知解析后的实际值
> 如 0.1 的 double 类型值，实际上等于 0.1000000000000000055511151231257827021181583404541015625
> 所以类似 double 值的源，也可以使用 Double.toString(double) 先进行字符串的转换再进行 BigDecimal 初始化使用

# 3 常用方法

- add(BigDecimal)
BigDecimal对象中的值相加，返回BigDecimal对象

- subtract(BigDecimal)
BigDecimal对象中的值相减，返回BigDecimal对象

- multiply(BigDecimal)
BigDecimal对象中的值相乘，返回BigDecimal对象

- divide(BigDecimal)
BigDecimal对象中的值相除，返回BigDecimal对象

- toString()
将BigDecimal对象中的值转换成字符串

- doubleValue()
将BigDecimal对象中的值转换成双精度数

- floatValue()
将BigDecimal对象中的值转换成单精度数

- longValue()
将BigDecimal对象中的值转换成长整数

- intValue()
将BigDecimal对象中的值转换成整数

- compareTo(BigDecimal)
比较大小。-1 表示小于，0 表示等于，1 表示大于

# 4 格式化

NumberFormat 类的 format() 方法

# 5 常见异常

除法的时候出现异常，ArithmeticException，出现无限循环小数导致，一般设置精度，divide 方法设置精确的小数点，如：divide(BigDecimal,2)