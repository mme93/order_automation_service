package automation_order.backend.calendar.service;

import automation_order.backend.calendar.model.dto.CalendarEventDto;
import automation_order.backend.order.model.entity.OrderEntity;
import automation_order.backend.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CalendarService {

    private final OrderRepository orderRepository;

    @Autowired
    public CalendarService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CalendarEventDto> getCalendar(Long userId) {
        List<CalendarEventDto> eventDtos = new ArrayList<>();
        for (OrderEntity orderEntity : this.orderRepository.findAll()) {
            if (orderEntity.getUserId() == userId) {
                eventDtos.add(new CalendarEventDto(
                        orderEntity.getOrderInformation(),
                        orderEntity.getFurtherInformation(),
                        this.createCalendarStringFromDate(orderEntity.getStartDate()),
                        this.createCalendarStringFromDate(orderEntity.getEndDate())
                ));
            }
        }
        return eventDtos;
    }

    private String createCalendarStringFromDate(Date date) {
        String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] resultDateSplit = date.toString().split(" ");
        String[] dateSplit = resultDateSplit[0].split("-");
        return monthArray[Integer.valueOf(dateSplit[1]) - 1] + " " + dateSplit[2] + ", " + dateSplit[0] + " " + resultDateSplit[1].
                substring(0, resultDateSplit[1].length() - 2);
    }
}
