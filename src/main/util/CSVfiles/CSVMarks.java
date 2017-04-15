package main.util.CSVfiles;

public class CSVMarks {
	
	private String filename;
	private String marks;
	private String status;
	
	public CSVMarks(String filename, String marks, String status) {
		super();
		this.filename = filename;
		this.marks = marks;
		this.status = status;
	}

	public CSVMarks() {
		super();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
