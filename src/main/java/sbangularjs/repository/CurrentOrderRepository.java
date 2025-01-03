package sbangularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbangularjs.model.CurrentOrder;

import java.util.List;

public interface CurrentOrderRepository extends JpaRepository<CurrentOrder, Integer> {

    List<CurrentOrder> findAllByUserId(Integer userId);
    CurrentOrder findByUserIdAndMedicineId(Integer userId, Integer medicineId);
}
