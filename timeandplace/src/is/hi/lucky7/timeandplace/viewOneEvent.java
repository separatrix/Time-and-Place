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
    private DBAdapter dbadapter;
    
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
                mTimeSetListener2, f_klst, f_min, false);
    	//Ferðamáta dialog
    	case 6:
    		String num[] = {"Gangandi","Hjólandi","Keyrandi"}; 
    		return new AlertDialog.Builder(viewOneEvent.this)
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
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	 // Núverandi dagsetning
        final Calendar c = Calendar.getInstance();
        ar = c.get(Calendar.YEAR);
        man = c.get(Calendar.MONTH);
        dagur = c.get(Calendar.DAY_OF_MONTH);
        klst = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE); 
        
	    String[] hlutir = new String[ ] {"Nafn", "Lýsing", "Dagsetning", "Upphafstími", "Lokatími", "Staðsetning", "Ferðamáti", "Eyða"};
	    //Arrayadapter birtir nofn í ListView
	    this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hlutir)); 
	    //Nær í id frá viewEvent
	    Bundle extras = getIntent().getExtras();
	    String value = extras.getString("id");
	    //Thessi virkar ekki, veit ekki af hverju
		//Event ev = dbadapter.getEvent(1);
	    
	}
	
	//Opnar dialog fyrir hvern hlut
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	//Ná í það sem ýtt var á.
    	Object o = this.getListAdapter().getItemId(position);
    	String numer = o.toString();
    	showDialog(Integer.parseInt(numer));
    } 
}
