package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;

public class StoreToDB extends AsyncTask<LocationData, Void, Void>  {

	//public static Context appctx;
	
	// 2. Create Domain and save movie information in domain
	public static void saveLocation(double latitude, double longitude, String timestamp)
	{
		try {
			 Connection.getAwsSimpleDB().createDomain(new CreateDomainRequest( Utilities.GPS_LOCATION_DOMAIN_NAME));
			 List<ReplaceableAttribute> attribute= new ArrayList<>(1);
			 attribute.add(new ReplaceableAttribute().withName(Utilities.GPS_LAT_ATTR_NAME).withValue(Double.toString(latitude)));
			 attribute.add(new ReplaceableAttribute().withName(Utilities.GPS_LONG_ATTR_NAME).withValue(Double.toString(longitude)));
			 attribute.add(new ReplaceableAttribute().withName(Utilities.GPS_TIMESTAMP_NAME).withValue(timestamp));
			 Connection.awsSimpleDB.putAttributes(new PutAttributesRequest(Utilities.GPS_LOCATION_DOMAIN_NAME, timestamp, attribute));
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected Void doInBackground(LocationData... params) {
		saveLocation(params[0].latitude, params[0].longitude, params[0].timeStamp);
		return null;
	}

	@Override
	protected void onPostExecute(Void param)
	{
		//Toast.makeText(appctx, "Location stored successfully!", Toast.LENGTH_LONG).show();
	}
	
}
