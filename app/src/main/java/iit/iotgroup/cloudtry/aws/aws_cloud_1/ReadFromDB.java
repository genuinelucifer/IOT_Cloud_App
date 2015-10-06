package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import java.util.List;

import com.amazonaws.services.simpledb.model.SelectRequest;

import android.os.AsyncTask;
import android.util.Log;

public class ReadFromDB extends AsyncTask<Void, Void, Void> {

	public static LocationData[] ldata;
	
	public static void getAllMovies() throws Exception
	{
		SelectRequest selectRequest=  new SelectRequest("select * from " + Utilities.GPS_LOCATION_DOMAIN_NAME).withConsistentRead(true);
		
		List<com.amazonaws.services.simpledb.model.Item> items  = Connection.getAwsSimpleDB().select(selectRequest).getItems();
		Log.w("TOTAL got : ", "Total " + items.size() + " got");
		try
		{
		com.amazonaws.services.simpledb.model.Item temp1;
		int size= items.size();
		ldata= new LocationData[size];
		
		for(int i=0; i<size;i++)
		{
			temp1= items.get( i );
			
			List<com.amazonaws.services.simpledb.model.Attribute> tempAttribute= temp1.getAttributes();
			ldata[i]= new LocationData();
			for(int j=0; j< tempAttribute.size();j++)
			{
				if(tempAttribute.get(j).getName().equals(Utilities.GPS_LAT_ATTR_NAME))
				{
					ldata[i].latitude=Float.valueOf(tempAttribute.get(j).getValue());
				}
				else if(tempAttribute.get(j).getName().equals(Utilities.GPS_TIMESTAMP_NAME))
				{
					ldata[i].timeStamp =tempAttribute.get(j).getValue();
				}
				else if(tempAttribute.get(j).getName().equals(Utilities.GPS_LONG_ATTR_NAME))
				{
					ldata[i].longitude =Float.valueOf(tempAttribute.get(j).getValue());
				}
			}
		}
		}
		catch( Exception eex)
		{
			throw new Exception("Exception GOT!", eex);
		}
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			getAllMovies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
