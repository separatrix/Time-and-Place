package is.hi.lucky7.timeandplace;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class TimeAndPlace extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button addButton = (Button) findViewById(R.id.addButton);
        final Button viewButton = (Button) findViewById(R.id.viewButton);
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
}