package automation_order.backend.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDto {

    private String companyName;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String street;

    private String postalCode;

    private String callNumber;

    private String sector;

    private List<UserDto> userDtoList;

    public CompanyDto(String companyName, String firstName, String lastName, String email, String city, String street, String postalCode, String callNumber, String sector) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.callNumber = callNumber;
        this.sector = sector;
    }
}
