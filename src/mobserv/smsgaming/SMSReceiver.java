package mobserv.smsgaming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * SMSReceiver will be instantiated every time a new SMS is
 * received, which will be forwarded to an instance of SMSParser;
 * 
 */
public class SMSReceiver extends BroadcastReceiver {
	
	private static SMSParser parser;
	
	@Override
	public void onReceive(Context context, Intent intent) {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = "Message was empty :(";            
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            str = "";
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                str += "SMS from " + msgs[i].getOriginatingAddress();                     
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";        
            }
        }	
        //---display the new SMS message---
        Log.i("SMSReceiver", str);
        try{
        	parser.parse(str);
        }
        catch (java.lang.NullPointerException e){
        	Log.e("SMSReceiver", "I don't know where the parser is !");
        }
	}
	
	/**
	 * Use this method to define the instance of SMSParser the SMS
	 * should be sent to through parse(message).
	 * @param _parser : instance of SMSParser
	 */
	protected static void setParser(SMSParser _parser){
		SMSReceiver.parser = _parser;
	}

}
