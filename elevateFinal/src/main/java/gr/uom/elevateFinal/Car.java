package gr.uom.elevateFinal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Car {

	@Id
	private String model;

	private String brand;
	private String fuelType;
	private String engine;
	private int seats;
	private double price;
	private String info;
	private int availability;
	
	//One car can have Many bookings
	@OneToMany(mappedBy="car", cascade = CascadeType.ALL)
	private List<Booking> bookings = new ArrayList<>();
	
	
	//ManyToOne car me dealership
	@ManyToOne
	@JoinColumn(name="dealership_owner")
	private DealerShip dealership;
	
	
	//Default Constructor
	public Car() {}
	
	public Car( String brand, String model, String fuelType, String engine, int seats, double price,String info, int availability) {
		this.brand = brand;
		this.model = model;
		this.fuelType = fuelType;
		this.engine = engine;
		this.seats = seats;
		this.price = price;
		this.info = info;
		this.availability = availability;
	}

	//Car Getters
	public String getBrand() {return brand;}
	public String getModel() {return model;}
	public String getFuelType() {return fuelType;}
	public String getEngine() {return engine;}
	public int getSeats() {return seats;}
	public double getPrice() {return price;}
	public String getInfo() {return info;}
	public int getAvailability() {return availability;}
	
	public int setAvailability(int availability) {
		return this.availability = availability;
	}

	//------------------------------------------------------------------------------------------//
	//car-booking 
	public void addBooking(Booking b) {
		bookings.add(b);
	}
	
	//------------------------------------------------------------------------------------------//
	
	//---------------------------------------------------------------------------------------//
									//car-dealership ManyToOne
	public void setDealerShip(DealerShip d) {
		dealership = d;
	}
	
	public DealerShip getDealerShip() {return dealership;}
	//----------------------------------------------------------------------------------------//

	//Method used in HelloService for bookTestDrive
	public void decreaseAvailability() throws Exception {
		if(this.availability >0) {
			this.availability-- ;
		} else {
			throw new Exception("Car not available for test drive");
		}
	}
}
