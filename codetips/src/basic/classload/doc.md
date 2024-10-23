# Class.forName VS ClassLoader

Class.forName 方法由 java.lang.Class 类调用，负责根据类名加载类，并执行静态初始化。
是一个静态方法，通过提供类的完全限定名，在运行时加载类。此方法还会执行类的**静态初始化块**。如果类名不存在或无法访问，将抛出
ClassNotFoundException 异常。

ClassLoader 是抽象类，提供了更灵活的类加载机制，可以自定义类加载过程，从不同来源加载类文件。
每个 Java 类都有关联的 ClassLoader 对象，负责将类文件加载到 Java 虚拟机中。ClassLoader 可以动态加载类，从不同来源加载类文件，如本地文件系统、网络等。

> 一般情况下，推荐使用 ClassLoader 来加载和使用类，因为它更灵活，并避免执行静态初始化的副作用。适用于需要从非标准位置加载类、实现热部署或模块化加载等复杂场景。
> 通过自定义 ClassLoader，可以精细控制类的加载过程。
> Class.forName 主要用于特定场景，如加载数据库驱动程序。