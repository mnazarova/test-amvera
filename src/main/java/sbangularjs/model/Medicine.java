package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Medicine {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "medicine_id")
    private Integer id;

    private String name;

    @Column(name = "group_id")
    private Integer groupId;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private Group group;*/

    private String description;

    private int quantity;

    private int price;

    private String img;

    public Medicine() {
    }

    /*public Group(String name) {
        this.name = name;
    }*/
}
