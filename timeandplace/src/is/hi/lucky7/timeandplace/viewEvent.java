package is.hi.lucky7.timeandplace;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class viewEvent extends ListActivity{
		private DBAdapter dba;
		private Cursor cur;
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewevent);
		dba = new DBAdapter(this);
		dba.open();
		fillData();
		
		final TextView view = (TextView) findViewById(R.id.view);

		view.append(Integer.toString(dba.getEventCount()));
		
		final EditText edit_ViewNum = (EditText) findViewById(R.id.edit_viewNum);
		final Button viewEventButton = (Button) findViewById(R.id.viewEventButton);

	}
		private void fillData() {
	        // Get all of the notes from the database and create the item list
	        Cursor c = dba.getAllEvents();
	        startManagingCursor(c);

	        String[] from = new String[] { DBAdapter.colName };
	        int[] to = new int[] { R.id.text_row };
	        
	        // Now create an array adapter and set it to display using our row
	        SimpleCursorAdapter ev =
	            new SimpleCursorAdapter(this, R.layout.event_row, c, from, to);
	        setListAdapter(ev);
		}
	
}
 