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
		LinkedHashSet<String> passwordList = new LinkedHashSet<String>();
		try {
			PreparedStatement preparedStatementGetPassword = connection.prepareStatement(SELECT_ALL_PASSWORD_SQL);
			ResultSet resultSetGetPassword = preparedStatementGetPassword.executeQuery();
			while (resultSetGetPassword.next()) {
				String password = resultSetGetPassword.getString("Password");
				passwordList.add(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passwordList;
	}

	public static int getLatestId() {
		int latestId = 0;
		try {
			PreparedStatement preparedStatementGetLatestId = connection.prepareStatement(SELECT_LATEST_ID_SQL);
			ResultSet resultSetGetLatestId = preparedStatementGetLatestId.executeQuery();
			while (resultSetGetLatestId.next()) {
				latestId = resultSetGetLatestId.getInt("Login_Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestId;

	}

	public LinkedHashSet<String> getUsername() {
		LinkedHashSet<String> userNameList = new LinkedHashSet<String>();
		try {
			PreparedStatement preparedStatementGetUsername = connection.prepareStatement(SELECT_ALL_USERNAMES_SQL);
			ResultSet resultSetGetUsername = preparedStatementGetUsername.executeQuery();
			while (resultSetGetUsername.next()) {
				String userName = resultSetGetUsername.getString("Username");
				userNameList.add(userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userNameList;
	}

	public int getIdForUserName(String usernameLogin) {
		int id = 0;
		try {
			PreparedStatement preparedStatementGetIdByUsername = connection.prepareStatement(SELECT_ID_BY_USERNAME_SQL);
			preparedStatementGetIdByUsername.setString(1, usernameLogin);
			ResultSet resultForGetIdByUsername = preparedStatementGetIdByUsername.executeQuery();
			while (resultForGetIdByUsername.next()) {
				id = resultForGetIdByUsername.getInt("Login_Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public int getId(String usernameLogin, String passwordLogin) {
		int id = 0;
		try {

			PreparedStatement preparedStatementGetId = connection
					.prepareStatement(SELECT_ID_BY_USERNAME_PASSWORD_LOGIN_SQL);
			preparedStatementGetId.setString(1, usernameLogin);
			preparedStatementGetId.setString(2, passwordLogin);
			ResultSet resultSetGetId = preparedStatementGetId.executeQuery();
			while (resultSetGetId.next()) {
				id = resultSetGetId.getInt("Login_Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getPassword(int id) {
		String password = "";
		try {

			PreparedStatement preparedStatementGetPasswordById = connection.prepareStatement(SELECT_PASSWORD_BY_ID_SQL);
			preparedStatementGetPasswordById.setInt(1, id);
			ResultSet resultSetGetPasswordById = preparedStatementGetPasswordById.executeQuery();
			while (resultSetGetPasswordById.next()) {
				password = resultSetGetPasswordById.getString("Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public LoginModel getUsername(String usernameLogin) {
		LoginModel loginModel = null;
		try {
			PreparedStatement preparedStatementForUsername = connection.prepareStatement(SELECT_USERNAME_SQL);
			preparedStatementForUsername.setString(1, usernameLogin);
			ResultSet resultSetForUsername = preparedStatementForUsername.executeQuery();
			while (resultSetForUsername.next()) {
				int id = resultSetForUsername.getInt("Login_Id");
				String username = resultSetForUsername.getString("Username");
				String password = resultSetForUsername.getString("Password");
				loginModel = new LoginModel(username, password, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginModel;
	}

	@Override
	public void updatePassword(String username,String password) {
		try {
			PreparedStatement preparedstatementForUpdatePassword = connection
					.prepareStatement(UPDATE_PASSWORD_SQL);
			preparedstatementForUpdatePassword.setString(1,password);	
		preparedstatementForUpdatePassword.setString(2, username);
		
		preparedstatementForUpdatePassword.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
