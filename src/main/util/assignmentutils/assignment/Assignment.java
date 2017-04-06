package main.util.assignmentutils.assignment;

public class Assignment {
	
	private String lastModified;
	private String size;
	private String name;
	private String path;
	private int marks;
	private String status;
	public double dn_size;
	public double total_size;
	public Assignment()
	{
		super();
	}
	public Assignment(String lastModified, String size, String name, String path) {
		super();
		this.lastModified = lastModified;
		this.size = size;
		this.name = name;
		this.path = path;
	}
	
	

	public Assignment(String lastModified, String size, String name, String path, int marks, String status) {
		super();
		this.lastModified = lastModified;
		this.size = size;
		this.name = name;
		this.path = path;
		this.marks = marks;
		this.status = status;
	}
	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the marks
	 */
	public int getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(int marks) {
		this.marks = marks;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
