package Lab10.stella.lzr;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.util.Log;

public class translatorString {
	
	private static final String NAMESPACE = "http://WebXml.com.cn/";
	private static final String URL = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx";
	private static final String OPERATION_NAME = "TranslatorSentenceString";
	private static final String REQUEST_PARA_NAME = "wordKey";
	private static final String SOAP_ACTION = "http://WebXml.com.cn/TranslatorSentenceString";
	private static final String RESPONSE_PROP_NAME = "TranslatorSentenceStringResult";
	private ProgressDialog mProgressDialog;
	
	public static String searchPlace(String word) {
		Log.i("hhh", "11");
		Log.i("hhh333", word);
		SoapObject soap = new SoapObject(NAMESPACE, OPERATION_NAME);
		soap.addProperty(REQUEST_PARA_NAME, word);
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
		Log.i("hhh", "12");
			
		//soapEnvelope.bodyOut = soap;
		soapEnvelope.dotNet = true;
		soapEnvelope.setOutputSoapObject(soap);
		Log.i("hhh", "13");
		HttpTransportSE transportSE = new HttpTransportSE(URL);
		
		try {Log.i("hhh", "14");
				transportSE.call(SOAP_ACTION, soapEnvelope);
				
				Log.i("hhh", "15");
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
		SoapObject bodyIn = (SoapObject)soapEnvelope.bodyIn;
		SoapObject result = (SoapObject)bodyIn.getProperty(RESPONSE_PROP_NAME);
		
		Log.i("hhh", bodyIn.toString());
		Log.i("hhh", result.toString());
			return ( result.toString());
	//.getPropertyAsString(3)
	
	}

}
