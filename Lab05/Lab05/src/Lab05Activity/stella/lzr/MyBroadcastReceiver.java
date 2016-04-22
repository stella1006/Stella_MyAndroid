package Lab05Activity.stella.lzr;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


public class MyBroadcastReceiver extends BroadcastReceiver {

	public MyBroadcastReceiver() {
		System.out.println("TestReceiver");
	}
	@Override
	public void onReceive(Context context, Intent intent) {
	    // TODO Auto-generated method stub
		System.out.println("onReceive");
	}
	//@Override
	/*public void onReceive(Context context, Intent intent) {
		System.out.println("已收到广播BCR");
	    // TODO Auto-generated method stub
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_ui);
		remoteViews.setTextViewText(R.id.newText, intent.getStringExtra("msg"));
		ComponentName componentName = new ComponentName(context, myWidget.class);
		AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), myWidget.class), remoteViews);
	}*/
  
}
