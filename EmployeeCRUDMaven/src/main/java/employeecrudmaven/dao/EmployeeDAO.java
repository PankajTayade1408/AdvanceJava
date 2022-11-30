package employeecrudmaven.dao;

import java.io.FileInputStream;
import java.util.*;

import employeecrudmaven.model.EmployeeModel;

public interface EmployeeDAO {

	// Queries for Employee1 Table
	public static final String INSERT = "INSERT INTO Employee1"
			+ "(firstname,lastname,age,salary,doj,country,image,loginId,fileName)Values" + "(?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_BY_ID = "select * from Employee1 where id=?";

	public static final String SELECT_LOGIN_ID = "select * From Employee1 where loginId=? ";

	public static final String SELECT_LATEST_ID = "select id from Employee1 order by id desc limit 1 ";

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

	public List<EmployeeModel> getAllEmployee(int loginId);

	public boolean updateEmployee(EmployeeModel employee,FileInputStream fileInputStream);

	public boolean deleteEmployee(int id) throws Exception;

	// Methods For Employee_Skill Table
	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills);

	public void deleteEmployeeSkillsById(int id, LinkedHashSet<String> skills);

	public LinkedHashSet<String> getEmployeeSkillsById(int id);

}
