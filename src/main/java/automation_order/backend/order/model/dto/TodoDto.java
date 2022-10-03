package automation_order.backend.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDto {

    private String information;

    private String todo;

    private int status;

    private Long oderID;

    public TodoDto(String information, String todo, int status) {
        this.information = information;
        this.todo = todo;
        this.status = status;
    }
}
