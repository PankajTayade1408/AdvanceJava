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
			preparedstatementForInsert.setString(6, employee.getCountry());
			preparedstatementForInsert.setInt(7,employee.getLoginId());
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
	
	public List<Integer> getLoginId() {
		List<Integer> loginId=new ArrayList<Integer>();
		try {
			PreparedStatement preparedstatementForLoginId = connection
					.prepareStatement(SELECT_ALL_LOGIN_ID_SQL);
			ResultSet resultsetForLoginId = preparedstatementForLoginId.executeQuery();
			while (resultsetForLoginId.next()) {
			int logId = resultsetForLoginId.getInt("loginId");
			loginId.add(logId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginId;
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
				String country=resultSetForGetById.getString("country");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining,country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<EmployeeModel> getAllEmployee(int loginId) {
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		try {
			PreparedStatement preparestatementForGetAllEmployee = connection.prepareStatement(SELECT_EMPLOYEE_BY_LOGIN_ID);
			preparestatementForGetAllEmployee.setInt(1,loginId);
			ResultSet resultsetForGetAllEmployee = preparestatementForGetAllEmployee.executeQuery();
			while (resultsetForGetAllEmployee.next()) {
				int id = resultsetForGetAllEmployee.getInt("id");
				String firstName = resultsetForGetAllEmployee.getString("firstname");
				String lastName = resultsetForGetAllEmployee.getString("lastname");
				String age = resultsetForGetAllEmployee.getString("age");
				String salary = resultsetForGetAllEmployee.getString("salary");
				String dateOfJoining = resultsetForGetAllEmployee.getString("doj");
				String country=resultsetForGetAllEmployee.getString("country");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl(); 
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employeeList.add(new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining,country));
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
			preparedstatementForUpdate.setString(6, employee.getCountry());
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
		EmployeeDAO employeeDAOImpl = new EmployeeDAOImpl();
		employeeDAOImpl.insertEmployee(employee);
		employeeDAOImpl.getEmployeeById(id);
		employeeDAOImpl.getAllEmployee(id);
		employeeDAOImpl.deleteEmployee(id);
		employeeDAOImpl.updateEmployee(employee);
		employeeDAOImpl.insertEmployeeSkillsById(id,skills);
		employeeDAOImpl.getEmployeeSkillsById(id);
		employeeDAOImpl.deleteEmployeeSkillsById(id, skills);
	}

	public ArrayList<String> getEmployeeEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmployeeEmailById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectLatestIdFromEmployee() {
		// TODO Auto-generated method stub
		return 0;
	}
}
