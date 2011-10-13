package is.hi.lucky7.timeandplace;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class viewEvent extends Activity{
		private DBAdapter dba;
		private Cursor cur;
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewevent);
		dba = new DBAdapter(this);
		dba.open();
	
		final EditText edit_ViewNum = (EditText) findViewById(R.id.edit_viewNum);
		final Button viewEventButton = (Button) findViewById(R.id.viewEventButton);
		final TextView TextView1 = (TextView) findViewById(R.id.textView1);
		
		viewEventButton.setOnClickListener(new View.OnClickListener() {
				
			public void onClick(View arg0) {
			// Read edit_ViewNum data and query database
				// Readwrite test:
				//CharSequence id = edit_ViewNum.getText();
				//TextView1.append(id);
				
				int id = Integer.parseInt(edit_ViewNum.getText().toString());
				cur = dba.getEvent(id);
				
				// Print cursor data to textview:
				
				//TextView1.append(convertToString(cur));
				
			}
		});
	
//		private CharacterSequence printCursor() 
//		{
// 		startManagingCursor(cur);
// 		String[] from = new String[] { DBAdapter.colName};
//			int[] to = new int[] { R.id.label };
//			SimpleCursorAdapter simp = new SimpleCursorAdapter(this, R.layout.event_row, cur, from, to);
//		}
	}
}
 