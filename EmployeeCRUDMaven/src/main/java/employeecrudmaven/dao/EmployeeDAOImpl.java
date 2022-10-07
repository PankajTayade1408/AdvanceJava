package employeecrudmaven.dao;

import java.sql.*;
import java.util.*;

import employeecrudmaven.dao.EmployeeDAO;
import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.dao.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	static Connection connection = DBConnection.getConnection();

	public void insertEmployee(EmployeeModel employee) {
		try {
			PreparedStatement preparedstatementForInsert = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			preparedstatementForInsert.setString(1, employee.getFirstname());
			preparedstatementForInsert.setString(2, employee.getLastname());
			preparedstatementForInsert.setString(3, employee.getAge());
			preparedstatementForInsert.setString(4, employee.getSalary());
			preparedstatementForInsert.setString(5, employee.getDoj());
			preparedstatementForInsert.setString(6, employee.getEmail());
			preparedstatementForInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int selectLatestIdFromEmployee(int id) {
		int employeeId = 0;
		try {
			PreparedStatement preparedstatementForLatestId = connection
					.prepareStatement(SELECT_LATEST_ID_FROM_EMPLOYEE_SQL);
			ResultSet resultsetForLatestId = preparedstatementForLatestId.executeQuery();
			while (resultsetForLatestId.next()) {
				employeeId = resultsetForLatestId.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeId;
	}

	public static int selectIdFromEmployee(int id)
	{
		int employeeId=0;
		try
		{
			PreparedStatement preparedstatementForLatestId = connection
					.prepareStatement(SELECT_ID_FROM_EMPLOYEE_SQL);
			ResultSet resultsetForLatestId = preparedstatementForLatestId.executeQuery();
			while (resultsetForLatestId.next()) {
				employeeId= resultsetForLatestId.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeId;
	}
	
	public ArrayList<String> getEmployeeEmail()
	{
		ArrayList<String> emailList=new ArrayList<String>();
		try
		{
			PreparedStatement preparedstatementForAllEmail=connection.prepareStatement(SELECT_ALL_EMAIL_FROM_EMPLOYEE_SQL);
			ResultSet resultsetForAllEmail=preparedstatementForAllEmail.executeQuery();
			while(resultsetForAllEmail.next())
			{
				String email=resultsetForAllEmail.getString("email");
				emailList.add(email);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return emailList;
	}
	
	public  String  getEmployeeEmailById(int id)
	{
		EmployeeModel employee=new EmployeeModel();
		String employeeEmail="";
		try
		{
			PreparedStatement preparedstatementForgetEmailById=connection.prepareStatement(SELECT_EMPLOYEE_EMAIL_BY_ID);
			preparedstatementForgetEmailById.setInt(1, employee.getId());
			ResultSet resultsetForGetEmailById=preparedstatementForgetEmailById.executeQuery();
			while(resultsetForGetEmailById.next())
			{
				employeeEmail=resultsetForGetEmailById.getString("email");
				System.out.println(employeeEmail);
			}
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		return employeeEmail;
	}
	public int insertEmployeeSkillsById(int id,LinkedHashSet<String> skills) {
		try {
			PreparedStatement preparedstatatementForInsertSkills = connection
					.prepareStatement(INSERT_EMPLOYEE_SKILLS_SQL);
			LinkedHashSet<String> checkSkillsSet = new LinkedHashSet<String>();
			preparedstatatementForInsertSkills.setInt(1,id);
			checkSkillsSet = skills;
				for (String checkedSkills : checkSkillsSet) {
					preparedstatatementForInsertSkills.setString(2, checkedSkills);
					preparedstatatementForInsertSkills.executeUpdate();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public EmployeeModel getEmployeeById(int id) {
		EmployeeModel employee = null;
		try {
			PreparedStatement preparestatementForGetById = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			preparestatementForGetById.setInt(1, id);
			ResultSet resultSetForGetById = preparestatementForGetById.executeQuery();
			EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
			while (resultSetForGetById.next()) {
				id = resultSetForGetById.getInt("id");
				String firstName = resultSetForGetById.getString("firstname");
				String lastName = resultSetForGetById.getString("lastname");
				String age = resultSetForGetById.getString("age");
				String salary = resultSetForGetById.getString("salary");
				String dateOfJoining = resultSetForGetById.getString("doj");
				String email=resultSetForGetById.getString("email");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining,email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<EmployeeModel> getAllEmployee() {
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		try {
			PreparedStatement preparestatementForGetAllEmployee = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
			ResultSet resultsetForGetAllEmployee = preparestatementForGetAllEmployee.executeQuery();
			while (resultsetForGetAllEmployee.next()) {
				int id = resultsetForGetAllEmployee.getInt("id");
				String firstName = resultsetForGetAllEmployee.getString("firstname");
				String lastName = resultsetForGetAllEmployee.getString("lastname");
				String age = resultsetForGetAllEmployee.getString("age");
				String salary = resultsetForGetAllEmployee.getString("salary");
				String dateOfJoining = resultsetForGetAllEmployee.getString("doj");
				String email=resultsetForGetAllEmployee.getString("email");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl(); 
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employeeList.add(new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining,email));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public boolean updateEmployee(EmployeeModel employee) {
		boolean udpatedEmployeeDetail = false;
		try (PreparedStatement preparedstatementForUpdate = connection
						.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			preparedstatementForUpdate.setString(1, employee.getFirstname());
			preparedstatementForUpdate.setString(2, employee.getLastname());
			preparedstatementForUpdate.setString(3, employee.getAge());
			preparedstatementForUpdate.setString(4, employee.getSalary());
			preparedstatementForUpdate.setString(5, employee.getDoj());
			preparedstatementForUpdate.setString(6, employee.getEmail());
			preparedstatementForUpdate.setInt(7, employee.getId());
			udpatedEmployeeDetail = preparedstatementForUpdate.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return udpatedEmployeeDetail;
	}
	
	public boolean deleteEmployee(int id) throws Exception {
		boolean deleteEmployee = false;
		try (
				PreparedStatement preparedstatementForDelete = connection
						.prepareStatement(DELETE_EMPLOYEE_BY_ID);) {
			preparedstatementForDelete.setInt(1, id);
			deleteEmployee = preparedstatementForDelete.executeUpdate() > 0;
		}
		return deleteEmployee;
	}

	public LinkedHashSet<String> getEmployeeSkillsById(int id) {
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		try {PreparedStatement preparedstatementForGetSkillsById = connection
					.prepareStatement(SELECT_EMPLOYEE_SKILL_BY_ID_SQL);
			preparedstatementForGetSkillsById.setInt(1, id);
			ResultSet resultSetForGetSkillsById = preparedstatementForGetSkillsById.executeQuery();
			LinkedHashSet<String> skillsFromBackEnd = new LinkedHashSet<String>();
			while (resultSetForGetSkillsById.next()) {
				String backEndSkills = resultSetForGetSkillsById.getString("empskills");
				skillsFromBackEnd.add(backEndSkills);
				skills = (LinkedHashSet) skillsFromBackEnd.clone();
			}
			skillsFromBackEnd.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return skills;
	}

	public void deleteEmployeeSkillsById(int employeeId, LinkedHashSet<String> skills) {
		try {
			PreparedStatement preparedstatementForDeleteSkills = connection
					.prepareStatement(DELETE_EMPLOYEE_SKILLS_SQL);
			preparedstatementForDeleteSkills.setInt(1, employeeId);
			for (String skillsToBeDelete : skills) {
				preparedstatementForDeleteSkills.setString(2, skillsToBeDelete);
				preparedstatementForDeleteSkills.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws Exception {
		EmployeeModel employee = new EmployeeModel();
		int id =0;
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
		employeeDAOImpl.insertEmployee(employee);
		employeeDAOImpl.getEmployeeById(id);
		employeeDAOImpl.getAllEmployee();
		employeeDAOImpl.deleteEmployee(id);
		employeeDAOImpl.updateEmployee(employee);
		employeeDAOImpl.insertEmployeeSkillsById(id,skills);
		employeeDAOImpl.getEmployeeSkillsById(id);
		employeeDAOImpl.deleteEmployeeSkillsById(id, skills);
	}


	
}
