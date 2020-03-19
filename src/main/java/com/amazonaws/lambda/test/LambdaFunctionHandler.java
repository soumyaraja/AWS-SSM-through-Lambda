package com.amazonaws.lambda.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.test.instance.EC2Client;

public class LambdaFunctionHandler implements RequestHandler<SNSEvent, String> {
	private static final Log LOG = LogFactory.getLog(LambdaFunctionHandler.class);
	
	

    public LambdaFunctionHandler() 
    {
    	
    }

 
    
    @Override
    public String handleRequest(SNSEvent event, Context context) {
    	context.getLogger().log("Received event: " + event);
        String message = event.getRecords().get(0).getSNS().getMessage();
        LOG.info(message);
        context.getLogger().log("From SNS: " + message);
        String reposne = null;
       //if(message.equalsIgnoreCase("START EC2"))
       //{
        
		try {
			reposne = EC2Client.main();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return reposne;
        //System.out.println("Lambda ec2 block");
       //}
       return reposne;
       
        
    }
}