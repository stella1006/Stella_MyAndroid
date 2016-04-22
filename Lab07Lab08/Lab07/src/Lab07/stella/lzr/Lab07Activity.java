package Lab07.stella.lzr;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Lab07Activity extends Activity {
    /** Called when the activity is first created. */
	private EditText zT;
	private EditText mT;
	private CheckBox cb;
	private PreferencesService service;
	private Button bt;
	private boolean checked;
	private AutoCompleteTextView autoFilename;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Context con;
		con = this;
        zT = (EditText) this.findViewById(R.id.zhanghao);
        mT = (EditText) this.findViewById(R.id.mima);
        bt = (Button)findViewById(R.id.login);
        //bt.setOnClickListener(new ButtonClickListener());
        
        cb = (CheckBox) this.findViewById(R.id.checkBox);
        
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(isChecked){ 
                    //cb.setChecked(false);
                	Log.e("click", "true");
                    isChecked = false;
                    
                    System.out.println(cb.isChecked());
                }else{ 
                	//cb.setChecked(true);
                	Log.e("click", "false");
                	 isChecked = true;
                	
                	 System.out.println(cb.isChecked());
                } 
            } 
        }); 
        
        service = new PreferencesService(this);
        Map<String, String> params = service.getpreferences();
        zT.setText(params.get("zhanghao"));
        mT.setText(params.get("mima"));
    }
    
    /*public class ButtonClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String zhanghao11 = zT.getText().toString();
			String mima11 = zT.getText().toString();
			
			if(zhanghao11.equals("android") && mima11.equals("android")) {
				Intent intent = new Intent();
				intent.setClass(Lab07Activity.this, Second.class);
				startActivity(intent);
				Lab07Activity.this.finish();
			}
			else {
				
				Toast.makeText(Lab07Activity.this, R.string.fail, 1).show();
			}
		}
    	
    }*/
    
    public void save1(View v) {
    	
    	String zhanghao;
    	String mima;
    	 System.out.println(cb.isChecked());
    	 System.out.println(checked);
    	if(cb.isChecked()) {
    		zhanghao = zT.getText().toString();
    		mima = mT.getText().toString();
    		service.save(zhanghao, mima);
    		Log.e("check", "save");
    	} else {
    		zhanghao = "";
    		mima = "";
    		service.save(zhanghao, mima);
    		Log.e("check", "nosave");
    	}
    	
    	Toast.makeText(this, R.string.success, 1).show();
    	String zhanghao11 = zT.getText().toString();
    	String mima11 = zT.getText().toString();
    	
    	if(zhanghao11.equals("android") && mima11.equals("android")) {
    		Intent intent = new Intent();
    		intent.setClass(Lab07Activity.this, Second.class);
    		startActivity(intent);
    		Lab07Activity.this.finish();
    	}
    	else {
    		
    		Toast.makeText(Lab07Activity.this, R.string.fail, 1).show();
    	}
    }
    
    
}
    

    
