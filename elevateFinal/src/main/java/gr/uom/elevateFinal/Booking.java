package gr.uom.elevateFinal;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//many bookings from 1 citizen
	@ManyToOne
	@JoinColumn(name = "citizen_name")
	private Citizen citizen;
	
	//many bookings for 1 car
	@ManyToOne
	@JoinColumn(name = "car_model")
	private Car car;
	
	private LocalDate date;
	private LocalTime time;
	
	//Default Constructor
	public Booking() {}
	
	public Booking(Long id,Citizen citizen, Car car, LocalDate date, LocalTime time) {
		this.id= id;
		this.citizen = citizen;
		this.car = car;
		this.date = date;
		this.time = time;
	}
	
	//Booking Getters
	public Long getId() {return id;}
	public Citizen getCitizen() {return citizen;}
	public Car getCar() {return car;}
	public LocalDate getDate() {return date;}
	public LocalTime getTime() {return time;}


}
