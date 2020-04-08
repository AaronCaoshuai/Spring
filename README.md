[TOC]



# Spring相关

## 概述 

官网:http://spring.io/

Spring 版本:

JDK8+ ---Spring5.x

JDK6+ ---Spring4.x

JDK5+ ---Spring3.x

Spring特点和好处:

1.方便解耦,简化开发,通过spring提供的ioc容器,我们可以将对象之间的依赖关系交由spring进行控制,避免硬编码所造成的程序耦合,有了spring,用户不必再为单实例模式类,属性文件解析等这些很底层的需求编写代码,可以更专注于上层的应用.

2.AOP编程的支持,通过spring提供的AOP功能,方便进行面向切面的编程,许多不容易用传统oop实现的功能可以通过AOP轻松应付.

3.声明式事务的支持,在spring中,我们可以从单调烦闷的事务管理代码中解脱出来,通过声明式方式灵活的进行事务的管理,提供开发效率和质量.

4.方便程序的测试,可以用非容器依赖的编程方式进行几乎所有的测试工作,在spring里,测试不再是昂贵的操作,而是随手可做的事情,例如:Spring对Junit4支持,可以通过注解方便的测试spring程序.

5.方便集成各种优秀框架,spring不排斥各种优秀的开源框架,相反,spring可以降低各种框架的使用难度,spring提供了对各种优秀框架(如:Struts,Hibernate,Hessian,Quartz)等的直接支持.

6.降低JavaEE API的使用难度,spring对很多难用的javaEE API(如:jdbc,javaMail,远程调用等)提供了一个薄薄的封装层,通过spring的简易封装,这些javaEE API的使用难度大为降低.

7.java源码是经典学习范例,Spring的源码设计精妙,结构清晰.处处体现着大师对java设计模式灵活运用以及对java技术的高深造诣,spring框架源码无疑是java技术的最佳实践范例,如果想在短时间内迅速提高自己的java技术水平和应用开发水平,学习和研究spring源码将会使你收到意想不到的效果.

Spring体系结构:

![1585482015063](C:\Users\semon\AppData\Roaming\Typora\typora-user-images\1585482015063.png)

Spring IOC:Inverse of Control 控制反转,对象的创建权力由程序反转给Spring框架

Spring DI:Dependency Injection 依赖注入,在Spring框架负责创建Bean对象时,动态的将依赖对象注入到Bean组件中

Spring AOP:Aspect Oriented Programming 面向切面编程,在不修改目标对象的源代码情况下,增强Ioc容器中的Bean功能.

Spring 容器:指的就是Ioc容器,底层也就是一个BeanFactory

## 基础篇

### 基于XML的使用

#### IOC配置

在Spring的xml文件中通过一个bean标签,完成IoC的配置

bean标签介绍:

​	作用:用于配置被Spring容器管理的bean的信息

​			默认情况下它调用的是类中的无参构造函数,如果没有无参构造则不能创建成功.

​	属性:

​			id:给对象在容器中提供一个唯一标识,用于获取对象.

​			class:指定类的全限定名,用于反射创建对象,默认情况下调用无参数构造函数

​			init-method:指定类中的初始化方法名称

​			destroy-method:指定类中销毁方法名称.比如:DataSource的配置中一般需要指定			destroy-method="close".

​			scope:指定对象的作用范围.

​				singleton:默认值,单例的(在整个容器中只有一个对象),生命周期如下:

​								 对象出生:当应用加载,创建容器时,对象就被创建了

 							    对象活着:只要容器在,对象一直活着

​								 对象死亡:当应用卸载,销毁容器时,对象就被销毁了

​				prototype:多例的,每次访问对象时,都会重新创建对象实例,生命周期如下:

​								 对象出生:当使用对象时,创建新的对象实例

​								 对象活着:只要对象在使用中,就一直活着

​								 对象死亡:当对象长时间不同时,被java的垃圾回收器回收了.

​				request:将spring创建的Bean对象存入到request域中

​				session:将spring创建的Bean对象存入到session域中

​				global session:WEB项目中,应用在Portlet环境.如果没有Portlet环境那么global 								session相当于session

​	bean实例化的三种方式:

​				第一种:使用默认无参构造函数

​				在默认情况下:它会根据默认无参构造函数来创建类对象

​				如果bean中没有默认无参构造函数,将会创建失败.

```java
	 <bean id="student" class="com.aaron.spring.ioc.xml.po.Student"/>
```

​				第二种:静态工厂

​				使用StaticFactory类中的静态方法createUserService创建对象,并存入spring容器:

​				使用StaticFactory类中的静态方法createUserService创建对象,并存入spring容器,id属性:指定bean的id,用于从容器中获取,class属性:指定静态工厂的全限定类名,factory-method属性:指定生产对象的静态方法

```java
/**
 * 静态工厂创建对象
 */
public class StaticFactory {

    public static UserService createUserService(){
        return new UserServiceImpl();
    }
}
<bean id="userService"class="com.aaron.spring.ioc.factory.StaticFactory" 
    factory-method="createUserService"/>
```

​				第三种:实例工厂

​				先把工厂的创建交给spring来管理,然后在使用工厂的bean来调用里面的方法.

factory-bean属性:用于指定实例工厂bean的id,factory-method属性:用于指定实例工厂中创建对象的方法.

```java
/**
 * 实例工厂
 */
public class InstanceFactory {

    public OrderService createOrderService(){
        return new OrderServiceImpl();
    }
}
<bean id="instanceFactory" class="com.aaron.spring.ioc.factory.InstanceFactory"/>
<bean id="orderService" factory-bean="instanceFactory" 
    factory-method="createOrderService"/>
```



#### DI配置

依赖指的就是Bean实例中的属性,依赖分为:简单类型(8种基本数据类型和String类型)的属性,POJO类型的属性,集合数组类型的属性

依赖注入:是Spring框架核心IoC的具体实现

为什么要进行依赖注入:如果一个bean中包含了一些属性,那么spring帮我们实例化了bean对象之后,也需要将对应的属性信息进行赋值操作,这种属性赋值操作,就是所谓的依赖注入(获取值,注入属性)

**依赖注入的方式**

**构造函数注入**:使用类中的构造函数,给成员变量赋值,赋值的操作不是我们自己做的,而是通过配置的方式,让spring框架来为我们注入.

使用构造函数的方式,给service中的属性传值要求:类中需要提供一个对应参数列表的构造函数.涉及的标签:constructor-arg

属性:index:指定参数在构造函数参数列表的索引位置

​		name:指定参数在构造函数中的名称

​		value:它能赋的值是基本数据类型和String类型

​		ref:它能赋的值是其他bean类型,也就是说,必须得是在配置文件中配置过得bean

```
 <bean id="worker" class="com.aaron.spring.ioc.xml.di.Worker">
        <constructor-arg name="name" value="张三"/>
        <constructor-arg name="sex" value="男"/>
        <constructor-arg name="age" value="22"/>
    </bean>
```

**set方法注入**

**手动装配方式(XML方式)**

​	需要配置bean标签的子标签property

​	需要配置bean中指定setter方法

​	**依赖注入不同类型的属性XML方式**

​	简单类型

​	引用类型

​	集合类型

​		数组或者List:

​		Set集合:

​		Map集合:

​		Properties集合:

```
  <bean id="project" class="com.aaron.spring.ioc.xml.di.Project">
        <property name="name" value="大项目"/>
        <property name="type" value="一级"/>
        <!-- 引用类型注入 -->
        <!-- 基本类型为 vlaue="" -->
        <property name="workerList">
            <list>
                <ref bean="worker"/>
            </list>
        </property>

        <property name="workerSet">
            <set>
                <ref bean="worker"/>
            </set>
        </property>
        <property name="workers">
            <array>
                <ref bean="worker"/>
            </array>
        </property>
        <property name="workerMap">
            <map>
                <entry key="1" value-ref="worker"/>
            </map>
        </property>
    </bean>
```

**自动装配方式(注解方式)**

​	@Autowired:

​	作用1:查找实例,从spring容器中根据bean的类型(byType)获取实例

​	作用2:赋值,将找到的实例,装配给另一实例的属性值

​	注意事项:一个java类型在同一个Spring容器中,只能有一个实例.

​	@Resource

​	作用1:查找实例,从spring容器中根据bean的名称(byName)获取实例

​	作用2:赋值,将找到的实例,装配给另一个实例的属性值.

​	@Inject 详解基于注解方式使用



### 基于注解和XML混合方式的使用

IoC注解使用方法:

​	1.spring配置文件中,配置context:component-scan标签,开启注解自动扫描,指定基础包

```
<?xml version="1.0" encoding="UTF-8"?><beans xmlns="http://www.springframework.org/schema/beans"       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"       xmlns:context="http://www.springframework.org/schema/context"       xmlns:aop="http://www.springframework.org/schema/aop"       xmlns:tx="http://www.springframework.org/schema/tx"       xsi:schemaLocation="http://www.springframework.org/schema/beans        http://www.springframework.org/schema/beans/spring-beans.xsd        http://www.springframework.org/schema/context        http://www.springframework.org/schema/context/spring-context.xsd        http://www.springframework.org/schema/tx        http://www.springframework.org/schema/tx/spring-tx.xsd        http://www.springframework.org/schema/aop        http://www.springframework.org/schema/aop/spring-aop.xsd">    <!--注解+xml的方式来实例化bean-->    <!--开启注解自动扫描,指定基础包名-->    <context:component-scan base-package="com.aaron.spring"/></beans>
```

​	2.类上面加上注解@Component,或者它的衍生注解@Controller@Service@Repository

```java
@Component
public class Course {

    private String name;

    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //...
}
```

常用注解:

​	**IoC注解(创建对象)**

​	相当于<bean id="" class=""/>

   @Component注解

​	作用:把资源让Spring来管理,相当于在xml中配置一个bean.

​    属性:value指定bean的id,如果不指定value属性,默认bean的id是当前类的类名,首字母小写.

   @Controller@Service@Repository注解

​	三个注解都是针对@Component的衍生注解,他们的作用及属性都是一模一样的,他们只不过是提供了更加明确的语义化 .如果注解中有且只有一个属性要赋值时,且名称是value,value在赋值时可以不写.

​	**DI注解(依赖注入)**

​	相当于<property name="" value=""/>

​	@Autowired

​		@Autowired默认是按类型装配的(byType)

​		@Autowired是由AutowiredAnnotationBeanPostProcessor类实现

​		@Autowired是spring自带的注解

​		@Autowired默认情况下要求依赖对象必须存在,如果需要允许null值,可以设置它的required属性为false,如:@Autowried(required=false)

​		如果我们想按名称装配(byName)可以结合@Qualifier注解进行使用

​	@Qualifier

​		在自动按照类型注入的基础之上,再按照Bean的id注入

​		它在给字段注入是不能独立使用必须和@Autowire一起使用

​		但是给方法参数注入时,可以独立使用

​	@Resource

​		@Resource默认按照名称装配,可以通过@Resource的name属性指定名称,如果没有指定name属性,当注解写在字段上时,默认去字段名进行按照名称查找,当找不到与名称匹配的bean时才按照类型进行装配

​		@Resource属于J2EE JSR250规范的实现

​		如果name属性一旦指定,就只会按照名称进行装配

​		推荐使用@Resource注解,因为他是属于J2EE的,减少了与spring的耦合,这样代码看起来就比较优雅.

​	@Inject

​		@Inject是根据类型进行自动装配的,如果需要按名称进行装配,则需要配合@Named;

​		@Inject是JSR330中的规范,需要导入javax.inject.Inject;实现注入

​		@Inject可以作用在变量,setter方法,构造函数上

​	@Value

​		给基本类型和String类型注入值

​		可以使用占位符获取属性文件中的值

​	@Autowired @Resource @Inject 区别

​		1.@Autowired是spring自带的,@Inject是JSR330规范实现的,@Resource是JSR250规范实现的,需要导入不同的包

​		2.@Autowired,@Inject用法基本一样,不同的是@Autowired有一个request属性

​		3.@Autowired,@Inject是默认按照类型匹配的,@Resource是按照名称匹配的

​		4.@Autowired如果需要按照名称匹配需要和@Qualifier一起使用,@Inject和@Name一起使用.

​	@Scope:指定bean的作用范围,value:singleton,prototype,request,session,globalsession

​	@PostConstruct:init-method

​	@PreDestroy:destroy-method

xml和注解的选择:

注解:配置简单,维护方便(找到了类,就相当于找到了对应的配置)

xml:修改时不用修改源码,不涉及重新编译和部署



### 基于纯注解方式的使用

注解和xml混合开发遗留的一些问题解决:

可以把开启自动扫描的注解,引入属性占位文件的,开启事务等的xml配置可以省略掉,到最后把xml文件也同时去掉,全部使用注解来代替使用

之前创建ApplicationContext都是通过读取XML文件进行创建的

```java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
```

从Spring3.0开始可以使用@Configuration定义配置类,可替换xml配置文件

配置类内部包含有一个或多个@Bean注解的方法,这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描,并用于构建bean定义对象,初始化Spring容器

@Configuration 相当于spring的xml配置文件,value用于指定配置类的字节码,

@Bean 相当于<bean>标签,作用为:注册bean对象,主要用来配置非自定义的bean,比如DruidDataSource,SqlSessionFactory,@Bean标注在方法上(返回某个实例的方法),属性:name给当前@Bean注解方法创建的对象指定一个名称(即bean的id),如果不指定,默认与标注的方法名相同,@Bean注解默认作用域为单例singleton作用域,可通过@Scope("prototype")设置为原型作用域

@ComponentScan 相当于context:component-scan标签

组件扫描器,扫描@Component@Controller@Service@Repository注解的类

该注解是编写在类上面的,一般配合@Configuration注解一起使用属性,basePackage:用于指定要扫描的包,value和basePackages的作用一样

@PropertySource相当于context:property-placeholder标签,编写在类上面,作用是加载properties配置文件,value[]:用于指定properties文件路径,如果在类路径下,需要写上classpath

@Import相当于<import>标签,用来组合多个配置类,在引入其他配置类时,可以不用再写@Configuration注解,当然写上也ok.value用来指定其他配置类的字节码文件

创建纯注解方式上下文容器:

ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

参考代码:

```
//spring3.0开始可以使用@Configuration定义配置类
//可以替换xml配置文件
@Configuration
//相当于xml中开启注解扫描
@ComponentScan(basePackages = "com.aaron.spring.ioc.pureanno")
//相当于<import>导入xml文件,来组合多个配置类,可以不再写@Configuration注解
//value:用来指定其他配置类的字节码文件
@Import({JdbcConfig.class})
public class SpringConfiguration {

    public SpringConfiguration() {
        System.out.println("spring 容器初始化");
    }
}
@Configuration//spring-xml 配置
//相当于context:property-placeHolder标签
@PropertySource("classpath:db.properties")
public class JdbcConfig {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;
    //注册bean对象,主要用来配置非自定义的bean,
    //比如DruidDataSource,SqlSessionFactory
    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        System.out.println("数据源属性配置开始");
        DataSource ds = new PooledDataSource();
        ((PooledDataSource) ds).setDriver(driver);
        ((PooledDataSource) ds).setUrl(url);
        ((PooledDataSource) ds).setUsername(username);
        ((PooledDataSource) ds).setPassword(password);
        System.out.println("数据源属性配置结束");
        return ds;
    }

}
public class IocPureAnnoTest {

    @Test
    public void testPureAnno(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = context.getBean(UserService.class);
        userService.saveUser();
    }
}


```



## 高级篇

### AOP相关

AOP,Aspect Oriented Programming的缩写,意为:面向切面编程

作用:在不修改目标类代码的前提下,可以通过AOP技术去增强目标类的功能,通过预编译的方式和运行期动态代理的方式实现程序功能的统一维护的一种技术.

AOP是一种编程范式,隶属于软工范畴,指导开发者如何组织程序结构

AOP最早由AOP联盟的组织提出的,指定了一套规范,Spring将AOP思想引入到框架中,必须遵守AOP联盟的规范

AOP是OOP的延续,是软件开发中的一个热点,也是Spring框架中的一个重要内容,是函数式编程的一种衍生范型

利用AOP可以对业务代码中业务逻辑和系统逻辑进行隔离,从而使得业务逻辑和系统逻辑质检的耦合度降低,提高程序的可重用性,同时提高了开发的效率

### 为什么使用AOP

作用:AOP采取横向抽取机制,补充了传统纵向继承体系(OOP)无法解决的重复性代码优化(性能监控,事务管理,安全检查,缓存),将业务逻辑和系统处理的代码(关闭连接,事务管理,操作日志记录)解耦

优势:重复性代码被抽取出来之后,维护更加方便

纵向继承和横向抽取的区别,对比代码重复代码继承抽取和使用AOP横向抽取的不同

AOP相关术语:

Joinpoint连接点:指那些被拦截到的点,在Spring中,这些点指的是方法,因为Spring只支持方法类型的连接点

Pointcut切入点:切入点是指我们要对哪些Joinpoint进行拦截的定义.

Advice通知,增强:指拦截到的Joinpoint之后所要做的事情就是通知,通知分为前置通知,后置通知,异常通知,最终通知和环绕通知(切面要完成的功能)

Introduction引介:一种特殊的通知在不修改类代码的前提下,Introduction可以在运行期为类动态的添加一些方法或Field

Target 目标对象 代理的目标对象

Weaving 织入指把增强应用到目标对象来创建新的代理对象的过程

Proxy代理:一个类被AOP织入增强后,就会产生一个结果代理类

Aspect切面:是切入点和通知的结合

Advisor:通知器和切面很相似.

**AOP实现之AspectJ**

AspectJ是一个java实现的AOP框架,他能够对java代码进行AOP编译(一般在编译器进行),让java代码具有AspectJ的AOP功能(当然需要特殊的编译器)

可以这样说AspectJ是目前实现AOP框架中最成熟,功能最丰富的语言,更幸运的是AspectJ是java程序完全兼容,几乎是无缝关联,

了解AspectJ应用到java代码的过程,成为织入,对于织入的概念,可以简单理解为aspect(切面)应用到目标函数的过程

对于织入这个过程,一般分为静态织入和动态织入,动态织入的方式是在运行时动态将要增强的代码织入到目标类中,这样往往是通过动态代理技术完成的,如java JDK的动态代理(Proxy 底层通过反射实现)或者CGLIB的动态代理(底层通过继承实现),Spring AOP采用的就是基于运行时增强的代理技术

AspectJ采用就是静态织入的方式,AspectJ主要采用的是编译期织入,在这个期间使用AspectJ的acj编译器(类似javac)把aspect类编译成class字节码后,在java目标类编译时织入,即先编译aspect类再编译目标类

**AOP实现之Spring AOP**

Spring AOP是通过动态代理技术实现的

而动态代理是基于反射设计的

动态代理技术的实现方式有两种:基于接口的JDK动态代理和基于继承的CGLIb动态代理

详解:参考设计模式的动态代理设计模式.

使用Spring AOP

其使用ProxyFactoryBean创建,使用<aop:advisor>定义通知器的方式实现AOP则需要通知类实现Advice接口,增强的类型有:

org.springframework.aop.MethodBeforeAdvice AfterReturningAdvice,ThrowsAdvice

org.aopalliance.intercept.MethodInterceptor 环绕通知

### 基于AspectJ的AOP使用

其实就是指的Spring+AspectJ整合,不过Spring已经将AspectJ收录到自身的框架中了,并且底层织入依然采用动态织入方式

**xml实现**

1.编写目标类和目标方法

2.使用XML实现,编写通知增强类,配置通知,将通知类交给Spring IOC容器管理

3.配置AOP切面

切入点表达式:格式 execution([修饰符] 返回值类型 包名.类名.方法名(参数))

表达式格式说明:

​	execution:必须要

​	修饰符:可省略

​	返回值类型:必须要,但是可以使用*通配符

​	包名: 多级包之间使用.分割;包名可以使用*代替,多级包名可以使用多个*代替;如果想省略中间的包名可以 使用..

​	类名:可以使用*代替;也可以写成\*DaoIMpl

​	方法名:也可以使用*号代替,可以写成add\*

​	参数:参数使用*代替,如果有多个参数,可以使用 ..代替

通知的类型

​	五种:前置通知,后置通知,环绕通知,最终通知,抛出异常通知,返回通知

​	前置通知:目标对象方法之前执行通知,<aop:before method="before" pointcut-ref="myPontcut"/>,方法开始时可以进行校验

​	后置通知:目标对象方法执行之后通知,有异常则不执行了,<aop:after-returing method="afterReturning" pointcut-ref="myPointcut"/>,可以修改方法的返回值

​	最终通知:目标对象方法之后执行通知,有没有异常都会执行<aop:after method="after" pointcut-ref="myPointcut"/> ,释放资源

​	环绕通知:目标对象方法之前和之后都会执行,<aop:around method="around" pointcut-ref="myPointcut"/> 事务,统计代码执行时机,日志打印

​	异常抛出通知:在抛出异常后通知 <aop:after-throwing method="afterThrowing" pointcut-ref="myPointcut"> 包装异常

**注解实现**

​	注解+xml

​	1.编写切面类(注意不是通知类,因为该类中可以指定切入点) 切面类=通知+切入点

​	2.开启自动扫描,开启AOP自动代理

​	纯注解方式

​	使用@Configuration @ComponentScan @EnableAspectJAutoProxy

### 整合Junit

单元测试

在测试类中,每个测试方法都有以下两行代码:

ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

UserService userService = context.getBean(UserService.class);

上述两段代码不是业务代码,可以简化,使用注解

Junit暴露了@RunWith ,可以替换掉它运行的容器,使用spring容器来管理junit

1.添加spring-test包 2.通过RunWith注解,指定Spring的运行器,SpringJunit4ClassRunner

3.通过@ContextConfiguration注解,指定spring运行器需要的配置文件路径

​	@ContextConfiguration(locations="classpath:applicationContext.xml")

4.通过@Autowired注解给测试类中的变量注入数据

### 事务支持

#### Spring框架事务管理相关接口

Spring不直接管理事务,而是提供了事务管理接口PlatformTransactionManager,通过这个接口,Spring为各个平台如JDBC,Hibernate等都提供了对应的事务管理器

1.PlatformTransactionManager接口--平台事务管理器(真正管理事务的类),该接口有具体的实现类,根据不同的持久层框架,需要选择不同的实现类

2.TransactionDefinition接口--事务定义信息(事务的隔离级别,传播行为,超时,只读)

3.TransactionsStatus接口:--事务的状态(是否新事务,是否已提交,是否有保存点,是否回滚)

4.总结:上述对象之间的关系:平台事务管理器真正管理事务对象,根据事务定义的信息

TransactionDefinition进行事务管理,在管理事务中产生一些状态,将状态记录到TransactionStatus中

5.PlatformTransactionManager接口中实现类和使用的方法

​	5.1接口的实现类

​	如果使用Spring的jdbc模板或者Mybatis框架,需要选择DataSourceTransactionManager实现类

​	如果是的是Hibernate框架,需要选择HIbernateTransactionManager实现类

​	5.2该接口的常用方法

​	void commit(TransactionStatus status)

​	TransactionStatus getTransaction(TransactionDefinition definition)

​	void rollback(TransactionStatus status)

6.TransactionDefinition

​	6.1.事务隔离级别的常量

​		static int ISOLATION_DEFAULT 数据库默认的隔离级别

​		static int ISOLATION_READ_UNCOMMITTED 读未提交

​		static int ISOLATION_READ_COMMITTED 读已提交

​		static int ISOLATION_REPEATABLE_READ  可重复读

​		static int ISOLATION_SERIALIZABLE  串行化

​	6.2事务的传播行为常量

​		解决的是业务层之间的方法调用

​		PROPAGATION_REQUEST 默认值 A中有事务,使用A中的事务,如果没有,B就会开启一个新的事务,把A包含进来,(保证A,B在同一个事务中)

​		PROPAGTION_SUPPORTS A中有事务,使用A中的事务,如果A中没有事务,那么B也不使用事务

​		PROPAGTION_MANDATORY A中有事务,使用A中的事务,如果A没有事务,抛出异常

​		PROPAGTION_REQUEST_NEW A中有事务,将A中的事务挂起,B创建一个新的事务(保证A,B没有在一个事务中)

​		PROPAGTION_NERVER A中有事务,抛出异常

​		PROPAGTION_NESTED 嵌套事务,当A执行之后,就会在这个位置设置一个保存点,如果B没有问题,执行通过,如果B出现异常,运行客户根据需求回滚(选择回滚到保存点或者最初始状态)

#### Spring框架事务管理的分类

​	1.Spring的编程式事务管理:通过手动编写代码的方式完成事务的管理

​	2.Spring的声明式事务管理(底层使用AOP的技术),通过一段配置的方式完成事务的管理

​		2.1基于AspectJ的XML方式

​		2.2基于AspectJ的注解+XML混用的方式

​		2.3基于AspectJ的纯注解方式

## 代码参考

## 面试篇

## 源码篇







