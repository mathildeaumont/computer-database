package com.excilys.endpoint;
import javax.xml.ws.Endpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.webservice.ComputerWebservice;

// TODO: Auto-generated Javadoc
// Endpoint publisher
/**
 * The Class ComputerEndpoint.
 */
public class ComputerEndpoint {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context-webservice.xml");
		ComputerWebservice ws = ctx.getBean(ComputerWebservice.class);
		Endpoint.publish("http://localhost:9898/webservice/computers", ws);
	}
}