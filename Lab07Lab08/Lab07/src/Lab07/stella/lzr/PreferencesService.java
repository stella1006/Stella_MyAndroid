package Lab07.stella.lzr;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	private Context con;

	public PreferencesService(Context con) {
		super();
		this.con = con;
	}
	
	public void save(String name, String mima) {
		SharedPreferences preferences = con.getSharedPreferences("infomation", con.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("zhanghao", name);
		editor.putString("mima", mima);
		editor.commit();
		
		
		
	}
	
	
	public Map<String, String> getpreferences() {
		Map<String, String> params = new HashMap<String, String>();
		SharedPreferences preferences = con.getSharedPreferences("infomation", con.MODE_PRIVATE);
		params.put("zhanghao", preferences.getString("zhanghao", ""));
		params.put("mima", preferences.getString("mima", ""));
		return params;
	}
}
