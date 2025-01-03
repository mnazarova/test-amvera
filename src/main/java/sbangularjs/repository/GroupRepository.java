package sbangularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbangularjs.DTO.GroupDto;
import sbangularjs.model.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query(value = "select new sbangularjs.DTO.GroupDto(gr.id, gr.name) from Group gr")
    List<GroupDto> findAllDTO();

//     where u.enabled = true order by u.fullName
//    @Query("select new com.evelopers.kap.account.model.user.UserDto(u.id, u.fullName) from User u where u.enabled = true and u.id = :userId")
//    UserDto getUserDtoByIdAndActive(@Param("userId") Long userId);

//    Group findByUsername(String username);
}
