package automation_order.backend.account.service;

import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity userEntity){
        this.userRepository.save(userEntity);
    }

}
