# Spring实例化Bean的三种方式
Spring实例化Bean的方式大致上可以分为三种，构造函数实例化，工厂方法实例化，静态工厂方法实例化。

# Spring内部Bean和父子Bean
测试结果：
内部顺利被实例化，其属性值也注入进innerbean中。
父子bean中的子bean，也通过继承parent bean，得到了name属性。

# Spring注入集合属性
Spring的依赖注入方式大体上可以分为三种：
1、构造函数注入
2、Setter方法注入
3、方法注入（lookup-method注入和replace-method注入）——
Spring的方法注入可分为两种
查找方法注入：用于注入方法返回结果，也就是说能通过配置方式替换方法返回结果。即我们通常所说的lookup-method注入。
替换方法注入：可以实现方法主体或返回结果的替换，即我们通常所说的replaced-method注入。

# BeanFactory和FactoryBean的区别
BeanFactory和FactoryBean是两个容易混淆的概念，很多人喜欢问两者之间的区别，其实两者之间并无内在联系。

BeanFactory接口：IoC容器的顶级接口，是IoC容器的最基础实现，也是访问Spring容器的根接口，负责对bean的创建，访问等工作。
FactoryBean接口：可以返回bean的实例的工厂bean，通过实现该接口可以对bean进行一些额外的操作，例如根据不同的配置类型返回不同类型的bean，
简化xml配置等。
在使用上也有些特殊，BeanFactory接口中有一个字符常量String FACTORY_BEAN_PREFIX = "&"; 
当我们去获取FactoryBean类型的bean时，如果beanName不加&则获取到对应bean的实例；如果beanName加上&，
则获取到BeanFactory本身的实例；FactoryBean接口对应Spring框架来说占有重要的地位，Spring本身就提供了70多个FactoryBean的实现。
他们隐藏了实例化一些复杂的细节，给上层应用带来了便利。从Spring3.0开始，FactoryBean开始支持泛型。

# Bean的作用域和生命周期
##1.Bean的作用域
singleton：单例Bean只在容器中存在一个实例，在Spring内部通过HashMap来维护单例bean的缓存
prototype：每次索取bean时都会创建一个全新的Bean
request：每次请求都会创建一个全新Bean，该类型作用于Web类型的Spring容器
session：每个会话创建一个全新Bean，该类型作用于Web类型的Spring容器
globalSession：类似于session作用域，只是其用于portlet环境的web应用。如果在非portlet环境将视为session作用域
总结：以上就是spring中bean的作用域，其中singleton，prototype属于Spring bean的基本作作用域，request，session，globalSession属于web应用环境的作用域，必须有web应用环境的支持
##2.Bean的生命周期
![Image text](https://raw.githubusercontent.com/lizhen19911120/img-storage/master/bean%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.png)
1、IoC容器启动
2、实例化bean
3、如果Bean实现了BeanNameAware接口，则调用setBeanName(String name)返回beanName，该方法不是设置beanName，而只是让Bean获取自己在BeanFactory配置中的名字
4、如果Bean实现BeanFactoryAware接口，会回调该接口的setBeanFactory(BeanFactory beanFactory)方法，传入该Bean的BeanFactory，这样该Bean就获得了自己所在的BeanFactory
5、如果Bean实现了ApplicationContextAware接口，则调用该接口的setApplicationContext(ApplicationContext applicationContext)方法，设置applicationContext
6、如果有Bean实现了BeanPostProcessor接口，则调用该接口的postProcessBeforeInitialzation(Object bean，String beanName)方法，将此BeanPostProcessor应用于给定的新bean实例
7、如果Bean实现了InitializingBean接口，则会回调该接口的afterPropertiesSet()方法
8、如果Bean配置了init-method方法，则会执行init-method配置的方法
9、如果Bean实现了BeanPostProcessor接口，则会回调该接口的postProcessAfterInitialization(Object bean，String beanName)方法
10、到此为止，spring中的bean已经可以使用了，这里又涉及到了bean的作用域问题，对于singleton类型的bean，Spring会将其缓存;对于prototype类型的bean，不缓存，每次都创建新的bean的实例
11、容器关闭，如果Bean实现了DisposableBean接口，则会回调该接口的destroy()方法销毁bean，
12、如果用户配置了定destroy-method，则调用自定义方法销毁bean

# BeanPostProcessor和BeanFactoryPostProcessor的区别
BeanPostProcessor接口：后置bean处理器，允许自定义修改新的bean实例，
应用程序上下文可以在其bean定义中自动检测BeanPostProcessor类型的bean，并将它们应用于随后创建的任何bean。
（例如：配置文件中注册了一个自定义BeanPostProcessor类型的bean，一个User类型的bean，
应用程序上下文会在创建User实例之后对User应用BeanPostProcessor）。

BeanFactoryPostProcessor接口：后置工厂处理器，允许自定义修改应用程序上下文的bean定义，
调整bean属性值。应用程序上下文可以在其bean定义中自动检测BeanFactoryPostProcessor，
并在创建任何非BeanFactoryPostProcessor类型bean之前应用它们（例如：配置文件中注册了一个自定义BeanFactoryPostProcessor类型的bean，
一个User类型的bean，应用程序上下文会在创建User实例之前对User应用BeanFactoryPostProcessor）。
![Image text](https://raw.githubusercontent.com/lizhen19911120/img-storage/master/BeanPostProcessor%E5%92%8CBeanFactoryPostProcessor%E7%9A%84%E5%8C%BA%E5%88%AB.png)

# IoC容器简介
IoC又叫依赖注入（DI）。它描述了对象的定义和依赖的一个过程，也就是说，依赖的对象通过构造参数、工厂方法参数或者属性注入，当对象实例化后依赖的对象才被创建，当创建bean后容器注入这些依赖对象。
这个过程基本上是反向的，因此命名为控制反转（IoC），它通过直接使用构造类来控制实例化，或者定义它们之间的依赖关系，或者类似于服务定位模式的一种机制。

org.springframework.beans 和 org.springframework.context 是Spring框架中IoC容器的基础，BeanFactory 接口提供一种高级的配置机制能够管理任何类型的对象。 
ApplicationContext 是 BeanFactory 的子接口。它能更容易集成Spring的AOP功能、消息资源处理（比如在国际化中使用）、事件发布和特定的上下文应用层比如在网站应用中的 WebApplicationContext。

总之，BeanFactory 提供了配置框架和基本方法，ApplicationContext添加更多的企业特定的功能。ApplicationContext是BeanFactory 的一个子接口，通过解析BeanFactory的源码是了解Spring的IoC容器的一个最佳选择。

在Spring中，由Spring IoC容器管理的对象叫做beans。bean就是由Spring IoC容器实例化、组装和以其他方式管理的对象。此外bean只是你应用中许多对象中的一个。Beans以及他们之间的依赖关系是通过容器配置元数据反映出来。

## ApplicationContext
很多人以完全声明的方式使用ApplicationContext，甚至没有以编程的方式去创建它，而是**依赖诸如ContextLoader等支持类来自动的实例化ApplicationContext**，作为Java EE web应用程序正常启动的一部分。

为了增强BeanFactory在面向框架风格的功能，上下文的包还提供了以下的功能：
<br>通过MessageSource接口访问i18n风格的消息
<br>通过ResourceLoader接口访问类似URL和文件资源
<br>通过ApplicationEventPublisher接口，即bean实现ApplicationListener接口来进行事件发布
<br>通过HierarchicalBeanFactory接口实现加载多个(分层)上下文，允许每个上下文只关注特定的层，例如应用中的web层

<br>下表列了BeanFactory 和 ApplicationContext接口和实现的一些特性：
![Image text](https://raw.githubusercontent.com/lizhen19911120/img-storage/master/BeanFactory%E5%92%8CApplicationContext%E5%8C%BA%E5%88%AB.png)
<br>ApplicationContext的各种实现都优于BeanFactory实现的原因之一，特别是当使用BeanFactoryPostProcessors和BeanPostProcessors的时候。这些机制
    实现了一些很重要的功能，例如：**属性的占位替换和AOP**。
    
# IoC容器启动过程简析（以XmlBeanFactory为例）
![Image text](https://raw.githubusercontent.com/lizhen19911120/img-storage/master/XmlBeanFactory%E5%AE%B9%E5%99%A8%E5%90%AF%E5%8A%A8%E8%BF%87%E7%A8%8B.png)
![Image text](https://raw.githubusercontent.com/lizhen19911120/img-storage/master/XmlBeanFactory%E5%AE%B9%E5%99%A8%E5%90%AF%E5%8A%A8%E8%BF%87%E7%A8%8B-%E4%BB%A3%E7%A0%81.png)

# 从Spring容器中获取bean实例
<br>1、解析beanName为canonicalName，即将别名转为正式名称
<br>2、如果缓存中有bean则从缓存中获取
<br>3、没有的话寻找父beanFactory中是否能找到这个bean
<br>4、还是没有的话，合并互相依赖的bean定义，递归获取/创建依赖的bean
<br>5、最后根据bean是否为sigleton、prototype、other scope来创建bean
<br>6、再返回bean之前，都执行getObjectForBeanInstance() 确定返回bean实例还是bean对应的FactoryBean实例

<br>针对第5步，
<br> 5.1 实例化bean
<br> 5.2 提前缓存ObjectFactory以解决bean之间的循环依赖（只能解决singleton）
<br> 5.3 初始化bean实例 这里大家要与第5.1步区分开,到这里bean已经完成了实例化,但是还没有完成初始化的操作,例如bean的属性填充
<br> 5.4 调用各种AwareMethods、调用BeanPostProcessor的方法、调用bean的init_method初始化方法
<br> 5.5 根据bean的作用域注册bean

<br>针对第5.1步，
<br> 5.1.1 如果有实例提供者，通过实例提供者实例化（Spring5新增的实例化策略）
<br> 5.1.2 如果有工厂，通过工厂实例化（实例工厂和静态工厂） 
<br> 5.1.3 构造函数实例化（无参构造和有参构造）

<br>针对第5.1.2步，
<br> 5.1.2.1 根据factoryBeanName返回的工厂bean实例是否为null，确认使用实例工厂还是静态工厂
<br> 5.1.2.2 如果显示指定bean构造参数，优先使用指定的构造参数
<br> 5.1.2.3 通过构造参数和factoryBean的方法进行匹配，确认使用合适的工厂方法进行实例化bean
<br> 5.1.2.4 调用Method.invoke()方法完成bean的实例化

<br>针对第5.1.3步，
<br> 不论是有参构造还是无参构造，都会执行以下二步机制
<br> 5.1.3.1 如果没有使用方法覆盖(replace-method或lookup-method注入),则直接使用反射创建bean的实例
<br> 5.1.3.2 否则必须使用CGLIB机制,生成增强子类，回调MethodReplacer的reimplement()方法;将对应的bean作为lookup-method的返回值
<br> 针对有参构造函数
<br> 5.1.3.3 如果显示指定bean构造参数，优先使用指定的构造参数
<br> 5.1.3.4 执行一系列步骤，使用匹配的构造函数实例化bean

<br> 针对5.3步，
<br> 5.3.1 在设置属性之前,给InstantiationAwareBeanPostProcessor一个修改bean状态的机会，InstantiationAwareBeanPostProcessor继承BeanPostProcessor
<br> 5.3.2 InstantiationAwareBeanPostProcessor可以控制是否继续对bean属性的填充,如果需要停止则直接返回，即不进行后续的默认的spring装配工作
<br> 5.3.3 获取beanDefinition的bean的属性值
<br> 5.3.4 默认spring不会自动装配依赖bean，如果开启autowire，spring根据设置选择合适的装配类型（按名字/按类型），将其他依赖的bean放进待装配的属性池(PropertyValues pvs)
<br> 5.3.5 调用applyPropertyValues()，将属性池中的属性装配给bean

<br> 针对5.3.5步，
<br> 5.3.5.1 获取类型转换器，深度拷贝属性池中的属性值
<br> 5.3.5.2 其中resolveValueIfNecessary()会根据属性值转换后的类型进行解析，做类型转换工作
<br> 5.3.5.3 使用类型转换器再进行可能的类型转换，将深度拷贝的转换后的属性池赋给bean进行属性填充
<br> 5.3.5.4 spring会筛选set×××()的方法，填充属性最终是应用Method.invoke()实现
