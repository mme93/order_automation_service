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

    public List<CustomerDto> getAllCustomer(String company) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (CustomerEntity customerEntity : this.customerRepository.findAll()) {
            if (customerEntity.getCompany().equals(company)) {
                customerDtos.add(new CustomerDto(
                        customerEntity.getId(),
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

    public boolean createCustomer(CustomerDto customerDto) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(Long.valueOf(id));
    }

    public Object getCustomerByID(String id) {
        return this.customerRepository.findById(Long.valueOf(id));
    }

    public void updateCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = this.customerRepository.findById(Long.valueOf(customerDto.getId())).get();
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setCity(customerDto.getCity());
        customerEntity.setStreet(customerDto.getStreet());
        customerEntity.setPostalCode(customerDto.getPostalCode());
        customerEntity.setCallNumber(customerDto.getCallNumber());
        customerEntity.setInformation(customerDto.getInformation());
        customerEntity.setCompany(customerDto.getCompany());
        this.customerRepository.saveAndFlush(customerEntity);
    }
}
