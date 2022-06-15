package pojo;

public class User {
	
	private int id;
	
    private String username;

    private String password;
    
    private String mail;

    public User(int id, String username, String password,String mail) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail=mail;
	}
    public User(String username, String password,String mail) {
    	super();
    	this.username=username;
    	this.password=password;
    	this.mail=mail;
    }
    public User() {
    	
    }


	public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id=id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
    

    

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mail=" + mail + "]";
	}
	@Override
    public boolean equals(Object obj) {

        if(!(obj instanceof User user))
            return false;

        return user.getPassword().equals(getPassword())
                && user.getUsername().equals(getUsername());

    }
}
