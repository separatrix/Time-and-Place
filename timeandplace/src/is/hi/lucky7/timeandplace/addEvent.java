package is.hi.lucky7.timeandplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.view.View;
import is.hi.lucky7.timeandplace.Event;
import is.hi.lucky7.timeandplace.DBAdapter;

public class addEvent extends Activity{
	private DBAdapter dbAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        
		final EditText edit_nafn = (EditText) findViewById(R.id.editText_name);
		final EditText edit_startTime = (EditText) findViewById(R.id.editText_starttime);
		final EditText edit_endTime = (EditText) findViewById(R.id.editText_endtime);
		final EditText edit_location = (EditText) findViewById(R.id.editText_location);
		final EditText edit_info = (EditText) findViewById(R.id.editText_info);

        final Button addEventButton = (Button) findViewById(R.id.addEventButton);
        
        addEventButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View arg0) {
        		String name = edit_nafn.getText().toString();
        		int start = Integer.parseInt(edit_startTime.getText().toString());
        		int end = Integer.parseInt(edit_endTime.getText().toString());
        		String loc = edit_location.getText().toString();
        		String info = edit_info.getText().toString();
        		
        		Event e = new Event(name, start, end, loc, info);
        		dbAdapter.AddEvent(e);
        	}
        });   
    }
}


