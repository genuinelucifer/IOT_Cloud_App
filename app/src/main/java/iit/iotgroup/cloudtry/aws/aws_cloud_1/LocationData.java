package iit.iotgroup.cloudtry.aws.aws_cloud_1;

public class LocationData {
	public double latitude;
	public double longitude;
	public String timeStamp;

	public String getString()
	{
		return "Lat: " + latitude + "  Long: " + longitude + "  TimeStamp: " + timeStamp;
	}
}
