package Lab06.stella.lzr;

import java.io.File;
import java.io.IOException;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyService extends Service {

	public MediaPlayer mp;
	
	public final Binder binder = new MyBinder();
	public String na;
	public Boolean pause;
	public String path;
	
	public File audio;
	
	
	
	class MyBinder extends Binder {
		MyService getService() {
			
			Log.i("info", "mybinder");
			return 	MyService.this;
		}
		
	}
	public MyService() {
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();
		//filename = (EditText)findViewById(R.id.filename);
		Log.d("info", "playww22");
		
		
		
		
		
		Log.d("info", "onCreate");
	}
	
	private EditText findViewById(int filename2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d("info", "onStart11");
		
		
			Log.d("info", "onStart");
	}
	
	@Override
	public void onDestroy() {
		//mp.release();
    	//mp = null;
		
		Log.d("info", "onDestroy11");
		mp.release();
		Log.d("info", "onDestroy");
		super.onDestroy();
	}
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d("info", "Bind Success");
		return binder;
	}
	
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		Log.d("info", "reBind Success");
		
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d("info", "Unbind Success");
		return super.onUnbind(intent);
	}
	
	 class PrepareListener implements OnPreparedListener{
    	private int position;
    	public PrepareListener(int position) {
			// TODO Auto-generated constructor stub
    		this.position = position;
		}

		
    	
    	public void onPrepared(MediaPlayer mpp) {
    		mp.start();
    		if(position > 0) mp.seekTo(position);
    	}
    }
	
	public void play(int position, String file) {
		Log.d("info", "play1");
		path = file;
		Log.d("info", "play111");
		Log.d("info", "play11");
		
		try {
			Log.d("info", file);
    		mp.reset();//把各项参数恢复到初始状态
    		Log.d("info", "play");
    		mp.setDataSource(file);
    		Log.d("info", "playww3");
    		mp.prepare();
    		Log.d("info", "playww4");
    		mp.setOnPreparedListener(new PrepareListener(position));
    		mp.start();
    } catch (Exception e) {
    	e.printStackTrace();
    	}
		
		
    }

	public void pause() {
		if(mp.isPlaying()) {
			mp.pause();
			pause = true;
			
		}else {
			if(pause) {
				mp.start();
				pause = false;		
			}
		}
	}
	
	public void reset() {
		if(mp.isPlaying()) {
			mp.seekTo(0);	
		} else {
			if(path != null) {
				play(0, path);
			}
		}
	}
	
	public void stop() {
		if(mp.isPlaying())
			mp.stop();
	}
	
	/*public void run() {  
        int CurrentPosition = 0;// 设置默认进度条当前位置  
        int total = mp.getDuration();//  
        while (mp != null && CurrentPosition < total) {  
            try {  
                Thread.sleep(1000);  
                if (mp != null) {  
                    CurrentPosition = mp.getCurrentPosition();  
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            mp.sb.setProgress(CurrentPosition);  
        }  
  
    }  */
	
	
	

}
