```yaml
title: JDK容器
author: samin
date: 2021-10-15
```

# 常见问题

## 概览

两大块：Collection（Iterable）、Map

## List、Set、Map 三者的区别

- `List` (对付顺序的好帮手)：存储的元素是有序的、可重复的
- `Set` (注重独一无二的性质)：存储的元素是无序的、不可重复的
- `Map` (用 key 来搜索的专家)：使用键值对（key-value）存储，类似于数学上的函数 y=f(x)，“x”代表 key，"y"代表 value，Key 是无序的、不可重复的，value 是无序的、可重复的，每个键最多映射到一个值

## 底层数据结构

### Collection

#### List

- Arraylist： Object[]数组
- Vector：Object[]数组
- LinkedList： 双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环)

#### Set

- HashSet（无序，唯一）: 基于 HashMap 实现的，底层采用 HashMap 来保存元素
- LinkedHashSet：LinkedHashSet 是 HashSet 的子类，并且其内部是通过 LinkedHashMap 来实现的。有点类似于我们之前说的 LinkedHashMap 其内部是基于 HashMap 实现一样，不过还是有一点点区别的
- TreeSet（有序，唯一）： 红黑树(自平衡的排序二叉树)

### Map

- HashMap： JDK1.8 之前 HashMap 由数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间
- LinkedHashMap： LinkedHashMap 继承自HashMap，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，LinkedHashMap 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
- Hashtable： 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的
- TreeMap： 红黑树（自平衡的排序二叉树）

> TreeMap、TreeSet 以及 JDK1.8 之后的 HashMap 底层都用到了红黑树。红黑树就是为了解决二叉查找树的缺陷，因为二叉查找树在某些情况下会退化成一个线性结构。

## 如何选用集合

- 需要根据键值获取到元素值时就选用 `Map` 接口下的集合，需要**排序**时选择 TreeMap,不需要排序时就选择 HashMap,需要保证**线程安全**就选用 ConcurrentHashMap。

- 只需要存放元素值时，就选择实现 `Collection` 接口的集合，需要保证**元素唯一**时选择实现 Set 接口的集合比如 TreeSet 或 HashSet，不需要就选择实现 List 接口的比如 ArrayList 或 LinkedList，然后再根据实现这些接口的集合的特点来选用。

## 为什么要使用集合

- 数组的缺点是一旦声明之后，长度就不可变了；
- 声明数组时的数据类型也决定了该数组存储的数据的类型；
- 数组存储的数据是有序的、可重复的，特点单一

**集合提高了数据存储的灵活性，Java 集合不仅可以用来存储不同类型不同数量的对象，还可以保存具有映射关系的数据**

## 迭代器 Iterator

### 为什么需要迭代器

Iterator 对象称为迭代器（设计模式的一种），迭代器可以对集合进行遍历，但每一个集合内部的数据结构可能是不尽相同的（Array、Set、Tree），所以每一个集合存和取都很可能是不一样的，虽然我们可以人为地在每一个类中定义 hasNext() 和 next() 方法，但这样做会让整个集合体系过于臃肿。于是就有了迭代器。

迭代器是将这样的方法抽取出接口，然后在每个类的内部，定义自己迭代方式，这样做就规定了整个集合体系的遍历方式都是 hasNext()和next()方法，使用者不用管怎么实现的，会用即可。迭代器的定义为：**提供一种方法访问一个容器对象中各个元素，而又不需要暴露该对象的内部细节。**

### 迭代器 Iterator 主要作用

Iterator 主要是用来遍历集合用的，它的特点是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，就会抛出 `ConcurrentModificationException` 异常

## 哪些集合是线程不安全的 ？如何解决 ？
我们常用的 Arraylist ,LinkedList,Hashmap,HashSet,TreeSet,TreeMap，PriorityQueue 都不是线程安全的。解决办法很简单，可以使用线程安全的集合来代替。

如果你要使用线程安全的集合的话， `java.util.concurrent` 包中提供了很多并发容器供你使用：

- **ConcurrentHashMap**: 可以看作是线程安全的 HashMap
- **CopyOnWriteArrayList**:可以看作是线程安全的 ArrayList，在读多写少的场合性能非常好，`远远好于 Vector`.
- **ConcurrentLinkedQueue**:高效的并发队列，使用链表实现。可以看做一个线程安全的 LinkedList，这是一个非阻塞队列。
- **BlockingQueue**: 这是一个接口，JDK 内部通过链表、数组等方式实现了这个接口。表示阻塞队列，非常适合用于作为数据共享的通道。
- **ConcurrentSkipListMap**:跳表的实现。这是一个Map，使用跳表的数据结构进行快速查找。

## Arraylist 和 Vector 的区别?

- ArrayList 是 List 的主要实现类，底层使用 Object[ ]存储，适用于频繁的查找工作，线程不安全 ；
- Vector 是 List 的古老实现类，底层使用 Object[ ]存储，线程安全的。

## Arraylist 与 LinkedList 区别

- 是否保证线程安全： ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
- 底层数据结构： Arraylist 底层使用的是 Object 数组；LinkedList 底层使用的是 双向链表 数据结构（JDK1.6 之前为循环链表，JDK1.7 取消了循环）
- 插入和删除是否受元素位置的影响：
    - ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。 比如：执行add(E e)方法的时候， ArrayList 会默认在将指定的元素追加到此列表的末尾，这种情况时间复杂度就是 O(1)。但是如果要在指定位置 i 插入和删除元素的话（add(int index, E element)）时间复杂度就为 O(n-i)。因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的(n-i)个元素都要执行向后位/向前移一位的操作。
    - LinkedList 采用链表存储，所以对于add(E e)方法的插入，删除元素时间复杂度不受元素位置的影响，近似 O(1)，如果是要在指定位置i插入和删除元素的话（(add(int index, E element)） 时间复杂度近似为o(n))因为需要先移动到指定位置再插入。
- 是否支持快速随机访问： LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于get(int index)方法)。
- 内存空间占用： ArrayList 的空 间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。

## 双向链表和双向循环链表

- 双向链表： 包含两个指针，一个 prev 指向前一个节点，一个 next 指向后一个节点。
- 双向循环链表： 最后一个节点的 next 指向 head，而 head 的 prev 指向最后一个节点，构成一个环。

## RandomAccess 接口

查看源码我们发现实际上 `RandomAccess` 接口中什么都没有定义。标识实现这个接口的类具有随机访问功能

## Comparable 和 Comparator 的区别
- Comparable 接口实际上是出自java.lang包 它有一个 compareTo(Object obj)方法用来排序
- Comparator接口实际上是出自 java.util 包它有一个compare(Object obj1, Object obj2)方法用来排序，搭配`Collection.sort()`使用

一般我们需要对一个集合使用自定义排序时，我们就要重写`compareTo()`方法或`compare()`方法，当我们需要对某一个集合实现两种排序方式，比如一个 song 对象中的歌名和歌手名分别采用一种排序方法的话，我们可以重写compareTo()方法和使用自制的 `Comparator` 方法或者以两个 `Comparator` 来实现歌名排序和歌星名排序，第二种代表我们只能使用两个参数版的 `Collections.sort()`

TreeMap的key如果是对象，需要继承Comparable接口，实现`compareTo`方法才能实现自动排序，否则会报`ClassCastException`错误

String、Integer等已经实现了Comparable接口

## 无序性和不可重复性的含义是什么

- 什么是无序性？无序性不等于随机性 ，无序性是指存储的数据在底层数组中并非按照数组索引的顺序添加 ，而是根据数据的哈希值决定的。
- 什么是不可重复性？不可重复性是指添加的元素按照 equals()判断时 ，返回 false，需要同时重写 equals()方法和 HashCode()方法。

## 比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同

- HashSet 是 Set 接口的主要实现类 ，HashSet 的底层是 HashMap，线程不安全的，可以存储 null 值；

- LinkedHashSet 是 HashSet 的子类，能够按照添加的顺序遍历；

- TreeSet 底层使用红黑树，能够按照添加元素的顺序进行遍历，排序的方式有自然排序和定制排序。

## 解决Hash冲突的方法
定址法，拉链法，再散列法

## HashMap和Hashtable的区别
- 线程安全：HashMap是非线程安全的，Hashtable是线程安全的，Hashtable内部的方法基本经过synchronized修饰（ConcurrentHashMap也是线程安全，比Hashtable效率高，因为Hashtable每个方法都阻塞）
- 效率：HashMap高，Hashtable基本淘汰，线程安全都会考虑ConcurrentHashMap
- 对null值支持：HashMap中，null可以作为键也可以作为值，而HashTable不允许null值
- 初始化容量大小和扩容大小不同
- 底层数据结构：JDK1.8以后，HashMap的链表超过阈值（默认为8）时，将链表转化为红黑树，减少搜索时间

## HashMap和HashSet的区别
| HashMap                   | HashSet                                                    |
| ------------------------- | ---------------------------------------------------------- |
| 实现Map接口               | 实现Set接口                                                |
| 存储键值对，key仅能为对象 | 仅存储对象                                                 |
| 调用put方法添加元素       | 调用add方法添加元素                                        |
| 使用Key计算hashcode       | 使用成员对象来计算hashcode，对相等的对象用equals方法来判断 |

## HashMap 和 TreeMap 区别

- TreeMap 和HashMap 都继承自AbstractMap ，但是需要注意的是TreeMap它还实现了NavigableMap接口和SortedMap 接口。
- 实现 NavigableMap 接口让 TreeMap 有了对集合内元素的搜索的能力。

## ConcurrentHashMap 和 Hashtable 的区别

- **底层数据结构**： JDK1.7 的 ConcurrentHashMap 底层采用 分段的**数组+链表** 实现，JDK1.8 采用的数据结构跟 HashMap1.8 的结构一样，**数组+链表/红黑二叉树**。Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构类似都是采用 数组+链表 的形式，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的；
- **实现线程安全的方式（重要）**：
    - 在 JDK1.7 的时候，**ConcurrentHashMap（分段锁） 对整个桶数组进行了分割分段(Segment)，每一把锁只锁容器其中一部分数据，多线程访问容器里不同数据段的数据，就不会存在锁竞争，提高并发访问率**。 到了 JDK1.8 的时候已经摒弃了 Segment 的概念，而是**直接用 Node 数组+链表+红黑树的数据结构来实现，并发控制使用 synchronized 和 CAS 来操作**。（JDK1.6 以后 对 synchronized 锁做了很多优化） 整个看起来就像是优化过且线程安全的 HashMap，虽然在 JDK1.8 中还能看到 Segment 的数据结构，但是已经简化了属性，只是**为了兼容旧版本**；
    - Hashtable(同一把锁) :**使用 synchronized 来保证线程安全，效率非常低下**。当一个线程访问同步方法时，其他线程也访问同步方法，可能会进入阻塞或轮询状态，如使用 put 添加元素，另一个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。

## 什么是快速失败(fail-fast)
快速失败(fail-fast) 是 Java 集合的一种    错误检测机制。在使用迭代器对集合进行遍历的时候，我们在多线程下操作非安全失败(fail-safe)的集合类可能就会触发 fail-fast 机制，导致抛出 `ConcurrentModificationException` 异常。 另外，在单线程下，如果在遍历过程中对集合对象的内容进行了修改的话也会触发 fail-fast 机制。

## 什么是安全失败(fail-safe)

采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。所以，在遍历过程中对原集合所作的修改并不能被迭代器检测到，故不会抛 `ConcurrentModificationException` 异常

# ArrayList

## 特点

- ArrayList 的底层是数组队列，相当于**动态数组**。与 Java 中的数组相比，它的容量能动态增长。在添加大量元素前，应用程序可以使用ensureCapacity操作来增加 ArrayList 实例的容量。这可以减少递增式再分配的数量。

- **继承于 AbstractList，实现了 List, RandomAccess, Cloneable, java.io.Serializable** 这些接口。

    - ArrayList 继承了AbstractList，实现了List。它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能。

    - ArrayList 实现了RandomAccess 接口， RandomAccess 是一个标志接口，表明实现这个这个接口的 List 集合是**支持快速随机访问**的。在 ArrayList 中，我们即可以通过元素的序号快速获取元素对象，这就是快速随机访问。

    - ArrayList 实现了Cloneable 接口，即覆盖了函数 clone()，**能被克隆**。

    - ArrayList 实现java.io.Serializable 接口，这意味着ArrayList**支持序列化，能通过序列化去传输**。

- 线性表的顺序存储，插入删除元素的时间复杂度为O（n）,求表长以及增加元素，取第 i 元素的时间复杂度为O（1）

- 和 Vector 不同，ArrayList 中的操作**不是线程安全的**！所以，建议在单线程中才使用 ArrayList，而在多线程中可以选择 Vector 或者 CopyOnWriteArrayList

## 核心源码

- 默认初始容量大小为 `DEFAULT_CAPACITY = 10`
- `transient Object[] elementData` 用于存放数据
- hugeCapacity方法可以得出ArrayList最大容量为Integre.MAX_VALUE
- MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8

## 内部类

ArrayList有四个内部类，其中的**Itr是实现了Iterator接口，同时重写了里面的hasNext()， next()， remove() 等方法**；其中的ListItr 继承 Itr，实现了ListIterator接口，同时重写了hasPrevious()， nextIndex()， previousIndex()， previous()， set(E e)， add(E e) 等方法，所以这也可以看出了 Iterator和ListIterator的区别: **ListIterator在Iterator的基础上增加了添加对象，修改对象，逆向遍历等方法，这些是Iterator不能实现的**。

## 关于长度

1. `length` 属性是针对数组说的,比如说你声明了一个数组,想知道这个数组的长度则用到了 length 这个属性.

2. `length()`方法是针对字 符串String说的,如果想看这个字符串的长度则用到 length()这个方法.

3. `size()`方法是针对泛型集合说的,如果想看这个泛型有多少个元素,就调用此方法来查看!


## 扩容
一 先从 ArrayList 的构造函数说起

**ArrayList有三种方式来初始化，构造方法源码如下：**

```java
   /**
     * 默认初始容量大小
     */
    private static final int DEFAULT_CAPACITY = 10;
    

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     *默认构造函数，使用初始容量10构造一个空列表(无参数构造)
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    
    /**
     * 带初始容量参数的构造函数。（用户自己指定容量）
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {//初始容量大于0
            //创建initialCapacity大小的数组
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {//初始容量等于0
            //创建空数组
            this.elementData = EMPTY_ELEMENTDATA;
        } else {//初始容量小于0，抛出异常
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }


   /**
    *构造包含指定collection元素的列表，这些元素利用该集合的迭代器按顺序返回
    *如果指定的集合为null，throws NullPointerException。 
    */
     public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

```

细心的同学一定会发现 ：**以无参数构造方法创建 ArrayList 时，实际上初始化赋值的是一个空数组。当真正对数组进行添加元素操作时，才真正分配容量。即向数组中添加第一个元素时，数组容量扩为10。** 下面在我们分析 ArrayList 扩容时会讲到这一点内容！

## 二 一步一步分析 ArrayList 扩容机制

这里以无参构造函数创建的 ArrayList 为例分析

### 1. 先来看 `add` 方法

```java
    /**
     * 将指定的元素追加到此列表的末尾。 
     */
    public boolean add(E e) {
   //添加元素之前，先调用ensureCapacityInternal方法
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //这里看到ArrayList添加元素的实质就相当于为数组赋值
        elementData[size++] = e;
        return true;
    }
```
### 2. 再来看看 `ensureCapacityInternal()` 方法

可以看到 `add` 方法 首先调用了`ensureCapacityInternal(size + 1)`

```java
   //得到最小扩容量
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
              // 获取默认的容量和传入参数的较大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }
```
**当 要 add 进第1个元素时，minCapacity为1，在Math.max()方法比较后，minCapacity 为10。**

### 3. `ensureExplicitCapacity()` 方法

如果调用 `ensureCapacityInternal()` 方法就一定会进过（执行）这个方法，下面我们来研究一下这个方法的源码！

```java
  //判断是否需要扩容
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            //调用grow方法进行扩容，调用此方法代表已经开始扩容了
            grow(minCapacity);
    }

```

我们来仔细分析一下：

- 当我们要 add 进第1个元素到 ArrayList 时，elementData.length 为0 （因为还是一个空的 list），因为执行了 `ensureCapacityInternal()` 方法 ，所以 minCapacity 此时为10。此时，`minCapacity - elementData.length > 0 `成立，所以会进入 `grow(minCapacity)` 方法。
- 当add第2个元素时，minCapacity 为2，此时e lementData.length(容量)在添加第一个元素后扩容成 10 了。此时，`minCapacity - elementData.length > 0 ` 不成立，所以不会进入 （执行）`grow(minCapacity)` 方法。
- 添加第3、4···到第10个元素时，依然不会执行grow方法，数组容量都为10。

直到添加第11个元素，minCapacity(为11)比elementData.length（为10）要大。进入grow方法进行扩容。

### 4. `grow()` 方法

```java
    /**
     * 要分配的最大数组大小
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * ArrayList扩容的核心方法。
     */
    private void grow(int minCapacity) {
        // oldCapacity为旧容量，newCapacity为新容量
        int oldCapacity = elementData.length;
        //将oldCapacity 右移一位，其效果相当于oldCapacity /2，
        //我们知道位运算的速度远远快于整除运算，整句运算式的结果就是将新容量更新为旧容量的1.5倍，
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //然后检查新容量是否大于最小需要容量，若还是小于最小需要容量，那么就把最小需要容量当作数组的新容量，
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
       // 如果新容量大于 MAX_ARRAY_SIZE,进入(执行) `hugeCapacity()` 方法来比较 minCapacity 和 MAX_ARRAY_SIZE，
       //如果minCapacity大于最大容量，则新容量则为`Integer.MAX_VALUE`，否则，新容量大小则为 MAX_ARRAY_SIZE 即为 `Integer.MAX_VALUE - 8`。
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```

**int newCapacity = oldCapacity + (oldCapacity >> 1),所以 ArrayList 每次扩容之后容量都会变为原来的 1.5 倍左右（oldCapacity为偶数就是1.5倍，否则是1.5倍左右）！**  奇偶不同，比如 ：10+10/2 = 15, 33+33/2=49。如果是奇数的话会丢掉小数.

>   ">>"（移位运算符）：>>1 右移一位相当于除2，右移n位相当于除以 2 的 n 次方。这里 oldCapacity 明显右移了1位所以相当于oldCapacity /2。对于大数据的2进制运算,位移运算符比那些普通运算符的运算要快很多,因为程序仅仅移动一下而已,不去计算,这样提高了效率,节省了资源 　

**我们再来通过例子探究一下`grow()` 方法 ：**

- 当add第1个元素时，oldCapacity 为0，经比较后第一个if判断成立，newCapacity = minCapacity(为10)。但是第二个if判断不会成立，即newCapacity 不比 MAX_ARRAY_SIZE大，则不会进入 `hugeCapacity` 方法。数组容量为10，add方法中 return true,size增为1。
- 当add第11个元素进入grow方法时，newCapacity为15，比minCapacity（为11）大，第一个if判断不成立。新容量没有大于数组最大size，不会进入hugeCapacity方法。数组容量扩为15，add方法中return true,size增为11。
- 以此类推······

**这里补充一点比较重要，但是容易被忽视掉的知识点：**

- java 中的 `length `属性是针对数组说的,比如说你声明了一个数组,想知道这个数组的长度则用到了 length 这个属性.
- java 中的 `length()` 方法是针对字符串说的,如果想看这个字符串的长度则用到 `length()` 这个方法.
- java 中的 `size()` 方法是针对泛型集合说的,如果想看这个泛型有多少个元素,就调用此方法来查看!

### 5. `hugeCapacity()` 方法。

从上面 `grow()` 方法源码我们知道： 如果新容量大于 MAX_ARRAY_SIZE,进入(执行) `hugeCapacity()` 方法来比较 minCapacity 和 MAX_ARRAY_SIZE，如果minCapacity大于最大容量，则新容量则为`Integer.MAX_VALUE`，否则，新容量大小则为 MAX_ARRAY_SIZE 即为 `Integer.MAX_VALUE - 8`。


```java
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        //对minCapacity和MAX_ARRAY_SIZE进行比较
        //若minCapacity大，将Integer.MAX_VALUE作为新数组的大小
        //若MAX_ARRAY_SIZE大，将MAX_ARRAY_SIZE作为新数组的大小
        //MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
```

## 三 `System.arraycopy()` 和 `Arrays.copyOf()`方法


阅读源码的话，我们就会发现 ArrayList 中大量调用了这两个方法。比如：我们上面讲的扩容操作以及`add(int index, E element)`、`toArray()` 等方法中都用到了该方法！


### 3.1 `System.arraycopy()` 方法

```java
    /**
     * 在此列表中的指定位置插入指定的元素。 
     *先调用 rangeCheckForAdd 对index进行界限检查；然后调用 ensureCapacityInternal 方法保证capacity足够大；
     *再将从index开始之后的所有成员后移一个位置；将element插入index位置；最后size加1。
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //arraycopy()方法实现数组自己复制自己
        //elementData:源数组;index:源数组中的起始位置;elementData：目标数组；index + 1：目标数组中的起始位置； size - index：要复制的数组元素的数量；
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }
```

我们写一个简单的方法测试以下：

```java
public class ArraycopyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[10];
		a[0] = 0;
		a[1] = 1;
		a[2] = 2;
		a[3] = 3;
		System.arraycopy(a, 2, a, 3, 3);
		a[2]=99;
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

}
```

结果：

```
0 1 99 2 3 0 0 0 0 0 
```

### 3.2 `Arrays.copyOf()`方法

```java
   /**
     以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）; 返回的数组的运行时类型是指定数组的运行时类型。 
     */
    public Object[] toArray() {
    //elementData：要复制的数组；size：要复制的长度
        return Arrays.copyOf(elementData, size);
    }
```

个人觉得使用 `Arrays.copyOf()`方法主要是为了给原有数组扩容，测试代码如下：

```java
public class ArrayscopyOfTest {

	public static void main(String[] args) {
		int[] a = new int[3];
		a[0] = 0;
		a[1] = 1;
		a[2] = 2;
		int[] b = Arrays.copyOf(a, 10);
		System.out.println("b.length"+b.length);
	}
}
```

结果：

```
10
```

### 3.3 两者联系和区别

**联系：**

看两者源代码可以发现 copyOf() 内部实际调用了 `System.arraycopy()` 方法

**区别：**

`arraycopy()` 需要目标数组，将原数组拷贝到你自己定义的数组里或者原数组，而且可以选择拷贝的起点和长度以及放入新数组中的位置 `copyOf()` 是系统自动在内部新建一个数组，并返回该数组。

## 四 `ensureCapacity`方法

ArrayList 源码中有一个 `ensureCapacity` 方法不知道大家注意到没有，这个方法 ArrayList 内部没有被调用过，所以很显然是提供给用户调用的，那么这个方法有什么作用呢？

```java
    /**
    如有必要，增加此 ArrayList 实例的容量，以确保它至少可以容纳由minimum capacity参数指定的元素数。
     *
     * @param   minCapacity   所需的最小容量
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // any size if not default element table
            ? 0
            // larger than default for default empty table. It's already
            // supposed to be at default size.
            : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

```

**最好在 add 大量元素之前用 `ensureCapacity` 方法，以减少增量重新分配的次数**

我们通过下面的代码实际测试以下这个方法的效果：

```java
public class EnsureCapacityTest {
	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<Object>();
		final int N = 10000000;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			list.add(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("使用ensureCapacity方法前："+(endTime - startTime));

	}
}
```

运行结果：

```
使用ensureCapacity方法前：2158
```

```java
public class EnsureCapacityTest {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        list = new ArrayList<Object>();
        long startTime1 = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后："+(endTime1 - startTime1));
    }
}
```

运行结果：

```
使用ensureCapacity方法前：1773
```

通过运行结果，我们可以看出向 ArrayList 添加大量元素之前最好先使用`ensureCapacity` 方法，以减少增量重新分配的次数。

# HashMap

## 简介

- HashMap 主要用来存放键值对，它基于哈希表的Map接口实现，是常用的Java集合之一
- 键不能重复，值可以重复
- JDK1.8 之前 HashMap 由 数组+链表 组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）
- JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）时，将链表转化为红黑树（**将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树**。是否转换为红黑树先要考虑是否达到扩容值，在扩容值范围内冲突达到8会进行转换），以减少搜索时间，具体可以参考 treeifyBin方法

## 底层结构分析

### JDK1.8以前

- 数组和链表 结合在一起使用也就是 `链表散列`
- 通过 `(n - 1) & hash` 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。
- 所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的 hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。
- 所谓 “拉链法” 就是：将链表和数组相结合。也就是说创建一个链表数组，数组中每一格就是一个链表。若遇到哈希冲突，则将冲突的值加到链表中即可。

### JDK1.8以后

- **loadFactor加载因子**

loadFactor加载因子是控制数组存放数据的疏密程度，loadFactor越趋近于1，那么 数组中存放的数据(entry)也就越多，也就越密，也就是会让链表的长度增加，loadFactor越小，也就是趋近于0，数组中存放的数据(entry)也就越少，也就越稀疏。

loadFactor太大导致查找元素效率低，太小导致数组的利用率低，存放的数据会很分散。loadFactor的默认值为0.75f是官方给出的一个比较好的临界值，提高了空间利用率减少查询成本。

**给定的默认容量为 16，负载因子为 0.75。Map 在使用过程中不断的往里面存放数据，当数量达到了 16 * 0.75 = 12 就需要将当前 16 的容量进行扩容**，而扩容这个过程涉及到 rehash、复制数据等操作，所以非常消耗性能。

- threshold

threshold = capacity * loadFactor，当Size>=threshold的时候，那么就要考虑对数组的扩增了，也就是说，这个的意思就是衡量数组是否需要扩增的一个标准。

## 构造方法

```java
// 默认构造函数。
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all   other fields defaulted
 }

 // 包含另一个“Map”的构造函数
 public HashMap(Map<? extends K, ? extends V> m) {
     this.loadFactor = DEFAULT_LOAD_FACTOR;
     putMapEntries(m, false);//下面会分析到这个方法
 }

 // 指定“容量大小”的构造函数
 public HashMap(int initialCapacity) {
     this(initialCapacity, DEFAULT_LOAD_FACTOR);
 }

 // 指定“容量大小”和“加载因子”的构造函数
 public HashMap(int initialCapacity, float loadFactor) {
     if (initialCapacity < 0)
         throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
     if (initialCapacity > MAXIMUM_CAPACITY)
         initialCapacity = MAXIMUM_CAPACITY;
     if (loadFactor <= 0 || Float.isNaN(loadFactor))
         throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
     this.loadFactor = loadFactor;
     this.threshold = tableSizeFor(initialCapacity);
 }
```

## put方法

- HashMap只提供了put用于添加元素，putVal方法只是给put方法调用的一个方法，并没有提供给用户使用。
- 元素添加
    - 如果定位到的数组位置没有元素 就直接插入。
    - 如果定位到的数组位置有元素就和要插入的key比较，如果key相同就直接覆盖，如果key不相同，就判断p是否是一个树节点，如果是就调用e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value)将元素添加进入。如果不是就遍历链表插入(插入的是链表尾部)。

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // table未初始化或者长度为0，进行扩容
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // (n - 1) & hash 确定元素存放在哪个桶中，桶为空，新生成结点放入桶中(此时，这个结点是放在数组中)
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    // 桶中已经存在元素
    else {
        Node<K,V> e; K k;
        // 比较桶中第一个元素(数组中的结点)的hash值相等，key相等
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
                // 将第一个元素赋值给e，用e来记录
                e = p;
        // hash值不相等，即key不相等；为红黑树结点
        else if (p instanceof TreeNode)
            // 放入树中
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // 为链表结点
        else {
            // 在链表最末插入结点
            for (int binCount = 0; ; ++binCount) {
                // 到达链表的尾部
                if ((e = p.next) == null) {
                    // 在尾部插入新结点
                    p.next = newNode(hash, key, value, null);
                    // 结点数量达到阈值，转化为红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    // 跳出循环
                    break;
                }
                // 判断链表中结点的key值与插入的元素的key值是否相等
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    // 相等，跳出循环
                    break;
                // 用于遍历桶中的链表，与前面的e = p.next组合，可以遍历链表
                p = e;
            }
        }
        // 表示在桶中找到key值、hash值与插入元素相等的结点
        if (e != null) { 
            // 记录e的value
            V oldValue = e.value;
            // onlyIfAbsent为false或者旧值为null
            if (!onlyIfAbsent || oldValue == null)
                //用新值替换旧值
                e.value = value;
            // 访问后回调
            afterNodeAccess(e);
            // 返回旧值
            return oldValue;
        }
    }
    // 结构性修改
    ++modCount;
    // 实际大小大于阈值则扩容
    if (++size > threshold)
        resize();
    // 插入后回调
    afterNodeInsertion(evict);
    return null;
} 
```

## get方法

```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        // 数组元素相等
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        // 桶中不止一个节点
        if ((e = first.next) != null) {
            // 在树中get
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            // 在链表中get
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

## resize方法

```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        // 超过最大值就不再扩充了，就只好随你碰撞去吧
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 没超过最大值，就扩充为原来的2倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else { 
        // signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    // 计算新的resize上限
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        // 把每个bucket都移动到新的buckets中
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { 
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        // 原索引
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        // 原索引+oldCap
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    // 原索引放到bucket里
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    // 原索引+oldCap放到bucket里
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

> - 注意 HashMap 只有扩容算法，不存在缩容，所以建议在声明的时候给出默认值
> - 在resize的时候，先对红黑树的所有元素重新进行hash运算，如最后元素数量小于6，则会退化为链表

## 需要遍历key和value的用例

```java
Set<java.util.Map.Entry<String, String>> entrys = map.entrySet();
for (java.util.Map.Entry<String, String> entry : entrys) {
    System.out.println(entry.getKey() + "--" + entry.getValue());
}
```

## HashMap 的长度为什么是 2 的幂次方

- 为了能让 HashMap 存取高效，尽量较少碰撞，也就是要尽量把数据分配均匀
- 采用二进制位操作 &，相对于%能够提高运算效率（`hash%length==hash&(length-1)`的前提是 length 是 2 的 n 次方），这就解释了 HashMap 的长度为什么是 2 的幂次方 ，本质是优化算法的前提是，长度必须是 2 的 n次方

## HashMap 多线程操作导致死循环问题

主要原因在于并发下的 Rehash 会造成元素之间会形成一个循环链表。不过，jdk 1.8 后解决了这个问题，但是还是不建议在多线程下使用 HashMap,因为多线程下使用 HashMap 还是会存在其他问题比如数据丢失。并发环境下推荐使用 `ConcurrentHashMap` 。

# LinkedList

## 结构

```java
private static class Node<E> {
    E item;//节点值
    Node<E> next;//后继节点
    Node<E> prev;//前驱节点

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

这个类就代表双端链表的节点Node。这个类有三个属性，分别是前驱节点，本节点的值，后继结点

## 构造方法

```java
// 空的构造方法
public LinkedList() {
}

// 已有链表的基础上增加链表
public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}
```

## 新增方法

- add(E e) 方法：将元素添加到链表尾部

- add(int index,E e)：在指定位置添加元素

- addAll(Collection c )：将集合插入到链表尾部

- addAll(int index, Collection c)： 将集合从指定位置开始插入

    - 检查index范围是否在size之内
    - toArray()方法把集合的数据存到对象数组中
    - 得到插入位置的前驱和后继节点
    - 遍历数据，将数据插入到指定位置
    - addFirst(E e)： 将元素添加到链表头部
    - addLast(E e)： 将元素添加到链表尾部，与 add(E e) 方法一样

## 根据位置取数据的方法

- get(int index)： 根据指定索引返回数据
- getLast() 方法在链表为空时，会抛出NoSuchElementException，而peekLast() 则不会，只是会返回 null
- getFirst(),element(),peek(),peekFirst() 这四个获取头结点方法的区别在于对链表为空时的处理，是抛出异常还是返回null，其中**getFirst() 和element() 方法将会在链表为空时，抛出异常**
- element()方法的内部就是使用getFirst()实现的。它们会在链表为空时，抛出NoSuchElementException
- getLast() 方法在链表为空时，会抛出NoSuchElementException，而peekLast() 则不会，只是会返回 null

## 根据对象得到索引的方法

- int indexOf(Object o)： 从头遍历找
- int lastIndexOf(Object o)： 从尾遍历找


## 检查链表是否包含某对象的方法

- contains(Object o)： 检查对象o是否存在于链表中

## 删除方法

- remove() , removeFirst() , pop(): 删除头节点 remove()和pop()都是调用了removeFirst()方法
- removeLast() , pollLast(): 删除尾节点 removeLast()在链表为空时将抛出NoSuchElementException，而pollLast()方法返回null
- remove(Object o): 删除指定元素
- remove(int index)：删除指定位置的元素