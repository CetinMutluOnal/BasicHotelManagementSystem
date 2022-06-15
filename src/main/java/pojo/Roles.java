package pojo;

public class Roles {

	private int id;
	
	private String roleName;
	
	public Roles (int id, String roleName) {
		this.id=id;
		this.roleName=roleName;
		
	}
	public Roles() {
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id=id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName=roleName;
	}
	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + "]";
	}
	
}
