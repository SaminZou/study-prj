package com.samin.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private Class configClass;
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 1. 扫描，得到 class 列表
        List<Class> classList = scan(configClass);

        // 2. 过滤 BeanDefinition（根据 @Lazy、@Service 生成 Bean 元数据）

        // 解析类 -> BeanDefinition -> beanDefinitionMap
        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(Component.class)) {
                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                    try {
                        BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor()
                                .newInstance();
                        beanPostProcessorList.add(instance);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }

                // 构建 BeanDefinition
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClass(clazz);

                Component component = (Component) clazz.getAnnotation(Component.class);
                String beanName = component.value();

                if (clazz.isAnnotationPresent(Scope.class)) {
                    Scope scopeAnnotation = (Scope) clazz.getAnnotation(Scope.class);
                    beanDefinition.setScope(scopeAnnotation.value());
                } else {
                    // 单例
                    beanDefinition.setScope("singleton");
                }

                // beanDefinition.setLazy();

                beanDefinitionMap.put(beanName, beanDefinition);
            }
        }

        // 3. 基于 class 创建单例实例
        instanceSingletonBean();
    }

    private List<Class> scan(Class configClass) {
        List<Class> classList = new ArrayList<>();
        ComponentScan componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = componentScan.value();

        // System.out.println(path);

        // 替换为可用路径
        path = path.replace(".", "/");

        // 获取到 -classpath 入参路径的加载器
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();

        URL url = classLoader.getResource(path);
        // com.samin.study.service 文件夹
        File file = new File(url.getFile());

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                String absolutePath = f.getAbsolutePath();
                absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                absolutePath = absolutePath.replace("\\", ".");

                // com.service.study.service.BizService
                try {
                    Class<?> clz = classLoader.loadClass(absolutePath);
                    classList.add(clz);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return classList;
    }

    private void instanceSingletonBean() {
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            if (beanDefinition.getScope()
                    .equals("singleton")) {
                // 创建 bean
                if (!singletonObjects.containsKey(beanName)) {
                    Object bean = doCreateBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, bean);
                }
            }
        }
    }

    public Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        try {
            // 1. 实例化
            Class beanClass = beanDefinition.getBeanClass();
            Object bean = beanClass.getDeclaredConstructor()
                    .newInstance();

            // 2. 属性填充（依赖注入）
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    // 属性赋值
                    Object o = getBean(field.getName());

                    field.setAccessible(true);
                    field.set(bean, o);
                }
            }

            // 3. Aware
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }

            // 初始化之前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            }

            // 4. 初始化
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }

            // 初始化之后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            }

            return bean;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        // 判断单例、原型
        if (beanDefinition.getScope()
                .equals("prototype")) {
            // 创建 bean
            return doCreateBean(beanName, beanDefinition);
        } else if (beanDefinition.getScope()
                .equals("singleton")) {
            // 单例池里面去拿
            Object bean = singletonObjects.get(beanName);
            if (bean == null) {
                Object newBean = doCreateBean(beanName, beanDefinition);
                singletonObjects.put(beanName, newBean);
                return newBean;
            }
            return bean;
        } else {
        }

        // 判断是否懒加载

        return null;
    }
}