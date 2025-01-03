package sbangularjs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "client_Id")
    private Integer id;

    private String surname;

    private String name;

    private String phone;

    private String email;

    private String username;

    public Client() {
    }

    public Client(String surname, String name, String phone, String email) {
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
