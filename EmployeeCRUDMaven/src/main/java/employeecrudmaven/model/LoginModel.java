package employeecrudmaven.model;

public class LoginModel {
  private String username;
  private String password;
  private String confPassword;
  
public LoginModel(String username, String password, String confPassword) {
	super();
	this.username = username;
	this.password = password;
	this.confPassword = confPassword;
}

public LoginModel(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfPassword() {
	return confPassword;
}
public void setConfPassword(String confPassword) {
	this.confPassword = confPassword;
}
  
}
