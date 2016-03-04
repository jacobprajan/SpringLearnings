package com.spring.dev.model;

import com.spring.dev.domain.Messaging;

public class Communication
{
	private Messaging messaging;
	
	public Communication(Messaging messaging)
	{
		this.messaging = messaging;
	}

	public void communicate()
	{
		messaging.sendMessage();
	}

}

/*
 
 Remark: Although constructor injection seems simple, it can easily lead to infamous constructor ambiguities 
 in case you have multiple constructors with same number of arguments but different types and if implicit 
 conversion is possible between different types. It happens mostly with primitive type (int, String etc..). 
 You can get rid of these ambiguities using ‘type’ attribute of constructor-arg element to specify exact 
 type of argument e.g.

	<bean id="activeMqMessaging" class="com.spring.dev.domain.ActiveMQMessaging" />
 
    <bean id="communication" class="com.spring.dev.model.Communication">
        <constructor-arg type="com.spring.dev.domain.Messaging">
            <ref bean="activeMqMessaging" />
        </constructor-arg>
    </bean> 

*/
