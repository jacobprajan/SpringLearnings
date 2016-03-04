package com.spring.dev.domain;

public class ActiveMQMessaging implements Messaging
{
	public void sendMessage() 
	{
        System.out.println("Constrcutor Injection: Sending Message via Active MQ");
    }
}
