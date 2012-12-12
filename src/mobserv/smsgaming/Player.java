package mobserv.smsgaming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Player instance allows to represent any player
 * of the game (user or not), and access to
 * any data associated to it.
 */
public class Player {

	Context context;
	String name;
	String phone_number;
	boolean isUser;
	HashMap<String,Integer> scores = new HashMap<String, Integer>();
	String separator = "_;_";

	
	Player(Context context) {
		this.context = context;
		name = "";
		phone_number = "";
		isUser = false;
	}
	
	Player(Context context,String name, String phone_number, boolean isUser) {
		this.context = context;
		this.name = name;
		this.phone_number = phone_number;
		this.isUser = isUser;
	}

	public String getName() {
		return name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public boolean isUser() {
		return isUser;
	}

	public int getScore(String group_name){
		return this.scores.get(group_name);
	}
	public void setScore(String group_name, Integer score) {
		this.scores.put(group_name, score);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	
	/**
	 * Use this method so that game data reflects
	 * the completion of a challenge by the user.
	 * @param group : instance of group corresponding to the completed challenge.
	 * @param challenge : instance of challenge completed by the player.
	 */
	public void challengeCompleted(Group group, Challenge challenge){
	
		Set<String> voidset = new HashSet<String>();
		voidset.add("null");
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		Set<String> set_p_prov = preferences.getStringSet(challenge.getGroupname()+separator+"P", voidset);
		if (set_p_prov!=voidset) {
			set_p_prov.remove(name+separator+phone_number+separator+isUser+separator+getScore(challenge.getGroupname()));
			set_p_prov.add(name+separator+phone_number+separator+isUser+separator+(getScore(challenge.getGroupname())+challenge.getValue()));
			editor.putStringSet(challenge.getGroupname()+separator+"P", set_p_prov);			
			editor.commit();
		}
		Set<String> set_c_prov = preferences.getStringSet(challenge.getGroupname()+separator+"C", voidset);
		if (set_c_prov!=voidset) {
			//System.out.println(set_c_prov.size());
			set_c_prov.remove(challenge.getObjective()+separator+challenge.getValue()+separator+"false");
			//System.out.println(set_c_prov.size());
			set_c_prov.add(challenge.getObjective()+separator+challenge.getValue()+separator+"true");
			//System.out.println(set_c_prov.size());
			editor.putStringSet(challenge.getGroupname()+separator+"C", set_c_prov);
			editor.commit();
		}
		challenge.setCompleted(true);
		setScore(challenge.getGroupname(),scores.get(challenge.getGroupname())+challenge.getValue());
	}

	public void newGroup(Group group) {
		this.setScore(group.getName(),0);
	}
	
	public String toString() {
		String ret = name+" - "+phone_number+" - "+isUser()+"\n";
		for (String group_name : scores.keySet()) {
			ret += "\t "+group_name+" : "+getScore(group_name)+"pts\n";
		}
		return ret;
	}
	
}
