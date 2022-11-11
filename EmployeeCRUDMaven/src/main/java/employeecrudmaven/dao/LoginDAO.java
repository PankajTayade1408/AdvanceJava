package employeecrudmaven.dao;

import java.util.*;

import employeecrudmaven.model.LoginModel;

public interface LoginDAO {
	public static final String SELECT_ALL_USERNAMES_SQL="select Username from Login";
	
	public static final String SELECT_ALL_PASSWORD_SQL="select Password from Login";
	
	public static final String SELECT_ALL_FROM_LOGIN_SQL="select * from Login";
	
	public static final String INSERT_lOGIN_TABLE_SQL="Insert into Login"+"(Username,Password)Values"+"(?,?)";

	public static final String SELECT_LATEST_ID_SQL="Select Login_Id from Login order by Login_Id desc limit 1";
	
	public static final String SELECT_ID_BY_USERNAME_PASSWORD_LOGIN_SQL="select Login_Id from Login where Username=? and Password=?";
	
	public static final String SELECT_ID_BY_USERNAME_SQL="select Login_Id from Login where Username=?";
	
	public static final String SELECT_PASSWORD_BY_ID_SQL="select Password from Login  where Login_Id=?";
	
	public static final String SELECT_USERNAME_SQL="select * from Login where Username=?";
	
	public static final String UPDATE_PASSWORD_SQL="update Login set Password=? where Username=?";
	
	public int getIdForUserName(String usernameLogin);
	
	public LinkedHashSet<String> getPassword();
	
	public String getPassword(int id);
	
	public LinkedHashSet<String> getUsername();
	
	public LoginModel getUsername(String usernameLogin);
	
	public void insertLogin(LoginModel loginModel);

	public int getId(String usernameLogin,String passwordLogin);

	public void updatePassword(String username,String password);
}
