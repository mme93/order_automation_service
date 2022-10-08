package automation_order.backend.order.service;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.model.dto.TodoDto;
import automation_order.backend.order.model.entity.OrderEntity;
import automation_order.backend.order.model.entity.TodoEntity;
import automation_order.backend.order.repository.OrderRepository;
import automation_order.backend.sms.model.SMS;
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

    public void createOrder(OrderDto orderDto) {
        String password=passwordGenerator.createPassword();
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
        StringBuilder builder = new StringBuilder();
        builder.append("Order was created at the company  "+orderDto.getCompany()+"\n");
        builder.append("You can see the order status at the following link: "+
                env.getProperty("order_status.url")+"?orderId="+orderDto.getId()+"&password="+password
                +"\n");
        builder.append("Order number is "+orderDto.getId()+" and the password is "+password+"\n");
        smsService.sendMsg(new SMS(orderDto.getCallNumber(),builder.toString()));
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
    }

    public void deleteOrder(String id) {
        this.orderRepository.deleteById(Long.valueOf(id));
    }
}
