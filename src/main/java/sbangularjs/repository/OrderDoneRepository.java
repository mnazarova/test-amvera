package sbangularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbangularjs.model.OrderDone;

import java.util.List;

public interface OrderDoneRepository extends JpaRepository<OrderDone, Integer> {
    List<OrderDone> findAllBySum(Integer sum);
    List<OrderDone> findAllByClientIdAndSum(Integer clientId, Integer sum);
    List<OrderDone> findAllByClientId(Integer clientId);
}
