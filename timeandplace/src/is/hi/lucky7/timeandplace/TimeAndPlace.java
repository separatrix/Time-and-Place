package is.hi.lucky7.timeandplace;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;

public class TimeAndPlace extends Activity {
    /** Called when the activity is first created. */
    //@Override
    	private notificationHandler not = new notificationHandler(this);
    	private AlarmManager alarm; // Notad i kodabut sem er kommentadur ut her ad nedan
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        // Thessi kodabutur keyrir forritid a 20 sekundna fresti
        /*
    	Intent tap_intent = new Intent(this, TimeAndPlace.class);
        PendingIntent runprogram = PendingIntent.getActivity(this, 0, tap_intent, 0);
        alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10*1000, 20*1000, runprogram);
        */
        
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
    	// Crashar ef aldrei hefur verid til notification med 
    	// thessu id.
    	// Thurfum ad passa ad thad gerist ekki i loka utgafu
    	// forritsins eda halda utan um thau notifications sem 
    	// er buid ad setja.
    	not.cancelNotification(1);
    }
    
    public void testAlarm() {
    	final int id = 2;
    	final long when = System.currentTimeMillis();
    	not.postNotification(id,"CheckerTest", "Success!",when);
    }
}