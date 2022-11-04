package employeecrudmaven.service;

import java.util.*;

import employeecrudmaven.model.LoginModel;

public interface LoginService {

	public boolean isPasswordNotEqualsConfirmPassword(String password, String confirmPassword);

	public boolean isUsernameExistsInDB(String username);
	
	public boolean regexValidationForUsername(String username);
	
	public boolean regexValidationForPassword(String password);
	
	public boolean isUsernameNotExistsInDBForLogin(String usernameLogin);

	public int getIdForUserName(String usernameLogin);//

	public LoginModel getUsername(String usrenameLogin);
	
	public boolean isPasswordNotExistsInDBForLogin(String usernameLogin, String passwordLogin);

	public LinkedHashSet<String> getPassword();//

	public LinkedHashSet<String> getUsername();//

	public void insertLogin(LoginModel registrationModel);//

	public int getId(String usernameLogin, String passwordLogin);//

}
