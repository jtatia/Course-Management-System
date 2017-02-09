package main.util.course;

public class Course {
	
	public int courseid;
	
	public String batchcode;

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public Course(int courseid, String batchcode) {
		super();
		this.courseid = courseid;
		this.batchcode = batchcode;
	}
	
	

}
