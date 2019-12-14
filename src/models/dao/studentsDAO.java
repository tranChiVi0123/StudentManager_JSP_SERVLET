package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import models.bean.Student;

public class studentsDAO {
	public static ArrayList<Student> getStudents() throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM student";
		ResultSet rs = mysqlHelper.getResultSet(query);
		ArrayList<Student> students = new ArrayList<Student>();
		while (rs.next()) {
			Student s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getBoolean(6), rs.getFloat(7));
			students.add(s);
		}
		return students;
	}

	public static Student getStudentById(int id) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM student WHERE _id='" + id + "';";
		ResultSet rs = mysqlHelper.getResultSet(query);
		Student s = null;
		while (rs.next()) {
			s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getBoolean(6), rs.getFloat(7));
		}
		return s;
	}

	public static boolean insertStudent(Student student) throws ClassNotFoundException, SQLException {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = formater.format(student.getBirthday());
		String gentle = student.Gentle() ? "1":"0";
		String query = "INSERT INTO student (_id, name, idfalcuty, class, birthday, gentle, score)"
				+ " VALUES ('"+student.getId()+"', N'"+student.getName()+"', '"+student.getIdFalcuty()+"',"
						+ " '"+student.getClassof()+"', '"+birthday+"', b'"+gentle+"', '"+student.getScore()+"');";
		boolean i = mysqlHelper.executeQueryMySQL(query);
		return i;
	}
	public static boolean updateStudent(Student student) throws ClassNotFoundException, SQLException {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = formater.format(student.getBirthday());
		String gentle = student.Gentle() ? "1":"0";
		String query = "UPDATE student SET name=N'"+student.getName()+"', idfalcuty='"+student.getIdFalcuty()+
				"' ,class = '"+student.getClassof()+"',birthday='"+birthday+"' ,gentle=b'"+gentle+"', score='"+student.getScore()+"'"
				+" WHERE _id='"+student.getId()+"';";
		boolean i = mysqlHelper.executeQueryMySQL(query);
		return i;
	}
	public static boolean deleteStudent(int id) throws ClassNotFoundException, SQLException {
		String query = "DELETE FROM student WHERE _id='"+id+"';";
		boolean i = mysqlHelper.executeQueryMySQL(query);
		return i;
	}
}
