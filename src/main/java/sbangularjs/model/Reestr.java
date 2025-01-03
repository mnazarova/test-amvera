package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Reestr {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Reestr_id")
    private Integer id;

    private int medicineId;
    private int count;
    private int orderId;

    public Reestr() {
    }

    public Reestr(int medicineId, int count, int orderId) {
        this.medicineId = medicineId;
        this.count = count;
        this.orderId = orderId;
    }
}
