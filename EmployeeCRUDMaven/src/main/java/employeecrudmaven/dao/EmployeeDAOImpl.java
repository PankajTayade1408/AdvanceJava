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
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			preparedStatement.setString(1, employee.getFirstname());
			preparedStatement.setString(2, employee.getLastname());
			preparedStatement.setInt(3, employee.getAge());
			preparedStatement.setDouble(4, employee.getSalary());
			preparedStatement.setString(5, employee.getDoj());
			preparedStatement.setString(6, employee.getCountry());
			preparedStatement.setInt(7, employee.getLoginId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int selectLatestIdFromEmployee(int id) {
		int employeeId = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LATEST_ID_FROM_EMPLOYEE_SQL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employeeId = resultSet.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeId;
	}

	public List<Integer> getLoginId() {
		List<Integer> loginId = new ArrayList<Integer>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOGIN_ID_SQL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int logId = resultSet.getInt("loginId");
				loginId.add(logId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginId;
	}

	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SKILLS_SQL);
			LinkedHashSet<String> checkedSkillsSet = new LinkedHashSet<String>();
			preparedStatement.setInt(1, id);
			checkedSkillsSet = skills;
			for (String checkedSkills : checkedSkillsSet) {
				preparedStatement.setString(2, checkedSkills);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public EmployeeModel getEmployeeById(int id) {
		EmployeeModel employee = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				int age = Integer.parseInt(resultSet.getString("age"));
				double salary = Double.parseDouble(resultSet.getString("salary"));
				String dateOfJoining = resultSet.getString("doj");
				String country = resultSet.getString("country");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining, country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public List<EmployeeModel> getAllEmployee(int loginId) {
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_LOGIN_ID);
			preparedStatement.setInt(1, loginId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				int age = Integer.parseInt(resultSet.getString("age"));
				double salary = Double.parseDouble(resultSet.getString("salary"));
				String dateOfJoining = resultSet.getString("doj");
				String country = resultSet.getString("country");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employeeList
						.add(new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining, country));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public boolean updateEmployee(EmployeeModel employee) {
		boolean udpatedEmployeeDetail = false;
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			preparedStatement.setString(1, employee.getFirstname());
			preparedStatement.setString(2, employee.getLastname());
			preparedStatement.setInt(3, employee.getAge());
			preparedStatement.setDouble(4, employee.getSalary());
			preparedStatement.setString(5, employee.getDoj());
			preparedStatement.setString(6, employee.getCountry());
			preparedStatement.setInt(7, employee.getId());
			udpatedEmployeeDetail = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return udpatedEmployeeDetail;
	}

	public boolean deleteEmployee(int id) throws Exception {
		boolean deleteEmployee = false;
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);) {
			preparedStatement.setInt(1, id);
			deleteEmployee = preparedStatement.executeUpdate() > 0;
		}
		return deleteEmployee;
	}

	public LinkedHashSet<String> getEmployeeSkillsById(int id) {
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_SKILL_BY_ID_SQL);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			LinkedHashSet<String> skillsFromBackEnd = new LinkedHashSet<String>();
			while (resultSet.next()) {
				String backEndSkills = resultSet.getString("empskills");
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
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SKILLS_SQL);
			preparedStatement.setInt(1, employeeId);
			for (String skillsToBeDelete : skills) {
				preparedStatement.setString(2, skillsToBeDelete);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
