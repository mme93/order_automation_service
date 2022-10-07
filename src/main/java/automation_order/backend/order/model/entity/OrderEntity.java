package automation_order.backend.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerID;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String street;

    private String postalCode;

    private String callNumber;

    private String information;

    private String company;

    //Order
    private String orderInformation;

    private String refNr;

    private String createDate;

    private Date startDate;

    private Date endDate;

    private String furtherInformation;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    private List<TodoEntity> todos;

    private Long userId;

    private int status;

    private String password;

    public OrderEntity(Long customerID, String firstName, String lastName, String email, String city, String street, String postalCode, String callNumber, String information, String company, String orderInformation, String refNr, String createDate, Date startDate, Date endDate, String furtherInformation, List<TodoEntity> todos, Long userId, int Status, String password) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.callNumber = callNumber;
        this.information = information;
        this.company = company;
        this.orderInformation = orderInformation;
        this.refNr = refNr;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.furtherInformation = furtherInformation;
        this.todos = todos;
        this.userId = userId;
        this.status = Status;
        this.password=password;
    }
}
