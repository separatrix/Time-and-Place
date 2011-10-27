package is.hi.lucky7.timeandplace;

import java.util.Calendar;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
            			//0 = gangandi, 1 = hjólandi, 2 = keyrandi
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

	}
    public void fSkra(View view) {
    	//Hér þarf líka að bakka á upphafsskjá.
    	final EditText edit_nafn = (EditText) findViewById(R.id.edi_nafn);
    	final EditText edit_lysing = (EditText) findViewById(R.id.edi_lysing);
        String nafn = edit_nafn.getText().toString();
    	String lysing = edit_lysing.getText().toString();
    	int fyrritimi = toTimestamp(ar,man,dagur,f_klst,f_min);
    	int seinnitimi = toTimestamp(ar,man,dagur,s_klst,s_min);
    	String stadsetning = "NULL";
    	// Herna vantar villutjekk. Forritid crashar ef null gildi eru skrad inn i event
    	// thar sem dalkar in gagnagrunn eru restricted vid NOT NULL gildi.
    	// Tharf ad passa ad name, start_time, end_time og transport seu ekki null.
    	// Spurning um ad passa lika upp a location upp a seinni tima.	
    	Event e = new Event(nafn,fyrritimi,seinnitimi,stadsetning,ferdamati,lysing);
    	dbAdapter.AddEvent(e); 
    	}
    public void fDag(View view) {
    	showDialog(0);
    	    }
    public void fUpphaf(View view) {
    	showDialog(1);
    	    }
    public void fLoka(View view) {
    	showDialog(2);
    	    }
    public void fFerda(View view) {
    	showDialog(3);
    	    }
    public int toTimestamp(int ar, int man, int dagur, int klst, int min) {
    	//Hér þarf að breyta í timestamp.
    	final Calendar c = Calendar.getInstance();
    	c.set(ar,man,dagur,klst,min);
    	return 0;
    }
}


