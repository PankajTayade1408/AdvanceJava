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
		LoginModel loginModel=loginDAO.getUsername(usernameLogin);
		if (loginModel==null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPasswordNotExistsInDBForLogin(String usernameLogin,String passwordLogin) {
		LoginModel loginModel=loginDAO.getUsername(usernameLogin);
		loginModel.getPassword();
		if (!passwordLogin.equals(loginModel.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public int getIdForUserName(String usernameLogin) {
		return loginDAO.getIdForUserName(usernameLogin);
	}

	public int getId(String usernameLogin, String passwordLogin) {
		return loginDAO.getId(usernameLogin, passwordLogin);
	}

	@Override
	public LoginModel getUsername(String usrenameLogin) {
		// TODO Auto-generated method stub
		return null;
	}

}
