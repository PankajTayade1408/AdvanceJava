package employeecrudmaven.model;

public class LoginModel {
  private String username;
  private String password;
  private String confPassword;
  private int id;
public LoginModel(String username, String password, String confPassword,int id) {
	super();
	this.username = username;
	this.password = password;
	this.confPassword = confPassword;
	this.id=id;
}

public LoginModel(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public LoginModel(String username, String password, int id) {
	super();
	this.username = username;
	this.password = password;
	this.id = id;
}

public LoginModel() {
}

public LoginModel(int id) {
	// TODO Auto-generated constructor stub
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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
