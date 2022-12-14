package employeecrudmaven.dao;

<<<<<<< HEAD
=======
import java.io.FileInputStream;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
import java.util.*;

import employeecrudmaven.model.EmployeeModel;

public interface EmployeeDAO {

<<<<<<< HEAD
	public static final String INSERT_EMPLOYEE_SQL = "INSERT INTO Employee1"
			+ "(firstname,lastname,age,salary,doj,email)Values" + "(?,?,?,?,?,?)"; 
=======
	// Queries for Employee1 Table
	public static final String INSERT = "INSERT INTO Employee1"
			+ "(firstname,lastname,age,salary,doj,country,image,loginId,fileName)Values" + "(?,?,?,?,?,?,?,?,?)";
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff

	public static final String SELECT_BY_ID = "select * from Employee1 where id=?";

<<<<<<< HEAD
	public static final String SELECT_ALL_EMPLOYEE = "select * from Employee1 ";
	
	public static final String SELECT_LATEST_ID_FROM_EMPLOYEE_SQL = "select id from Employee1 order by id desc limit 1 ";
	
	public static final String SELECT_ALL_EMAIL_FROM_EMPLOYEE_SQL="select email from Employee1";
	
	public static final String SELECT_EMPLOYEE_EMAIL_BY_ID="select email from Employee1 where id=?";
	
	public static final String DELETE_EMPLOYEE_BY_ID = "delete from Employee1 where id=?"; 

	public static final String UPDATE_EMPLOYEE_SQL = "update Employee1 set firstname=?,lastname=?,age=?,salary=?,doj=?,email=? where id=?"; 
=======
	public static final String SELECT_LOGIN_ID = "select * From Employee1 where loginId=? ";

	public static final String SELECT_LATEST_ID = "select id from Employee1 order by id desc limit 1 ";
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff

	public static final String DELETE_BY_ID = "delete from Employee1 where id=?";

	public static final String UPDATE_SQL = "update Employee1 set firstname=?,lastname=?,age=?,salary=?,doj=?,country=?,image=?,fileName=? where id=?";
	
	// Queries for employee_skill table -
	public static final String INSERT_SKILLS = "INSERT INTO Employee_skills" + "(empid,empskills)Values"
			+ "(?,?)";

	public static final String DELETE_SKILLS = "delete from Employee_skills where empid=? AND empskills=?  ";

	public static final String SELECT_SKILL_BY_ID = "select * from employee_skills where empid=?"; //

	// Methods for Employee1 Table
	public void insertEmployee(EmployeeModel employee,FileInputStream fileInputStream);

	public EmployeeModel getEmployeeById(int id);

<<<<<<< HEAD
	public boolean updateEmployee(EmployeeModel employee);
	
	public  ArrayList<String> getEmployeeEmail();
 
	public boolean deleteEmployee(int id) throws Exception;
	
	public  String  getEmployeeEmailById(int id);
	
	public int selectLatestIdFromEmployee();
	
	//Methods For Employee_Skill Table
	public  int insertEmployeeSkillsById(int id,LinkedHashSet<String> skills);
	
	public void deleteEmployeeSkillsById(int id,LinkedHashSet<String> skills);
=======
	public List<EmployeeModel> getAllEmployee(int loginId);

	public boolean updateEmployee(EmployeeModel employee,FileInputStream fileInputStream);

	public boolean deleteEmployee(int id) throws Exception;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff

	// Methods For Employee_Skill Table
	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills);

	public void deleteEmployeeSkillsById(int id, LinkedHashSet<String> skills);

	public LinkedHashSet<String> getEmployeeSkillsById(int id);

}
