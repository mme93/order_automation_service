package automation_order.backend.account.service;

import automation_order.backend.account.model.dto.CustomerDto;
import automation_order.backend.account.model.entity.CustomerEntity;
import automation_order.backend.account.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto>getAllCustomer(String company){
        List<CustomerDto>customerDtos = new ArrayList<>();
        for(CustomerEntity customerEntity:this.customerRepository.findAll()){
            if(customerEntity.getCompany().equals(company)){
                customerDtos.add(new CustomerDto(
                        customerEntity.getFirstName(),
                        customerEntity.getLastName(),
                        customerEntity.getEmail(),
                        customerEntity.getCity(),
                        customerEntity.getStreet(),
                        customerEntity.getPostalCode(),
                        customerEntity.getCallNumber(),
                        customerEntity.getInformation(),
                        customerEntity.getCompany()
                ));
            }
        }
        return customerDtos;
    }

    public boolean createCustomer(CustomerDto customerDto){
        try{
            this.customerRepository.save(new CustomerEntity(
                    customerDto.getFirstName(),
                    customerDto.getLastName(),
                    customerDto.getEmail(),
                    customerDto.getCity(),
                    customerDto.getStreet(),
                    customerDto.getPostalCode(),
                    customerDto.getCallNumber(),
                    customerDto.getInformation(),
                    customerDto.getCompany()
            ));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
