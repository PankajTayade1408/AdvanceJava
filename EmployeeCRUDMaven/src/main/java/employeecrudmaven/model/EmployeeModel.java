package employeecrudmaven.model;

import java.util.*;

public class EmployeeModel {
   private int id;
   public String firstName;
   private String lastName;
   private LinkedHashSet<String>skills=new LinkedHashSet<String>();
   private String age;
   private String salary;
   private  String dateOfJoining;
   
public EmployeeModel(int id, String firstName, String lastName, String age, String salary, String dateOfJoining) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;                        
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
}

public EmployeeModel(int id, LinkedHashSet<String> skills) {
	super();
	this.id = id;                                  
	this.skills = skills;
}

public EmployeeModel(String firstName, String lastName, String age, String salary,
		String dateOfJoining) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
}

public EmployeeModel(int id, String firstName, String lastName, LinkedHashSet<String> skills, String age, String salary,
		String dateOfJoining) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.skills = skills;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
}

public EmployeeModel() {
	
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

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

public String getSalary() {
	return salary;
}

public void setSalary(String salary) {
	this.salary = salary;
}

public String getDoj() {
	return dateOfJoining;
}

public void setDateOfJoining(String dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}
}
   
  