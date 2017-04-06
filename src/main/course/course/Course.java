package main.course.course;

public class Course {
	
	private int serialNo;   	// primary key, not Null , auto-increment
	
	private String courseId; 	// primary key, not Null  
	
	private String courseName;	// full name of course e.g. Algorithms

	private String courseInfo;	// String type course Info (longtext/blob in the database) 
	
	public Course()
	{
		
	}
	
	public Course(int serialNo,String courseId,String courseName,String courseInfo)
	{
		this();
		this.serialNo=serialNo;
		this.courseInfo=courseInfo;
		this.courseName=courseName;
		this.courseId=courseId;
	}
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseInfo() {
		//System.out.println("Inside the COURSE FUNCTION");
		return courseInfo;
	}

	public void setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}
	
	public String toString()
	{
		return courseId+" - "+courseName; 
	}
}
