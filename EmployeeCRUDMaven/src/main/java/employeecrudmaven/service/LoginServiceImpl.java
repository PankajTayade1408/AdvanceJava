package employeecrudmaven.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import employeecrudmaven.dao.LoginDAO;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.LoginModel;

public class LoginServiceImpl implements LoginService {
	LoginDAO loginDAO = new LoginDAOImpl();
	public final String regexForUsername = "([a-zA-Z]+).{3,}";
	public final String regexForPassword = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$&+,:;=?@#|'<>.-^*()%!]).{5,14})";
	public final String regexForNoWhiteSpace="\\S*";
	public final String regexForNonDigit="\\D*";
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

	public boolean isUsernameNotExistsInDBForLogin(String usernameLogin) {
		LoginModel loginModel = loginDAO.getUsername(usernameLogin);
		if (loginModel == null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isPasswordNotExistsInDBForLogin(String usernameLogin, String passwordLogin) {
		LoginModel loginModel = loginDAO.getUsername(usernameLogin);
		LinkedHashSet<String> passwordSet = new LinkedHashSet<String>();
		passwordSet = loginDAO.getPassword();
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
		return null;
	}

	@Override
	public boolean regexValidationForUsername(String username) {
		if ((Pattern.matches(regexForUsername, username) && Pattern.matches(regexForNoWhiteSpace, username) && Pattern.matches(regexForNonDigit, username)) ==false) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean regexValidationForPassword(String password) {
		if ((Pattern.matches(regexForPassword, password)  && Pattern.matches(regexForNoWhiteSpace, password))==false) {
			return true;
		} else {
			return false;
		}
	}

}
