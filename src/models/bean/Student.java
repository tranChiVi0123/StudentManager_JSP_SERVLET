package models.bean;

import java.util.Date;

public class Student {
	private int id;
	private String name;
	private String idFalcuty;
	private String classof;
	private boolean gentle;
	private Date birthday;
	private float score;

	public Student(int id, String name, String idFalcuty, String classof, Date birthday, boolean gentle, float score) {
		super();
		this.id = id;
		this.name = name;
		this.idFalcuty = idFalcuty;
		this.classof = classof;
		this.gentle = gentle;
		this.birthday = birthday;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdFalcuty() {
		return idFalcuty;
	}

	public void setIdFalcuty(String idFalcuty) {
		this.idFalcuty = idFalcuty;
	}

	public String getClassof() {
		return classof;
	}

	public void setClassof(String classof) {
		this.classof = classof;
	}

	public boolean Gentle() {
		return gentle;
	}

	public void setGentle(boolean gentle) {
		this.gentle = gentle;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
