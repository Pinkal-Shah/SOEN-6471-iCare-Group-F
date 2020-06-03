package iCare;

public class booking {
	private int id;
	private String name, dateTime, department;


 public int getId(){
	 return id; 
	 }
 
	public String getName() {
		return name;
	}

	public String getDateTime() {
		return dateTime;
	}
	
	public String getDepartment() {
		return department;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id= id;
	}
	
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
}