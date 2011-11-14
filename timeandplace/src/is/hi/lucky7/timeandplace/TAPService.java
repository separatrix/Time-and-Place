package is.hi.lucky7.timeandplace;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TAPService extends Service{
	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 5000;
	private final IBinder mBinder = new MyBinder();
	private notificationHandler not = new notificationHandler(this);
	private static String TAG = TAPService.class.getSimpleName();
	
	
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "OnCreate'd");
		pollForUpdates();
	}
	
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
		Log.d(TAG, "OnStart'd");
	}

	private void pollForUpdates() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				// Toast print test here ...
		    	not.postNotification(2,"ServiceTest", "Success!",System.currentTimeMillis());
		    	toastprint();

				}
		}, 0, UPDATE_INTERVAL);
	}
	
	public void toastprint()
	{
		Toast.makeText(this, "TAPService test!",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
			
			not.cancelNotification(2);
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
