package pojo;

public class userRole {
	
	private int userId;
	
	private int roleID;

	public userRole(int userId, int roleID) {
		super();
		this.userId = userId;
		this.roleID = roleID;
	}
	
	public userRole() {
		
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	@Override
	public String toString() {
		return "userRole [userId=" + userId + ", roleID=" + roleID + "]";
	}
	
	

}
