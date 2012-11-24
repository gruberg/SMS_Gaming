package mobserv.smsgaming;

import java.util.ArrayList;

public class Group {
	
	String name;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Challenge> challenges = new ArrayList<Challenge>();
	
	Group() {
		name = "";
	}
	
	Group(String name){
		this.name = name;
	}
	
	Group(String name, ArrayList<Player> players, ArrayList<Challenge> challenge){
		this.name = name;
		this.players = players;
		this.challenges = challenge;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Challenge> getChallenges() {
		return challenges;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setChallenges(ArrayList<Challenge> challenges) {
		this.challenges = challenges;
	}
	
	void addPlayer(Player player) {
		this.players.add(player);
	}
	
	void removePlayer(Player player) {
		this.players.remove(player);
	}
	
}
