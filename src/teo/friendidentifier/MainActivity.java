package teo.friendidentifier;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void confirmData(){
		ConfirmDialogFragment newFragment = new ConfirmDialogFragment();
		FragmentManager fragmentManager = getFragmentManager();
	    newFragment.show(fragmentManager, "confirm");
		
	}
	
	public void sendToService(String s){
		
		Intent mServiceIntent = new Intent(this, ServerSendService.class);
		mServiceIntent.putExtra("teo.friendidentifier.id", s);
		
		// Starts the IntentService
		this.startService(mServiceIntent);
	}
	
	/** Called when the user clicks Button 1 */
	public void clickButton1(View view) {
	    //send to server
		sendToService("friend1");
		
		//confirm
		confirmData();

	}
	
	/** Called when the user clicks Button 2 */
	public void clickButton2(View view) {
	    //send to server
		sendToService("friend2");
		//confirm
		confirmData();
	}
	
	/** Called when the user clicks Button 3 */
	public void clickButton3(View view) {
	    //send to server
		sendToService("friend3");
		//confirm
		confirmData();
	}
	
	/** Called when the user clicks Button 4 */
	public void clickButton4(View view) {
	    //send to server
		sendToService("friend4");
		//confirm
		confirmData();
	}
	
	/** Called when the user clicks Button 5 */
	public void clickButton5(View view) {
	    //send to server
		sendToService("friend5");
		//confirm
		confirmData();
	}

}
