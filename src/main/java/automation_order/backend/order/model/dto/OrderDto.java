package automation_order.backend.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long id;

    //Order Person Info

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

    private List<TodoDto> todos;

    private Long userId;

    private int Status;

    private String password;

}
