package Lab08.stella.lzr;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends Activity {

	/** Called when the activity is first created. */
	
	private EditText nameedit, ageedit, teledit;
	private Button queding, quxiao;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.edit22);

	Bundle bundle = this.getIntent().getExtras();
	name = bundle.getString("name");
	System.out.println("name" + name);
	nameedit = (EditText) findViewById(R.id.name2);
	teledit = (EditText) findViewById(R.id.number2);
	queding = (Button) findViewById(R.id.Yes2);
	quxiao = (Button) findViewById(R.id.No2);
	mySQLiteOpenHelper helper = new mySQLiteOpenHelper(Edit.this, "mylist");
	SQLiteDatabase db = helper.getReadableDatabase();
	Cursor cursor = db.query("mylist",
	new String[] { "name", "number" }, "name=?",
	new String[] { name }, null, null, null);
	
	while (cursor.moveToNext()) {
	People modle = new People();

	nameedit.setText(modle.setName(cursor.getString(cursor
	.getColumnIndex("name"))));
	
	teledit.setText(modle.setNumber(cursor.getString(cursor
	.getColumnIndex("number"))));
	}
	
	
	queding.setOnClickListener(new OnClickListener() {

	public void onClick(View v) {
	String name1 = nameedit.getText().toString();
	String tel = teledit.getText().toString();

	ContentValues values = new ContentValues();
	values.put("name", name1);
	values.put("number", tel);
	mySQLiteOpenHelper helper = new mySQLiteOpenHelper(Edit.this,
	"mylist");
	SQLiteDatabase db = helper.getWritableDatabase();
	db.update("mylist", values, "name=?", new String[] { name });
	db.close();
	Intent intent = new Intent();
	intent.setClass(Edit.this, Lab08Activity.class);
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