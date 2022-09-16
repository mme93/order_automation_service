package automation_order.backend.sms.controller;

import automation_order.backend.sms.model.SMS;
import automation_order.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/SMS")
public class SMSController {

    private final SMSService smsService;

    @Autowired
    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }


    @PostMapping("/send")
    public String senSMS() {
        SMS sms = new SMS();
        sms.setPhone("+4915734696774");
        sms.setMsg("Test Message from Spring Boot");
        return smsService.sendMsg(sms);
    }

}
