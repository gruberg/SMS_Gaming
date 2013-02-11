package mobserv.smsgaming;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Provides methods to see if a given challenge has been completed
 * @author lolo
 *
 */
public class SMSParser {

	ArrayList<Group> groups = new ArrayList<Group>();
	
	SMSParser() {
		groups = null;
		SMSReceiver.setParser(this);
	}
	
	SMSParser(ArrayList<Group> groups) {
		this.groups = groups;
		Log.d("SMSParser", "instantiated");
		SMSReceiver.setParser(this);
	}
	
	/**
	 * Looks through the SMS inbox for messages containing
	 *  <code>chall.objective</code>.
	 * 
	 * @param chall	challenge we're looking for
	 * @param act	activity the method was called from
	 * @param lastT	timestamp of the last search, 0 if first
	 * @return		first matching SMS, null if nothing found
	 */
	public String searchSMS(Challenge chall, Activity act, long lastSearch){
		String[] projection = {"person","date","body"};
		Cursor cursor = act.getContentResolver().query(Uri.parse("content://sms/inbox"), projection, null, null, null);
		long timestamp;
		
		cursor.moveToFirst();

		do{
		   String msgData = cursor.getString(2);
		   timestamp = cursor.getLong(1);
		   //for(int idx=0;idx<cursor.getColumnCount();idx++) //generic case
		   Log.d("SMSParser", msgData);
		   if (msgData.contains(chall.objective) && (timestamp > lastSearch)){
		       Log.d("SMSParser", "Challenge completed : "+msgData);
		       return msgData;
		   }		   
		}while(cursor.moveToNext() && (timestamp > lastSearch));
		return "No matching sms !!";
	}
	/**
	 * wrapper for searchSMS without timestamp
	 * 
	 * @param chall
	 * @param act
	 * @return
	 * @deprecated Add a timestamp as 3rd parameter
	 */
	@SuppressLint("UseValueOf")
	public String searchSMS(Challenge chall, Activity act){
		return searchSMS(chall, act, Long.parseLong("1355226757372"));
	}
	/**
	 * Looks through the SMS inbox for messages containing <code>obj</code>.
	 * 
	 * @param obj	string we're looking for
	 * @param act	activity the method was called for
	 * @return		first matching SMS
	 */
	public String searchSMS(String obj, Activity act){
		return searchSMS(
				new Challenge(act, obj, 0, null, false),
				act);
	}
	
	/**
	 * This method is called by SMSReceiver when messages are incoming
	 * Currently only printing the SMS
	 * 
	 * @param msg : String containing one or more SMS
	 */
	@Deprecated
	public void parse(String msg){
		Log.i("SMSParser", msg);
		try{
			//groups.get(0).getUser().challengeCompleted(groups.get(0), groups.get(0).getChallenges().get(0));
			//groups.get(0).getChallenges().get(0).setCompleted();
		}
		catch (java.lang.NullPointerException e){
			Log.e("SMSParser", "I don't know where the 'groups' is !!!");
		}
	}
}
