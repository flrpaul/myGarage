package gr.uom.elevateFinal;



import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloServiceConfig implements CommandLineRunner {

	@Autowired
	private HelloService hs;

	@Override
	public void run(String... args) throws Exception {
		
		//-------------------------   DealerShips    -----------------------//
		
		DealerShip d1 = new DealerShip(1002, "Audi","Kostas", "kostas90@0");
		hs.addDealerShip(d1);
		DealerShip d2 = new DealerShip(1003,"BMW","Makis", "makis52@2");
		hs.addDealerShip(d2);
		DealerShip d3 = new DealerShip(1004,"Toyota", "Themis", "themisto@0");
		hs.addDealerShip(d3);
		
		
		//--------------------------	Cars   ----------------------------//
		
		Car c1 = new Car("Audi", "R8", "gasoline", "V10 Plus", 2, 253.290,"A luxury Car",2);
		c1.setDealerShip(d1);
		hs.addCar(c1);
		
		Car c2 = new Car("BMW", "M4-COMPETITION ", "gasoline", "Twin-turbocharged 3.0L", 4, 249.999,"A perfect Car",15);
		c2.setDealerShip(d2);
		hs.addCar(c2);
		
		Car c3 = new Car("Toyota", "Supra", "gasoline", "turbocharged B58 3.0L", 4, 74.459,"Old but gold", 10);
		c3.setDealerShip(d3);
		hs.addCar(c3);
		
		Car c4 = new Car("Tesla", "S Plaid", "Electric", "tri-motor", 4, 89.989,"Once you get it, you wont care about services", 20);
		c4.setDealerShip(d3);
		hs.addCar(c4);
		
		Car c5 = new Car("Audi", "RS7", "gasoline", "twin-turbo 4.0L V8", 4, 174.979,"A menace", 6);
		c5.setDealerShip(d1);
		hs.addCar(c5);
		
		Car c6 = new Car("Toyota", "Cross", "gasoline", "2.0L M20A-FKS", 5, 30.759,"A family car", 10);
		c6.setDealerShip(d3);
		hs.addCar(c6);
		
		
		//------------------------ 	Citizens  ---------------------------------//
		
		Citizen citizen1 = new Citizen(123321,"Giorgos", "Petmezas", "giorgospetm@gmail.com","apassword");
		hs.addCitizen(citizen1);
		
		Citizen citizen2 = new Citizen(908070,"Joe", "Papagewrgiou", "joepap@gmail.com","APASSWORD");
		hs.addCitizen(citizen2);
		
		Citizen citizen3 = new Citizen(102030, "Mpampis", "Pap", "mpampispap@gmail.com", "nopassword");
		hs.addCitizen(citizen3);
		
		Citizen citizen4 = new Citizen(102030, "Kostas", "apostolou", "kostasap@yahoo.com", "kostaspassword");
		hs.addCitizen(citizen4);
		
		
		//------------------------- Date & Time for Bookings ---------------------------------//
		
		LocalTime lt = LocalTime.now();
		LocalDate ld = LocalDate.now();
		
		LocalTime lt2 = LocalTime.of(16, 25);
		LocalDate ld2 = LocalDate.of(2024, 11, 19);
		
		LocalTime lt4 = LocalTime.of(14, 58);
		LocalDate ld4 = LocalDate.of(2024, 12, 30);
		
		LocalTime lt5 = LocalTime.of(16, 30);
		LocalDate ld5 = LocalDate.of(2024, 11, 29);
		
		
		//-------------------------    Bookings   ----------------------------------------------//
		
		Booking b1 = new Booking(10L,citizen1,c2,ld,lt);
		hs.addBooking(b1);
		
		Booking b2 = new Booking(11L, citizen2,c2, ld2,lt2);
		hs.addBooking(b2);
		
		Booking b3 = new Booking(20L,citizen1,c3, ld,lt);
		hs.addBooking(b3);
		
		Booking b4 = new Booking(30L, citizen1, c3, ld4,lt4);
		hs.addBooking(b4);
		
		Booking b5 = new Booking(40L, citizen3, c3, ld5, lt5);
		hs.addBooking(b5);
	}
}
