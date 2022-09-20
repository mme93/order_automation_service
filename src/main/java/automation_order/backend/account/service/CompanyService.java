package automation_order.backend.account.service;

import automation_order.backend.account.model.dto.CompanyDto;
import automation_order.backend.account.model.entity.CompanyEntity;
import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.repository.CompanyRepository;
import automation_order.backend.security.utility.JWTUtility;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final JWTUtility jwtUtility;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserService userService, JWTUtility jwtUtility) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.jwtUtility = jwtUtility;
    }

    public boolean createCompany(CompanyDto companyDto) {
        for (CompanyEntity companyEntity : companyRepository.findAll()) {
            if (companyEntity.getCompanyName().equals(companyDto.getCompanyName())) {
                return false;
            }
        }
        companyRepository.save(new CompanyEntity(
                companyDto.getCompanyName(),
                companyDto.getFirstName(),
                companyDto.getLastName(),
                companyDto.getEmail(),
                companyDto.getCity(),
                companyDto.getStreet(),
                companyDto.getPostalCode(),
                companyDto.getCallNumber(),
                companyDto.getSector()
        ));
        return true;
    }

    public CompanyDto getCompany(String authorization) throws ObjectNotFoundException {
        String company=userService.findUserByName(
                jwtUtility.getUsernameFromToken(authorization.substring(7))).getCompany();
        for (CompanyEntity companyEntity : companyRepository.findAll()) {
            if (companyEntity.getCompanyName().equals(company)) {
                return new CompanyDto(
                        companyEntity.getCompanyName(),
                        companyEntity.getFirstName(),
                        companyEntity.getLastName(),
                        companyEntity.getEmail(),
                        companyEntity.getCity(),
                        companyEntity.getStreet(),
                        companyEntity.getPostalCode(),
                        companyEntity.getCallNumber(),
                        companyEntity.getSector(),
                        userService.findUsersByCompany(companyEntity.getCompanyName())
                );
            }
        }
        return null;
    }

    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (CompanyEntity companyEntity : companyRepository.findAll()) {
            companyDtos.add(new CompanyDto(
                    companyEntity.getCompanyName(),
                    companyEntity.getFirstName(),
                    companyEntity.getLastName(),
                    companyEntity.getEmail(),
                    companyEntity.getCity(),
                    companyEntity.getStreet(),
                    companyEntity.getPostalCode(),
                    companyEntity.getCallNumber(),
                    companyEntity.getSector()
            ));
        }
        return companyDtos;
    }

}
