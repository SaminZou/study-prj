```yaml
title: Java代码优化总结
author: samin
date: 2021-02-15
```

# 优化目标

- 减小代码的体积

- 提高代码运行的效率

# 优化细节

- 尽量指定类、方法的final修饰符

带有final修饰符的类是不可派生的。在Java核心API中，有许多应用final的例子，例如java.lang.String，整个类都是final的。为类指定final修饰符可以让类不可以被继承，为方法指定final修饰符可以让方法不可以被重写。如果指定了一个类为final，则该类所有的方法都是final的。Java编译器会寻找机会内联所有的final方法，内联对于提升Java运行效率作用重大，具体参见Java运行期优化。

此举能够使性能平均提高50%。

- 尽量重用对象

特别是String对象的使用，出现字符串连接时应该使用 StringBuilder/StringBuffer 代替。由于Java虚拟机不仅要花时间生成对象，以后可能还需要花时间对这些对象进行垃圾回收和处理，因此，生成过多的对象将会给程序的性能带来很大的影响。

- 尽可能使用局部变量

调用方法时传递的参数以及在调用中创建的临时变量都保存在栈中速度较快，其他变量，如静态变量、实例变量等，都在堆中创建，速度较慢。另外，栈中创建的变量，随着方法的运行结束，这些内容就没了，不需要额外的垃圾回收。

- 及时关闭流

Java编程过程中，进行数据库连接、I/O流操作时务必小心，在使用完毕后，及时关闭以释放资源。因为对这些大对象的操作会造成系统大的开销，稍有不慎，将会导致严重的后果。

- 尽量减少对变量的重复计算

明确一个概念，对方法的调用，即使方法中只有一句语句，也是有消耗的，包括创建栈帧、调用方法时保护现场、调用方法完毕时恢复现场等。所以例如下面的操作：

for (int i = 0; i < list.size(); i++){...}

建议替换为：

for (int i = 0, int length = list.size(); i < length; i++){...}

这样，在list.size()很大的时候，就减少了很多的消耗

- 尽量采用懒加载的策略，即在需要的时候才创建

```java
String str="aaa";
        if(i==1){
        list.add(str);
        }

// 建议替换为

        if(i==1){
        String str="aaa";
        list.add(str);
        }
```

- 慎用异常

异常对性能不利。抛出异常首先要创建一个新的对象，Throwable接口的构造函数调用名为fillInStackTrace()的本地同步方法，fillInStackTrace()方法检查堆栈，收集调用跟踪信息。只要有异常被抛出，Java虚拟机就必须调整调用堆栈，因为在处理过程中创建了一个新的对象。异常只能用于错误处理，不应该用来控制程序流程。

- 不要在循环中使用try…catch…，应该把其放在最外层

- 为底层以数组方式实现的集合、工具类指定初始长度

比如ArrayList、LinkedLlist、StringBuilder、StringBuffer、HashMap、HashSet等等，以StringBuilder为例：

1. StringBuilder() // 默认分配16个字符的空间
2. StringBuilder(int size) // 默认分配size个字符的空间
3. StringBuilder(String str) // 默认分配16个字符+str.length()个字符空间

可以通过类（这里指的不仅仅是上面的StringBuilder）的来设定它的初始化容量，这样可以明显地提升性能。比如StringBuilder吧，length表示当前的StringBuilder能保持的字符数量。因为当StringBuilder达到最大容量的时候，它会将自身容量增加到当前的2倍再加2，无论何时只要StringBuilder达到它的最大容量，它就不得不创建一个新的字符数组然后将旧的字符数组内容拷贝到新字符数组中—-这是十分耗费性能的一个操作。试想，如果能预估到字符数组中大概要存放5000个字符而不指定长度，最接近5000的2次幂是4096，每次扩容加的2不管，那么：

1. 在4096 的基础上，再申请8194个大小的字符数组，加起来相当于一次申请了12290个大小的字符数组，如果一开始能指定5000个大小的字符数组，就节省了一倍以上的空间；
2. 把原来的4096个字符拷贝到新的的字符数组中去。

这样，既浪费内存空间又降低代码运行效率。所以，给底层以数组实现的集合、工具类设置一个合理的初始化容量是错不了的，这会带来立竿见影的效果。但是，注意，像HashMap这种是以数组+链表实现的集合，别把初始大小和你估计的大小设置得一样，因为一个table上只连接一个对象的可能性几乎为0。初始大小建议设置为2的N次幂，如果能估计到有2000个元素，设置成new HashMap(128)、new
HashMap(256)都可以。

- 当复制大量数据时，使用System.arraycopy()命令

- 乘法和除法使用移位操作

```java
for(val=0;val< 100000;val+=5){
        a=val*8;
        b=val/2;
        }

        用移位操作可以极大地提高性能，因为在计算机底层，对位的操作是最方便、最快的，因此建议修改为：


        for(val=0;val< 100000;val+=5){
        a=val<<3;
        b=val>>1;
        }
```

移位操作虽然快，但是可能会使代码不太好理解，因此最好加上相应的注释。

- 循环内不要不断创建对象引用

```java
for(int i=1;i<=count;i++){
        Object obj=new Object();
        }

        这种做法会导致内存中有count份Object对象引用存在，count很大的话，就耗费内存了，建议为改为：


        Object obj=null;

        for(int i=0;i<=count;i++){
        obj=new Object();
        }

```

这样的话，内存中只有一份Object对象引用，每次new Object()的时候，Object对象引用指向不同的Object罢了，但是内存中只有一份，这样就大大节省了内存空间了。

- 基于效率和类型检查的考虑，应该尽可能使用array，无法确定数组大小时才使用ArrayList

- 尽量使用HashMap、ArrayList、StringBuilder，除非线程安全需要，否则不推荐使用Hashtable、Vector、StringBuffer，后三者由于使用同步机制而导致了性能开销

- 不要将数组声明为public static final

因为这毫无意义，这样只是定义了引用为static final，数组的内容还是可以随意改变的，将数组声明为public更是一个安全漏洞，这意味着这个数组可以被外部类所改变。

- 尽量在合适的场合使用单例

使用单例可以减轻加载的负担、缩短加载的时间、提高加载的效率，但并不是所有地方都适用于单例，简单来说，单例主要适用于以下三个方面：

1. 控制资源的使用，通过线程同步来控制资源的并发访问
2. 控制实例的产生，以达到节约资源的目的
3. 控制数据的共享，在不建立直接关联的条件下，让多个不相关的进程或线程之间实现通信

- 尽量避免随意使用静态变量

要知道，当某个对象被定义为static的变量所引用，那么gc通常是不会回收这个对象所占有的堆内存的，如：

```java
public class A {
    private static B b = new B();
}
```

此时静态变量b的生命周期与A类相同，如果A类不被卸载，那么引用B指向的B对象会常驻内存，直到程序终止

- 及时清除不再需要的会话

为了清除不再活动的会话，许多应用服务器都有默认的会话超时时间，一般为30分钟。当应用服务器需要保存更多的会话时，如果内存不足，那么操作系统会把部分数据转移到磁盘，应用服务器也可能根据MRU（最近最频繁使用）算法把部分不活跃的会话转储到磁盘，甚至可能抛出内存不足的异常。如果会话要被转储到磁盘，那么必须要先被序列化，在大规模集群中，对对象进行序列化的代价是很昂贵的。因此，当会话不再需要时，应当及时调用HttpSession的invalidate()
方法清除会话。

- 实现RandomAccess接口的集合比如ArrayList，应当使用最普通的for循环而不是foreach循环来遍历

这是JDK推荐给用户的。JDK
API对于RandomAccess接口的解释是：实现RandomAccess接口用来表明其支持快速随机访问，此接口的主要目的是允许一般的算法更改其行为，从而将其应用到随机或连续访问列表时能提供良好的性能。实际经验表明，实现RandomAccess接口的类实例，假如是随机访问的，使用普通for循环效率将高于使用foreach循环；反过来，如果是顺序访问的，则使用Iterator会效率更高。可以使用类似如下的代码作判断：

```java
if(list instanceof RandomAccess){
        for(int i=0;i<list.size();i++){

        }
        }else{
        Iterator<?> iterator=list.iterable();
        while(iterator.hasNext()){
        iterator.next()
        }
        }
```

foreach循环的底层实现原理就是迭代器Iterator，所以后半句反过来，如果是顺序访问的，则使用Iterator会效率更高的意思就是顺序访问的那些类实例，使用foreach循环去遍历。

- 使用同步代码块替代同步方法

这点在多线程模块中的synchronized锁方法块一文中已经讲得很清楚了，除非能确定一整个方法都是需要进行同步的，否则尽量使用同步代码块，避免对那些不需要进行同步的代码也进行了同步，影响了代码执行效率。

- 将常量声明为static final，并以大写命名

这样在编译期间就可以把这些内容放入常量池中，避免运行期间计算生成常量的值。另外，将常量的名字以大写命名也可以方便区分出常量与变量

- 不要创建一些不使用的对象，不要导入一些不使用的类

这毫无意义，如果代码中出现”The value of the local variable i is not used”、”The import java.util is never used”，那么请删除这些无用的内容

- 程序运行过程中避免使用反射

关于，请参见反射。反射是Java提供给用户一个很强大的功能，功能强大往往意味着效率不高。不建议在程序运行过程中使用尤其是频繁使用反射机制，特别是Method的invoke方法，如果确实有必要，一种建议性的做法是将那些需要通过反射加载的类在项目启动的时候通过反射实例化出一个对象并放入内存—-用户只关心和对端交互的时候获取最快的响应速度，并不关心对端的项目启动花多久时间。

- 使用数据库连接池和线程池

这两个池都是用于重用对象的，前者可以避免频繁地打开和关闭连接，后者可以避免频繁地创建和销毁线程

- 使用带缓冲的输入输出流进行IO操作

带缓冲的输入输出流：BufferedReader、BufferedWriter、BufferedInputStream、BufferedOutputStream

这可以极大地提升IO效率

- 顺序插入和随机访问比较多的场景使用ArrayList，元素删除和中间插入比较多的场景使用LinkedList

这个，理解ArrayList和LinkedList的原理就知道了

- 不要让public方法中有太多的形参

public方法即对外提供的方法，如果给这些方法太多形参的话主要有两点坏处：

1. 违反了面向对象的编程思想，Java讲求一切都是对象，太多的形参，和面向对象的编程思想并不契合
2. 参数太多势必导致方法调用的出错概率增加

至于这个”太多”指的是多少个，3、4个吧。比如我们用JDBC写一个insertStudentInfo方法，有10个学生信息字段要插如Student表中，可以把这10个参数封装在一个实体类中，作为insert方法的形参。

- 字符串变量和字符串常量equals的时候将字符串常量写在前面

```java
String str="123";
        if(str.equals("123")){...}

// 建议修改为


        String str="123";
        if("123".equals(str)){...}
```

这么做主要是可以避免空指针异常

- 不要对数组使用toString()方法

看一下对数组使用toString()打印出来的是什么：

```java
public static void main(String[] args) { 
    int[] is = new int[]{1, 2, 3}; 
    System.out.println(is.toString()); 
}
``` 

结果是：[I@18a992f

本意是想打印出数组内容，却有可能因为数组引用is为空而导致空指针异常。不过虽然对数组toString()没有意义，但是对集合toString()是可以打印出集合里面的内容的，因为集合的父类AbstractCollections重写了Object的toString()方法。

- 不要对超出范围的数字基本数据类型做向下强制转型，同时注意数字基本类型相加的结果范围

- 公用的集合类中不使用的数据一定要及时remove掉

如果一个集合类是公用的（也就是说不是方法里面的属性），那么这个集合里面的元素是不会自动释放的，因为始终有引用指向它们。所以，如果公用集合里面的某些数据不使用而不去remove掉它们，那么将会造成这个公用集合不断增大，使得系统有内存泄露的隐患。


- 把一个基本数据类型转为字符串，基本数据类型.toString()是最快的方式、String.valueOf(数据)次之、数据+””最慢

把一个基本数据类型转为一般有三种方式，我有一个Integer型数据i，可以使用i.toString()、String.valueOf(i)、i+””三种方式，三种方式的效率如何，看一个测试：

```java
public static void main(String[] args) { 
    int loopTime = 50000; 
    Integer i = 0; 
    long startTime = System.currentTimeMillis(); 
    for (int j = 0; j < loopTime; j++) { 
        String str = String.valueOf(i); 
    } 
    System.out.println("String.valueOf() : " + (System.currentTimeMillis() - startTime) + "ms"); 
    
    startTime = System.currentTimeMillis(); 
    for (int j = 0; j < loopTime;j++){ 
        String str = i.toString(); 
    } 
    System.out.println("Integer.toString() : " + (System.currentTimeMillis() - startTime) + "ms");
    
    startTime = System.currentTimeMillis(); 
    for (int j = 0; j < loopTime; j++) { 
        String str = i + ""; 
    } 
    System.out.println("i + \"\" : " + (System.currentTimeMillis() - startTime) + "ms"); 
}
``` 
运行结果为：  
String.valueOf() : 11ms  
Integer.toString() : 5ms  
i + "" : 25ms

所以以后遇到把一个基本数据类型转为String的时候，优先考虑使用toString()方法。至于为什么，很简单：

1. String.valueOf()方法底层调用了Integer.toString()方法，但是会在调用前做空判断 

2. Integer.toString() 方法就不说了，直接调用了
   
3. i + "" 底层使用了StringBuilder实现，先用append方法拼接，再用toString()方法获取字符串 
   
三者对比下来，明显是2最快、1次之、3最慢

- 使用最有效率的方式去遍历Map

遍历Map的方式有很多，通常场景下我们需要的是遍历Map中的Key和Value，那么推荐使用的、效率最高的方式是：

```java
public static void main(String[] args) { 
    HashMap<String, String> hm = new HashMap<>(); 
    hm.put("111", "222");
    Set<Map.Entry<String, String>> entrySet = hm.entrySet(); 
    Iterator<Map.Entry<String, String>> iter = entrySet.iterator(); 
    while (iter.hasNext()) { 
        Map.Entry<String, String> entry = iter.next(); 
        System.out.println(entry.getKey() + "\t" + entry.getValue()); 
    } 
}
```

如果你只是想遍历一下这个Map的key值，那用 Set keySet = hm.keySet(); 会比较合适一些

- 对需要关闭方法的资源类 try{}catch(){} 调用,善用 try(){}catch(){} 语法糖

# 《Effect Java》 推荐方式


- 考虑用静态工厂方法替代构造函数

Sample:

Integer.valueOf("1")  
Boolean.valueOf("true")

优势：
1. 可读性高（方法名）

new Point(x,y)和Point.at(x,y)、Point.origin()。构造函数只能看出两个参数，不知其意，后者更易理解。

2. 性能（不一定创建对象）

在某些情况下，可以事先进行实例化一些对象，调用时直接调用即可，不需要进行改变。比如 Boolean

```java
public final class Boolean implements Serializable, Comparable<Boolean> {
    
    // 预先设置两个对象
    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);

    public Boolean(boolean var1) {
        this.value = var1;
    }

    public Boolean(String var1) {
        this(parseBoolean(var1));
    }

    // 工厂方法
    public static Boolean valueOf(boolean var0) {
        // 返回预先设置的对象，而不是创建对象
        return var0?TRUE:FALSE;
    }
    // 工厂方法
    public static Boolean valueOf(String var0) {
        return parseBoolean(var0)?TRUE:FALSE;
    }
    // ... other code
}
``` 

3. 灵活性高

可根据具体情况，返回子类，相当于更强大的工厂，直接从父类获取到子类，其适用于工具类（提供各种API）

```java
public class Collections {
    
    // 私有，典型工厂
    private Collections() {
    }

    public static final List EMPTY_LIST = new EmptyList<>();
    
    // 工厂方法
    public static final <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }
    
    private static class EmptyList<E> extends AbstractList<E> implements RandomAccess, Serializable {
    // code
    }

    // 工厂方法
    public static <E> List<E> checkedList(List<E> list, Class<E> type) {
    // 根据具体情况，获取相应子类
        return (list instanceof RandomAccess ?
                new CheckedRandomAccessList<>(list, type) :
                new CheckedList<>(list, type));
    }

    // 子类1
    static class CheckedRandomAccessList<E> extends CheckedList<E> implements RandomAccess {
        CheckedRandomAccessList(List<E> list, Class<E> type) {
            super(list, type);
        }

        public List<E> subList(int fromIndex, int toIndex) {
            return new CheckedRandomAccessList<>(
                    list.subList(fromIndex, toIndex), type);
        }
    }

    // 子类2
    static class CheckedList<E> extends CheckedCollection<E> implements List<E> {
    // code
    }
}
```

- 多个构造函数时，考虑使用构造器

尤其在进行Android开发时，会碰到这种情况。通常是一个对象，具有多个成员变量可能需要初始化，常规方法，需要提供大量构造函数

```java
// 非Android中的AlertDialog，便于说明问题，举个例子
public class AlertDialog {

    private int width;
    private int height;
    private String title;
    private String confirmText;
    private String denyText;

    private AlertDialog(){}
    
    public AlertDialog(int width, int height){ // 空白的警告框
         AlertDialog(width,height,null);
    }

    // 带标题的警告框
    public AlertDialog(int width, int height, String title){
        AlertDialog(width, height, title, "确定");
    }

    // 带标题的警告框，有确定按钮
    public AlertDialog(int width, int height, String title, String confirm){
        AlertDialog(width, height, title, confirm, null);
    }

    // 带标题的警告框，有确定按钮，取消按钮
    public AlertDialog(int width, int height, String title, String confirm, String denyText){
        // set every thing.
    }
}
```

有多种样式的警告框，为了调用方便，必须提供多个构造函数。否则用户在调用时，只能使用完整构造函数，容易犯错且无法进行阅读。极不灵活。如果采用另外一种方式，则可以解决，但会花费很多经历处理并发的情况

```java
// 非Android中的AlertDialog，便于说明问题，举个例子
public class AlertDialog {
    
    private int width;
    private int height;
    private String title;
    private String confirmText;
    private String denyText;

    public AlertDialog(){}// 空白的构造函数
   
    public void setWidth(int width){
        this.width = width;
    }
    // 其他set方法
}
```

调用时，通过调用各个参数的set方法进行设置。 

缺点：并发、无法进行参数校验。

在Android中，大量的控件都使用了构造器Builder。

```java
// 非Android中的AlertDialog，便于说明问题，举个例子
public class AlertDialog {

    private int width;
    private int height;
    private String title;
    private String confirmText;
    private String denyText;

    // private
    private AlertDialog(){}

    // Builder中使用
    protected AlertDialog(Builder b){
        width = b.width;
        height = b.height;
        // .....
        if(width==0||height==0) throws new Exception("size must be set");
    }

    // 构造器
    public static class Builder {
        private int width;
        private int height;
        private String title;
        private String confirmText;
        private String denyText;

        // 注意：返回的Builder。
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        // 其他set...
        
        public AlertDialog build(){
            return AlertDialog(this);
        }
    }
}
```

于是，可以根据相应需求，进行相应设置，并在AlertDialog真正构造时，进行参数校验。就像这样：  
new AlertDialog.Builder().setTitle("提示").build();

- 通过私有化构造器强化不可实例化的能力

有一些工具类，仅仅是提供一些能力，自己本身不具备任何属性，所以，不适合提供构造函数。  
然而，缺失构造函数编译器会自动添加上一个无参的构造器。  
所以，需要提供一个私有化的构造函数。为了防止在类内部误用，再加上一个保护措施和注释。

```java
public class Util{
    private Util(){
        // 抛出异常，防止内部误调用
        throw new AssertionError();
    }
}
```

弊端是无法对该类进行继承，子类会调用super()。

- 避免创建不必要的对象

**对象的重用**  
**昂贵的对象**，使用对象池  
**廉价的对象**，慎用对象池，现代JVM对廉价对象的创建和销毁非常快，此时不适于使用对象池

- 消除过期的对象引用

以下三种情况可能会造成内存泄露:

1. 自己管理的内存（数组长度减小后，pop出的对象容易导致内存泄漏）

```java
public class Stack{
    
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack(){
         elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        // allocate新的堆内存和栈内存
        elements[size++]=e;
    }

    public Object pop(){
        if(size==0) throw new EmptyStackException();
        // pop出element[size]，该对象不再有效。内存泄漏原因。
        return element[--size];
    }
    
    private void ensureCapacity(){
        if(elements.length==size)
            elements = Arrays.copyOf(elements, 2*size+1);
    }
}
```
弹出的对象不再有效，但JVM不知道，所以会一直保持该对象，造成内存泄露。

```java
// 解决
public Object pop(){
    if(size==0) throw new EmptyStackException();
    elements[size] = null; // 等待回收
    return element[--size];
}
```   

2. 缓存

缓存的对象容易被程序员遗忘，需要设置机制来维护缓存，例如不定期回收不再使用的缓存（使用定时器）。某些情况下，使用WeakHashMap可以达到缓存回收的功效。注，只有缓存依赖于外部环境，而不是依赖于值时，WeakHashMap才有效。   

3. 监听和回调

使用监听和回调要记住取消注册。确保回收的最好的实现是使用弱引用（weak reference），例如，只将他们保存成WeakHashMap的键。

- 避免显示调用GC

Java的GC有强大的回收机制，可以简单的记住：不要显示调用finalizer。可以这样理解：  

jvm是针对具体的硬件设计的，然而程序却不是针对具体硬件设计的，所以，java代码无法很好的解决gc问题（因为他具有平台差异化）。另外，finalizer的性能开销也非常大，从这个角度上考虑也不应该使用它。

- 覆盖equals方法请遵守通用约定

1. 自反性   

x.equals(x) == true

2. 对称性   

当前仅当y.equals(x)==true时，x.equals(y)==true

3. 传递性

if(x.equals(y)&&y.equals(z))，y.equals(z)==true

4. 一致性

5. 非空性
   
x.equals(null)==false

- 覆盖equals方法时总要覆盖hashCode

为了保证基于散列的集合使用该类（HashMap、HashSet、HashTable），同时，也是Object.hashCode的通用约定，覆盖equals方法时，必须覆盖hashCode。

- 始终覆盖toString

Object的toString方法的通用约定是该对象的描述。注意覆盖时，如果有格式，请备注或者严格按照格式返回。

- 谨慎覆盖clone

- 考虑实现Comparable接口

- 使类和成员的可访问性最小化

目的是解耦。简单来讲，使用修饰符的优先级从大到小，private>protected>default(缺省)>public。如果在设计之初，设计为private修饰符后，在之后的编码过程如果不得不扩大其作用于，应该先检查是否设计的确如此。

子类覆盖超类，不允许访问级别低于超类的访问级别。（超类的protected，子类覆盖后不能改为default）。

成员变量决不允许是公有的。一旦设置为公有，则放弃了对他处理的能力。这种类并不是线程安全的。即使是final的，也不允许。除非希望通过public static final来暴露常量。成员变量总是需要使用setter和getter来维护。有一个例外：长度非零的数组。这是安全漏洞的一个根源。

```java
public Object pop(){
    if(size==0) throw new EmptyStackException();
    elements[size] = null; // 等待回收
    return element[--size];
}
```

改进：

```java
private static final Thing[] PRIVATE_VALUES = {...}
// 此时获取到的才是“常量”
public static final List<Thing> VALUS = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES))
```

另一种：

```java
private static final Thing[] PRIVATE_VALUES = {...}
// 此时获取到的才是“常量”
public static final Thing[] values(){
    return PRIVATE_VALUES.clone();
}
```

- 在公有类中使用访问方法而非公有成员变量

- 使可变性最小化

- 复合优先于继承

继承有利于代码复用，但是尽可能不要进行跨包的继承。包内的继承是优秀的设计方式，一个包里的文件处在同一个程序员的控制之下。但是继承有其局限性：子类依赖于超类。超类一旦发生更改，将可能破坏子类。并且，如果超类是有缺陷的，子类也会得“遗传病”。

复合，即不扩展已有的类，而是在的类中新增一个现有类的。相当于现有类作为一个组建存在于新类中。如此，将只会用到需要用到的东西，而不表现现有类所有的方法和成员变量。新类也可以称为“包装类”，也就是设计模式中的Decorate模式。

- 要么就为继承而设计，并提供文档说明，要么就禁止继承

- 接口优于抽象类

- 接口只用于定义类型

- 类层次优先于标签类

- 用函数对象表示策略

函数参数可以传入类似listener的对象，目的是使用listener中的方法。如果使用匿名的参数，每一次调用会创建新的对象。可以将listener声明为成员变量，每次都复用同一个对象，并且可以使用静态域（static变量）。比如String类的CASE_INSENSITIVE_ORDER域。

- 优先考虑静态类成员

嵌套类的目的应该只是为了他的外围类提供服务，如果以后还可能用于其他环境中，则应该设计为顶层类。静态类相当于一个普通的外部类，只是恰好声明在了一个类内部。通常的用户是：Calculator.Operation.PLUS等。和普通类的区别只是，在PLUS前，有了2个前缀，来表明其含义。而非静态类必须存在于外部类对象中。不要手动在外部创建一个内部非静态类对象，创建的过程是：instance.New MemberClass()。这非常奇怪。

如果成员类不需要访问外围类，则需要添加static，是他成为静态成员类，否则每个实例都将包含一个额外指向外围对象的引用。将会影响垃圾回收机制。

- 应指定泛型的具体类型，而不是直接使用原生类型。

例如，应该指定List<E>，而不建议直接使用List。

- 消除非首检警告

在使用IDE进行编码时，强大的IDE都会在你编码过程中提示warning，需要尽可能的消除warning，至少，应该小心这些warning。慎用SuppresWarning，如果IDE提示你可以通过添加该注解解决掉warning，请不要那么做。如果实在要使用，请添加注释说明原因。

- 列表优先于数组

类比泛型，数组是有一定缺陷的。List<SuperClass>和List<SubClass>是没有关系的，而Sub[]是Super[]的子类。

```java
// Fails at runtime
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in"; // throw exception

// won't compile
List<Object> ol = new ArrayList<Long>(); // Incompatible types
ol.add("I don't fit in");
```

从代码中可以看到，使用泛型，会提前发现错误。

- 优先考虑泛型

- 优先考虑泛型方法

- 利用有限制通配符来提升API的灵活性

PECS，producer-extends，consumer-super。

```java
//public class Stack<E>{
    // public Stack();
    // public void push(E e);
    // public E pop();
    // public boolean isEmpty();
//}

public void pushAll(Iterator<? extends E> src){
    for(E e : src)
    push(e);
}

public void popAll(Collection<? super E> dst){
    while(!isEmpty()){
    dst.add(pop());
    }
}

// Get and Put Principle
```

所有comparable和comparator都是消费者（Consumer）。

- 优先考虑类型安全的异构容器
  
- 用enum代替int常量

```java
public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
public enum Orange { NAVEL, TEMPLE, BLOOD }
```

枚举型在java中非常强大，当需要一组固定常量时，使用enum比int好很多。比如代码可读性，安全性等。

- enum用实例域代替序数

```java
// bad solution
public enum Ensemble {
    
    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET;

    public int numberOfMusicians() { return ordinal() + 1; }
}
//

// improvement
public enum Ensemble {
    
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;
    Ensemble(int size) { this.numberOfMusicians = size; }
    public int numberOfMusicians() { return numberOfMusicians; }
}
```

永远不要像第一种的方式，利用序数访问enum，需要在构造函数中使用参数来初始化。

- 用EnumSet代替位域

```java
public class Text{
    public static final int STYLE_BOLD = 1 << 0; // 1
    public static final int STYLE_ITALIC = 1 << 1; // 2
    public static final int STYLE_UNDERLINE = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    public void applyStyles(int styles){
        // ...
    }
}

//
text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
```

以上叫做位图法，但是有更好的方案来传递多组常量——EnumSet。

```java
public class Text{
    public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

    // 注意此处，使用的是Set而不是EnumSet
    public void applyStyles(Set<Style> styles){
        // ...
    }
}

//
text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
```

- 用EnumMap代替序数索引

任何时候都不要使用enum的ordinal()方法

- 用接口模拟可伸缩的枚举

- 注解优先于命名模式

- 坚持使用Override注解

- 检查参数的有效性

公有方法检查参数，参数异常需要跑出Exception。私有方法利用断言assertion检查参数。

- 必要时进行保护性拷贝

假设类的客户端会尽其所能来破坏这个类的约束条件，因此你必须保护性的设计程序。以下是一个不可变类的设计。

```java
public Period(Date start, Date end){
    this.start = new Date(start); // 使用了值的拷贝，没有使用原对象（指针）
    this.end = new Date(end);
    if(this.start.compareTo(this.end)>0)
    throw new IllegalArgumentException(start + " after " + end)
}
```

注意：保护性拷贝是在检查参数之前进行的，防止多线程的影响。不要使用clone方法进行保护性拷贝。

以上方法防御了传入参数的修改，但是对于get方法获取到的对象，仍然可以被修改，通过以下方法可以防止这种攻击。

```java
public Date start(){
    return new Date(start);
}

public Date end(){
    return new Date(end);
}
```

- 谨慎设计方法签名
  
- 慎用重载

- 慎用可变参数
  
- 返回0长度的数组或者集合，而不是null

null一般用于表示没有被初始化或处理，如果方法返回了null，则需要在上层做更多的处理，以防止NPE。

- 为所有导出的API元素编写文档注释

正确的javadoc文档，需要每个被导出的类、接口、构造器、方法和域之前增加文档注释。注释应该是对实现透明的，只需要简洁的描述它和客户端之间的约定。并且，还应该附上该方法的副作用。

- 将局部变量的作用域最小化

- for-each优先于for循环

for-each规避掉了for循环的index变量的引用，通常来说它是不必要的——会增加引入错误的风险，并且风险一旦发生，很难被发现。不过有三种情况下，无法使用for-each（注：在jdk1.8中已经很好的解决了这些问题）。

1. 过滤
2. 转换
3. 平行迭代

- 如果需要精确的答案，请避免使用float和double

float和double是执行的二进制浮点运算，目的是在广泛数值范围上使用精确的快速近似计算而设计的。然而他们并没有提供完全精确的计算（实际应用中，经常会碰到出现x.99999等结果）。尤其是，在进行货币计算时，他们并不适用。比如：

System.out.println(1.03-.42);

得到的结果将是：0.610000000001。

为了解决这个问题，需要使用BigDecimal。然而这也有一些问题，相对于普通的运算，它显得更加麻烦，而且也更慢。通常来说后一个缺点可以忽略，但是前者可能会让人很不舒服。有一种做法是将需要处理的数值*10(或更多），使用int进行计算，不过需要你自己处理四舍五入等操作。

- 基本类型优先于装箱基本类型

基本类型只有值，装箱类具有与他们值不同的同一性。

基本类型只有功能完备的值，装箱类还具有非功能值：null

所以你可能会碰到NPE

基本类型省空间省时间

- 如果有更精确的类型，请避免使用字符串

字符串不适合代替其他值的类型。 例如：int，boolean等 、枚举类型、聚集类型

- 当心字符串连接的性能
    
操作符“+”可以将多个字符串进行连接。但是在大规模使用“+”的情况下，连接n个字符串的开销是n的平房级时间。这是由于字符串的不可变性导致的。在这种情况下请使用StringBuilder进行连接。

- 通过接口引用对象

- 接口优先于反射机制

使用反射机制会带来以下的问题：

1. 丧失了编译期类型检查
2. 代码笨拙冗长
3. 性能损失

反射基本上只适合用在编写组件时、代码分析器、RPC等场景下使用。在使用反射机制时，如果可能，尽可能只通过反射机制实例化对象，而访问方法时，使用已知的接口或者超类。

- 谨慎使用JNI

- 遵守普遍的命名规则

- 只针对异常情况才使用异常

不要尝试通过异常机制来做正常代码应该做的事情，比如，检查数组下标。

jvm很少对异常进行优化，因为它只用于不正常的情况。并且，如果你将代码放入try-catch代码块，jvm就丧失了本来可以对它进行的优化。

- 对于可恢复的情况使用受检异常，对于编程错误的情况使用运行时异常

如果期望调用者适当的恢复，则需要使用受检异常，强迫调用者食用try-catch代码块，或者将他们抛出去
  
当调用发生前提违例——违反约定的情况时，使用运行时异常，这个时候程序已经无法再执行下去了

例如调用数组的-1索引

# 优化格言

很多计算上的过失都被归咎于效率（没有必要达到的效率），而不是任何其他原因——甚至包括盲目的做傻事。                

——William A. Wulf


不要去计较效率上的一些小小的得失，在97%的情况下，不成熟的优化才是一切问题的根源。

——Donald E. Knuth

在优化方面，我们应该遵守两条规则：

规则1:不要进行优化。

规则2（仅针对专家）：还是不要进行优化——也就是说，在你还没有绝对清晰的优化方案前，请不要进行优化。

——M. A. Jackson

# 总结

优化的弊大于利。

要努力编写好的程序，而不是快的程序。低耦合的重要性远远大于性能。当程序编写得足够低耦合后，通过工具发现了性能瓶颈的代码块，才可以保证对其的修改不影响任何外部环境。