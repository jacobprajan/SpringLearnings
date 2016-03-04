package com.spring.dev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan which will make Spring auto detect the annotated beans via scanning the  
// specified package and wire them wherever needed (using @Resource or @Autowired ).

@Configuration
@ComponentScan(basePackages = "com.spring.dev")
public class AppConfig
{

}



/*

You might have noticed that above class is Empty, no @Bean methods, acting as a placeholder instead. 
So where will the beans come from?

Actually, they will be auto-detected, thanks to @ComponentScan annotation.

@ComponentScan(basePackages = "com.spring.dev")
@ComponentScan basePackages attribute takes package name[s] as input which will be search for to find any 
class annotated with Spring specific annotations.

Below are commonly used Spring annotation which makes a bean auto-detectable:

@Repository - Used to mark a bean as DAO Component on persistence layer
@Service - Used to mark a bean as Service Component on business layer
@Controller - Used to mark a bean as Controller Component on Presentation layer
@Configuration - Used to mark a bean as Configuration Component.
@Component - General purpose annotation, can be used as a replacement for above annotations.

Remark: In our example, you can even discard the Configuration class altogether as it does not contain 
any @Bean method. We will see how to scan the beans in that case below.

Same Configuration in XML terms can be expressed as below (let’s name it app-config.xml)

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
  
    <context:component-scan base-package="com.spring.dev" />
  
</beans>


*/