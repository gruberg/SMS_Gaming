package mobserv.smsgaming;

import java.io.Serializable;

public class User implements Serializable {

 private static final long serialVersionUID = 8187386238755292638L;
 private Long id;
 private String group;
 private String mail;

 public User() {

 }

 public Long getId() {
 return id;
 }

 public void setId(Long id) {
 this.id = id;
 }

 public void setGroup(String group) {
 this.group = group;
 }
 
 public String getGroup() {
 return this.group;
 }
 
 public void setMail(String mail) {
 this.mail = mail;
 }

 public String getMail() {
 return mail;
 }
 
}
