package com.spring.dev.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain
{
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// Sets the active profiles
		// context.getEnvironment().setActiveProfiles("Development");
		
		context.getEnvironment().setActiveProfiles("Production");
		
		// Scans the mentioned package[s] and register all the @Component
		// available to Spring
		context.scan("com.spring.dev");
		context.refresh();
		context.close();
	}
}

/*

Notice how we have set the active profiles to be used on runtime. context.scan("package") scans the 
mentioned package and registers all the @Component but when it encounters the @Profile annotation on 
a bean/configuration, it compare the profile value with the one supplied in environment. If the value matches, 
it registers that bean else it skips it. In our case it’s the DevDatabaseConfig dataDource which will be 
injected in AppConfig.dataSource.

*/
