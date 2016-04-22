package Lab08.stella.lzr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class mySQLiteOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	private static final String DB_NAME = "mylist.db";
	private static final String TABLE_NAME = "mylist";
	
	private static final String SQL_CREATE_TABLE =  "create table " + TABLE_NAME + " (_id integer primary key autoincrement," + " name text not null, number text);";
	
	
	public mySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	public mySQLiteOpenHelper(Context context, String name, int version) {
		super(context, name, null, version);
		}

	public mySQLiteOpenHelper(Context context, String name) {
		this(context, name, VERSION);
		}
	
	public mySQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("out", "creat a SQLiteDatabase");
		db.execSQL("create table mylist(id int, name varchar(20), number varchar(20))");
		//"create table mylist(id int, name varchar(20), number varchar(20))"
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("out", "upDate a SQLiteDatabase");
	}

}
