package is.hi.lucky7.timeandplace;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TAPService extends Service{
	private Timer timer = new Timer();
	private final IBinder mBinder = new MyBinder();
	private notificationHandler not = new notificationHandler(this);
	private static String TAG = TAPService.class.getSimpleName();
	private static final long locationUpdateInterval = 60000; // Time between GPS updates (ms)
	private static final long notificationCheckInterval = 10000; // Time between "event in time-range" checks (ms)
	private long lastLocationUpdate;
	private DBAdapter dba;
	private Cursor c;
	
		@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "OnCreate'd");
		dba = new DBAdapter(this);
		dba.open();
	}
		@Override
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		Log.d(TAG, "OnStart'd");
		lastLocationUpdate = System.currentTimeMillis();
		pollForUpdates();
	}

	private void pollForUpdates() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				// Location update check
				if((System.currentTimeMillis()-lastLocationUpdate)>=locationUpdateInterval) {
					// TODO: GPS check here or change to constant location update
					lastLocationUpdate = System.currentTimeMillis();
					not.postNotification(3,"Location Update",
							Long.toString(lastLocationUpdate),System.currentTimeMillis());
				}
				
				
				// TODO: Find a way to send data to main activity for future purposes
				
				// Fetch Events within time range
				c = dba.getEventsWithinTimeRange(86400000); // 24*60*60*1000 = 86400000
				
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					
					
					
					// TODO: Calculate travel time here. 
					// If currentTime + travelTime >= startTime make notification
					int idIndex = c.getColumnIndex(DBAdapter.colId);
					int nameIndex = c.getColumnIndex(DBAdapter.colName);
					int infoIndex = c.getColumnIndex(DBAdapter.colInfo);
					int startIndex = c.getColumnIndex(DBAdapter.colStartTime);
					int latIndex = c.getColumnIndex(DBAdapter.colLatitude);
					int lonIndex = c.getColumnIndex(DBAdapter.colLongitude);
					
					if(true) {
						not.postNotification(c.getInt(idIndex), c.getString(nameIndex), 
								c.getString(infoIndex),c.getLong(startIndex));
						// Set event status to passed here to begin with
						// Might be better if it would happen on notification click
						// in the future
						
						Event ev = dba.getEvent(c.getInt(idIndex));
						ev.setPassed(true);
						dba.updateEvent(ev);
						
					}
					
				}
				
				//{colId, colName, colStartTime, colLatitude, colLongitude};
				
				// Estimate Travel Time to events within time range with location info
				// Check events: Compare travel time with current time and start time of events
				//				within range from previous step. 
				//				If Current time + travel time >= start time send notification 
				
				// Service functionality test
		    	//not.postNotification(2,"ServiceTest", "Success!",System.currentTimeMillis()); 

				}
		}, 0, notificationCheckInterval);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
			
			not.cancelNotification(2); // Service functionality test
		}
		
		Log.d(TAG, "OnDestroy'd");
	}
	// We return the binder class upon a call of bindService
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		TAPService getService() {
			return TAPService.this;
		}
	}	
}
