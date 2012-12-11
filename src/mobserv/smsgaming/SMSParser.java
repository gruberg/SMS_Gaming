package mobserv.smsgaming;

import java.util.ArrayList;

import android.util.Log;

public class SMSParser {

	ArrayList<Group> groups = new ArrayList<Group>();
	
	SMSParser() {
		groups = null;
		SMSReceiver.setParser(this);
	}
	
	SMSParser(ArrayList<Group> groups) {
		this.groups = groups;
		SMSReceiver.setParser(this);
	}
	
	/**
	 * This method is called by SMSReceiver when messages are incoming
	 * @param msg : String containing one or more SMS
	 */
	public void parse(String msg){
		Log.i("SMSParser", msg);
	}
}
