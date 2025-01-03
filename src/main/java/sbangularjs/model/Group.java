package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "group_id")
    private Integer id;

    @Column(name = "group_name")
    private String name;

    private String description;

    /*@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicine> medicines;*/

    public Group() {
    }

    /*public Group(String name) {
        this.name = name;
    }*/
}
