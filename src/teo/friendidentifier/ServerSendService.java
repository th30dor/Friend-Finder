package teo.friendidentifier;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;

@SuppressLint("DefaultLocale")
public class ServerSendService extends IntentService {

	public ServerSendService(){
		super(null);
	}
	
	@Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
    	
        String dataString = workIntent.getStringExtra("teo.friendidentifier.id");
        
        String s = dataString.toLowerCase();
        s = s.toUpperCase();
        
        // Do work here, based on the contents of dataString
        
    }
}

