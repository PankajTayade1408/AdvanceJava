package employeecrudmaven.model;

import java.math.BigDecimal;
import java.util.*;

public class EmployeeModel {
   private int id;
   public String firstName;
   private String lastName;
   private LinkedHashSet<String>skills=new LinkedHashSet<String>();
   private int age;
   private double salary;
   private  String dateOfJoining;
   private String country;
   private int loginId;

   public EmployeeModel(int id, LinkedHashSet<String> skills) {
	super();
	this.id = id;                                  
	this.skills = skills;
}

public EmployeeModel(int id, String firstName, String lastName, LinkedHashSet<String> skills, int age, double salary,
		String dateOfJoining,String country) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.skills = skills;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
	this.country=country;
}

public EmployeeModel(String firstName, String lastName, LinkedHashSet<String> skills, int age, double salary,
		String dateOfJoining, String country, int loginId) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.skills = skills;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
	this.country = country;
	this.loginId = loginId;
}

public EmployeeModel() {
}

public int getLoginId() {
	return loginId;
}

public void setLoginId(int loginId) {
	this.loginId = loginId;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getFirstname() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastname() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public LinkedHashSet<String> getSkills() {
	return skills;
}

public void setSkills(LinkedHashSet<String> skills) {
	this.skills = skills;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public double getSalary() {
	return salary;
}

public void setSalary(double salary) {
	this.salary = salary;
}

public String getDoj() {
	return dateOfJoining;
}

public void setDateOfJoining(String dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

}
   
  