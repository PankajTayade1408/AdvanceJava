package employeecrudmaven.service;

import java.util.LinkedHashSet;

import employeecrudmaven.model.LoginModel;

public interface LoginService {

	public boolean isPasswordNotEqualsConfirmPassword(String password, String confirmPassword);

	public boolean isUsernameExistsInDB(String username);

	public boolean isUsernameNotExistsInDBForLogin(String usernameLogin);

	public boolean isPasswordNotExistsInDBForLogin(String passwordLogin);

	public LinkedHashSet<String> getPassword();

	public LinkedHashSet<String> getUsername();

	public void insertLogin(LoginModel registrationModel);
	
}
