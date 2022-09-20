package automation_order.backend.account.service;

import automation_order.backend.account.model.dto.UserDto;
import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserDto>findUsersByCompany(String company){
        List<UserDto> userDtoList= new ArrayList<>();
        for(UserEntity userEntity:userRepository.findAllByCompany(company)){
            userDtoList.add(new UserDto(
                    userEntity.getUsername(),
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getEmail(),
                    userEntity.getCallNumber(),
                    userEntity.getRoll(),
                    userEntity.getInfo()
            ));
        }
        return userDtoList;
    }

    public UserDto findUserByName(String username){
        UserEntity userEntity=userRepository.findByUsername(username);
        return new UserDto(
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getCallNumber(),
                userEntity.getRoll(),
                userEntity.getInfo()
        );
    }

}
