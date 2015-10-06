package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import java.util.List;

import com.amazonaws.services.simpledb.model.SelectRequest;

import android.os.AsyncTask;

public class ReadFromDB extends AsyncTask<Void, Void, Void> {

	public static LocationData[] ldata;
	
	public static LocationData[] getAllMovies() throws Exception
	{
		SelectRequest selectRequest=  new SelectRequest("select * from " + Utilities.GPS_LOCATION_DOMAIN_NAME).withConsistentRead(true);
		
		List<com.amazonaws.services.simpledb.model.Item> items  = Connection.getAwsSimpleDB().select(selectRequest).getItems();
		
		try
		{
		com.amazonaws.services.simpledb.model.Item temp1;
		int size= items.size();
		LocationData[] locationList= new LocationData[size];
		
		for(int i=0; i<size;i++)
		{
			temp1= items.get( i );
			
			List<com.amazonaws.services.simpledb.model.Attribute> tempAttribute= temp1.getAttributes();
			locationList[i]= new LocationData();
			for(int j=0; j< tempAttribute.size();j++)
			{
				if(tempAttribute.get(j).getName().equals(Utilities.GPS_LAT_ATTR_NAME))
				{
					locationList[i].latitude=Float.valueOf(tempAttribute.get(j).getValue());
				}
				else if(tempAttribute.get(j).getName().equals(Utilities.GPS_TIMESTAMP_NAME))
				{
					locationList[i].timeStamp =tempAttribute.get(j).getValue();
				}
				else if(tempAttribute.get(j).getName().equals(Utilities.GPS_LONG_ATTR_NAME))
				{
					locationList[i].longitude =Float.valueOf(tempAttribute.get(j).getValue());
				}
			}
		}
		return locationList;
		}
		catch( Exception eex)
		{
			throw new Exception("Exception GOT!", eex);
		}
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		try {
			ldata = getAllMovies();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
