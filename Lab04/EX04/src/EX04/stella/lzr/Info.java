package EX04.stella.lzr;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class Info extends Activity {
	private static final String MYTAG = "MytagTest";
	
	
	private Button button;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.hahaha);
	    // TODO Auto-generated method stub
	    
	    button = (Button)findViewById(R.id.button2);
	    
	    Intent intent2 = getIntent();
		Bundle bundle1 = intent2.getExtras();
		
		String name = bundle1.getString("name");
		String class1 = bundle1.getString("class");
		((TextView)findViewById(R.id.two)).setText(name + "同学是来自" + class1 + "班级的");
		
		
	    
	    button.setOnClickListener(new OnClickListener() {
	    	public void onClick(View v){
	    		
	    		Intent intent1 = new Intent();
	    		intent1.setClass(Info.this, EX04Activity.class);
	    		
	    		startActivity(intent1);
	    		Info.this.finish();
	    		
	    	}
	    	
	    });
	    
	    		}

}
