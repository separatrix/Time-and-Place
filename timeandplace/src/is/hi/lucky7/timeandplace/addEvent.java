package is.hi.lucky7.timeandplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
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
        
		final EditText edit_nafn = (EditText) findViewById(R.id.edi_nafn);
		final EditText edit_startTime = (EditText) findViewById(R.id.edi_timasetning);
		//final EditText edit_endTime = (EditText) findViewById(R.id.editText_endtime);
		final EditText edit_location = (EditText) findViewById(R.id.edi_stadsetning);
		//final EditText edit_info = (EditText) findViewById(R.id.editText_info);

        final Button addEventButton = (Button) findViewById(R.id.btn_skra);

	}
        public void BaetaVid(View view) {
    		final EditText edit_nafn = (EditText) findViewById(R.id.edi_nafn);
    		final EditText edit_startTime = (EditText) findViewById(R.id.edi_timasetning);
    		//final EditText edit_endTime = (EditText) findViewById(R.id.editText_endtime);
    		final EditText edit_location = (EditText) findViewById(R.id.edi_stadsetning);
    		//final EditText edit_info = (EditText) findViewById(R.id.editText_info);
        	String name = edit_nafn.getText().toString();
    		int start = Integer.parseInt(edit_startTime.getText().toString());
    		//int end = Integer.parseInt(edit_endTime.getText().toString());
    		int end = 0;
    		String loc = edit_location.getText().toString();
    		//String info = edit_info.getText().toString();
    		String info = "NULL";
    		
    		// Herna vantar villutjekk. Forritid crashar ef null gildi eru skrad inn i event
    		// thar sem dalkar in gagnagrunn eru restricted vid NOT NULL gildi.
    		// Tharf ad passa ad name, start_time, end_time og transport seu ekki null.
    		// Spurning um ad passa lika upp a location upp a seinni tima.
    		
    		// Ath einnig nyjan Event smid sem tekur lika vid transport
    		Event e = new Event(name, start, end, loc, info);
    		dbAdapter.AddEvent(e);
    }
}


