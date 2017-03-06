package main.professor.professor;

public class Professor {

	private int sno;
	
	private String username;
	
	private String firstname;
	
	private String middlename;
	
	private String lastname;
	
	private String email;
	
	private char sex;
	
	private String password;
	
	private String securityques;
	
	private String answer;
	
	private String courseid1;
	
	private String courseid2;
	
	private String courseid3;
	
	private String courseid4;
	
	private String courseid5;

	public Professor(int sno, String username, String firstname, String middlename, String lastname, String email,
			char sex, String password, String securityques, String answer, String courseid1, String courseid2,
			String courseid3, String courseid4, String courseid5) {
		super();
		this.sno = sno;
		this.username = username;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.sex = sex;
		this.password = password;
		this.securityques = securityques;
		this.answer = answer;
		this.courseid1 = courseid1;
		this.courseid2 = courseid2;
		this.courseid3 = courseid3;
		this.courseid4 = courseid4;
		this.courseid5 = courseid5;
	}

	public Professor(String username, String firstname, String middlename, String lastname, String email, char sex,
			String password, String securityques, String answer, String courseid1, String courseid2, String courseid3,
			String courseid4, String courseid5) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.sex = sex;
		this.password = password;
		this.securityques = securityques;
		this.answer = answer;
		this.courseid1 = courseid1;
		this.courseid2 = courseid2;
		this.courseid3 = courseid3;
		this.courseid4 = courseid4;
		this.courseid5 = courseid5;
	}

	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}

	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the courseid1
	 */
	public String getCourseid1() {
		return courseid1;
	}

	/**
	 * @param courseid1 the courseid1 to set
	 */
	public void setCourseid1(String courseid1) {
		this.courseid1 = courseid1;
	}

	/**
	 * @return the courseid2
	 */
	public String getCourseid2() {
		return courseid2;
	}

	/**
	 * @param courseid2 the courseid2 to set
	 */
	public void setCourseid2(String courseid2) {
		this.courseid2 = courseid2;
	}

	/**
	 * @return the courseid3
	 */
	public String getCourseid3() {
		return courseid3;
	}

	/**
	 * @param courseid3 the courseid3 to set
	 */
	public void setCourseid3(String courseid3) {
		this.courseid3 = courseid3;
	}

	/**
	 * @return the courseid4
	 */
	public String getCourseid4() {
		return courseid4;
	}

	/**
	 * @param courseid4 the courseid4 to set
	 */
	public void setCourseid4(String courseid4) {
		this.courseid4 = courseid4;
	}

	/**
	 * @return the courseid5
	 */
	public String getCourseid5() {
		return courseid5;
	}

	/**
	 * @param courseid5 the courseid5 to set
	 */
	public void setCourseid5(String courseid5) {
		this.courseid5 = courseid5;
	}
	
	
}
