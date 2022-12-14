package employeecrudmaven.dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

import employeecrudmaven.dao.EmployeeDAO;
import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.dao.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	static Connection connection = DBConnection.getConnection();

	public void insertEmployee(EmployeeModel employee, FileInputStream fileInputStream) {
		try {
<<<<<<< HEAD
			PreparedStatement preparedstatementForInsert = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			preparedstatementForInsert.setString(1, employee.getFirstname());
			preparedstatementForInsert.setString(2, employee.getLastname());
			preparedstatementForInsert.setString(3, employee.getAge());
			preparedstatementForInsert.setString(4, employee.getSalary());
			preparedstatementForInsert.setString(5, employee.getDoj());
			preparedstatementForInsert.setString(6, employee.getEmail());
			preparedstatementForInsert.executeUpdate();
=======
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1, employee.getFirstname());
			preparedStatement.setString(2, employee.getLastname());
			preparedStatement.setInt(3, employee.getAge());
			preparedStatement.setDouble(4, employee.getSalary());
			preparedStatement.setString(5, employee.getDoj());
			preparedStatement.setString(6, employee.getCountry());
			preparedStatement.setBinaryStream(7, fileInputStream);
			preparedStatement.setInt(8, employee.getLoginId());
			preparedStatement.setString(9, employee.getfileName());
			preparedStatement.executeUpdate();
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
	public int selectLatestIdFromEmployee() {
=======
	public static int selectLatestIdFromEmployee(int id) {
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		int employeeId = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LATEST_ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employeeId = resultSet.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employeeId;
	}
<<<<<<< HEAD
	
	public ArrayList<String> getEmployeeEmail() {
		ArrayList<String> emailList = new ArrayList<String>();
		try {
			PreparedStatement preparedstatementForAllEmail = connection
					.prepareStatement(SELECT_ALL_EMAIL_FROM_EMPLOYEE_SQL);
			ResultSet resultsetForAllEmail = preparedstatementForAllEmail.executeQuery();
			while (resultsetForAllEmail.next()) {
				String email = resultsetForAllEmail.getString("email");
				emailList.add(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailList;
	}

	public String getEmployeeEmailById(int id) {
		EmployeeModel employee = new EmployeeModel();
		String employeeEmail = null;
		try {
			PreparedStatement preparedstatementForgetEmailById = connection
					.prepareStatement(SELECT_EMPLOYEE_EMAIL_BY_ID);
			preparedstatementForgetEmailById.setInt(1, id);
			ResultSet resultsetForGetEmailById = preparedstatementForgetEmailById.executeQuery();
			while (resultsetForGetEmailById.next()) {
				employeeEmail = resultsetForGetEmailById.getString("email");
=======

	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SKILLS);
			LinkedHashSet<String> checkedSkillsSet = new LinkedHashSet<String>();
			preparedStatement.setInt(1, id);
			checkedSkillsSet = skills;
			for (String checkedSkills : checkedSkillsSet) {
				preparedStatement.setString(2, checkedSkills);
				preparedStatement.executeUpdate();
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeEmail;
	}

	public EmployeeModel getEmployeeById(int id) {
		EmployeeModel employee = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
<<<<<<< HEAD
			while (resultSetForGetById.next()) {
				id = resultSetForGetById.getInt("id");
				String firstName = resultSetForGetById.getString("firstname");
				String lastName = resultSetForGetById.getString("lastname");
				String age = resultSetForGetById.getString("age");
				String salary = resultSetForGetById.getString("salary");
				String dateOfJoining = resultSetForGetById.getString("doj");
				String email = resultSetForGetById.getString("email");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining, email);
=======
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				int age = Integer.parseInt(resultSet.getString("age"));
				double salary = Double.parseDouble(resultSet.getString("salary"));
				String dateOfJoining = resultSet.getString("doj");
				String country = resultSet.getString("country");
				String path=resultSet.getString("fileName"); 
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				Blob image;
				byte[] profilePicture;
				image = resultSet.getBlob(10);
				profilePicture = image.getBytes(1, (int) image.length());
				employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining, country,
						profilePicture,path);
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
<<<<<<< HEAD
	
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
				String email = resultsetForGetAllEmployee.getString("email");
				LinkedHashSet<String> skills = new LinkedHashSet<String>();
				EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
				skills = employeeDAOImpl.getEmployeeSkillsById(id);
				employeeList.add(new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining, email));
=======

	public List<EmployeeModel> getAllEmployee(int loginId) {
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_ID);
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
				Blob image = null;
				byte[] profilePicture;
				image = resultSet.getBlob(10);
				if (image != null) {
					profilePicture = image.getBytes(1, (int) image.length());
					employeeList.add(new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining,
							country, profilePicture));
				}
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public boolean updateEmployee(EmployeeModel employee, FileInputStream fileInputStream) {
		boolean udpatedEmployeeDetail = false;
<<<<<<< HEAD
		try (PreparedStatement preparedstatementForUpdate = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			preparedstatementForUpdate.setString(1, employee.getFirstname());
			preparedstatementForUpdate.setString(2, employee.getLastname());
			preparedstatementForUpdate.setString(3, employee.getAge());
			preparedstatementForUpdate.setString(4, employee.getSalary());
			preparedstatementForUpdate.setString(5, employee.getDoj());
			preparedstatementForUpdate.setString(6, employee.getEmail());
			preparedstatementForUpdate.setInt(7, employee.getId());
			udpatedEmployeeDetail = preparedstatementForUpdate.executeUpdate() > 0;
=======
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);) {
			preparedStatement.setString(1, employee.getFirstname());
			preparedStatement.setString(2, employee.getLastname());
			preparedStatement.setInt(3, employee.getAge());
			preparedStatement.setDouble(4, employee.getSalary());
			preparedStatement.setString(5, employee.getDoj());
			preparedStatement.setString(6, employee.getCountry());
			preparedStatement.setBinaryStream(7, fileInputStream);
			preparedStatement.setString(8, employee.getfileName());
			preparedStatement.setInt(9, employee.getId());
			udpatedEmployeeDetail = preparedStatement.executeUpdate() > 0;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		} catch (Exception e) {
			e.printStackTrace();
		}
		return udpatedEmployeeDetail;
	}

	public boolean deleteEmployee(int id) throws Exception {
		boolean deleteEmployee = false;
<<<<<<< HEAD
		try (PreparedStatement preparedstatementForDelete = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);) {
			preparedstatementForDelete.setInt(1, id);
			deleteEmployee = preparedstatementForDelete.executeUpdate() > 0;
=======
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);) {
			preparedStatement.setInt(1, id);
			deleteEmployee = preparedStatement.executeUpdate() > 0;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		}
		return deleteEmployee;
	}
	
	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills) {
		try {
			PreparedStatement preparedstatatementForInsertSkills = connection
					.prepareStatement(INSERT_EMPLOYEE_SKILLS_SQL);
			LinkedHashSet<String> checkSkillsSet = new LinkedHashSet<String>();
			preparedstatatementForInsertSkills.setInt(1, id);
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

	public LinkedHashSet<String> getEmployeeSkillsById(int id) {
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		try {
<<<<<<< HEAD
			PreparedStatement preparedstatementForGetSkillsById = connection
					.prepareStatement(SELECT_EMPLOYEE_SKILL_BY_ID_SQL);
			preparedstatementForGetSkillsById.setInt(1, id);
			ResultSet resultSetForGetSkillsById = preparedstatementForGetSkillsById.executeQuery();
			LinkedHashSet<String> skillsFromDB = new LinkedHashSet<String>();
			while (resultSetForGetSkillsById.next()) {
				String backEndSkills = resultSetForGetSkillsById.getString("empskills");
				skillsFromDB.add(backEndSkills);
				skills = (LinkedHashSet) skillsFromDB.clone();
=======
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SKILL_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			LinkedHashSet<String> skillsFromBackEnd = new LinkedHashSet<String>();
			while (resultSet.next()) {
				String backEndSkills = resultSet.getString("empskills");
				skillsFromBackEnd.add(backEndSkills);
				skills = (LinkedHashSet) skillsFromBackEnd.clone();
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
			}
			skillsFromDB.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return skills;
	}

	public void deleteEmployeeSkillsById(int employeeId, LinkedHashSet<String> skills) {
		try {
<<<<<<< HEAD
			PreparedStatement preparedstatementForDeleteSkills = connection
					.prepareStatement(DELETE_EMPLOYEE_SKILLS_SQL);
			preparedstatementForDeleteSkills.setInt(1, employeeId);
			for (String deleteSkills : skills) {
				preparedstatementForDeleteSkills.setString(2, deleteSkills);
				preparedstatementForDeleteSkills.executeUpdate();
=======
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SKILLS);
			preparedStatement.setInt(1, employeeId);
			for (String skillsToBeDelete : skills) {
				preparedStatement.setString(2, skillsToBeDelete);
				preparedStatement.executeUpdate();
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
=======

>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
}
