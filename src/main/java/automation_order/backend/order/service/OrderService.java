package automation_order.backend.order.service;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.model.entity.OrderEntity;
import automation_order.backend.order.model.entity.TodoEntity;
import automation_order.backend.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderDto orderDto) {
        List<TodoEntity> todoEntities = new ArrayList<>();
        orderDto.getTodos().forEach(todoDto -> {
            todoEntities.add(new TodoEntity(
                    todoDto.getInformation(),
                    todoDto.getTodo(),
                    todoDto.getStatus()
            ));
        });
        this.orderRepository.save(new OrderEntity(
                orderDto.getCustomerID(),
                orderDto.getFirstName(),
                orderDto.getLastName(),
                orderDto.getEmail(),
                orderDto.getCity(),
                orderDto.getStreet(),
                orderDto.getPostalCode(),
                orderDto.getCallNumber(),
                orderDto.getInformation(),
                orderDto.getCompany(),
                orderDto.getOrderInformation(),
                orderDto.getRefNr(),
                orderDto.getCreateDate(),
                orderDto.getStartDate(),
                orderDto.getEndDate(),
                orderDto.getFurtherInformation(),
                todoEntities
        ));
    }

    public List<OrderDto> getAll() {
        return null;
    }

    public OrderDto getOrderById(String id) {
        return null;
    }

    public void updateOrder(OrderDto orderDto) {
    }

    public void deleteOrder(String id) {
        this.orderRepository.deleteById(Long.valueOf(id));
    }
}
