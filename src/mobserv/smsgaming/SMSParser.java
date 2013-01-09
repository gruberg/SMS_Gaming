package mobserv.smsgaming;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
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
	 * Will look through the SMS inbox for messages containing the target.
	 * 
	 * @param target : string to look for in the messages
	 * @param act : activity the method was called from
	 */
	public String searchSMS(String target, Activity act){
		String[] projection = {"person","date","body"};
		Cursor cursor = act.getContentResolver().query(Uri.parse("content://sms/inbox"), projection, null, null, null);
		cursor.moveToFirst();

		do{
		   String msgData = "";
		   for(int idx=0;idx<cursor.getColumnCount();idx++)
		   {
		       msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
		   }
		   Log.v("SMSParser", msgData);
		   if (msgData.contains(target))
			   return msgData;
		}while(cursor.moveToNext());
		return "";
	}
	
	/**
	 * This method is called by SMSReceiver when messages are incoming
	 * @param msg : String containing one or more SMS
	 */
	public void parse(String msg){
		Log.i("SMSParser", msg);
		try{
			groups.get(0).getUser().challengeCompleted(groups.get(0), groups.get(0).getChallenges().get(0));
			//groups.get(0).getChallenges().get(0).setCompleted();
		}
		catch (java.lang.NullPointerException e){
			Log.e("SMSParser", "I don't know where the 'groups' is !!!");
		}
	}
}
