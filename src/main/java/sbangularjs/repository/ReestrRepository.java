package sbangularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbangularjs.model.Reestr;

import java.util.List;

public interface ReestrRepository extends JpaRepository<Reestr, Integer> {

    List<Reestr> findAllByOrderId(Integer userId);
    Reestr findByOrderIdAndMedicineId(Integer userId, Integer medicineId);
}
