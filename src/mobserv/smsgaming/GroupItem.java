package mobserv.smsgaming;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class GroupItem extends Activity {
	String separator = "_;_";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
        this.setContentView(R.layout.group_item_view);

        TextView txtProduct = (TextView) findViewById(R.id.group_label);

        Intent i = getIntent();
        // getting attached intent data
        String groupname = i.getStringExtra("name");

        //ArrayList<String> players = i.getStringArrayListExtra("players");
        // displaying selected product name
        txtProduct.setText(groupname);

		 */

		ActionBar actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		String label1 = "Scores";
		Tab tab = actionBar.newTab();
		tab.setText(label1);
		TabListener<ScoresTab> tl = new TabListener<ScoresTab>(this,
				label1, ScoresTab.class);
		tab.setTabListener(tl);
		actionBar.addTab(tab);

		String label2 = "Challenges";
		tab = actionBar.newTab();
		tab.setText(label2);
		TabListener<ChallengesTab> tl2 = new TabListener<ChallengesTab>(this,
				label2, ChallengesTab.class);
		tab.setTabListener(tl2);
		actionBar.addTab(tab);

		
	}
	
	class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 *
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// User selected the already selected tab. Usually do nothing.
		}

	}
}
