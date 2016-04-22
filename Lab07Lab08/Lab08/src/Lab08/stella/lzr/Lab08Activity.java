package Lab08.stella.lzr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Lab08Activity extends Activity {
    /** Called when the activity is first created. */
	private Button addBt;
	private ListView listView;
	private ArrayList<Map<String, Object>> list = null;
	Context con = this;
	private SimpleAdapter adapter = null;
	private HashMap<String, Object> map;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> numbers = new ArrayList<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addBt = (Button)findViewById(R.id.button);
        addBt.setOnClickListener(new BTOnClickListener());
        Log.i("check", "aaa");
        listView = (ListView)findViewById(R.id.listView);
        Log.i("check", "aaa11");
        mySQLite();
        Log.i("check", "aaa33");
        
    }

    public class BTOnClickListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent();
			intent.setClass(Lab08Activity.this, Add.class);
			startActivity(intent);
			
		}
    	
    }
	private void mySQLite() {
		// TODO Auto-generated method stub
		
		Log.i("check", "b1");
		mySQLiteOpenHelper helper = new mySQLiteOpenHelper(con, "mylist");
		SQLiteDatabase db = helper.getReadableDatabase();
		Log.i("check", "b2");
		//查询数据库里的数据
		Cursor cursor = db.query("mylist", new String[] {"name", "number" }, null, null, null, null, null);
		Log.i("check", "b22");
		list = new ArrayList<Map<String, Object>>();
		Log.i("check", "b23");
		
		while(cursor.moveToNext()) {
			People modle = new People();
			map = new HashMap<String, Object>();
			map.put("name", modle.setName(cursor.getString(cursor
					.getColumnIndex("name"))).toString());
			map.put("number", modle.setNumber(cursor.getString(cursor
					.getColumnIndex("number"))).toString());
			list.add(map);
		}
		Log.i("check", "b3");
		adapter = new MYSimpleAdapter(Lab08Activity.this, list, R.layout.main, new String[] {"name", "number"}, new int[] {R.id.name, R.id.number});
		listView.setAdapter(adapter);
		db.close();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int positon,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Lab08Activity.this, Edit.class);
				Bundle bundle = new Bundle();
				//这个地方通过名字传值来判断数据的话其实不太好，大家可以通过ID     //来判断是哪条数据的，这里传值是为了另一个类中需要删除数据
				bundle.putString("name", list.get(positon).get("name").toString());
				bundle.putString("number", list.get(positon).get("number").toString());
				intent.putExtras(bundle);
				intent.setClass(Lab08Activity.this, Edit.class);
				startActivity(intent);		
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String dName = list.get(arg2).get("name").toString();
				String dNumber = list.get(arg2).get("number").toString();
				
				
				
				mySQLiteOpenHelper helper = new mySQLiteOpenHelper(Lab08Activity.this,
						"mylist");
				SQLiteDatabase db = helper.getWritableDatabase();
						
				Cursor cursor = db.query("mylist",
							new String[] { "name", "number" }, "name=?",
								new String[] { dName }, null, null, null);
				db.delete("mylist", "name=?", new String[] { dName });
				list.remove(arg2);
				db.close();
				adapter.notifyDataSetChanged(); 
				return true;
			}
			
			
			
		});
	}
	
	// TODO Auto-generated method stub
	 private class MYSimpleAdapter extends SimpleAdapter {
	public MYSimpleAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}
	Context context;
	private LayoutInflater inflater = getLayoutInflater();
	
	public  View getView( final int  position, View convertView, ViewGroup parent) {
		Log.i("hhh", "hhh");
		
    	convertView = inflater.inflate(R.layout.itemm, null);
    	Log.i("hhh", "hhh22");
    	TextView t = (TextView)(convertView.findViewById(R.id.name_id));
    	Log.i("hhh", "hhh223");
    	t.setText(list.get(position).get("name").toString()); 
    	Log.i("hhh", "hhh224");
    	TextView t2 = (TextView)(convertView.findViewById(R.id.class_id));
    	Log.i("hhh", "hhh2245");
    	t2.setText(list.get(position).get("number").toString()); 
    	Log.i("hhh", "hhh2255");
   	
   	
    	return convertView;

   } 

	/*public boolean onCreateOptionsMenu(Menu menu) {
	menu.add(0, 1, 1, "添加");
	return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case 1:
	Intent intent = new Intent();
	intent.setClass(Lab08Activity.this, Add.class);
	startActivity(intent);
	break;
	}
	return super.onOptionsItemSelected(item);
	}*/

	 }
}