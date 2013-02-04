package mobserv.smsgaming;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChallengesAdapter extends BaseAdapter {
	ArrayList<Challenge> challenges;
	LayoutInflater inflater;

	ChallengesAdapter(Context context, ArrayList<Challenge> challenges) {
		inflater = LayoutInflater.from(context);
		this.challenges = challenges;
	}

	@Override
	public int getCount() {
		return challenges.size();
	}

	@Override
	public Object getItem(int arg0) {
		return challenges.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null) {

		holder = new ViewHolder();

		convertView = inflater.inflate(R.layout.challenges_tab_item, null);

		holder.objective = (TextView)convertView.findViewById(R.id.objective);
		holder.points = (TextView)convertView.findViewById(R.id.points);

		convertView.setTag(holder);

		} else {

		holder = (ViewHolder) convertView.getTag();

		}

		holder.objective.setText(challenges.get(position).getObjective());
		holder.points.setText(Integer.toString(challenges.get(position).getValue()));
		return convertView;

	}
	private class ViewHolder {

		public ViewHolder() {
		}

		TextView objective;
		TextView points;

		}

}