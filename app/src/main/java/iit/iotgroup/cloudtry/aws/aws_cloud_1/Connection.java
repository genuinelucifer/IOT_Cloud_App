package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

public class Connection {

	public static AmazonSimpleDB awsSimpleDB;
	public static AmazonSimpleDB getAwsSimpleDB()
	{
		if(awsSimpleDB==null)
		{
			BasicAWSCredentials credentials= new BasicAWSCredentials(Utilities.ACCESS_KEY, Utilities.SECRET_KEY);
			awsSimpleDB= new AmazonSimpleDBClient(credentials);
		}
		return awsSimpleDB;
	}
}
