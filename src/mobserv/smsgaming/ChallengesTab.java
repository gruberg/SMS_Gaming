package mobserv.smsgaming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ChallengesTab extends Fragment {
	ListView lvListe;
	ArrayList<Group> groups = new ArrayList<Group>();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Challenge> challenges = new ArrayList<Challenge>();

	String separator = "_;_";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return (LinearLayout) inflater.inflate(R.layout.challenges_tab, container, false);
    }
    
    public void onActivityCreated(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);

		this.readData();

    	// NEED TO "GET" THE GROUPNAME
    	ChallengesAdapter adapter = new ChallengesAdapter(getActivity(),groups.get(0).getChallenges());

		lvListe = (ListView) getActivity().findViewById(R.id.listViewChallenges);

		lvListe.setAdapter(adapter);
    }
    
    public void readData() {
		int i,j;

		challenges.clear();
		players.clear();
		groups.clear();

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

		Set<String> voidset = new HashSet<String>();
		voidset.add("null");


		Set<String> set_groups_prov = preferences.getStringSet("groups", voidset);
		if (set_groups_prov!=voidset){
			for (i=0;i<set_groups_prov.size();i++) {
				String g_name_prov = set_groups_prov.toArray()[i].toString();
				if (getGroup(g_name_prov)==null){
					Group group_prov = new Group(g_name_prov);
					//Toast.makeText(getBaseContext(),"new group :"+g_name_prov,Toast.LENGTH_SHORT).show();	


					Set<String> set_p_prov = preferences.getStringSet(g_name_prov+separator+"P", voidset);
					Set<String> set_c_prov = preferences.getStringSet(g_name_prov+separator+"C", voidset);

					if (set_p_prov!=voidset) {
						for (j=0;j<set_p_prov.size();j++) {

							String[] p_prov = set_p_prov.toArray()[j].toString().split(separator);
							String p_name_prov = p_prov[0];
							String p_number_prov = p_prov[1];
							boolean p_isuser_prov = Boolean.parseBoolean(p_prov[2]);
							int p_score_prov = Integer.parseInt(p_prov[3]);

							//Toast.makeText(getBaseContext(),"new player :"+p_name_prov+" with n�:"+p_number_prov,Toast.LENGTH_SHORT).show();

							if (getPlayer(p_name_prov,p_number_prov)==null) {
								Player player_prov = new Player(getActivity(),p_name_prov,p_number_prov,p_isuser_prov);
								player_prov.setScore(g_name_prov, p_score_prov);
								this.players.add(player_prov);
								group_prov.addPlayer(player_prov);
							}
							else {
								Player player_prov;
								player_prov = getPlayer(p_name_prov,p_number_prov);
								player_prov.setScore(g_name_prov, p_score_prov);
								group_prov.addPlayer(player_prov);
							}
						}
					}

					if (set_c_prov!=voidset) {
						for (j=0;j<set_c_prov.size();j++) {
							String c_name_prov = set_c_prov.toArray()[j].toString().split(separator)[0];
							String c_points_prov = set_c_prov.toArray()[j].toString().split(separator)[1];
							boolean c_completed_prov = Boolean.parseBoolean(set_c_prov.toArray()[j].toString().split(separator)[2]);
							if (getChallenge(c_name_prov,g_name_prov)==null) {
								//Toast.makeText(getBaseContext(),"new c:"+c_name_prov+" value:"+c_points_prov+" done?:"+c_completed_prov,Toast.LENGTH_SHORT).show();
								Challenge c_prov = new Challenge(getActivity(), c_name_prov,Integer.parseInt(c_points_prov), g_name_prov,c_completed_prov);
								this.challenges.add(c_prov);
								group_prov.addChallenge(c_prov);
							}
						}
					}	
					groups.add(group_prov);
				}
			}
		}
	}
    
	public Player getPlayer(String name, String number) {
		Player ret = null;
		for (Player player : this.players) {
			if ((player.getName().equals(name))&&(player.getPhone_number().equals(number))) {
				ret = player;
			}
		}
		return ret;
	}

	public Group getGroup(String groupname) {
		Group ret = null;
		for (Group group : this.groups) {
			if (group.getName().equals(groupname)) {
				ret = group;
			}
		}
		return ret;
	}

	public Challenge getChallenge(String objective,String groupname){
		Challenge ret = null;
		for (Challenge challenge: this.challenges) {
			if ((challenge.getObjective().equals(objective))&&(challenge.getGroupname().equals(groupname))) {
				ret = challenge;
			}
		}
		return ret;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Challenge> getChallenges() {
		return challenges;
	}

	public Player getUser(){
		Player ret = null;
		for (Player player : players) {
			if (player.isUser()) ret = player;
		}
		return ret;
	}
	public void printData() {
		System.out.println("nb groups:"+groups.size());
		for(Group group : groups) {
			System.out.println(group);
		}
		System.out.println("nb players:"+players.size());
		for (Player player : players) {
			System.out.println(player);
		}
		System.out.println("nb challenges:"+challenges.size());
		for (Challenge challenge: challenges) {
			System.out.println(challenge);
		}
	}
 
}