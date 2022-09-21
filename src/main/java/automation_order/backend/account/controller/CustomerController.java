package automation_order.backend.account.controller;

import automation_order.backend.account.service.CustomerService;
import automation_order.backend.security.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public void createCustomer(@RequestHeader("Company") String company){
        System.err.println(company);
    }

}
