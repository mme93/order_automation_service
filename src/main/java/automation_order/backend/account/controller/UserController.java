package automation_order.backend.account.controller;

import automation_order.backend.account.model.dto.UserDto;
import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserDto userDto) {
        this.userService.createUser(new UserEntity(
                userDto.getPassword(),
                userDto.getUsername(),
                userDto.getRoll())
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        return null;
    }

}
