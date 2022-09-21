package automation_order.backend.account.controller;

import automation_order.backend.account.model.dto.UserDto;
import automation_order.backend.account.model.entity.UserEntity;
import automation_order.backend.account.service.UserService;
import automation_order.backend.security.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JWTUtility jwtUtility;

    @Autowired
    public UserController(UserService userService, JWTUtility jwtUtility) {
        this.userService = userService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserDto userDto) {
        this.userService.createUser(new UserEntity(
                userDto.getPassword(),
                userDto.getUsername(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getCallNumber(),
                userDto.getRoll(),
                userDto.getCompany(),
                userDto.getInfo())
        );
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authorization) {
        System.err.println();
        return new ResponseEntity(this.userService.findUserByName(
                jwtUtility.getUsernameFromToken(
                        authorization.substring(7)
                )), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return null;
    }

}
