package gr.uom.elevateFinal;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	@Autowired
	private CarRepository carrepository;
	
	@Autowired
	private CitizenRepository citizenrepository;
	
	@Autowired
	private DealerShipRepository dealershiprepository;
	
	@Autowired
	private BookingRepository bookingrepository;
	
	
	//Car entity addition and get
	public void addCar(Car c) throws Exception {
		carrepository.save(c);
	}
	public List<Car> getAllCars() throws Exception {
		return carrepository.findAll();
	}

	
	//Citizen entity addition on config
	public void addCitizen(Citizen citizen) throws Exception {
		Optional<Citizen> byId = citizenrepository.findById(citizen.getFname());
		if(!byId.isPresent()) {
			citizenrepository.save(citizen);
		}
	}
	//DealerShip entity addition on config
	public void addDealerShip(DealerShip d) throws Exception{
		Optional<DealerShip> byId = dealershiprepository.findById(d.getAfm());
		if(!byId.isPresent()) {
			dealershiprepository.save(d);
		}
	}
	
	//Booking entity addition on config
	public void addBooking(Booking b) throws Exception {
		bookingrepository.save(b);
	}
	
	
	//------Logic for searching cars based on criteria (brand, model, fueltype, engine)  -----//
	//It works
	//4 E(ANAZHTHSH AUTOKINHTOU COMPLETE)
	public List<Car> searchCars(String brand, String model, String fuelType, String engine) throws Exception {
		if (brand !=null && model !=null && fuelType !=null && engine !=null) {
			return carrepository.findByBrandAndModelAndFuelTypeAndEngine(brand, model, fuelType,engine);
		}
		else if (brand !=null && model !=null && fuelType !=null) {
			return carrepository.findByBrandAndModelAndFuelType(brand, model, fuelType);
		}
		else if (brand !=null && model !=null && engine !=null) {
			return carrepository.findByBrandAndModelAndEngine(brand, model,engine);
		}
		else if (brand !=null && engine !=null) {
			return carrepository.findByBrandAndEngine(brand, engine);
		}
		else if(brand!= null && model !=null) {
			return carrepository.findByBrandAndModel(brand, model);		
		}
		else if(brand != null && fuelType !=null) {
			return carrepository.findByBrandAndFuelType(brand, fuelType);
		}
		
		else if(brand !=null) {
			return carrepository.findByBrand(brand);
		}
		else if(model !=null) {
			return carrepository.findByModel(model);
		}
		else if (engine !=null) {
			return carrepository.findByEngine(engine);
		}
		else if(fuelType !=null) {
			return carrepository.findByFuelType(fuelType);
		}
		else {
			return carrepository.findAll();
		}
	}
	
	//--------------------------- LEITOURGIA 1 ----------------------------------//
	public Citizen loginCitizen(String email, String passwordentered) throws Exception {
		return citizenrepository.findByEmail(email).filter(citizen -> citizen.getPassword().equals(passwordentered))
				.orElseThrow( () -> new Exception("Invalid email or password"));
	}
	
	public DealerShip loginDealerShip(int afm, String password) throws Exception {
		return dealershiprepository.findById(afm)
		        .filter(dealer -> dealer.getPassword().equals(password))
		        .orElseThrow(() -> new Exception("Invalid owner or password"));
	}
	
	
	//------------------------- LEITOURGIA 2 ------------------------------------//
	public void registerCitizen(Citizen citizen) throws Exception {
		if(citizenrepository.existsById(citizen.getFname())) {
			throw new Exception("Citizen already exists");
		}
		citizenrepository.save(citizen);
	}
	
	public void registerDealerShip(DealerShip dealership) throws Exception {
		if(dealershiprepository.existsById(dealership.getAfm())) {
			throw new Exception("Dealership already exists");
		}
		dealershiprepository.save(dealership);
	}
	
	//--------------------- LEITOURGIA 4 -----------------------------------------//
	
	public void updateCarAvailability(String carId, int newavailability) throws Exception {
		Car car = carrepository.findById(carId).orElseThrow( ()-> new Exception("Car not found"));
		car.setAvailability(newavailability);
		carrepository.save(car);
		System.out.println("You changed " + carId +"'s availability to: " + newavailability);
	}
	
	//------------------ LEITOURGIA 6 -------------------------------------------//

	public void bookTestDrive(Booking booking) throws Exception {
		
		LocalDateTime bookingDateTime = LocalDateTime.of(booking.getDate(), booking.getTime());
		LocalDateTime now = LocalDateTime.now();
		
		if( bookingDateTime.isBefore(now)) {
			throw new Exception("People usually not book past bookings. Think about future ones.");
		}
		
		Car car = carrepository.findById(booking.getCar().getModel())
				.orElseThrow( () -> new Exception("Car not found"));
		
		System.out.println("Car Availability before test drive " + car.getAvailability());
		
		if (car.getAvailability() > 0 ) {
//			car.setAvailability(car.getAvailability() -1);
			car.decreaseAvailability();
			carrepository.save(car);
			bookingrepository.save(booking);
			System.out.println("Car Availability after test  drive " + car.getAvailability());
		} else {
			throw new Exception("Car not available for Test Drive");
		}
	}
	
	//------------------ LEIOURGIA 7 -----------------------------------//
	public void purchaseCar(String carId) throws Exception {
		Car car = carrepository.findById(carId).orElseThrow( () -> new Exception("Car not found"));
		
		System.out.println("Car Availability before purchase " + car.getAvailability());
		
		if(car.getAvailability() > 0) {
			car.decreaseAvailability();
			carrepository.save(car);
			
		System.out.println("Car availability after purchase " + car.getAvailability());
		
		} else {
			throw new Exception("Car out of stock");
		}
	}
	

	
	
}
