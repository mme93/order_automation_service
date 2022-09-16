package automation_order.backend.account.controller;

import automation_order.backend.account.model.dto.UserDto;
import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
