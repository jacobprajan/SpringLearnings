package com.spring.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 
@Configuration
@ComponentScan(basePackages = "com.spring.dev")
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {
 
    /*
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}


/*

Spring configuration class are the ones annotated with @Configuration. These classes contains methods 
annotated with @Bean. 
These @Bean annotated methods generates beans managed by Spring container.

@PropertySource(value = { “classpath:application.properties” }) annotation makes the properties available 
from named property file[s] (referred by value attribute) to Spring Envirronment. Environment interface 
provides getter methods to read the individual property in application.

Notice the PropertySourcesPlaceholderConfigurer bean method. This bean is required only for resolving ${…} 
placeholders in @Value annotations. In case you don’t use ${…} placeholders, you can remove this bean altogether.


Above Configuration can be expressed in XML based approach as follows (let’s name it app-config.xml):

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
    http://www.springframework.org/schema/context   http://www.springframework.org/schema/context/spring-context-4.0.xsd">
 
    <context:component-scan base-package="com.spring.dev"/>
 
    <bean class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">
        <property name="ignoreUnresolvablePlaceholders" value="true"/>
        <property name="locations">
          <list>
            <value>classpath:application.properties</value>
          </list>
        </property>
     </bean>
</beans>

*/