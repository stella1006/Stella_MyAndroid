package Lab07.stella.lzr;

import java.io.File;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Second extends Activity {
	private AutoCompleteTextView autoFilename;
	String[] nameStrings;
	Button save;
	Button read;
	Button delete;
	FileService service;
	// private String[] ary = new String[]{"abc", "abcd"};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second);;
	    
 // TODO Auto-generated method stub
	    
	    autoFilename = (AutoCompleteTextView)findViewById(R.id.Auto);
	    nameStrings = this.fileList();
	   
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, nameStrings);
	    Log.e("auto", "auto");
	    autoFilename.setAdapter(adapter);
        //设置输入多少字符后提示，默认值为2
	    autoFilename.setThreshold(2);
	    Log.e("auto", "auto2");
	    service = new FileService(getApplicationContext());
	    
	    save = (Button)findViewById(R.id.save);
	    read = (Button)findViewById(R.id.read);
	    delete = (Button)findViewById(R.id.delete);
	    		
	    save.setOnClickListener(new saveOnClickListener());
	    read.setOnClickListener(new readOnClickListener());
	    delete.setOnClickListener(new deleteOnClickListener());
	}

	
	public final class saveOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText text1 = (EditText)findViewById(R.id.Auto);
			EditText text2 = (EditText)findViewById(R.id.context);
			String fileName = text1.getText().toString() + ".txt";
			String context = text2.getText().toString();
			
			try {
				Toast.makeText(getApplicationContext(), R.string.savesuccess, 1).show();	
				service.save(fileName, context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.savefail, 1).show();	
			}		
			
		}
		
	}
	
	public class readOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText text3 = (EditText)findViewById(R.id.Auto);
			EditText text4 = (EditText)findViewById(R.id.context);
			String fileName3 = text3.getText().toString();
			try {
				service.read(fileName3);
				text4.setText(service.read(fileName3));
				Toast.makeText(getApplicationContext(), R.string.readsuccess, 1).show();	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.readfail, 1).show();
			}
		}
		
	}
	
	public class deleteOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText text5 = (EditText)findViewById(R.id.Auto);
			
			String fileName5 = text5.getText().toString();
			File file = new File("/data/data/" + getPackageName().toString()  
		            + "/files", fileName5);
			try {
				service.delete(file);
				Toast.makeText(getApplicationContext(), R.string.deletesuccess, 1).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.deletefail, 1).show();
			}
		}
		
	}
}
