package employeecrudmaven.model;

import java.util.*;

public class EmployeeModel {
<<<<<<< HEAD
   private int id;
   public String firstName;
   private String lastName;
   private LinkedHashSet<String>skills=new LinkedHashSet<String>();
   private String age;
   private String salary;
   private  String dateOfJoining;
   private String email;
   
public EmployeeModel(int id, String firstName, String lastName, String age, String salary, String dateOfJoining,
		String email) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
	this.email = email;
}

public EmployeeModel(int id, LinkedHashSet<String> skills) {
	super();
	this.id = id;                                  
	this.skills = skills;
}

public EmployeeModel(String firstName, String lastName, String age, String salary, String dateOfJoining, String email) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
	this.email = email;
}

public EmployeeModel(int id, String firstName, String lastName, LinkedHashSet<String> skills, String age, String salary,
		String dateOfJoining, String email) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.skills = skills;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
	this.email = email;
}

public EmployeeModel() {
	
}

public EmployeeModel(String firstName, String lastName, LinkedHashSet<String> skills, String age, String salary,
		String dateOfJoining, String email) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.skills = skills;
	this.age = age;
	this.salary = salary;
	this.dateOfJoining = dateOfJoining;
	this.email = email;
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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

=======
	private int id;
	public String firstName;
	private String lastName;
	private LinkedHashSet<String> skills = new LinkedHashSet<String>();
	private int age;
	private double salary;
	private String dateOfJoining;
	private String country;
	private int loginId;
	private byte[] profilePicture;
	private String fileName;

	public String getfileName() {
		return fileName;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public EmployeeModel(int id, LinkedHashSet<String> skills) {
		super();
		this.id = id;
		this.skills = skills;
	}

	public EmployeeModel(int id, String firstName, String lastName, LinkedHashSet<String> skills, int age,
			double salary, String dateOfJoining, String country, byte[] profilePicture, String fileName) {
		super();
		this.id = id;
		this.firstName = firstName;			// Constructor for getEmployeeById() of EmployeeDAOImpl class
		this.lastName = lastName;
		this.skills = skills;
		this.age = age;
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
		this.country = country;
		this.profilePicture = profilePicture;
		this.fileName = fileName;
	}

	public EmployeeModel(int id, String firstName, String lastName, LinkedHashSet<String> skills, int age,
			double salary, String dateOfJoining, String country, byte[] profilePicture) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;	    // Constructor for getAllEmployee() of EmployeeDAOImpl class
		this.skills = skills;
		this.age = age;
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
		this.country = country;
		this.profilePicture = profilePicture;
	}

	public EmployeeModel(String firstName, String lastName, LinkedHashSet<String> skills, int age, double salary,
			String dateOfJoining, String country, int loginId, String fileName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.skills = skills;
		this.age = age;
		this.salary = salary;					//Constructor for insertNewEmployee() of Controller Class
		this.dateOfJoining = dateOfJoining;
		this.country = country;
		this.loginId = loginId;
		this.fileName = fileName;
	}

	public EmployeeModel(int id, String firstName, String lastName, LinkedHashSet<String> skills, int age,
			double salary, String dateOfJoining, String country, String fileName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.skills = skills;
		this.age = age;						//Constructor for updateEmployee() of Controller Class
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
		this.country = country;
		this.fileName = fileName;
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

>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
}
