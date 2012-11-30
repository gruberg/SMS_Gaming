package mobserv.smsgaming;

import java.util.ArrayList;

public class ServerCom {

	ArrayList<Group> groups = new ArrayList<Group>();
	
	ServerCom() {
		groups = null;
	}
	
	ServerCom(ArrayList<Group> groups) {
		this.groups = groups;
	}
}
