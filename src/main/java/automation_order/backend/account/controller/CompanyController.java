package automation_order.backend.account.controller;

import automation_order.backend.account.model.dto.CompanyDto;
import automation_order.backend.account.service.CompanyService;
import automation_order.backend.security.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity createRoll(@RequestBody CompanyDto companyDto){
        if(companyService.createCompany(companyDto)){
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/")
    public ResponseEntity<CompanyDto>getCompany(@RequestHeader("Company") String company){
        return new ResponseEntity(companyService.getCompany(company),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>>getAll(){
        List<CompanyDto>companyDtos=companyService.getAll();
        if(companyDtos.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(companyDtos,HttpStatus.OK);
        }
    }

}
