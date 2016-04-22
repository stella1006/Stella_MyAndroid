package EX03homework.stella.lzr;


import java.util.ArrayList;
import java.util.List;



import android.view.View;
import android.view.View.OnClickListener;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.MarginLayoutParams;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EX03homeworkActivity extends Activity {
    /** Called when the activity is first created. */

	private EditText test;
	private Context con;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        con = this;
        /**
         * 动态添加编辑文本框
         */
        test = new EditText(con);
        test.setText("");
        test.setId(11); 
        /**
         * 用TableLayout动态添加拨号键盘
         */
        TableLayout tab = new TableLayout(this);
        tab.setStretchAllColumns(false);
        List<String> list = new ArrayList<String>();
        /**
         * 将键盘中的数字或字符添加到list数组中
         */
        for(int i=1; i < 13; i++)
        {
        	if(i < 10) list.add(""+i);
        	else if(i == 10) list.add("*");	
        	else if(i == 11) list.add("0");		
        	else list.add("#"); 		
        }

        tab.addView(test);//为tab添加View对象     
        int size = list.size();
        int display = 3;
        int total = (size + display - 1) / display;
        List<String> tmp = null;
        /**
         * 将list中的字符排布到table中
         */
        for(int i = 0; i < total; i++)
        {
        	TableRow row = new TableRow(this);
        	if(i==0) {
        		if(size < display) {
        			tmp = list;
        		}else{
        			tmp = list.subList(0, display);
        		}
        	
        /**
         * 为每个table中的位置添加Button，并设置监听事件
         */
        	for(int x = 0; x < tmp.size(); x++){
        		Button Btn = new Button(this);
        		Btn.setText(tmp.get(x));
        		ButtonListener bnl = new ButtonListener();
        		Btn.setOnClickListener(bnl);   		
        		row.addView(Btn);
     	}
        	
        }else{
        	if(size - i * display < display) {
        		tmp = list.subList(i*display, size-1);
        		}else{
				tmp = list.subList(i*display, i*display+display);
			}
        	for(int x = 0; x < tmp.size(); x++) {
        		Button Btn = new Button(this);	
        		Btn.setText(tmp.get(x));
        		Btn.setWidth(LayoutParams.WRAP_CONTENT);
        		ButtonListener bnl = new ButtonListener();
        		int id = Btn.getId();
        		Btn.setOnClickListener(bnl);    		
        		row.addView(Btn);  		
        		}
        	}
        	tab.addView(row);     	
        }
        setContentView(tab);       
    }
    
    public final class ButtonListener implements View.OnClickListener {
    	public void onClick(View v) {
    		//当点击键盘Button时，将字符添加到编辑文本框中
    		test.setText(test.getText().toString() + ((Button)v).getText().toString());    		
    	}
    	
    }
}