package teo.friendidentifier;

import java.io.IOException;
import java.util.Date;

import teo.friendidentifier.dataentityendpoint.Dataentityendpoint;
import teo.friendidentifier.dataentityendpoint.model.DataEntity;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

@SuppressLint("DefaultLocale")
public class ServerSendService extends IntentService {

	public ServerSendService(){
		super(null);
	}

	public boolean isConnectedToWiFI(){

		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);


		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnectedOrConnecting();

		if (isConnected){
			return activeNetwork.getType() == ConnectivityManager.TYPE_WIFI; 
		}
		else 
			return false;
	}

	public void sendToServer(String s){

		Dataentityendpoint.Builder endpointBuilder = new Dataentityendpoint.Builder(
				AndroidHttp.newCompatibleTransport(),
				new JacksonFactory(),
				new HttpRequestInitializer() {
					public void initialize(HttpRequest httpRequest) { }
				});
		Dataentityendpoint endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();
		try {
			DataEntity note = new DataEntity().setFriend(s);
		    note.setOwner("Teo");
		    
		    String noteID = new Date().toString();
	        note.setId(noteID);
		    
        
			DataEntity result = endpoint.insertDataEntity(note).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}



	@Override
	protected void onHandleIntent(Intent workIntent) {
		// Gets data from the incoming Intent

		String dataString = workIntent.getStringExtra("teo.friendidentifier.id");

		boolean done = false;

		String s = dataString.toLowerCase();

		//do work here

		while(!done){

			//if already connected to the internet
			if (isConnectedToWiFI()){

				sendToServer(s);
				done = true;
			}
			//or else, wait for connection to begin
			else{

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	}
}

