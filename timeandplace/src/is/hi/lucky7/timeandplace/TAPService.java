package is.hi.lucky7.timeandplace;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TAPService extends Service{
	private Timer timer = new Timer();
	private final IBinder mBinder = new MyBinder();
	private notificationHandler not = new notificationHandler(this);
	private static String TAG = TAPService.class.getSimpleName();
	private static final long notificationCheckInterval = 10000; // Time between "event in time-range" checks (ms)
	private DBAdapter dba;
	private Cursor c;
	// Set the initial co-ordinates to the Icelandic Phallic Museum
	private double lat=64.14310;
	private double lon=-21.9146;
	
	
		@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "OnCreate'd");
		dba = new DBAdapter(this);
		dba.open();
		/* Use the LocationManager class to obtain GPS locations */
	}
		@Override
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener); 
		Log.d(TAG, "OnStart'd");
		pollForUpdates();
	}

	private void pollForUpdates() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				// Fetch Events within time range
				c = dba.getEventsWithinTimeRange(86400000); // 24*60*60*1000 = 86400000
				
				// Cycle through Events within range
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					
					// TODO: Calculate travel time here. 
					// Estimate Travel Time to events within time range with location info
					// Where the double precision numbers lat and lon are the current location
					// and c.getLong(latIndex) is the location latitude
					// and c.getLong(lonIndex) is the location longitude
					
					// TODO: change if expression below to something like:
					// if(currentTime + travelTimeToEvent >= startTimeOfEvent)  -> make notification
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
				
				}
		}, 0, notificationCheckInterval);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();			
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
	public class MyLocationListener implements LocationListener {
		public void onLocationChanged(Location loc) {
			lat = loc.getLatitude();
			lon = loc.getLongitude();
			// Print the location to screen for testing purposes
			//String Text = "My current location is: " + loc.getLatitude() + " " + loc.getLongitude();
			//Toast.makeText( getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
		}
		//@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText( getApplicationContext(),"Gps Disabled",Toast.LENGTH_SHORT ).show();
		}
		//@Override
		public void onProviderEnabled(String provider) { 
			Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show();
		}
		//@Override
		public void onStatusChanged(String provider, int status, Bundle extras){}
		public double getLon(Location loc) {
			return loc.getLongitude();
		}
	}
}
