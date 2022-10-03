package automation_order.backend.order.model.entity;

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
@Table(name = "todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String information;

    private String todo;

    private int status;

    public TodoEntity(String information, String todo, int status) {
        this.information = information;
        this.todo = todo;
        this.status = status;
    }
}
