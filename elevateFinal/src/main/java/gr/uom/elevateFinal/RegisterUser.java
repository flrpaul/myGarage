package gr.uom.elevateFinal;

public class RegisterUser {

	private String role;
	private int afm;
	private String companyname;
	private String owner;
	private String password;
	private String fname;
	private String lname;
	private String email;
	
	//Default Constructor
	public RegisterUser() {}
	
	// Prime Constructor
	public RegisterUser(String role, int afm, String companyname, String owner, String password, String fname,
			String lname, String email) {
		this.role = role;
		this.afm = afm;
		this.companyname = companyname;
		this.owner = owner;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}


	//---------- Getters ----------//
	public String getRole() {return role;}
	public int getAfm() {return afm;}
	public String getCompanyname() {return companyname;}
	public String getOwner() {return owner;}
	public String getPassword() {return password;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getEmail() {return email;}
	
	
}
