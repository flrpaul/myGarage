package gr.uom.elevateFinal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {

	// Logic used on HelloService ------//
	List<Car> findByBrand(String brand);
	List<Car> findByModel(String model);
	List<Car> findByFuelType(String fuelType);
	List<Car> findByEngine(String engine);
	List<Car> findByBrandAndModel(String brand, String model);
	List<Car> findByBrandAndEngine(String brand, String engine);
	List<Car> findByBrandAndFuelType(String brand, String FuelType);
	List<Car> findByBrandAndModelAndEngine(String brand, String model, String engine);
	List<Car> findByBrandAndModelAndFuelType(String brand, String model, String fuelType);
	List<Car> findByBrandAndModelAndFuelTypeAndEngine(String brand, String model, String FuelType, String engine);
}
