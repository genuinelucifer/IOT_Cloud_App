package iit.iotgroup.cloudtry.aws.aws_cloud_1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends Activity {
	static ReadFromDB rDB;
	LocationData[] locList;
	ListView loclv;
	ArrayList<String> locListString;
    ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get);
        loclv =  (ListView) findViewById(R.id.listView1);

        locListString = new ArrayList<>();
        adapter = new ArrayAdapter<>( this,R.layout.activity_listview,locListString);
        loclv.setAdapter(adapter);
        rDB = new ReadFromDB();
        rDB.execute();
        new populateListView().execute();
	}

    ProgressDialog pd;
	public class populateListView extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute()
        {
            pd = new ProgressDialog(ViewActivity.this);
            pd.setMessage("Loading data...\nPlease wait.");
            pd.show();
        }

		@Override
		protected Void doInBackground(Void... params) {
            while(rDB.getStatus() != AsyncTask.Status.FINISHED);
            locList = ReadFromDB.ldata;
            locListString.clear();
            for(LocationData ld : locList){
                locListString.add(ld.getString());
            }
            return null;
		}

		@Override
		protected void onPostExecute(Void param)
		{
            pd.setMessage("");
            pd.dismiss();
            adapter.notifyDataSetChanged();
            Toast.makeText(ViewActivity.this, "Data Loaded!!", Toast.LENGTH_SHORT).show();
        }

	}

}
