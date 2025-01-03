package sbangularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbangularjs.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByUsername(String username);
    List<Client> findAllBySurnameContainingIgnoreCaseAndEmailContainingIgnoreCase(String surname, String email);
    List<Client> findBySurnameContainingIgnoreCase(String surname);
    List<Client> findByEmailContainingIgnoreCase(String email);
}
