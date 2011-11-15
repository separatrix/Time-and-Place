package is.hi.lucky7.timeandplace;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class event_test extends Activity{
	private DBAdapter DBA;
	private Event e;
    //@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_test);
		DBA = new DBAdapter(this);
		DBA.open();
		
		final TextView tw1 = (TextView) findViewById(R.id.TextView01);
		final TextView tw2 = (TextView) findViewById(R.id.TextView02);
		final TextView tw3 = (TextView) findViewById(R.id.TextView03);
		final TextView tw4 = (TextView) findViewById(R.id.TextView04);
		final TextView tw5 = (TextView) findViewById(R.id.TextView05);
		final TextView tw6 = (TextView) findViewById(R.id.TextView06);
		final TextView tw7 = (TextView) findViewById(R.id.TextView07);
		final TextView tw8 = (TextView) findViewById(R.id.TextView08);

		// Skrifa i textviews ...
		
		e = DBA.getEvent(1);
		
		tw1.append(e.getName());
		tw2.append(Long.toString(e.getStartTime()));
		tw3.append(Long.toString(e.getEndTime()));
		tw4.append(Integer.toString(e.getTransport()));
		tw5.append(e.getInfo());
		tw6.append(Double.toString(e.getLatitude()));
		tw7.append(Double.toString(e.getLongitude()));
		tw8.append(Boolean.toString(e.getPassed()));
		
	}

}
