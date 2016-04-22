package EX04.stella.lzr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class EX04Activity extends Activity {
	private static final String MYTAG = "MytagTest";
    /** Called when the activity is first created. */
	
	List<Map<String, String>> mDataList ;
	Map<String, String> mMap ;
	ListView mListView;
	SimpleAdapter MYSimpleAdapter;
	MYSimpleAdapter mmSimpleAdapter;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListView = (ListView) findViewById(R.id.mlist);
        mDataList = new ArrayList<Map<String, String>>();
        mMap = new HashMap<String, String>();
        mmSimpleAdapter = new  MYSimpleAdapter(this); 
        mListView.setAdapter(mmSimpleAdapter); 							
        }
    	
       private class MYSimpleAdapter extends SimpleAdapter {
    	   Context context;
    	   private LayoutInflater inflater = getLayoutInflater();
    	   public MYSimpleAdapter(Context context){
    		 super(context, mDataList, 0, null, null);
    		
    	    inflater = LayoutInflater.from(context); 
    		mMap.put("name", "赵一");
      		mMap.put("class", "嵌软");
      		mDataList.add(mMap);
      		
      		mMap = new HashMap<String, String>();
      		mMap.put("name", "钱二");
      		mMap.put("class", "数媒");
      		mDataList.add(mMap);
      		
      		mMap = new HashMap<String, String>();
      		mMap.put("name", "孙三");
      		mMap.put("class", "电政");
      		mDataList.add(mMap);

      		mMap = new HashMap<String, String>();
      		mMap.put("name", "李四");
      		mMap.put("class", "电政");
      		mDataList.add(mMap);
      		
      		mMap = new HashMap<String, String>();
      		mMap.put("name", "王五");
      		mMap.put("class", "计应");
      		mDataList.add(mMap);
    		
    		  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		        	Intent intent = new Intent();
    					
    		        	intent.setClass(EX04Activity.this, Info.class);
    					Bundle bundle = new Bundle();
    					bundle.putString("name", mDataList.get(position).get("name").toString());
    					bundle.putString("class", mDataList.get(position).get("class").toString());
    					intent.putExtras(bundle);
    					startActivity(intent);
    					EX04Activity.this.finish();
    		        
    		        }
    		  });
    		
    	  }
    	  
    	   public Object getItem(int arg0) { 
               // TODO Auto-generated method stub 
               return arg0; 
           } 
    	   
    	   public int getCount() { 
               // TODO Auto-generated method stub 
               return mDataList.size(); 
           } 
    	   
    	   public long getItemId(int arg0) { 
               // TODO Auto-generated method stub 
               return arg0; 
           } 
    	   
    	   public  View getView( final int  position, View convertView, ViewGroup parent) {
    
    		// TODO Auto-generated method stub
    		
            	convertView = inflater.inflate(R.layout.item, null);
            	
           	ImageButton Btn;
            Btn = (ImageButton)convertView.findViewById(R.id.ivLogo1);
            
            if(Btn == null)
           		Log.e(MYTAG, "nullll");

            Btn.setTag(position);  
           	Btn.setOnClickListener(new View.OnClickListener() {	
           		public void onClick(View v) {
           			mDataList.remove(position);  
           			mmSimpleAdapter.notifyDataSetChanged(); 
           			Log.e(MYTAG, "5");  	
           		}
           	});
           	
            TextView t = (TextView)(convertView.findViewById(R.id.name_id));
		    t.setText(mDataList.get(position).get("name").toString()); 
		    TextView t2 = (TextView)(convertView.findViewById(R.id.class_id));
		    t2.setText(mDataList.get(position).get("class").toString()); 
            return convertView;
       
    	   }    
    	   public MYSimpleAdapter(Context context,
   				List<? extends Map<String, ?>> data, int resource,
   				String[] from, int[] to) {
   			super(context, data, resource, from, to);
   			// TODO Auto-generated constructor stub
   		}
    	   
       }
    	  
     

}