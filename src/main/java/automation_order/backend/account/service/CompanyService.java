package automation_order.backend.account.service;

import automation_order.backend.account.model.dto.CompanyDto;
import automation_order.backend.account.model.dto.RollDto;
import automation_order.backend.account.model.entity.CompanyEntity;
import automation_order.backend.account.model.entity.RollEntity;
import automation_order.backend.account.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean createCompany(CompanyDto companyDto) {
        for (CompanyEntity companyEntity : companyRepository.findAll()) {
            if (companyEntity.getName().equals(companyDto.getName())) {
                return false;
            }
        }
        companyRepository.save(new CompanyEntity(companyDto.getName()));
        return true;
    }

    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (CompanyEntity companyEntity : companyRepository.findAll()) {
            companyDtos.add(new CompanyDto(companyEntity.getName()));
        }
        return companyDtos;
    }

}
