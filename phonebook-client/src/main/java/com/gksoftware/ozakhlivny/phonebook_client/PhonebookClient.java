package com.gksoftware.ozakhlivny.phonebook_client;

import java.rmi.RMISecurityManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

import gk.phonebook.facade.service.IPhonebookService;

@ManagedResource(objectName = "bean:name=jmxClient", description = "Phonebook client")
public class PhonebookClient {
	  private static IPhonebookService phonebookService;

	  @ManagedOperation(description = "Get stored name in the database." )
	  @ManagedOperationParameters({
	    @ManagedOperationParameter(name = "id", description = "Entry id")
	  })
	  public String getNameById(int id) {
	    return phonebookService.getPhonebookEntry(id).getName();
	  }

	  public static void main(String[] args) {
		System.out.println("Client starting...");

	    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationcontext.xml");

	    phonebookService = context.getBean("rmiProxy", IPhonebookService.class);

	    System.out.println("Client is running...");
        while(true){}
	  }
}
