package Lab10.stella.lzr;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab10Activity extends Activity {
	
	private EditText wordd;
	private Button searchh;
	private TextView resultt;
	private Thread threadTranslator;
	private String word2;
	private Object answer;
	private ProgressDialog mProgressDialog;
	private Context con;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        wordd = (EditText)findViewById(R.id.word);
        searchh = (Button)findViewById(R.id.search);
        resultt = (TextView)findViewById(R.id.result);
        //mProgressDialog = new ProgressDialog(con);
        
        searchh.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				word2 = wordd.getText().toString();
				threadTranslator = new Thread(runnable);
				mProgressDialog = ProgressDialog.show(Lab10Activity.this, "Requesting", "Requesting...");
				threadTranslator.start();
				Log.i("hhh", "1");
				
			}
        	
        });
    }
    
    
    Runnable runnable = new Runnable() {
    	public void run() {
    		Log.i("hhh", "2");
    		answer = translatorString.searchPlace(word2);
    		//resultt.setText(answer.toString());
    		Log.i("hhh55", answer.toString());
			mHandler.obtainMessage(1, answer).sendToTarget();
			mProgressDialog.dismiss();
    	}
    };
    
    private Handler mHandler = new Handler() {
    	public void handleMessage(android.os.Message msg) {
    		Log.i("hhh", "3");
    		if(msg.what == 1) {
    			Log.i("hhh", "4");
    			resultt.setText(filterHtml((String)msg.obj));
    			Log.i("hhh", "5");
    		}
    	}
    };
    
    public String filterHtml(String result) {
      	 
		// TODO Auto-generated method stub
		if(null == result){  
            return "";  
        }  
        result =  result.replaceAll("anyType","").trim();  
       // result =  result.replaceAll("{","").trim(); 
        result =  result.replaceAll("string=","").trim();
        return result;
	}
    /*public boolean onCreateOptionMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
    	
    }*/
}