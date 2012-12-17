package mobserv.smsgaming;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GroupAdapter extends BaseAdapter {

	ArrayList<Group> groups;
	LayoutInflater inflater;
	
	GroupAdapter(Context context, ArrayList<Group> groups){
		inflater = LayoutInflater.from(context);
		this.groups = groups;
	}
	public int getCount() {
		return groups.size();
	}

	public Object getItem(int arg0) {
		return groups.get(arg0);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Group group;

		if(convertView == null) {

		holder = new ViewHolder();

		convertView = inflater.inflate(R.layout.listgroups, null);

		holder.nameGroup = (TextView)convertView.findViewById(R.id.nameGroup);

		holder.nbPlayers = (TextView)convertView.findViewById(R.id.nbPlayers);
		
		holder.nbPoints = (TextView)convertView.findViewById(R.id.nbPoints);

		convertView.setTag(holder);

		} else {

		holder = (ViewHolder) convertView.getTag();

		}

		group = groups.get(position);
		
		holder.nameGroup.setText(group.getName());

		holder.nbPlayers.setText("("+Integer.toString(group.getPlayers().size())
				+" players)");
		
		String str_position = Integer.toString(group.getPosition(group.getUser()));
		int int_position = group.getPosition(group.getUser());
		if (int_position==1) str_position += "st";
		else if (int_position==2) str_position += "nd";
		else if (int_position==3) str_position += "rd";
		else str_position += "th";
		holder.nbPoints.setText(str_position+" ("+Integer.toString(group.getUser().getScore(group.getName()))+"pts)");

		return convertView;

	}

	private class ViewHolder {

		public ViewHolder() {
		}

		TextView nameGroup;

		TextView nbPlayers;
		
		TextView nbPoints;

		}
}
