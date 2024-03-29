package is.hi.lucky7.timeandplace;

import java.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    //Timi upphafs atburdar
    private int f_klst;
    private int f_min;
    //Timi loka atburdar
    private int s_klst;
    private int s_min;
    private int ferdamati;
	private int hnitLat;
	private int hnitLon;
    
    protected Dialog onCreateDialog(int id) {
    	// TODO: F�ra strengi � strings.xml
        switch (id) {
        //Dagsetningar dialog
        case 0:
            return new DatePickerDialog(this,
                        veljadag,
                        ar, man, dagur);
        //Fyrra t�ma dialog
        case 1:
            return new TimePickerDialog(this,
                    mTimeSetListener, klst, min, false);
        //Seinna t�ma dialog
    	case 2: 
    		return new TimePickerDialog(this,
                mTimeSetListener2, f_klst, f_min, false);
    	//Fer�am�ta dialog
    	case 3:
    		String num[] = {"Gangandi","Hj�landi","Keyrandi"}; 
    		return new AlertDialog.Builder(addEvent.this)
            	.setTitle("Fer�am�ti")
            	.setItems(num, new DialogInterface.OnClickListener() {
            		public void onClick(DialogInterface dialog, int which) {
            			//0 = gangandi, 1 = hj�landi, 2 = keyrandi
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
    //Fyrrra skipti� sem t�minn birtist
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                	f_klst = hourOfDay;
                	f_min = minute;
                	//showDialog(2);
                }
            };
    //Seinna skipti� sem t�minn birtist
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
        
        // N�verandi dagsetning
        final Calendar c = Calendar.getInstance();
        ar = c.get(Calendar.YEAR);
        man = c.get(Calendar.MONTH);
        dagur = c.get(Calendar.DAY_OF_MONTH);
        klst = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);

	}
    public void fSkra(View view) {
    	//H�r �arf l�ka a� bakka � upphafsskj�.
    	final EditText edit_nafn = (EditText) findViewById(R.id.edi_nafn);
    	final EditText edit_lysing = (EditText) findViewById(R.id.edi_lysing);
        String nafn = edit_nafn.getText().toString();
    	String lysing = edit_lysing.getText().toString();
    	long fyrritimi = toTimestamp(ar,man,dagur,f_klst,f_min);
    	long seinnitimi = toTimestamp(ar,man,dagur,s_klst,s_min);
    	int latitude = hnitLat;
    	int longitude = hnitLon;
    	boolean tempPass = false;
    	// Herna vantar villutjekk. Forritid crashar ef null gildi eru skrad inn i event
    	// thar sem dalkar in gagnagrunn eru restricted vid NOT NULL gildi.
    	// Tharf ad passa ad name, start_time, end_time og transport seu ekki null.
    	// Spurning um ad passa lika upp a location upp a seinni tima
    	
    	Event e = new Event(nafn,fyrritimi,seinnitimi,latitude,longitude,ferdamati,lysing,tempPass);
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
    public void fStads(View view) {
    	startActivityForResult(
    			new Intent(getApplicationContext(), Map.class),
    			0);
    		}
    public long toTimestamp(int ar, int man, int dagur, int klst, int min) {
    	Calendar c = Calendar.getInstance();
    	c.set(ar,man,dagur,klst,min);
    	return c.getTimeInMillis();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 0 && resultCode == 1) {
    		Bundle b = data.getExtras();
    		try {
    			hnitLat = b.getInt("lat");
    			hnitLon = b.getInt("lon");
    			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    			dialog.setTitle("Hnit");
    			dialog.setMessage("Valin hnit eru: "+hnitLat+", "+hnitLon);
    			dialog.show();
    		} catch (Exception e) {
    		}    		
    	}
    }
}


