package Lab08.stella.lzr;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Add extends Activity {

	/** Called when the activity is first created. */
	private EditText nameedit,ageedit,teledit;
	private Button queding,quxiao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.edit);
	
	nameedit=(EditText)findViewById(R.id.name);
	teledit=(EditText)findViewById(R.id.number);
	queding=(Button)findViewById(R.id.Yes);
	quxiao=(Button)findViewById(R.id.No);
	queding.setOnClickListener(new OnClickListener() {

	public void onClick(View v) {
	String name=nameedit.getText().toString();
	String tel=teledit.getText().toString();
	Log.i("check", "ccc");
	
	ContentValues values = new ContentValues();
	values.put("name", name);
	values.put("number", tel);
	Log.i("check", "ccc2");
	
	mySQLiteOpenHelper helper = new mySQLiteOpenHelper(Add.this, "mylist");
	SQLiteDatabase db = helper.getWritableDatabase();
	db.insert("mylist", null, values);
	db.close();
	
	if(db==null) {
		Log.i("che", "null");
	}
	Log.i("check", "ccc3");
	Intent intent=new Intent();
	intent.setClass(Add.this, Lab08Activity.class);
	startActivity(intent);
	finish();
	}

	
	});
	
	quxiao.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	});
	}

}