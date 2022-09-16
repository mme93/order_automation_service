package automation_order.backend.sms.service;

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

    @Autowired
    private final Environment env;

    public SMSService(Environment env) {
        this.env = env;
    }

    @PostConstruct
    private void setup() {
        this.webClient = WebClient.builder().build();
    }

    public String sendMsg(SMS sms){

        WebClient.RequestBodySpec requestBodySpec = this.webClient.post().uri(env.getProperty("sms.url"));
        requestBodySpec
                .body(Mono.just(new JSONObject(sms).toString()), String.class)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8));
        String response = requestBodySpec.retrieve().bodyToMono(String.class).block();
        return response;
    }

}
