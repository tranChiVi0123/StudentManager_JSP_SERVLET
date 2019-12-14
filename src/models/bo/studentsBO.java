package models.bo;

import java.sql.SQLException;

import models.bean.Student;
import models.dao.studentsDAO;

public class studentsBO {
	public void insertStudentBO(Student student) throws ClassNotFoundException, SQLException {
		studentsDAO.insertStudent(student);
	}
	public void updateStudentBO(Student student) throws ClassNotFoundException, SQLException {
		studentsDAO.updateStudent(student);
	}
	public void deleteStudentBO(int id) throws ClassNotFoundException, SQLException {
		studentsDAO.deleteStudent(id);
	}
}
