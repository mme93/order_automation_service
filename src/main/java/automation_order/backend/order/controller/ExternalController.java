package automation_order.backend.order.controller;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.order.model.dto.OrderLogin;
import automation_order.backend.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/external")
public class ExternalController {

    private final OrderService orderService;

    @Autowired
    public ExternalController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String getOrder(){
        return "Order";
    }
    @GetMapping("/order/{orderId}/{password}")
    public ResponseEntity getOrderById(@PathVariable  Long orderId,@PathVariable  String password){
        System.err.println(password);
        for(OrderDto orderDto: this.orderService.getAll()){
            if(orderDto.getId().equals(orderId)&&orderDto.getPassword().equals(password)){
                return new ResponseEntity(orderDto,HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/order/exist")
    public ResponseEntity<Boolean> isOrderExist(@RequestBody OrderLogin orderLogin){
        for(OrderDto orderDto: this.orderService.getAll()){
            if(orderDto.getId().equals(orderLogin.getOrderId())&&orderDto.getPassword().equals(orderLogin.getPassword())){
                return new ResponseEntity(true,HttpStatus.OK);
            }
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }

}
