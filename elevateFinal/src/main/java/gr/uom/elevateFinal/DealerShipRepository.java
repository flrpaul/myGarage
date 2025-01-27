package gr.uom.elevateFinal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerShipRepository extends JpaRepository<DealerShip, Integer> {

	//------ Step 2 Sundesh DealerShip logic, if not working DELETE HERE ------//
	Optional<DealerShip> findByOwner(String owner);
	
}
