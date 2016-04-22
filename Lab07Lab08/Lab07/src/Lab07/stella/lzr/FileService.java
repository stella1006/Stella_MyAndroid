package Lab07.stella.lzr;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class FileService {
	/**
	 * 保存文件
	 * @param fileName文件名称
	 * @param context文件内容
	 */
	private Context con;
	
	public FileService(Context con) {
		super();
		this.con = con;
	}

	public void save(String fileName, String context) throws Exception {
		// TODO Auto-generated method stub
		//IO j2se
		FileOutputStream outStream = con.openFileOutput(fileName, con.MODE_PRIVATE);
		outStream.write(context.getBytes());
		outStream.close();
	}
	/**
	 * 读取文件内容
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public String read(String filename) throws Exception {
		FileInputStream inStream = con.openFileInput(filename);
		ByteArrayOutputStream  outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		
		return new String(data);
	}
	
	public void delete(File filename) throws Exception {
		
			filename.delete();
			Log.e("delete", "success");
			
		
		
	}

}
