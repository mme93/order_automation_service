package automation_order.backend.account.controller;

import automation_order.backend.account.model.dto.CustomerDto;
import automation_order.backend.account.service.CustomerService;
import automation_order.backend.security.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final JWTUtility jwtUtility;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(JWTUtility jwtUtility, CustomerService customerService) {
        this.jwtUtility = jwtUtility;
        this.customerService = customerService;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable String id){
        this.customerService.deleteCustomer(id);
    }

    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody CustomerDto customerDto) {
        if (this.customerService.createCustomer(customerDto)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id){
        return new ResponseEntity(this.customerService.getCustomerByID(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>>getAllCustomer(@RequestHeader("Company")String company){
        List<CustomerDto> customerDtos = this.customerService.getAllCustomer(company);
        if (customerDtos.size()>0) {
            return new ResponseEntity(customerDtos,HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
