package com.test.instance;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.InstanceNetworkInterfaceSpecification;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.GetConsoleOutputRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.CreateActivationRequest;
import com.amazonaws.services.simplesystemsmanagement.model.CreateActivationResult;
import com.amazonaws.services.simplesystemsmanagement.model.CreateAssociationRequest;
import com.amazonaws.services.simplesystemsmanagement.model.SendCommandRequest;
import com.amazonaws.services.simplesystemsmanagement.model.SendCommandResult;
import com.amazonaws.services.simplesystemsmanagement.model.Target;

public class ManageInstance 
{

	public ManageInstance()
	{

	}

	private static final Log LOG = LogFactory.getLog(ManageInstance.class);

	public AmazonEC2 CreateInstance()
	{
		

		AmazonEC2 ec2Client = AmazonEC2ClientBuilder
				.standard()
				.withRegion(Regions.EU_WEST_1)
				.build();

		return ec2Client;
	}



	public void startInstance(String instanceId,AmazonEC2 ec2Client)
	{


		StartInstancesRequest startInstancesRequest = new StartInstancesRequest()
				.withInstanceIds(instanceId);

		ec2Client.startInstances(startInstancesRequest);


	}

	public void stoptInstance(String instanceId, AmazonEC2 ec2Client)
	{
		StopInstancesRequest stopInstancesRequest = new StopInstancesRequest()
				.withInstanceIds(instanceId);

		ec2Client.stopInstances(stopInstancesRequest);
	}

	public Integer getInstanceStatus(String instanceId, AmazonEC2 ec2Client) {
		DescribeInstancesRequest describeInstanceRequest = new DescribeInstancesRequest().withInstanceIds(instanceId);
		DescribeInstancesResult describeInstanceResult = ec2Client.describeInstances(describeInstanceRequest);
		InstanceState state = describeInstanceResult.getReservations().get(0).getInstances().get(0).getState();
		return state.getCode();
	}

	public String runCommand( String instanceId)
	{

		String resposne = null;
		String endpointssm = "https://ssm.eu-west-1.amazonaws.com";
		List<String> commandlist = new ArrayList<String>();
		//commandlist.add(System.getenv("Ssm_command1"));
		commandlist.add(System.getenv("Ssm_command"));

		EndpointConfiguration endPoint = new EndpointConfiguration(endpointssm, "eu-west-1");
		

		AWSSimpleSystemsManagement ssmClient =  AWSSimpleSystemsManagementClientBuilder
				.standard()
				.withEndpointConfiguration(endPoint)
				//.withRegion(Regions.EU_WEST_1)
				.build();


	//DescribeIamInstanceProfileAssociationsRequest request1 = new DescribeIamInstanceProfileAssociationsRequest();
	//request1.withAssociationIds("javassm");
     //Target target = new Target();
		//target.
		LOG.info("ssmClient "  + ssmClient);
		SendCommandRequest request = new SendCommandRequest()
				.withInstanceIds(instanceId)
				.withDocumentName("AWS-RunShellScript")
				.withComment("Remote Java Code Run")
				.addParametersEntry("commands", commandlist);

		LOG.info(request.getComment());
		/*CreateActivationRequest asc = new CreateActivationRequest();
	
	asc.setDescription("XML Transformation");
	asc.setIamRole("");
	CreateActivationResult res = ssmClient.createActivation(asc);*/
		//LOG.info(res.getActivationId());
		SendCommandResult result =ssmClient.sendCommand(request);
		int code = result.getSdkHttpMetadata().getHttpStatusCode();
		LOG.info(code);

		resposne = 	 Integer.toString(code); 
		return resposne;


	}
	
	
	
}


