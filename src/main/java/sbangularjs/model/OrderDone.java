package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class OrderDone {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "OrderId")
    private Integer orderId;

    private int clientId;
    private int employeeId;
    private int sum;
    private String date;

    public OrderDone() {}

    public OrderDone(int clientId, int employeeId, int sum, String date) {
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.sum = sum;
        this.date = date;
    }

}
