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
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String username;

    private String roll;

    private String company;

    public UserEntity(String password, String username, String roll,String company) {
        this.password = password;
        this.username = username;
        this.roll = roll;
        this.company=company;
    }
}
