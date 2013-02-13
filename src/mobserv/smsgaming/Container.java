package mobserv.smsgaming;

import java.util.ArrayList;
import java.util.List;


public class Container
{
 public List user_list;

 public List getUser_list() {
 return user_list;
 }

 public void setUser_list(List user_list) {
 this.user_list = user_list;
 }

 public Container()
 {
 user_list = new ArrayList();
 }

 public Container(List user_list)
 {
 this.user_list = user_list;
 }

}
