package employeecrudmaven.service;

import java.util.*;

import employeecrudmaven.dao.LoginDAO;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.LoginModel;

public class LoginServiceImpl implements LoginService {
	LoginDAO loginDAO = new LoginDAOImpl();
	
	public LinkedHashSet<String> getPassword() {
		return null;
	}

	public LinkedHashSet<String> getUsername() {
		return null;
	}

	public void insertLogin(LoginModel registrationModel) {
		loginDAO.insertLogin(registrationModel);
	}

	public boolean isPasswordNotEqualsConfirmPassword(String password, String confirmPassword) {
		if (!password.equals(confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUsernameExistsInDB(String username) {
		LinkedHashSet<String> usernameSet = new LinkedHashSet<String>();
		usernameSet = loginDAO.getUsername();
		if (usernameSet.contains(username)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isUsernameNotExistsInDBForLogin(String usernameLogin) {
		LinkedHashSet<String> usernameSet = new LinkedHashSet<String>();
		usernameSet = loginDAO.getUsername();
		if (!usernameSet.contains(usernameLogin)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isPasswordNotExistsInDBForLogin(String passwordLogin) {
		LinkedHashSet<String> passwordSet = new LinkedHashSet<String>();
		passwordSet = loginDAO.getPassword();
		if (!passwordSet.contains(passwordLogin)) {
			return true;
		} else {
			return false;
		}
	}
	 
}
