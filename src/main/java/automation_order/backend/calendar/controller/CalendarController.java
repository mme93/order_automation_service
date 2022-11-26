package automation_order.backend.calendar.controller;

import automation_order.backend.calendar.model.dto.CalendarEventDto;
import automation_order.backend.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CalendarEventDto>> getCalendar(@PathVariable Long userId){
        return new ResponseEntity<>(this.calendarService.getCalendar(userId), HttpStatus.OK);
    }
}
