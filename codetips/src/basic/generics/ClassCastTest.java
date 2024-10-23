package basic.generics;

/**
 * 演示主方法
 *
 * @author samin
 * @date 2021-01-10
 */
public class ClassCastTest {

    public static void main(String[] args) {
        // 分别为泛型、Object、限定类型的工厂模式获取 Bean
        Foo tmp = new Foo();

        BeanFactory beanFactory = new BeanFactory();
        Foo foo = beanFactory.getBean(tmp);

        BeanFactory2 beanFactory2 = new BeanFactory2();
        Foo foo2 = (Foo) beanFactory2.getBean(tmp);

        BeanFactory3<Foo> beanFactory3 = new BeanFactory3<>();
        Foo foo3 = beanFactory3.getBean(tmp);
        // 以下语句报错，因为 Foo2 没有继承 Foo
        // BeanFactory3<Foo2> beanFactory4 = new BeanFactory3<>();

        System.out.println("tmp : " + System.identityHashCode(tmp));
        System.out.println("foo : " + System.identityHashCode(foo));
        System.out.println("foo2 : " + System.identityHashCode(foo2));
        System.out.println("foo3 : " + System.identityHashCode(foo3));
    }
}
