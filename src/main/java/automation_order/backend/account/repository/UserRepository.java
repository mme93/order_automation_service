package automation_order.backend.account.repository;

import automation_order.backend.account.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
    List<UserEntity> findAllByCompany(String company);
}
