package automation_order.backend.sms.controller;

import automation_order.backend.sms.model.SMS;
import automation_order.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;


@RestController
@RequestMapping("/SMS")
public class SMSController {

    private final SMSService smsService;

    @Autowired
    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }


    @PostMapping("/send")
    public ResponseEntity<String> senSMS(@RequestBody SMS sms) {
        try{
            return new ResponseEntity<String>(smsService.sendMsg(sms), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        }
    }

}
