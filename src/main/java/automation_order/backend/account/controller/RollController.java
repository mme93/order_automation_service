package automation_order.backend.account.controller;

import automation_order.backend.account.model.dto.RollDto;
import automation_order.backend.account.service.RollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roll")
public class RollController {

    private final RollService rollService;

    public RollController(RollService rollService) {
        this.rollService = rollService;
    }

    @PostMapping("/create")
    public ResponseEntity createRoll(@RequestBody RollDto rollDto){
        if(rollService.createRoll(rollDto)){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<RollDto>>getAll(){
        List<RollDto>rollDtos=rollService.getAll();
        if(rollDtos.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(rollDtos,HttpStatus.OK);
        }
    }
}
