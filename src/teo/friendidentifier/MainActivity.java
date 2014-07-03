package teo.friendidentifier;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	String ownerName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences settings = getPreferences(MODE_PRIVATE);		
		ownerName = settings.getString("nameOfOwner", "Default");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showNameFragment() {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Name");
		alert.setMessage("Please input the name:");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				
				String value = input.getText().toString();				
				SharedPreferences settings = getPreferences(MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("nameOfOwner",value);
				editor.commit();

				
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		
			case R.id.action_settings:
				showNameFragment();
				return true;
			default:
				return super.onOptionsItemSelected(item);

		}

	}

	public void confirmData() {
		ConfirmDialogFragment newFragment = new ConfirmDialogFragment();
		FragmentManager fragmentManager = getFragmentManager();
		newFragment.show(fragmentManager, "confirm");

	}

	public void sendToService(String s) {

		if (ownerName.equals("Default")){
			
			SharedPreferences settings = getPreferences(MODE_PRIVATE);		
			ownerName = settings.getString("nameOfOwner", "Default");
			
		}
		
		Intent mServiceIntent = new Intent(this, ServerSendService.class);
		mServiceIntent.putExtra("teo.friendidentifier.id", s);
		mServiceIntent.putExtra("teo.friendidentifier.ownerName", ownerName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		
		mServiceIntent.putExtra("teo.friendidentifier.time", 
				sdf.format(new Date()));
		

		// Starts the IntentService
		this.startService(mServiceIntent);
	}

	/** Called when the user clicks Button 1 */
	public void clickButton1(View view) {
		// send to server
		sendToService(getString(R.string.button1));

		// confirm
		confirmData();

	}

	/** Called when the user clicks Button 2 */
	public void clickButton2(View view) {
		// send to server
		sendToService(getString(R.string.button2));
		// confirm
		confirmData();
	}

	/** Called when the user clicks Button 3 */
	public void clickButton3(View view) {
		// send to server
		sendToService(getString(R.string.button3));
		// confirm
		confirmData();
	}

	/** Called when the user clicks Button 4 */
	public void clickButton4(View view) {
		// send to server
		sendToService(getString(R.string.button4));
		// confirm
		confirmData();
	}

	/** Called when the user clicks Button 5 */
	public void clickButton5(View view) {
		// send to server
		sendToService(getString(R.string.button5));
		// confirm
		confirmData();
	}

}
