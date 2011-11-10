package is.hi.lucky7.timeandplace;

import android.app.ListActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class viewEvent extends ListActivity{
		private DBAdapter dba;
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.viewevent);
		dba = new DBAdapter(this);
		dba.open();
		fillData();
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
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	super.onListItemClick(l, v, position, id);
	    	//Ná í það sem ýtt var á.
	    	Object o = this.getListAdapter().getItemId(position);
	    	String keyword = o.toString();
	    	//Toast.makeText(this, "Þú valdir: " + keyword, Toast.LENGTH_LONG).show();
			Intent viewOneEvent = new Intent(getApplicationContext(), 
					viewOneEvent.class);
					viewOneEvent.putExtra("id",keyword);
			startActivity(viewOneEvent);
	    }
	
}
 
//Prufa prufa