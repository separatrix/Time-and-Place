package is.hi.lucky7.timeandplace;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class gpstest extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps);
		/* Use the LocationManager class to obtain GPS locations */
		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener); 
		}
	/* Class My Location Listener */
	public class MyLocationListener implements LocationListener {
		public void onLocationChanged(Location loc) {
			loc.getLatitude();
			loc.getLongitude();
			String Text = "My current location is: " + loc.getLatitude() + " " + loc.getLongitude();
			Toast.makeText( getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
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
	}
}

