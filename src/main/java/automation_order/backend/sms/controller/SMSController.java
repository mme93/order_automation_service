package automation_order.backend.sms.controller;

import automation_order.backend.order.service.OrderService;
import automation_order.backend.sms.model.SMS;
import automation_order.backend.sms.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/SMS")
public class SMSController {

    private final SMSService smsService;
    private final OrderService orderService;

    @Autowired
    public SMSController(SMSService smsService, OrderService orderService) {
        this.smsService = smsService;
        this.orderService = orderService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> senSMS(@RequestBody SMS sms) {
        try{
            return new ResponseEntity<>(smsService.sendMsg(sms), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @GetMapping("/init")
    public ResponseEntity<String> initGSMModule(){
        try{
            if(smsService.initGSMModule()){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        }
    }

}
