package mobserv.smsgaming;

import java.util.ArrayList;

public class Player {

	String name;
	String phone_number;
	boolean isUser;
	int score;
	
	Player() {
		name = "";
		phone_number = "";
		isUser = false;
		score = 0;
	}
	
	Player(String name, String phone_number, boolean isUser, int score) {
		this.name = name;
		this.phone_number = phone_number;
		this.isUser = isUser;
		this.score = score;
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

	public int getScore() {
		return score;
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

	public void setScore(int score) {
		this.score = score;
	}
}
