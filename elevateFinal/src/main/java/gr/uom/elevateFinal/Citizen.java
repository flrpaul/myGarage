package gr.uom.elevateFinal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Citizen{

	@Id
	private String fname;
	
	@Column(nullable=false)
	private int afm;
	
	private String lname;
	private String email;
	private String password;
	

	//OneToMany
	@OneToMany(mappedBy = "citizen")
	private List<Booking> bookings = new ArrayList<Booking>();
	
	
	
	
	//Default Constructor
	public Citizen() {}
	
	
	public Citizen(int afm, String fname, String lname, String email, String password) {
		this.afm = afm;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
	}

	//Citizen Getters
	public int getAfm() {return afm;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	
	
	//OneToMany citizens me bookings
	public List<Booking> getBooking() {return bookings;}
	public void setBooking(List<Booking> b) {
		bookings = b;
	}
	
}
