package Lab05Activity.stella.lzr;



import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

public class myWidget extends AppWidgetProvider {
	private static final String MYTAG = "MytagTest";
	private static final String UPDATE_ACTION = "lzr.appwidget.UPDATE_APP_WIDGET";
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        // TODO Auto-generated method stub
    	Intent intent = new Intent(context, Lab05Activity.class);
    	
    	PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
    	RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_ui);
    	remoteViews.setOnClickPendingIntent(R.id.widgetLayout, pendingIntent);
    	appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    	
    	super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	        // TODO Auto-generated method stub
	        super.onDeleted(context, appWidgetIds);
	    }

    @Override
    public void onDisabled(Context context) {
        // TODO Auto-generated method stub
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        // TODO Auto-generated method stub
    	 System.out.println("appwidget--->onEnabled()");
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
    	 System.out.println("appwidget--->onReceive1()");
    	/* if(intent.getStringExtra("msg") == null)
    		 System.out.println("null");
    	 
    	 
    	 RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_ui);
 		 //remoteViews.setTextViewText(R.id.newText, "hahaha");
    	 
    	 remoteViews.setTextViewText(R.id.newText, intent.getStringExtra("msg"));
 		 System.out.println("接收到了");
 		 
 		
 		
 		 AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), myWidget.class), remoteViews);*/
 		 System.out.println("appwidget--->onReceive2()");
    	 super.onReceive(context, intent);
    }

    
	 


	    
	    
} 
