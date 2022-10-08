package automation_order.backend.sms.service;

import automation_order.backend.order.model.dto.OrderDto;
import automation_order.backend.sms.model.SMS;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class SMSService {

    private WebClient webClient;

    private final Environment env;

    @Autowired
    public SMSService(Environment env) {
        this.env = env;
    }

    @PostConstruct
    private void setup() {
        this.webClient = WebClient.builder().build();
    }

    public SMS createSMS(OrderDto orderDto){
        StringBuilder builder = new StringBuilder();
        builder.append("Order was created at the company  "+orderDto.getCompany()+"\n");
        builder.append("You can see the order status at the following link: "+
                env.getProperty("order_status.url")+"?orderId="+orderDto.getId()+"&password="+orderDto.getPassword()
                +"\n");
        builder.append("Order number is "+orderDto.getId()+" and the password is "+orderDto.getPassword()+"\n");
        return new SMS(orderDto.getCallNumber(),builder.toString());
    }

    public String sendMsg(SMS sms) {

        WebClient.RequestBodySpec requestBodySpec = this.webClient.post().uri(env.getProperty("sms.url")+"SMS");
        requestBodySpec
                .body(Mono.just(new JSONObject(sms).toString()), String.class)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8));
        String response = requestBodySpec.retrieve().bodyToMono(String.class).block();
        return response;
    }
    public boolean initGSMModule(){
        String response = this.webClient.get().uri(env.getProperty("sms.url")+"init").retrieve().bodyToMono(String.class).block();
        return response.equals("Okay");
    }

}

