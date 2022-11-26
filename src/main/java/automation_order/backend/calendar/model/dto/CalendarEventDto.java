package automation_order.backend.calendar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CalendarEventDto {

    private String title;
    private String description;
    private String startTime;
    private String endTime;

}
