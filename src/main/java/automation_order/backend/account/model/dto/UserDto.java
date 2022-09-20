package automation_order.backend.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String password;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String callNumber;

    private String company;

    private String roll;

    private String info;

    public UserDto(String username, String firstName, String lastName, String email, String callNumber, String roll, String info) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.callNumber = callNumber;
        this.roll = roll;
        this.info = info;
    }
}
