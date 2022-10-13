package employeecrudmaven.dao;

import java.util.*;

import employeecrudmaven.model.LoginModel;

public interface LoginDAO {
	public static final String SELECT_ALL_USERNAMES_SQL="select Username from Login";
	
	public static final String SELECT_ALL_PASSWORD_SQL="select Password from Login";
	
	public static final String SELECT_ALL_FROM_LOGIN_SQL="select * from Login";
	
	public static final String INSERT_lOGIN_TABLE_SQL="Insert into Login"+"(Username,Password)Values"+"(?,?)";

    public LinkedHashSet<String> getPassword();
	
	public LinkedHashSet<String> getUsername();
	
	public void insertLogin(LoginModel loginModel);
}
