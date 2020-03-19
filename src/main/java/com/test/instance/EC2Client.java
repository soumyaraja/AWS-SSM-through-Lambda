package com.test.instance;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.services.ec2.AmazonEC2;


public class EC2Client {

	private static final Log LOG = LogFactory.getLog(EC2Client.class);
	public static String main() throws InterruptedException 
	{
		 
		 String resposne = null;
		 
		 
		
		 String instanceId = System.getenv("Instance_id");
		 //String instanceId = "i-06879d1be9f81f82b";
		 
		 

		 ManageInstance instance = new ManageInstance();
		 AmazonEC2 client = instance.CreateInstance();
		 int code = instance.getInstanceStatus(instanceId, client);
		 LOG.info("EC2 status "  + code );
		 
		 
		if(instance.getInstanceStatus(instanceId, client)==16)
		 {
			 LOG.info("EC2 status "  + code );
			 LOG.info("Stopping instance");
			 instance.stoptInstance(instanceId, client); 
			 LOG.info("instance Stopped");
			 return resposne = Integer.toString(16);
			 
		 }
		 
		 else if (instance.getInstanceStatus(instanceId, client)==80)
		 {
			 LOG.info("EC2 status "  + code );
			 LOG.info("Starting Instance");	 
		  instance.startInstance(instanceId, client);
		  LOG.info("instance Started");
		  Thread.sleep(50000);
		  resposne = instance.runCommand(instanceId);
		return resposne;
		 
		 }
		return resposne;
		
		
		
		 
		 
		 
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		String test =  main();
	}

}
