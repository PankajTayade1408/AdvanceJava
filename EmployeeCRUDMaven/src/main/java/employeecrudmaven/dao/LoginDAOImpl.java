package employeecrudmaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import employeecrudmaven.model.LoginModel;

public class LoginDAOImpl implements LoginDAO {

	static Connection connection = DBConnection.getConnection();
	
	public void insertLogin(LoginModel loginModel) {
		try {
			PreparedStatement preparedStatementUsername = connection.prepareStatement(INSERT_lOGIN_TABLE_SQL);
			preparedStatementUsername.setString(1, loginModel.getUsername());
			
			preparedStatementUsername.setString(2, loginModel.getPassword());
			preparedStatementUsername.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedHashSet<String> getPassword() {
		LinkedHashSet<String> passwordList=new LinkedHashSet<String>();
		try
		{
			PreparedStatement preparedStatementGetPassword=connection.prepareStatement(SELECT_ALL_PASSWORD_SQL);
			ResultSet resultSetGetPassword=preparedStatementGetPassword.executeQuery();
			while(resultSetGetPassword.next())
			{
			   String password=resultSetGetPassword.getString("Password");
			   passwordList.add(password);
			   
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return passwordList;
	}
	
	public static int getLatestId()
	{
		int latestId=0;
		try
		{
			PreparedStatement preparedStatementGetLatestId=connection.prepareStatement(SELECT_LATEST_ID_SQL);
			ResultSet resultSetGetLatestId=preparedStatementGetLatestId.executeQuery();
			while(resultSetGetLatestId.next())
			{
			    latestId=resultSetGetLatestId.getInt("Login_Id");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return latestId;
		
	}
	public LinkedHashSet<String> getUsername() {
		LinkedHashSet<String> userNameList=new LinkedHashSet<String>();
		try
		{
			PreparedStatement preparedStatementGetUsername=connection.prepareStatement(SELECT_ALL_USERNAMES_SQL);
			ResultSet resultSetGetUsername=preparedStatementGetUsername.executeQuery();
			while(resultSetGetUsername.next())
			{
			   String userName=resultSetGetUsername.getString("Username");
			   userNameList.add(userName);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userNameList;
	}

	public int  getId(String usernameLogin, String passwordLogin) {
		int id=0;
		try
		{

			PreparedStatement preparedStatementGetId=connection.prepareStatement(SELECT_ID_BY_USERNAME_PASSWORD_LOGIN_SQL);
			preparedStatementGetId.setString(1, usernameLogin);
			preparedStatementGetId.setString(2, passwordLogin);
			ResultSet resultSetGetId=preparedStatementGetId.executeQuery();
			while(resultSetGetId.next())
			{
				id=resultSetGetId.getInt("Login_Id");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}

	
}
