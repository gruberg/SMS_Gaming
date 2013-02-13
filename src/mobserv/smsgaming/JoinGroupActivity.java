package mobserv.smsgaming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JoinGroupActivity extends Activity {

	
	EditText mEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_group);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mEdit   = (EditText)findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_join_group, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void joinThatGroup(View view){
		confirmGroupCreation();


	}

	
	public void joinThatGroup1(View view){		
		Context context = getApplicationContext();
		CharSequence text = "You joined the weekly challenge group";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		

	}

	
	public void joinThatGroup2(View view){
		Context context = getApplicationContext();
		CharSequence text = "you joined the Eurecom group";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

	}

	public class NoGroupDialogFragment extends DialogFragment {
		
		
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage(R.string.noGroup)
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				ArrayList<Group> groups = new ArrayList<Group>();
				ArrayList<Player> players = new ArrayList<Player>();
				ArrayList<Challenge> challenges = new ArrayList<Challenge>();
				
				String separator = "_;_";

				public void onClick(DialogInterface dialog, int id) {
					
					String mEmail;
					
					String groupname = mEdit.getText().toString();
				    SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
					mEmail= sharedPreferences.getString("USER", "");
					User u = new User();
					u.setGroup(groupname);
					u.setMail(mEmail);
					final UserController uc = new UserController();
					//Log.v("JoinGroupActivity", "UC instanciated");
					try {
					    uc.create(u);
					} catch (Exception e) {
					    //return;
						Log.e("JoinGroupActivity", "exception uc.create");
					}
					this.addGroup(groupname);
					Context context = getApplicationContext();
					CharSequence text = "Group "+ groupname + " created";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				public void readData() {
					int i,j;

					challenges.clear();
					players.clear();
					groups.clear();

					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

					Set<String> voidset = new HashSet<String>();
					voidset.add("null");
					// gets all groups
					Set<String> set_groups_prov = preferences.getStringSet("groups", voidset);
					// if not void, then loop on the groups to get their data
					if (set_groups_prov!=voidset){
						for (i=0;i<set_groups_prov.size();i++) {
							String g_name_prov = set_groups_prov.toArray()[i].toString();
							if (getGroup(g_name_prov)==null){
								Group group_prov = new Group(g_name_prov);
								//System.out.println("new group :"+g_name_prov);	


								Set<String> set_p_prov = preferences.getStringSet(g_name_prov+separator+"P", voidset);
								Set<String> set_c_prov = preferences.getStringSet(g_name_prov+separator+"C", voidset);
								String bet_prov = preferences.getString(g_name_prov+separator+"B","");
								//System.out.println(set_p_prov);
								//System.out.println(set_c_prov);
								//System.out.println(bet_prov);
								if (set_p_prov!=voidset) {
									for (j=0;j<set_p_prov.size();j++) {

										String[] p_prov = set_p_prov.toArray()[j].toString().split(separator);
										String p_name_prov = p_prov[0];
										String p_number_prov = p_prov[1];
										boolean p_isuser_prov = Boolean.parseBoolean(p_prov[2]);
										int p_score_prov = Integer.parseInt(p_prov[3]);

										//System.out.println("new player :"+p_name_prov+" with n*:"+p_number_prov);

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
											//System.out.println("new c:"+c_name_prov+" value:"+c_points_prov+" done?:"+c_completed_prov);
											Challenge c_prov = new Challenge(getActivity(), c_name_prov,Integer.parseInt(c_points_prov), g_name_prov,c_completed_prov);
											this.challenges.add(c_prov);
											group_prov.addChallenge(c_prov);
										}
									}
								}	
								
								if (!bet_prov.equals("")) {
									group_prov.setBet(bet_prov);
								}
								groups.add(group_prov);
							}
						}
					}
				}


				public void addGroup(String groupname){
					
					//System.out.println("in addgroup");

					this.readData();
					//this.printData();

					Set<String> voidset = new HashSet<String>();
					voidset.add("null");
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
					SharedPreferences.Editor editor = preferences.edit();

					Set<String> set_groups_prov = preferences.getStringSet("groups", voidset);
					set_groups_prov.add(groupname);
					editor.putStringSet("groups", set_groups_prov);
					
					Player user = getUser();
					Set<String> groupP = new HashSet<String>();
					groupP.add(user.getName()+separator+user.getPhone_number()+separator+"true"+separator+"0");
					editor.putStringSet(groupname+separator+"P", groupP);

					Set<String> groupC = new HashSet<String>();
					groupC.add("SpongeBob"+separator+"30"+separator+false);
					groupC.add("Eurecom"+separator+"20"+separator+false);
					editor.putStringSet(groupname+separator+"C", groupC);

					editor.putString(groupname+separator+"B", "Winner gets free cinema ticket");
					
					editor.commit();

					//this.readData();	
					this.printData();



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
						Log.d("printData",group.toString());
					}
					System.out.println("nb players:"+players.size());
					for (Player player : players) {
						Log.d("printData",player.toString());
					}
					System.out.println("nb challenges:"+challenges.size());
					for (Challenge challenge: challenges) {
						Log.d("printData",challenge.toString());
					}
				}
				
				
				
			})
			.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// User cancelled the dialog
				}
			});
			// Create the AlertDialog object and return it
			return builder.create();
		}
		
		
	}

	public void confirmGroupCreation() {
		DialogFragment newFragment = new NoGroupDialogFragment();
		newFragment.show(getFragmentManager(), "nogroup");
	}
	
	

}
