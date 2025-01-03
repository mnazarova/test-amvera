package sbangularjs.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    private Integer id;

    private String name;

    public GroupDto() {
    }

    public GroupDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
