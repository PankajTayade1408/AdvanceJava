package employeecrudmaven.service;

import java.util.*;

import employeecrudmaven.dao.LoginDAO;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.LoginModel;

public class LoginServiceImpl implements LoginService {
	LoginDAO loginDAO = new LoginDAOImpl();


	@Override
	public LinkedHashSet<String> getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashSet<String> getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertLogin(LoginModel registrationModel) {

		loginDAO.insertLogin(registrationModel);
	}

	public boolean isPasswordEqualsConfirmPassword(String username, String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isUsernameExistsInDB(String username, String password, String confPassword) {
		LinkedHashSet<String> usernameSet = new LinkedHashSet<String>();
		usernameSet = loginDAO.getUsername();
		if (usernameSet.contains(username)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isUsernameExistsInDBForLogin(String usernameLogin) {
		LinkedHashSet<String> usernameSet = new LinkedHashSet<String>();
		usernameSet = loginDAO.getUsername();
		if (usernameSet.contains(usernameLogin)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isPasswordExistsInDBForLogin(String passwordLogin) {
		LinkedHashSet<String> passwordSet = new LinkedHashSet<String>();
		passwordSet = loginDAO.getPassword();
		if (passwordSet.contains(passwordLogin)) {
			return false;
		} else {
			return true;
		}
	}

}
