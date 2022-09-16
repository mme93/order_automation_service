package automation_order.backend.sms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SMS")
public class SMSController {

    @PostMapping
    public String test(){
        return "Test";
    }

}
