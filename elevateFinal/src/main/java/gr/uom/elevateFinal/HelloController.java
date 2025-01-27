package gr.uom.elevateFinal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class HelloController {

	@Autowired
	private HelloService hs;
	
	
	@GetMapping(path="/cars")
	public List<Car> getAllCars() throws Exception {
		return hs.getAllCars();
	}
	//------------------------ Leitourgia 3 ----------------//
	@PostMapping(path="/addcar")
	public void addCar(@RequestBody Car c) throws Exception {
		System.out.println("Received Car: " + c);
		System.out.println("Received Dealership ID: " + c.getDealerShip().getAfm());
		hs.addCar(c);
	}
	
	//-------------------- Leitourgia 5  -------------------//
	//It works, try Localhost/searchCars?brand=Audi
	@GetMapping(path="/searchcars")
	public List<Car> searchCars(@RequestParam(required = false) String brand,
								@RequestParam(required = false) String model,
								@RequestParam(required = false) String fuelType,
								@RequestParam(required = false) String engine) throws Exception{
		return hs.searchCars(brand, model, fuelType, engine);
	}
	


	
	//---------------------- Leitourgia 4 ----------------------//
	
	@PutMapping(path="/updatecaravailability")
	public void updateCarAvailability(@RequestParam String carId,
										@RequestParam int newavailability) throws Exception {
		hs.updateCarAvailability(carId, newavailability);
	}
	
	
	//--------------------- Leitourgia 6 -----------------------//
	//Krathsh Test Drive (meta apo elegxo dia8esimothtas)
	@PostMapping(path="/booktestdrive")
	public void bookTestDrive(@RequestBody Booking booking) throws Exception {
		hs.bookTestDrive(booking);
	}
	
	//--------------------- LEITOURGIA 7 ---------------------//
	@PostMapping(path="/purchasecar")
	public void purchaseCar(@RequestParam String carId) throws Exception {
		hs.purchaseCar(carId);
	}
	
	
	
	// ------------------- Testing Login ----------------------//
	@PostMapping(path = "/login")
	public String login(@RequestBody LoginIdentifier request) throws Exception {
		if("citizen".equalsIgnoreCase(request.getRole() )) {
			hs.loginCitizen(request.getEmail(), request.getPassword());
			return "Citizen logged in successfully";
		} else if("dealership".equalsIgnoreCase(request.getRole())) {
			hs.loginDealerShip(request.getAfm(), request.getPassword() );
			return "Dealership Logged in Successfully";
		} else {
			throw new Exception("Invalid role was provided");
		}
	
	}
	
	@PostMapping(path ="/register")
	public String register(@RequestBody RegisterUser request) throws Exception {
		if("citizen".equalsIgnoreCase(request.getRole() )) {
			Citizen citizen = new Citizen (request.getAfm(),
										   request.getFname(),
										   request.getLname(),
										   request.getEmail(),
										   request.getPassword()
										   );
			hs.registerCitizen(citizen);
			return "Citizen has registered Successfully";
		} else if ("dealership".equalsIgnoreCase(request.getRole() )) {
			DealerShip dealership = new DealerShip (request.getAfm(),
													request.getCompanyname(),
													request.getOwner(),
													request.getPassword()
													);
			hs.registerDealerShip(dealership);
			return "Dealership has registered successfully!";
		} else {
			throw new Exception("Something went wrong");
		}
		
		
		
	}

}
