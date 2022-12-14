package automation_order.backend.order.controller;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.service.OrderService;
import automation_order.backend.sms.model.SMS;
import automation_order.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final SMSService smsService;

    @Autowired
    public OrderController(OrderService orderService, SMSService smsService) {
        this.orderService = orderService;
        this.smsService = smsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        String password=this.orderService.createOrder(orderDto);
        SMS sms = this.smsService.createSMS(this.orderService.getOrderByPassword(password));
        return new ResponseEntity(this.smsService.sendMsg(sms),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAll() {
        return new ResponseEntity<>(this.orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(this.orderService.getOrderById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId){
        this.orderService.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateOrder(@RequestBody OrderDto orderDto) {
        this.orderService.updateOrder(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/updateStatus")
    public ResponseEntity updateOrderStatus(@RequestBody OrderDto orderDto) {
        this.orderService.updateOrderStatus(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
