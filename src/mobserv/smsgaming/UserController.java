package mobserv.smsgaming;

import java.util.List;

import org.restlet.resource.ClientResource;

import android.util.Log;

public class UserController {

 public final ClientResource cr = new ClientResource(
 EngineConfiguration.gae_path + "/rest/user");
 
 public UserController() {
 EngineConfiguration.getInstance();
 }

 public void create(User user) throws Exception {
 final UserControllerInterface uci = cr
 .wrap(UserControllerInterface.class);
 uci.create(user);
 try {
 //uci.create(user);
 Log.i("UserController", "Creation success !");
 } catch (Exception e) {
 Log.i("UserController", "Creation failed !");
 throw e;
 }
 }

 public List getAllUsers() {
 final UserControllerInterface uci = cr
 .wrap(UserControllerInterface.class);
 Container content = uci.getAllUsers();
 return content.getUser_list();
 }
}