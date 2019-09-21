package broadcast.service.com.serviceupdatingui;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class BroadcastService extends Service {

	private static final String TAG               = "BroadcastService";
	public  static final String BROADCAST_ACTION  = "com.sathya.verifone.displayevent";

	private final Handler handler = new Handler();

	Intent intent;
	int    counter = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();

    	intent = new Intent(BROADCAST_ACTION);	
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		handler.removeCallbacks(sendUpdatesToUI);
		handler.postDelayed(sendUpdatesToUI, 1000); // 1 second

		return super.onStartCommand(intent, flags, startId);
	}

//	@Override
//    public void onStart(Intent intent, int startId) {
//        handler.removeCallbacks(sendUpdatesToUI);
//        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
//
//    }

    private Runnable sendUpdatesToUI = new Runnable() {
    	public void run() {
    		DisplayLoggingInfo();    		
    	    handler.postDelayed(this, 1000); // 10 seconds
    	}
    };    
    
    private void DisplayLoggingInfo() {
    	Log.d(TAG, "entered DisplayLoggingInfo");

    	intent.putExtra("time", new Date().toLocaleString());
    	intent.putExtra("counter", String.valueOf(++counter));
    	sendBroadcast(intent);
    }
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {		
        handler.removeCallbacks(sendUpdatesToUI);		
		super.onDestroy();
	}		
}
