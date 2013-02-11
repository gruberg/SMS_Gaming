package mobserv.smsgaming;


import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Challenge instance allows to represent any challenge
 * of the game OF THE USER, and access to
 * any data associated to it (which group it corresponds to,
 * if it is completed or not, ..).
 */
public class Challenge {

	String objective;
	int value;
	String groupname;
	boolean completed;
	String separator = "_;_";
	Context context;
	int lastSearch;
	
	Challenge(Context context) {
		objective = "";
		value = 0;
		groupname = "";
		completed = false;
		this.context = context;
	}
	
	Challenge(Context context, String objective, int value, String groupname, boolean completed) {
		this.objective = objective;
		this.value = value;
		this.groupname = groupname;
		this.completed = completed;
		this.context = context;
		this.lastSearch = 0;
	}
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getObjective() {
		return objective;
	}
	public int getValue() {
		return value;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted() {
		this.completed = true;
		Set<String> voidset = new HashSet<String>();
		voidset.add("null");
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		Set<String> set_c_prov = preferences.getStringSet(this.getGroupname()+separator+"C", voidset);
		if (set_c_prov!=voidset) {
			//System.out.println(set_c_prov.size());
			set_c_prov.remove(this.getObjective()+separator+this.getValue()+separator+"false");
			//System.out.println(set_c_prov.size());
			set_c_prov.add(this.getObjective()+separator+this.getValue()+separator+"true");
			//System.out.println(set_c_prov.size());
			editor.putStringSet(this.getGroupname()+separator+"C", set_c_prov);
			editor.commit();
		}
	}
	
	public String toString(){
		String ret = objective+" ("+isCompleted()+") - "+value+"pts ("+groupname+")\n";
		return ret;
	}

	
}
