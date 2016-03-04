package com.spring.dev.model;

import com.spring.dev.domain.Messaging;

public class Communication
{
	private Messaging messaging;

	// DI via Setter
	
	// In this approach, dependency is injected/provided using Setter Method. 
	// Property Setter approach is more widely used in industry and simple to understand.

	public void setMessaging(Messaging messaging)
	{
		this.messaging = messaging;
	}

	public void communicate()
	{
		messaging.sendMessage();
	}

}
