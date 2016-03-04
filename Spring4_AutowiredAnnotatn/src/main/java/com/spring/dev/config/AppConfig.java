package com.spring.dev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @ComponentScan which will make Spring auto detect the annotated beans via scanning the  
// specified package and wire them wherever needed (using @Resource or @Autowired ).

@Configuration
@ComponentScan("com.spring.dev")
public class AppConfig
{

}