package mobserv.smsgaming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GroupItem extends Activity {
	String separator = "_;_";

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.group_item_view);
 
        TextView txtProduct = (TextView) findViewById(R.id.group_label);
        
        Intent i = getIntent();
        // getting attached intent data
        String groupname = i.getStringExtra("name");
 
        //ArrayList<String> players = i.getStringArrayListExtra("players");
        // displaying selected product name
        txtProduct.setText(groupname);
 
    }
}
