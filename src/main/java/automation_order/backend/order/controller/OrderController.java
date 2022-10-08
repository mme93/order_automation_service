package automation_order.backend.order.controller;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.model.dto.TodoDto;
import automation_order.backend.order.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity(this.orderService.createOrder(orderDto),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAll() {
        return new ResponseEntity<>(this.orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(this.orderService.getOrderById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delteOrder(@PathVariable String id) {
        this.orderService.deleteOrder(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateOrder(@RequestBody OrderDto orderDto) {
        this.orderService.updateOrder(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
