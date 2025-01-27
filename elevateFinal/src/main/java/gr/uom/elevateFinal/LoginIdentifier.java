package gr.uom.elevateFinal;

public class LoginIdentifier {

	private String email;
	private int afm;
	private String password;
	private String role;
	
	
	//Default Constructor
	public LoginIdentifier() {}
	
	//Optimal Constructor
	public LoginIdentifier(String email, int afm,String password, String role) {
		this.afm = afm;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	// ---- Getters ---- //
	public String getEmail() {return email;}
	public int getAfm() {return afm;}
	public String getPassword() {return password;}
	public String getRole() {return role;}
}
