package is.hi.lucky7.timeandplace;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


// TODO: Cleanup!

public class TimeAndPlace extends Activity {
    /** Called when the activity is first created. */
    //@Override
    	private notificationHandler not = new notificationHandler(this);
    	private DBAdapter dba;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, TAPService.class));
        setContentView(R.layout.main);
        }
    
    public void AddEvent(View view) {
		// Switch to addEvent screen:
		Intent addevent = new Intent(getApplicationContext(),
				addEvent.class);
		startActivity(addevent);
    }
    
    public void ViewEvent(View view) {
    	// Switch to viewEvent screen:
    	Intent viewevent = new Intent(getApplicationContext(),
    			viewEvent.class);
    	startActivity(viewevent);
    }
    
    // Til ad testa Event og DB virkni
    public void ViewEventTest(View view) {
    	// Switch to viewEvent screen:
    	Intent vieweventtest = new Intent(getApplicationContext(),
    			event_test.class);
    	startActivity(vieweventtest);
    }
    
    public void testAlarm(View view) {
    	final int id = 1;
    	final long when = System.currentTimeMillis();
    	not.postNotification(id,"titill", "strengur",when);
    }
    
    public void cancelAlarmTest(View view) {
    	// Crashes if there isn't and has never been a
    	// notification with that id number.
    	// Watch out!
    	not.cancelNotification(1);
    }
    // Service start/stop test buttons
    public void startServiceTest(View view) {
    	startService(new Intent(this, TAPService.class));
    }
    
    public void stopServiceTest(View view) {
    	stopService(new Intent(this, TAPService.class));
    }
    public void gpsTest(View view) {
    	Intent gpstest = new Intent(getApplicationContext(),
    			gpstest.class);
    	startActivity(gpstest);
    }
    
    public void setEvent1NotPassed(View view) {
		dba = new DBAdapter(this);
		dba.open();
		
		Event ev = dba.getEvent(1);
		ev.setPassed(false);
		dba.updateEvent(ev);
    }
}