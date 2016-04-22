package Lab09.stella.lzr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lab09Activity extends Activity {
    /** Called when the activity is first created. */
	private Button bt = null;
	private EditText tel = null;
	private TextView address = null;
	private String baseUrl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
	private HttpEntity httpEntity = null;
	private Thread threadMobile = null;
	private String number;
	
	 private Handler mHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if(msg.what == 1) {
					address.setText(filterHtml((String)msg.obj));  
				}										
			};
		};
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tel = (EditText)findViewById(R.id.tel);
        bt = (Button)findViewById(R.id.button);
        address = (TextView)findViewById(R.id.address);
        bt.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				number = tel.getText().toString();
				if(number.equals("") || number.length() < 7) {
					Toast.makeText(getApplicationContext(), "Telephone Numbers are wrong", 1).show();
					tel.setText("");
					return;
				}
				threadMobile = new Thread(runnable);
				threadMobile.start();	
			}	
        });
    }
    
    
    Runnable runnable = new Runnable() {
		
		public void run() {
			// TODO Auto-generated method stub
		
			
			HttpClient httpClient = new DefaultHttpClient();
		    HttpPost post = new HttpPost(baseUrl);
		    List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("mobileCode", number));
		    params.add(new BasicNameValuePair("userId", ""));
		  
			try {
			
				post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				HttpResponse httpResponse = httpClient.execute(post);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
	                // 解析返回的内容  
	                String result = EntityUtils.toString(httpResponse.getEntity());  
	                // 将查询结果经过解析后显示在TextView中  
	                mHandler.obtainMessage(1,result).sendToTarget();
	            }  
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
	};
	
   
    public String filterHtml(String result) {
   	 
		// TODO Auto-generated method stub
		if(null == result){  
            return "";  
        }  
        return result.replaceAll("</?[^>]+>","").trim();  
		
	}
   /* private static String pareseXml(InputStream responseStream) throws Exception {  

        XmlPullParser parser = Xml.newPullParser();  
        parser.setInput(responseStream, "UTF-8");      
        int event = parser.getEventType();    
        while(event != XmlPullParser.END_DOCUMENT){  
            switch(event){  
            case XmlPullParser.START_TAG:  
                if("getMobileCodeInfoResult".equals(parser.getName())){  
                    return parser.nextText();  
                }  
                break;  
            }  
            event = parser.next();  
        }  
        return null;  
    }*/
}