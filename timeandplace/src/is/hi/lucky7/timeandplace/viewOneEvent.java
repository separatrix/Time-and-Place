package is.hi.lucky7.timeandplace;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import is.hi.lucky7.timeandplace.Event;

public class viewOneEvent extends ListActivity {
    private int ar;
    private int man;
    private int dagur;
    private int klst;
    private int min;
    //T�mi upphafs atbur�ar
    private int f_klst;
    private int f_min;
    //T�mi loka atbur�ar
    private int s_klst;
    private int s_min;
    private int ferdamati;
    public DBAdapter dbadapter;
    public Event ev;
    // TODO: Make viewing location (latitude,longitude) an option
    
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        //case 0:
        //	Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show();
        //	Context mContext = getApplicationContext();
        //	Dialog dialog = new Dialog(mContext);

       // 	dialog.setContentView(R.layout.dia_nafn);
        	//dialog.setTitle("Custom Dialog");
      //  	dialog.show();
        	//TextView text = (TextView) dialog.findViewById(R.id.text);
        	//text.setText("Hello, this is a custom dialog!");
        	//showDialog(dialog);
        		
        //Dagsetningar dialog
        case 2:
            return new DatePickerDialog(this,
                        veljadag,
                        ar, man, dagur);
        //Fyrra t�ma dialog
        case 3:
            return new TimePickerDialog(this,
                    mTimeSetListener, klst, min, false);
        //Seinna t�ma dialog
    	case 4: 
    		return new TimePickerDialog(this,
                mTimeSetListener2, s_klst, s_min, false);
    	//Fer�am�ta dialog
    	case 6:
    		String num[] = {"Gangandi","Hj�landi","Keyrandi"}; 
    		return new AlertDialog.Builder(viewOneEvent.this)
            	.setTitle("Fer�am�ti")
            	.setItems(num, new DialogInterface.OnClickListener() {
            		public void onClick(DialogInterface dialog, int which) {
            			//0 = gangandi, 1 = hj�landi, 2 = keyrandi
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
    //Fyrrra skipti� sem t�minn birtist
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                	f_klst = hourOfDay;
                	f_min = minute;
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
    
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    dbadapter = new DBAdapter(this);
		dbadapter.open();
	    String ferdamatiarray[] = {"Gangandi","Hj�landi","Keyrandi"};
	    //N�r � id fr� viewEvent
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
        
	    String[] hlutir = new String[ ] {ev.getName(), ev.getInfo(), "Dagsetning", "Upphafst�mi", "Lokat�mi", "Sta�setning", ferdamatiarray[ev.getTransport()], "Ey�a"};
	    //Arrayadapter birtir nofn � ListView
	    this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hlutir)); 
	    
	}
	
	//Opnar dialog fyrir hvern hlut
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	//N� � �a� sem �tt var �.
    	Object o = this.getListAdapter().getItemId(position);
    	String numer = o.toString();
    	int numerint = Integer.parseInt(numer);
    	if (numer == "7") {
    		dbadapter.deleteEvent(ev.getId());
    	}
    	else if (numerint == 0) { 
    		customName();
    	}
    	else if (numerint == 1) { 
    		customLysing();
    	}
    	else {
    	showDialog(Integer.parseInt(numer));
    	}
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
    public void customName() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dia_nafn);
        dialog.setTitle("Nafn");
        EditText txtNewText = (EditText) dialog.findViewById(R.id.txtNewText);
        txtNewText.setText(ev.getName());
        dialog.show();
            }
    public void customLysing() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dia_nafn);
        dialog.setTitle("L�sing");
        EditText txtNewText = (EditText) dialog.findViewById(R.id.txtNewText);
        txtNewText.setText(ev.getInfo());
        dialog.show();
            }
    public void uppfaeraNafn(View view) {
    	//EditText txtNewText = (EditText) dialog.findViewById(R.id.txtNewText);
    	//ev.setName(txtNewText.getText());
    	dbadapter.updateEvent(ev);
    }
}
