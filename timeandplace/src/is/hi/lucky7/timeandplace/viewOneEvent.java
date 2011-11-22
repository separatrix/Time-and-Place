package is.hi.lucky7.timeandplace;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import is.hi.lucky7.timeandplace.Event;

public class viewOneEvent extends ListActivity {
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
    public DBAdapter dbadapter;
    public Event ev;
    // TODO: Make viewing location (latitude,longitude) an option
    // TODO: Fix editing of StartTime, EndTime and Date
    // TODO: Make deleting and event available
    
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        //Dagsetningar dialog
        case 2:
            return new DatePickerDialog(this,
                        veljadag,
                        ar, man, dagur);
        //Fyrra tíma dialog
        case 3:
            return new TimePickerDialog(this,
                    mTimeSetListener, klst, min, false);
        //Seinna tíma dialog
    	case 4: 
    		return new TimePickerDialog(this,
                mTimeSetListener2, s_klst, s_min, false);
    	//Ferðamáta dialog
    	case 6:
    		String num[] = {"Gangandi","Hjólandi","Keyrandi"}; 
    		return new AlertDialog.Builder(viewOneEvent.this)
            	.setTitle("Ferðamáti")
            	.setItems(num, new DialogInterface.OnClickListener() {
            		public void onClick(DialogInterface dialog, int which) {
            			//0 = gangandi, 1 = hjólandi, 2 = keyrandi
            			ferdamati = which;
            			uppfaeraFerdamata(ferdamati); 
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
    
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    dbadapter = new DBAdapter(this);
		dbadapter.open();
	    String ferdamatiarray[] = {"Gangandi","Hjólandi","Keyrandi"};
	    //Nær í id frá viewEvent
	    Bundle extras = getIntent().getExtras();
	    String value = extras.getString("id");
		ev = dbadapter.getEvent(Integer.parseInt(value));
	 // Upphafs dagsetning
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ev.getStartTime());
        ar = c.get(Calendar.YEAR);
        man = c.get(Calendar.MONTH);
        dagur = c.get(Calendar.DAY_OF_MONTH);
        klst = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE); 
        // Loka dagsetning
        c.setTimeInMillis(ev.getEndTime());
        s_klst = c.get(Calendar.HOUR_OF_DAY);
        s_min = c.get(Calendar.MINUTE); 
        
	    String[] hlutir = new String[ ] {ev.getName(), ev.getInfo(), "Dagsetning", "Upphafstími", "Lokatími", "Staðsetning", ferdamatiarray[ev.getTransport()], "Eyða"};
	    //Arrayadapter birtir nofn í ListView
	    this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hlutir)); 
	    
	}
	
	//Opnar dialog fyrir hvern hlut
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	//Ná í það sem ýtt var á.
    	Object o = this.getListAdapter().getItemId(position);
    	String numer = o.toString();
    	showDialog(Integer.parseInt(numer));
    } 
    public void uppfaeraFerdamata(int ferdamati) {
    	ev.setTransport(ferdamati);
    	dbadapter.updateEvent(ev); 
    } 
    public void uppfaeraFyrritima(long timi) {
    	ev.setStartTime(timi);
    	dbadapter.updateEvent(ev);
    }
    public void uppfaeraSeinnitima(long timi) {
    	ev.setEndTime(timi);
    	dbadapter.updateEvent(ev);
    }
    public void uppfaeraLysingu(String lysing) {
    	ev.setInfo(lysing);
    	dbadapter.updateEvent(ev);
    }
    public void uppfaeraNafn(String nafn) {
    	ev.setInfo(nafn);
    	dbadapter.updateEvent(ev);
    }
}
