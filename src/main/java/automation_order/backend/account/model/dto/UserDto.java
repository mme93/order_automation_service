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

    private String company;

    private String roll;

    public UserDto(String username, String company, String roll) {
        this.username = username;
        this.company = company;
        this.roll = roll;
    }
}
