package main.student.student;

public class Student {

	private int id;
	
	private String batch;
	
	private String rollno;
	
	private String firstname;
	
	private String middlename;
	
	private String lastname;
	
	private char sex;
	
	private int age;
	
	private String email;
	
	private String password;
	
	private String securityques;

	private String answer;
	
	private String subject1;
	
	private String subject2;
	
	private String subject3;
	
	private String subject4;
	
	private String subject5;
	
	private String subject6;
	
	private String subject7;
	
	private String subject8;
	
	private String subject9;
	
	private String subject10;
	
	public Student()
	{
		
	}
	
	public Student(int id,  String rollno, String firstname, String middlename, String lastname, char sex, int age,
			String email, String batch,String password, String securityques, String answer, String subject1, String subject2,
			String subject3, String subject4, String subject5, String subject6, String subject7, String subject8,
			String subject9, String subject10) {
		this();
		this.id = id;
		this.batch=batch;
		this.rollno = rollno;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.password = password;
		this.securityques = securityques;
		this.answer = answer;
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
		this.subject4 = subject4;
		this.subject5 = subject5;
		this.subject6 = subject6;
		this.subject7 = subject7;
		this.subject8 = subject8;
		this.subject9 = subject9;
		this.subject10 = subject10;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the rollno
	 */
	public String getRollno() {
		return rollno;
	}

	/**
	 * @param rollno the rollno to set
	 */
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the middlename
	 */
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * @param middlename the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the sex
	 */
	public char getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the securityques
	 */
	public String getSecurityques() {
		return securityques;
	}

	/**
	 * @param securityques the securityques to set
	 */
	public void setSecurityques(String securityques) {
		this.securityques = securityques;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the subject1
	 */
	public String getSubject1() {
		return subject1;
	}

	/**
	 * @param subject1 the subject1 to set
	 */
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	/**
	 * @return the subject2
	 */
	public String getSubject2() {
		return subject2;
	}

	/**
	 * @param subject2 the subject2 to set
	 */
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	/**
	 * @return the subject3
	 */
	public String getSubject3() {
		return subject3;
	}

	/**
	 * @param subject3 the subject3 to set
	 */
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	/**
	 * @return the subject4
	 */
	public String getSubject4() {
		return subject4;
	}

	/**
	 * @param subject4 the subject4 to set
	 */
	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}

	/**
	 * @return the subject5
	 */
	public String getSubject5() {
		return subject5;
	}

	/**
	 * @param subject5 the subject5 to set
	 */
	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}

	/**
	 * @return the subject6
	 */
	public String getSubject6() {
		return subject6;
	}

	/**
	 * @param subject6 the subject6 to set
	 */
	public void setSubject6(String subject6) {
		this.subject6 = subject6;
	}

	/**
	 * @return the subject7
	 */
	public String getSubject7() {
		return subject7;
	}

	/**
	 * @param subject7 the subject7 to set
	 */
	public void setSubject7(String subject7) {
		this.subject7 = subject7;
	}

	/**
	 * @return the subject8
	 */
	public String getSubject8() {
		return subject8;
	}

	/**
	 * @param subject8 the subject8 to set
	 */
	public void setSubject8(String subject8) {
		this.subject8 = subject8;
	}

	/**
	 * @return the subject9
	 */
	public String getSubject9() {
		return subject9;
	}

	/**
	 * @param subject9 the subject9 to set
	 */
	public void setSubject9(String subject9) {
		this.subject9 = subject9;
	}

	/**
	 * @return the subject10
	 */
	public String getSubject10() {
		return subject10;
	}

	/**
	 * @param subject10 the subject10 to set
	 */
	public void setSubject10(String subject10) {
		this.subject10 = subject10;
	}
	
	/**
	 * @return the batch
	 */
	public String getBatch() {
		return batch;
	}

	/**
	 * @param batch the batch to set
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	public String toString() {
		return firstname+" "+middlename+" "+lastname;
	}
	
}
