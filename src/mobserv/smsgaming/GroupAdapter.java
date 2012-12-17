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

		if(convertView == null) {

		holder = new ViewHolder();

		convertView = inflater.inflate(R.layout.listgroups, null);

		holder.nameGroup = (TextView)convertView.findViewById(R.id.nameGroup);

		holder.nbPlayers = (TextView)convertView.findViewById(R.id.nbPlayers);

		convertView.setTag(holder);

		} else {

		holder = (ViewHolder) convertView.getTag();

		}

		holder.nameGroup.setText(groups.get(position).getName());

		holder.nbPlayers.setText(Integer.toString(groups.get(position).getPlayers().size()));

		return convertView;

	}

	private class ViewHolder {

		public ViewHolder() {
		}

		TextView nameGroup;

		TextView nbPlayers;

		}
}
