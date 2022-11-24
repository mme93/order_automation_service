package automation_order.backend.order.service;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.model.dto.TodoDto;
import automation_order.backend.order.model.entity.OrderEntity;
import automation_order.backend.order.model.entity.TodoEntity;
import automation_order.backend.order.repository.OrderRepository;
import automation_order.backend.sms.service.SMSService;
import automation_order.backend.utility.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PasswordGenerator passwordGenerator;
    private final SMSService smsService;
    private final Environment env;

    @Autowired
    public OrderService(OrderRepository orderRepository, PasswordGenerator passwordGenerator, SMSService smsService, Environment env) {
        this.orderRepository = orderRepository;
        this.passwordGenerator = passwordGenerator;
        this.smsService = smsService;
        this.env = env;
    }

    public String createOrder(OrderDto orderDto) {
        String password = passwordGenerator.createPassword();
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
                todoEntities,
                orderDto.getUserId(),
                orderDto.getStatus(),
                password
        ));
        return password;
    }

    public List<OrderDto> getAll() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        this.orderRepository.findAll().forEach(orderEntity -> {
            List<TodoDto> todos = new ArrayList<>();
            orderEntity.getTodos().forEach(todoEntity -> todos.add(new TodoDto(
                    todoEntity.getInformation(),
                    todoEntity.getTodo(),
                    todoEntity.getStatus(),
                    todoEntity.getId()
            )));
            orderDtoList.add(new OrderDto(
                    orderEntity.getId(),
                    orderEntity.getCustomerID(),
                    orderEntity.getFirstName(),
                    orderEntity.getLastName(),
                    orderEntity.getEmail(),
                    orderEntity.getCity(),
                    orderEntity.getStreet(),
                    orderEntity.getPostalCode(),
                    orderEntity.getCallNumber(),
                    orderEntity.getInformation(),
                    orderEntity.getCompany(),
                    orderEntity.getOrderInformation(),
                    orderEntity.getRefNr(),
                    orderEntity.getCreateDate(),
                    orderEntity.getStartDate(),
                    orderEntity.getEndDate(),
                    orderEntity.getFurtherInformation(),
                    todos,
                    orderEntity.getUserId(),
                    orderEntity.getStatus(),
                    orderEntity.getPassword()
            ));
        });

        return orderDtoList;
    }

    public OrderDto getOrderByPassword(String password) {
        OrderEntity orderEntity = this.orderRepository.findByPassword(password);
        List<TodoDto> todos = new ArrayList<>();
        orderEntity.getTodos().forEach(todoEntity -> todos.add(new TodoDto(
                todoEntity.getInformation(),
                todoEntity.getTodo(),
                todoEntity.getStatus(),
                todoEntity.getId()
        )));
        OrderDto orderDto = new OrderDto(
                orderEntity.getId(),
                orderEntity.getCustomerID(),
                orderEntity.getFirstName(),
                orderEntity.getLastName(),
                orderEntity.getEmail(),
                orderEntity.getCity(),
                orderEntity.getStreet(),
                orderEntity.getPostalCode(),
                orderEntity.getCallNumber(),
                orderEntity.getInformation(),
                orderEntity.getCompany(),
                orderEntity.getOrderInformation(),
                orderEntity.getRefNr(),
                orderEntity.getCreateDate(),
                orderEntity.getStartDate(),
                orderEntity.getEndDate(),
                orderEntity.getFurtherInformation(),
                todos,
                orderEntity.getUserId(),
                orderEntity.getStatus(),
                orderEntity.getPassword());
        return orderDto;
    }

    public OrderDto getOrderById(Long id) {
        OrderEntity orderEntity = this.orderRepository.findById(id).get();
        List<TodoDto> todos = new ArrayList<>();
        orderEntity.getTodos().forEach(todoEntity -> todos.add(new TodoDto(
                todoEntity.getInformation(),
                todoEntity.getTodo(),
                todoEntity.getStatus(),
                todoEntity.getId()
        )));
        OrderDto orderDto = new OrderDto(
                orderEntity.getId(),
                orderEntity.getCustomerID(),
                orderEntity.getFirstName(),
                orderEntity.getLastName(),
                orderEntity.getEmail(),
                orderEntity.getCity(),
                orderEntity.getStreet(),
                orderEntity.getPostalCode(),
                orderEntity.getCallNumber(),
                orderEntity.getInformation(),
                orderEntity.getCompany(),
                orderEntity.getOrderInformation(),
                orderEntity.getRefNr(),
                orderEntity.getCreateDate(),
                orderEntity.getStartDate(),
                orderEntity.getEndDate(),
                orderEntity.getFurtherInformation(),
                todos,
                orderEntity.getUserId(),
                orderEntity.getStatus(),
                orderEntity.getPassword());
        return orderDto;
    }

    public void updateOrder(OrderDto orderDto) {
        OrderEntity orderEntity = this.orderRepository.findById(orderDto.getId()).get();
        orderEntity.setFirstName(orderDto.getFirstName());
        orderEntity.setLastName(orderDto.getLastName());
        orderEntity.setEmail(orderDto.getEmail());
        orderEntity.setCity(orderDto.getCity());
        orderEntity.setStreet(orderDto.getStreet());
        orderEntity.setPostalCode(orderDto.getPostalCode());
        orderEntity.setCallNumber(orderDto.getCallNumber());
        orderEntity.setInformation(orderDto.getInformation());
        orderEntity.setOrderInformation(orderDto.getOrderInformation());
        orderEntity.setRefNr(orderDto.getRefNr());
        orderEntity.setCreateDate(orderDto.getCreateDate());
        orderEntity.setStartDate(orderDto.getStartDate());
        orderEntity.setEndDate(orderDto.getEndDate());
        orderEntity.setFurtherInformation(orderDto.getFurtherInformation());
        List<TodoEntity> todoEntities = new ArrayList<>();
        for (int i = 0; i < orderDto.getTodos().size(); i++) {
            TodoEntity todoEntity=orderEntity.getTodos().get(i);
            TodoDto dto=orderDto.getTodos().get(i);
            todoEntity.setTodo(dto.getTodo());
            todoEntity.setStatus(dto.getStatus());
            todoEntity.setInformation(dto.getInformation());
            todoEntities.add(todoEntity);
        }
        orderEntity.setTodos(todoEntities);
        this.orderRepository.save(orderEntity);
    }

    public void updateOrderStatus(OrderDto orderDto) {
        OrderEntity orderEntity = this.orderRepository.findById(orderDto.getId()).get();
        orderEntity.setStatus(orderDto.getStatus());
        this.orderRepository.save(orderEntity);
    }

    public void deleteOrder(Long id) {
        this.orderRepository.deleteById(id);
    }
}
