package employeecrudmaven.service;

import java.util.LinkedHashSet;

import employeecrudmaven.model.LoginModel;


public interface LoginService  {
	    
	    public boolean isPasswordEqualsConfirmPassword(String username,String password,String confirmPassword);
	    
	    public boolean isUsernameExistsInDB(String username,String password,String confPassword);//if(username exists in DB)
	    
	    public boolean isUsernameExistsInDBForLogin(String usernameLogin);
	    
	    public boolean isPasswordExistsInDBForLogin(String passwordLogin);
	    
		public LinkedHashSet<String> getPassword();
		
		public LinkedHashSet<String> getUsername();
		
		public void insertLogin(LoginModel registrationModel);
}
