package employeecrudmaven.dao;

import java.sql.*;
import java.util.*;

import employeecrudmaven.model.EmployeeModel;

public interface EmployeeDAO {

	// Queries for Employee1 Table
	public static final String INSERT_EMPLOYEE_SQL = "INSERT INTO Employee1"
			+ "(firstname,lastname,age,salary,doj,loginId)Values" + "(?,?,?,?,?,?)"; 

	public static final String SELECT_EMPLOYEE_BY_ID = "select * from Employee1 where id=?";

	public static final String SELECT_EMPLOYEE_BY_LOGIN_ID = "select * from Employee1 where loginId=?";
	
	public static final String SELECT_ALL_EMPLOYEE = "select * from Employee1 ";
	
	public static final String SELECT_LATEST_ID_FROM_EMPLOYEE_SQL = "select id from Employee1 order by id desc limit 1 ";
	
	public static final String SELECT_ID_FROM_EMPLOYEE_SQL = "select id from Employee1 group by id "; 
	
	public static final String DELETE_EMPLOYEE_BY_ID = "delete from Employee1 where id=?"; 

	public static final String UPDATE_EMPLOYEE_SQL = "update Employee1 set firstname=?,lastname=?,age=?,salary=?,doj=? where id=?"; 

	public static final String SELECT_ALL_LOGIN_ID_SQL="select loginId from Employee1 ";
	
	// Queries for employee_skill table -
	public static final String INSERT_EMPLOYEE_SKILLS_SQL = "INSERT INTO Employee_skills" + "(empid,empskills)Values" + "(?,?)";
		
	public static final String DELETE_EMPLOYEE_SKILLS_SQL = "delete from Employee_skills where empid=? AND empskills=?  ";
	
	public static final String SELECT_EMPLOYEE_SKILL_BY_ID_SQL = "select * from employee_skills where empid=?"; //
	
	public static final String SELECT_ALL_EMPLOYEE_SKILLS_SQL="select * from employee_skills";
	
	
	// Methods for Employee1 Table
	public void insertEmployee(EmployeeModel employee);

	public EmployeeModel getEmployeeById(int id);
   
	public List<EmployeeModel> getAllEmployee(int loginId);

	public boolean updateEmployee(EmployeeModel employee);
 
	public boolean deleteEmployee(int id) throws Exception;
	
	public List<Integer> getLoginId();
	
	//Methods For Employee_Skill Table
	public  int insertEmployeeSkillsById(int id,LinkedHashSet<String> skills);
	
	public void deleteEmployeeSkillsById(int id,LinkedHashSet<String> skills);

	public  LinkedHashSet<String> getEmployeeSkillsById(int id);
	

}
