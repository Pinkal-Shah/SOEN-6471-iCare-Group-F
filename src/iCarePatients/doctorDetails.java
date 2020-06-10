package iCarePatients;

public class doctorDetails {
	private int id;
	private String name, department;


 public int getId(){
	 return id; 
	 }
 
	public String getName() {
		return name;
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
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
}