package mobserv.smsgaming;

public class Challenge {

	String objective;
	int value;
	
	Challenge() {
		objective = "";
		value = 0;
	}
	
	Challenge(String objective, int value) {
		this.objective = objective;
		this.value = value;
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
	
}
