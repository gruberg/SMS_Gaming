package mobserv.smsgaming;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScoresAdapter extends BaseAdapter {

	ArrayList<Player> players;
	Group group;
	LayoutInflater inflater;
	

	ScoresAdapter(Context context, Group group, ArrayList<Player> players){
		inflater = LayoutInflater.from(context);
		this.players = players;
		this.group = group;
		System.out.println("HERE MOTHERFUCKER");
	}

	public int getCount() {
		return players.size();
	}


	public Object getItem(int arg0) {
		return players.get(arg0);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null) {

		holder = new ViewHolder();

		convertView = inflater.inflate(R.layout.scores_tab_item, null);

		holder.namePlayer = (TextView)convertView.findViewById(R.id.namePlayer);

		holder.scorePlayer = (TextView)convertView.findViewById(R.id.scorePlayer);

		holder.rank = (TextView)convertView.findViewById(R.id.rank);

		convertView.setTag(holder);

		} else {

		holder = (ViewHolder) convertView.getTag();

		}
		holder.rank.setText(Integer.toString(group.getPosition(players.get(position))));
		holder.namePlayer.setText(players.get(position).getName());
		holder.scorePlayer.setText(Integer.toString(players.get(position).getScore(group.getName())));
		
		return convertView;

	}

	private class ViewHolder {

		public ViewHolder() {
		}

		TextView rank;
		
		TextView namePlayer;

		TextView scorePlayer;

		}

}
