package gr.uom.elevateFinal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, String> {
	//--------------- Business Logic starting here -----------------//
	Optional<Citizen> findByEmail(String email);
}
