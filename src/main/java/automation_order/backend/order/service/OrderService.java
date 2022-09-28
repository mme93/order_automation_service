package automation_order.backend.order.service;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderDto orderDto) {
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
