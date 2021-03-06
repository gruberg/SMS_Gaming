package mobserv.smsgaming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Group instance allows to represent any group
 * of the game, and access to
 * any data associated to it (players, challenges,...)
 */
public class Group {
	
	String name;
	String bet;
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Challenge> challenges = new ArrayList<Challenge>();
	
	Group() {
		name = "";
	}
	
	Group(String name){
		this.name = name;
	}
	
	Group(String name, ArrayList<Player> players, ArrayList<Challenge> challenge, String bet){
		this.name = name;
		this.players = players;
		this.challenges = challenge;
		this.bet = bet;
	}

	public String getName() {
		return name;
	}
	
	public String getBet() {
		return bet;
	}
	
	public void setBet(String _bet) {
		this.bet = _bet;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<String> getPlayerNumbers(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("+33633975228");
		list.add("0633975228");
		return list;
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
	
	void addChallenge(Challenge challenge) {
		//TO COMPLETE!!
		this.challenges.add(challenge);
	}
	
	void removeChallenge(Challenge challenge) {
		this.challenges.remove(challenge);
	}
	
	public Player getUser(){
		Player ret = null;
		for (Player player : players) {
			if (player.isUser()) ret = player;
		}
		return ret;
	}
	
	public String getPositionStr(Player player){		
		int ret = 1;
		String pos = "";
		for (Player p: players) {
			if ((p!=player)&&(p.getScore(getName())>player.getScore(getName()))) {
				ret += 1;
			}
		}
		
		if (ret == 1) {pos = Integer.toString(ret) + "st";}
		else if (ret == 2) {pos = Integer.toString(ret) + "nd";}
		else {pos = Integer.toString(ret) + "rd";}
		return pos;
	}
	
	public int getPosition(Player player){		
		int ret = 1;
		for (Player p: players) {
			if ((p!=player)&&(p.getScore(getName())>player.getScore(getName()))) {
				ret += 1;
			}
			
		}
		return ret;
	}
	public void orderPlayers() {
		Collections.sort(players, new RankComparator());
	}
	
	
	public String toString(){
		String ret = "-----------------\n";
		
		ret += this.name+"\n";

		for (Player player : players) {
			ret += "\tplayer: "+player.getName()+" - "+player.getScore(this.name)+"pts\n";
		}
		ret += "-------\n";
		for (Challenge challenge : challenges) {
			ret += "\tchallenge: "+challenge.getObjective()+" ("+challenge.isCompleted()+") - "+challenge.getValue()+"pts\n";
		}
		ret += "-----------------\n";
		return ret;
	}
	
	class RankComparator implements Comparator<Player> {
		public int compare(Player p1, Player p2){
	                //tri desc
			if (getPosition(p1) < getPosition(p2)) {
				return -1;
			} else if (getPosition(p1) > getPosition(p2)) {
				return 1;        	
			} else {
				return 0;
			}
		}      
	}
	
}
