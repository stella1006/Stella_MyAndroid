package Lab05Activity.stella.lzr;


import android.R.bool;
import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

public class Lab05Activity extends Activity {
    /** Called when the activity is first created. */
	private Button button1;
	private Button button2;
	private MyBroadcastReceiver mBcR = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mBcR = new MyBroadcastReceiver();
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
        	boolean isRegistered = false;	
        	public void onClick(View v) {
          		if(isRegistered) {
        			unregisterReceiver(mBcR);
        			button1.setText("注册广播接收器");
        			isRegistered = false;
        		}
        		else {    			
        			IntentFilter filter = new IntentFilter();
        			filter.addAction("SYSU_ANDROID_2014");		
        			Lab05Activity.this.registerReceiver(mBcR, filter);	
        			button1.setText("注销广播接收器");
        			isRegistered = true;	
        		}
        	}
        });
        
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {	
        	 public void onClick(View v) {
        		 EditText mMessage;
             	mMessage = (EditText)findViewById(R.id.editText);  	

             	Intent intent = new Intent();
             	intent.setAction("SYSU_ANDROID_2014");
             	intent.putExtra("msg", mMessage.getText().toString());
             	System.out.println(mMessage.getText().toString());
             	sendBroadcast(intent);
             }
        });
       
    }
    
    public class MyBroadcastReceiver extends BroadcastReceiver {

    	@Override
    	public void onReceive(Context context, Intent intent) {
    	    // TODO Auto-generated method stub
    		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_ui);
    		remoteViews.setTextViewText(R.id.newText, intent.getStringExtra("msg"));
    		ComponentName componentName = new ComponentName(context, myWidget.class);
    		AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), myWidget.class), remoteViews);
    	}
      
    }
}