package automation_order.backend.security.service;

import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    public SecurityUserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(UserEntity user:repository.findAll()){
            if(user.getUsername().equals(username)){
                User credentialUser = new User(username,user.getPassword(), new ArrayList<>());
                return credentialUser;
            }
        }
        return null;
    }
}
