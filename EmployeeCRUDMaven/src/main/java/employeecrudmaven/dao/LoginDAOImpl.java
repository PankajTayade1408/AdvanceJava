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
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_lOGIN_TABLE_SQL);
			prepareStatement.setString(1, loginModel.getUsername());
			prepareStatement.setString(2, loginModel.getPassword());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getLatestId() {
		int latestId = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LATEST_ID_SQL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				latestId = resultSet.getInt("Login_Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestId;
	}

	public int getIdForUserName(String username) {
		int id = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_USERNAME_SQL);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("Login_Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public int getId(String username, String password) {
		int id = 0;
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement(SELECT_ID_BY_USERNAME_PASSWORD_LOGIN_SQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("Login_Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getPassword(int id) {
		String password = "";
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_ID_SQL);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				password = resultSet.getString("Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public LoginModel getUsername(String username) {
		LoginModel loginModel = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERNAME_SQL);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("Login_Id");
				String usernameFromDB = resultSet.getString("Username");
				String passwordFromDB = resultSet.getString("Password");
				loginModel = new LoginModel(usernameFromDB, passwordFromDB, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginModel;
	}

	public void updatePassword(String username,String password) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(UPDATE_PASSWORD_SQL);
			preparedStatement.setString(1,password);	
		preparedStatement.setString(2, username);
		
		preparedStatement.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
