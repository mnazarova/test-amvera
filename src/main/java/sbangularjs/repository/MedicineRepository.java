package sbangularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sbangularjs.model.Medicine;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    List<Medicine> findAllByNameContainingIgnoreCase(String name);
    List<Medicine> findAllByGroupId(Integer group_id);
    List<Medicine> findAllByNameContainingIgnoreCaseAndGroupId(String name, Integer group_id);

/*
    @Query("select Medicine from Medicine m where u.enabled = true and u.id = :userId")
//    UserDto getUserDtoByIdAndActive(@Param("userId") Long userId);
    List<Medicine> findAllByNameAnd(@Param("userId") String name);

    @Query("select Medicine from Medicine m where m. .enabled = true and u.id = :group_id")
    List<Medicine> findAllByGroupId(@Param("group_id") Long group_id);
*/

//    where u.enabled = true order by u.fullName
//    @Query("select new com.evelopers.kap.account.model.user.UserDto(u.id, u.fullName) from User u where u.enabled = true and u.id = :userId")
//    UserDto getUserDtoByIdAndActive(@Param("userId") Long userId);
}
