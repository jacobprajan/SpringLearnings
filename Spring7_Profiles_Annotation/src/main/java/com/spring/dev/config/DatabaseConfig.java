package com.spring.dev.config;

import javax.sql.DataSource;

public interface DatabaseConfig 
{
 
    DataSource createDataSource();
     
}

/*

Both DevDatabaseConfig and ProductionDatabaseConfig are simple configuration classes implementing 
DatabaseConfig interface. What special about these classes are that they are annotated with @Profile annotation.

@Profile annotation on a component registers that component in Spring context only when that profile is active. 
Profile activation means this profile value should be available either by

>> Setting spring.profiles.active property (via JVM arguments, environment variable or Servlet context parameter 
in web.xml in case of web applications)
>> ApplicationContext.getEnvironment().setActiveProfiles(“ProfileName”);

Based on your environment you will provide the value of profile, and the beans dependent on that profile 
will be registered in Spring container.

*/