package automation_order.backend.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String street;

    private String postalCode;

    private String callNumber;

    private String information;

    private String company;

}
