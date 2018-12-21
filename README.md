# Spring实例化Bean的三种方式
Spring实例化Bean的方式大致上可以分为三种，构造函数实例化，工厂方法实例化，静态工厂方法实例化、实例提供者。

# Spring内部Bean和父子Bean
测试结果：
内部顺利被实例化，其属性值也注入进innerbean中。
父子bean中的子bean，也通过继承parent bean，得到了name属性。

# Spring注入集合属性
Spring的依赖注入方式大体上可以分为三种：
1、构造函数注入（实例化bean时，默认行为）
2、Setter方法注入（初始化bean时，默认行为）
3、自动装配依赖bean（初始化bean时，默认关闭）
~~4、方法注入（lookup-method注入和replace-method注入）~~
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
<br>ObjectFactory/ObjectProvider提供类似于FactoryBean的功能。但是目的上有一定的差别。

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
BeanPostProcessor接口：后置bean处理器，ApplicationContext容器额外功能，允许自定义修改新的bean实例，
应用程序上下文可以在其bean定义中自动检测BeanPostProcessor类型的bean，并将它们应用于随后创建的任何bean。
（例如：配置文件中注册了一个自定义BeanPostProcessor类型的bean，一个User类型的bean，
应用程序上下文会在创建User实例之后对User应用BeanPostProcessor）。

BeanFactoryPostProcessor接口：后置工厂处理器，ApplicationContext容器额外功能，允许自定义修改应用程序上下文的bean定义，
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

<br>下表列了BeanFactory 和 ApplicationContext接口和实现的一些特性：
![Image text](https://raw.githubusercontent.com/lizhen19911120/img-storage/master/BeanFactory%E5%92%8CApplicationContext%E5%8C%BA%E5%88%AB.png)
<br>ApplicationContext的各种实现都优于BeanFactory实现的原因之一，特别是当使用BeanFactoryPostProcessors和BeanPostProcessors的时候(参考PostProcessorRegistrationDelegate.registerBeanPostProcessors())。这些机制
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
<br> 5.3.4 默认spring不会自动装配依赖bean（参看AbstractBeanDefinition的autowireMode = AUTOWIRE_NO），如果开启autowire（xml配置文件<bean>的autowire属性设置为非NO，@Autowired或者@Resource注解配合AutowiredAnnotationBeanPostProcessor，本质上是通过BeanPostProcessor实现），spring根据设置选择合适的装配类型（按名字/按类型），将其他依赖的bean放进待装配的属性池(PropertyValues pvs)
<br> 5.3.5 调用applyPropertyValues()，将属性池中的属性装配给bean

<br> 针对5.3.5步，
<br> 5.3.5.1 获取类型转换器，深度拷贝属性池中的属性值
<br> 5.3.5.2 其中resolveValueIfNecessary()会根据属性值转换后的类型进行解析，做类型转换工作
<br> 5.3.5.3 使用类型转换器再进行可能的类型转换，将深度拷贝的转换后的属性池赋给bean进行属性填充
<br> 5.3.5.4 spring会筛选set×××()的方法，填充属性最终是应用Method.invoke()实现

## ApplicationContextSprng ApplicationContext容器refresh过程（扩展原始beanFactory容器功能）简析
很多人以完全声明的方式使用ApplicationContext，甚至没有以编程的方式去创建它，而是**依赖诸如ContextLoader等支持类来自动的实例化ApplicationContext**，作为Java EE web应用程序正常启动的一部分。
为了增强BeanFactory在面向框架风格的功能，上下文的包还提供了以下的功能：


以下分析以ClassPathXmlApplicationContext为例——
<br>首先，相较于XmlBeanFactory(beanFactory实现)，它能够同时加载多个配置文件，后加载的覆盖前面的bean定义
<br>其次，ApplicationContext容器通过refresh()方法增加许多额外的功能，主要有：
<br>其中的prepareBeanFactory()步骤，实现自定义bean的class文件的classLoader设置、beanFactory的语言处理器设置(比如#{bean.xxx})、额外的ignoreDependencyInterface()和registerResolvableDependency()、增加对AspectJ的支持等
<br>调用BeanFactoryPostProcessor接口的方法
<br>通过MessageSource接口访问i18n风格的消息
<br>通过ResourceLoader接口访问类似URL和文件资源
<br>初始化初始事件广播器，然后在广播器中添加注册事件监听器，实现通过ApplicationEventPublisher接口，即bean实现ApplicationListener接口来进行事件发布
<br>通过HierarchicalBeanFactory接口实现加载多个(分层)上下文，允许每个上下文只关注特定的层，例如应用中的web层

# spring中的pointcut、advisor概念
在应用Advice增强前，需要对生效的类、方法调用做出筛选，pointcut就是做这一工作的类，其中有ClassFilter和MethodMatcher，分别对类和方法做出具体的逻辑工作。
<br>Spring支持两种方法匹配器(MethodMatcher)：
<br>1、静态方法匹模式：所谓静态方法匹配器，仅对方法名签名（包括方法名和入参类型及顺序）进行匹配。
<br>2、动态方法匹配器：动态方法匹配器会在运行期方法检查入参的值。 静态匹配仅会判断一次（编译时就完成匹配？），而动态匹配因为每次调用方法的入参可能不一样，所以每次调用方法都必须判断。

Spring提供的切点类型（pointcut）
<br>1、静态方法切点-->org.springframework.aop.support.StaticMethodMatcherPointcut
静态方法切点的抽象基类，默认情况下匹配所有的类。最常用的两个子类NameMatchMethodPointcut和 AbstractRegexpMethodPointcut ， 前者提供简单字符串匹配方法签名，后者使用正则表达式匹配方法签名。
<br>2、动态方法切点-->org.springframework.aop.support.DynamicMethodMatcherPointcut
动态方法切点的抽象基类，默认情况下匹配所有的类
<br>3、注解切点-->org.springframework.aop.support.annotation.AnnotationMatchingPointcut
<br>4、表达式切点-->org.springframework.aop.support.ExpressionPointcut
提供了对AspectJ切点表达式语法的支持
<br>5、流程切点-->org.springframework.aop.support.ControlFlowPointcut
该切点是一个比较特殊的节点，它根据程序执行的堆栈信息查看目标方法是否由某一个方法直接或间接发起调用，一次来判断是否为匹配的链接点
<br>6、复合切点-->org.springframework.aop.support.ComposablePointcut
该类是为实现创建多个切点而提供的操作类
  
当advice和pointcut在一起时，就成了advisor（切面）
切面可以分为3类：一般切面、切点切面、引介切面。Spring提供了这三类切面实现（advisor）

一般切面Advisor
<br>org.springframework.aop.Advisor代表一般切面，仅包含一个Advice ,因为Advice包含了横切代码和连接点信息，所以Advice本身一个简单的切面，只不过它代表的横切的连接点是所有目标类的所有方法，因为这个横切面太宽泛，所以一般不会直接使用。

切点切面PointcutAdvisor
<br>org.springframework.aop.PointcutAdvisor ,代表具有切点的切面，包括Advice和Pointcut两个类，这样就可以通过类、方法名以及方位等信息灵活的定义切面的连接点，提供更具实用性的切面。PointcutAdvisor主要有5个具体的实现类：
DefaultPointcutAdvisor：最常用的切面类型，它可以通过任意Pointcut和Advice定义一个切面，唯一不支持的就是引介的切面类型，一般可以通过扩展该类实现自定义的切面
NameMatchMethodPointcutAdvisor：通过该类可以定义按方法名定义切点的切面
AspectJExpressionPointcutAdvisor：用于AspectJ切点表达式定义切点的切面
StaticMethodMatcherPointcutAdvisor：静态方法匹配器切点定义的切面，默认情况下匹配所有的的目标类
AspectJPointcutAdvisor：用于AspectJ语法定义切点的切面

<br>引介切面IntroductionAdvisor
org.springframework.aop.IntroductionAdvisor代表引介切面， 引介切面是对应引介增强的特殊的切面，它应用于类层上面，所以引介切点使用ClassFilter进行定义。

# spring AOP的实现过程
xml中的<aop:aspectj-autoproxy>元素即开启了AOP对spring中的bean的动态代理。具体的
实现方式大体是顺着以上对于spring创建bean的过程再加上额外的工作完成的。由于源码较为繁琐且
大致实现差不多，所以这里简要说明步骤：
<br> 1、<aop:aspectj-autoproxy/>不是spring默认的xml命名空间，所以会通过xml中的namespaceUri获取对应的NamespaceHandler解析器，完成对标签的解析工作之外，并注册了AnnotationAwareAspectJAutoProxyCreator到spring容器中
<br> 2、AnnotationAwareAspectJAutoProxyCreator实现了BeanPostProcessor接口，读过之前的章节或者对bean的生命周期有所了解的同学一定知道，在bean实例化完成之前和完成之后分别会自动调用BeanPostProcessor接口的postProcessBeforeInitialization和postProcessAfterInitialization方法
<br> postProcessBeforeInstantiation()主要做了以下工作：1、判断beanClass是否为AOP基础类例如Advice，Pointcut或者是一个Advisor或者beanClass是否需要被自动代理，不需要的话则直接缓存；2、判断有无自定义TargetSource（bean的target source），如果是的话，则在此方法里创建代理，而不是在postProcessAfterInitialization()中创建        
<br> postProcessAfterInitialization()主要做了以下工作：如果bean被子类标识为要代理的bean，则使用配置的拦截器创建代理（如果是提前缓存以解决循环依赖的bean则先跳过）。                           

<br> 针对2的postProcessAfterInitialization()
<br> 2.1 wrapIfNecessary()执行真正的创建代理工作
<br> 2.1.1 做以下工作：如果已经处理过或者不需要创建代理，则返回；需要创建代理的bean则创建代理；缓存创建的代理bean
<br> 2.1.1.1 针对创建代理的步骤，做以下工作：
<br> 获取所有的候选的aspect切面，具体逻辑是遍历spring容器中的bean，然后先判断是否是有<<aop:include>>指定了哪些aspect切面是使用的则只创建它们，如果没有指定
则会根据类型判断是否是aspect切面类进行创建与否。在创建切面实例时，还会检查@Aspect注解的值，如果有指明（PERTHIS、PERTARGET等）则会创建多例的切面实例，默认是单
例的（Schema风格只支持singleton实例化模型，而@AspectJ风格支持这三种实例化模型；当使用perthis或pertarget属性时，切面类不能是单例bean）
<br> 2.1.1.2 获得所有候选的aspect切面实例后，然后就会从中挑选出适合应用于当前创建的bean实例的切面，用来创建代理实例。大概的判断依据是Advisor中的pointcut的ClassFilter和MethodMatcher。
<br> 2.1.1.3 现在真正开始创建AOP代理了，先确定给定bean的advisors，包括特定的拦截器和公共拦截器（参看“Aop基于Advice接口的增强实现”中的Advice、Interceptor），是否适配Advisor接口，将它们统一转为Advisor加入到现有的切面实例中。    
然后根据设置的条件判断是使用CGLIB动态代理（返回ObjenesisCglibAopProxy，使用增强子类加回调方法等创建bean的代理对象）还是JDK动态代理（返回JdkDynamicAopProxy——一个InvocationHandler，使用jdk动态代理创建bean的代理对象）

<br> 3、以上工作创建好bean的代理对象后，在调用被代理的bean方法时就会通过JdkDynamicAopProxy的invoke()方法执行增强方法的逻辑了。
有几个特点，首先是equals()和hashCode()方法默认在被代理的bean的接口没有重写时是不被增强的；其次如果目标对象是Adviced类型，则直接使用反射进行调用；最后调用代理对象的advice链依次执行+执行目标对象的方法，最后返回执行结果。而且执行是有顺序的。
<br> 如果是CGLIB，在调用被代理的bean方法时就会通过ObjenesisCglibAopProxy的DynamicAdvisedInterceptor内部类的intercept()方法执行增强逻辑了。
与jdk时的情况一样，调用代理对象的advice链依次执行+执行目标对象的方法，最后返回执行结果。而且执行是有顺序的。

#使用SpringData设计Dao层
Locale: “国际化信息”也称为“本地化信息”，一般需要两个条件才可以确定一个特定类型的本地化信息，它们分别是“语言类型”和“国家/地区的类型”。如中文本地化信息既有中国大陆地区的中文，又有中国台湾、中国香港地区的中文，还有新加坡地区的中文。Java通过java.util.Locale类表示一个本地化对象，它允许通过语言参数和国家/地区参数创建一个确定的本地化对象。 
本地化工具类: JDK的java.util包中提供了几个支持本地化的格式化操作工具类：NumberFormat、DateFormat、MessageFormat。
特别地，MessageFormat在NumberFormat和DateFormat的基础上提供了强大的占位符字符串的格式化功能，它支持时间、货币、数字以及对象属性的格式化操作。
ResourceBoundle: Java为我们提供用于加载本地化资源文件的方便类,ResourceBoundle为加载及访问资源文件提供便捷的操作，从相对于类的全路径的目录中加载一个名为**\<资源名\>**\_\<语言代码>\_\<国家/地区代码\>.properties的本地化资源文件。会自动根绝文件名和locale匹配。
MessageSource: spring提供的整合ResourceBoundle功能并添加其他功能的方便类，ReloadableResourceBundleMessageSource可以设置实时读取更新后的项目配置的资源文件，注意修改的文件是部署在服务器上的资源文件。