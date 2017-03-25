package main.professor.professor;

// password for username samrat is currently Samrat86
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
	
	public String courseids[];
	
	public Professor()
	{
		
	}

	public Professor(int sno, String username, String firstname, String middlename, String lastname, String email,
			char sex, String password, String securityques, String answer, String courseids[]) {
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
		this.courseids=courseids;
	}

	public Professor(String username, String firstname, String middlename, String lastname, String email, char sex,
			String password, String securityques, String answer, String courseids[]) {
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
		this.courseids=courseids;
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
	 * @return the courseids[]
	 */
	public String[] getCourseids() {
		return courseids;
	}

	/**
	 * @param courseids the courseids[] to set
	 */
	public void setCourseids(String courseids[]) {
		this.courseids = courseids;
	}
	
	public String toString()
	{
		return firstname+" "+middlename+" "+lastname;
		
	}
}
