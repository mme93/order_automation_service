package automation_order.backend.account.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String street;

    private String postalCode;

    private String callNumber;

    private String information;

    public CustomerEntity(String firstName, String lastName, String email, String city, String street, String postalCode, String callNumber, String information) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.callNumber = callNumber;
        this.information = information;
    }
}
