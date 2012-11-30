package mobserv.smsgaming;

import java.util.ArrayList;

public class SMSParser {

	ArrayList<Group> groups = new ArrayList<Group>();
	
	SMSParser() {
		groups = null;
	}
	
	SMSParser(ArrayList<Group> groups) {
		this.groups = groups;
	}
}
