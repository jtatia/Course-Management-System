package main.student.coursepanel.objects;

public class UploadInfo {
	
	private String lastModified;
	private String size;
	private String name;
	private String parentDir;
	

	public UploadInfo() {
		super();
	}

	public UploadInfo(String lastModified, String size, String name,String parentDir) {
		super();
		this.lastModified = lastModified;
		this.size = size;
		this.name = name;
		this.parentDir=parentDir;
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
	
	
	public String getParentDir() {
		return parentDir;
	}

	public void setParentDir(String parentDir) {
		this.parentDir = parentDir;
	}


}

