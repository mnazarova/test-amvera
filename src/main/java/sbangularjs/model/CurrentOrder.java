package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CurrentOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "CurrentOrderId")
    private Integer id;

    private int medicineId;
    private int count;
    private int userId;

    public CurrentOrder() {
    }

    public CurrentOrder(int medicineId, int count, int userId) {
        this.medicineId = medicineId;
        this.count = count;
        this.userId = userId;
    }
/*public Group(String name) {
        this.name = name;
    }*/
}
