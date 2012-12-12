package mobserv.smsgaming;


import android.content.Context;

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
	
	Challenge(Context context) {
		objective = "";
		value = 0;
		groupname = "";
		completed = false;
		this.context = context;
	}
	
	Challenge(Context context, String objective, int value, String groupname) {
		this.objective = objective;
		this.value = value;
		this.groupname = groupname;
		this.completed = false;
		this.context = context;
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

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public String toString(){
		String ret = objective+" ("+isCompleted()+") - "+value+"pts ("+groupname+")\n";
		return ret;
	}

	
}
