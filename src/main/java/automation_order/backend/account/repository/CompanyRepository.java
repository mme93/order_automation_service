package automation_order.backend.account.repository;

import automation_order.backend.account.model.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {
    CompanyEntity findByCompanyName(String companyName);
}
