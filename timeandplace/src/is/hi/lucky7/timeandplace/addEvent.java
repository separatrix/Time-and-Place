package is.hi.lucky7.timeandplace;

import java.util.Calendar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import is.hi.lucky7.timeandplace.Event;
import is.hi.lucky7.timeandplace.DBAdapter;

public class addEvent extends Activity{
	private DBAdapter dbAdapter;
    /** Called when the activity is first created. */
    private int ar;
    private int man;
    private int dagur;
    private int klst;
    private int min;
    //Tími upphafs atburðar
    private int f_klst;
    private int f_min;
    //Tími loka atburðar
    private int s_klst;
    private int s_min;
    private int ferdamati;
    
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        //Dagsetningar dialog
        case 0:
            return new DatePickerDialog(this,
                        veljadag,
                        ar, man, dagur);
        //Fyrra tíma dialog
        case 1:
            return new TimePickerDialog(this,
                    mTimeSetListener, klst, min, false);
        //Seinna tíma dialog
    	case 2: 
    		return new TimePickerDialog(this,
                mTimeSetListener2, f_klst, f_min, false);
    	//Ferðamáta dialog
    	case 3:
    		String num[] = {"Gangandi","Hjólandi","Keyrandi"}; 
    		return new AlertDialog.Builder(addEvent.this)
            	.setTitle("Ferðamáti")
            	.setItems(num, new DialogInterface.OnClickListener() {
            		public void onClick(DialogInterface dialog, int which) {
            			ferdamati = which;
            			}
            })
            .create();
        }
        return null;
    }
    //Dagsetning
    private DatePickerDialog.OnDateSetListener veljadag =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    ar = year;
                    man = monthOfYear;
                    dagur = dayOfMonth;
                }
            };
    //Fyrrra skiptið sem tíminn birtist
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                	f_klst = hourOfDay;
                	f_min = minute;
                	//showDialog(2);
                }
            };
    //Seinna skiptið sem tíminn birtist
    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    s_klst = hourOfDay;
                    s_min = minute;
                        }
                    };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        
        // Núverandi dagsetning
        final Calendar c = Calendar.getInstance();
        ar = c.get(Calendar.YEAR);
        man = c.get(Calendar.MONTH);
        dagur = c.get(Calendar.DAY_OF_MONTH);
        klst = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
		//final EditText edit_nafn = (EditText) findViewById(R.id.edi_nafn);
		//final EditText edit_startTime = (EditText) findViewById(R.id.edi_lysing);
		//final EditText edit_endTime = (EditText) findViewById(R.id.editText_endtime);
		//final EditText edit_location = (EditText) findViewById(R.id.edi_stadsetning);
		//final EditText edit_info = (EditText) findViewById(R.id.editText_info);

        //final Button addEventButton = (Button) findViewById(R.id.btn_skra);

	}
        //public void BaetaVid(View view) {
    		//final EditText edit_nafn = (EditText) findViewById(R.id.edi_nafn);
    		//final EditText edit_startTime = (EditText) findViewById(R.id.edi_timasetning);
    		//final EditText edit_endTime = (EditText) findViewById(R.id.editText_endtime);
    		//final EditText edit_location = (EditText) findViewById(R.id.edi_stadsetning);
    		//final EditText edit_info = (EditText) findViewById(R.id.editText_info);
        	//String name = edit_nafn.getText().toString();
    		//int start = Integer.parseInt(edit_startTime.getText().toString());
    		//int end = Integer.parseInt(edit_endTime.getText().toString());
    		//int end = 0;
    		//String loc = edit_location.getText().toString();
    		//String info = edit_info.getText().toString();
    		//String info = "NULL";
    		
    		// Herna vantar villutjekk. Forritid crashar ef null gildi eru skrad inn i event
    		// thar sem dalkar in gagnagrunn eru restricted vid NOT NULL gildi.
    		// Tharf ad passa ad name, start_time, end_time og transport seu ekki null.
    		// Spurning um ad passa lika upp a location upp a seinni tima.
    		
    		// Ath einnig nyjan Event smid sem tekur lika vid transport
    		//Event e = new Event(name, start, end, loc, info);
    		//dbAdapter.AddEvent(e);
    //}
}


