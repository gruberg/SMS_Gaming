package mobserv.smsgaming;

import java.util.ArrayList;

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
		SMSReceiver.setParser(this);
	}
	
	/**
	 * Looks through the SMS inbox for messages containing
	 *  <code>chall.objective</code>.
	 * 
	 * @param chall	challenge we're looking for
	 * @param act	activity the method was called from
	 * @return		first matching SMS
	 */
	public String searchSMS(Challenge chall, Activity act){
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
		   if (msgData.contains(chall.objective))
			   return msgData;
		}while(cursor.moveToNext());
		return "";
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
