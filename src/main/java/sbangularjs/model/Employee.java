package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "employee_Id")
    private Integer employeeId;
    private String surname;
    private String name;
    private String position;
    private String phone;
    private String username;

    public Employee() {}

    public Employee(String surname, String name, String position, String phone) {
        this.surname = surname;
        this.name = name;
        this.position = position;
        this.phone = phone;
    }
}