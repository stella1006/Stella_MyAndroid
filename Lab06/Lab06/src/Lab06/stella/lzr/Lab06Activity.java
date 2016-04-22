package Lab06.stella.lzr;

import java.io.File;
import java.text.SimpleDateFormat;





import Lab06.stella.lzr.MyService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Lab06Activity extends Activity {
    /** Called when the activity is first created. */
	
	Button startB, pauseB, resetB, endB, quitB;
	 SeekBar sb;
	boolean atChange, mnChange;
	boolean change = true;
	MyService mm = new MyService(); 
	private  MyServiceConnection conn = new MyServiceConnection();
	private TextView text;
	private TextView time;
	private String file;
	public String path;
	public EditText filename;
	SimpleDateFormat dtime;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startB = (Button)findViewById(R.id.play);
        startB.setOnClickListener(new starttt());
        endB = (Button)findViewById(R.id.stop);
        endB.setOnClickListener(new endddd());
        pauseB = (Button)findViewById(R.id.pause);
        pauseB.setOnClickListener(new pauseee());
        resetB = (Button)findViewById(R.id.reset);
        resetB.setOnClickListener(new resettt());
        quitB = (Button)findViewById(R.id.quit);
        quitB.setOnClickListener(new quittt());
        sb = (SeekBar)findViewById(R.id.seekBar);
        text = (TextView)findViewById(R.id.zhuangtai);
        time = (TextView)findViewById(R.id.time);
        filename = (EditText)findViewById(R.id.filename);
       
        dtime = new SimpleDateFormat("mm:ss");
        sb.setOnSeekBarChangeListener(new SeekBarListener());
        
        Intent intent = new Intent();
		intent.setClass(Lab06Activity.this, MyService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);  
    }
    
    class MyServiceConnection implements ServiceConnection {
    	public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
    		mm = ((MyService.MyBinder)(service)).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mm = null;
		}
    }
  
    Handler handler = new Handler();
    Runnable updateThread = new Runnable(){  
        public void run() {  
            //获得歌曲现在播放位置并设置成播放进度条的值 
        sb.setProgress(mm.mp.getCurrentPosition()); 
       // time.setText(mm.mp.getCurrentPosition());
        time.setText(dtime.format(mm.mp.getCurrentPosition())+"/"+dtime.format(mm.mp.getDuration()));
            //每次延迟100毫秒再启动线程  
        handler.postDelayed(updateThread, 100);  
        }  
    };
    
    class SeekBarListener implements OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			sb.setMax(mm.mp.getDuration());  		
			// TODO Auto-generated method stub
			if(fromUser==true){  
               // mm.mp.seekTo(progress);  
			}
		}
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			atChange = false;
			mnChange = true;	
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			int progress = sb.getProgress();
			if(!atChange && mnChange) {
				int mMax = mm.mp.getDuration();
				int sBM = sb.getMax();
				
				mm.mp.seekTo(mMax * progress / sBM);
				mm.play(progress, path);
				atChange = true;
				mnChange = false;
			} 	
		}
	}
    	
    private final class starttt implements OnClickListener {

		public void onClick(View v) {
			file = filename.getText().toString();
			// TODO Auto-generated method stu
			File audio = new File(Environment.getExternalStorageDirectory(), file);
    		if(audio.exists()) {
    			path = audio.getAbsolutePath();
    			mm.play(0, path);
    			handler.post(updateThread); 		
    		} else {
    			path = null;
    			Toast.makeText(getApplicationContext(), R.string.noexist, 1).show();	
    		}	 
			 text.setText("Playing");	  
		}
    }
    
    
    private final class pauseee implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			mm.pause();
			if(change) {
				text.setText("Pausing");
				change = !change;
			} else {
				text.setText("Playing");
				change = !change;
			}
		}	
    }
    
    private final class resettt implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			mm.reset();
			text.setText("Playing");
		}
    }
    private final class endddd implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			mm.stop();
			 text.setText("Stop");
			 //handler.removeCallbacks(updateThread);
		}	
    }
    
    private final class quittt implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			 handler.removeCallbacks(updateThread);
			mm.onDestroy();
		}
    	
    }
   
}