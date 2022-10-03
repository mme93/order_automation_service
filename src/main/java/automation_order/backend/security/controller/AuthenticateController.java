package automation_order.backend.security.controller;

import automation_order.backend.account.model.dto.UserDto;
import automation_order.backend.account.service.UserService;
import automation_order.backend.security.model.JwtRequest;
import automation_order.backend.security.model.Login;
import automation_order.backend.security.service.SecurityUserService;
import automation_order.backend.security.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticateController {

    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final SecurityUserService securityUserService;
    private final UserService userService;

    @Autowired
    public AuthenticateController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, SecurityUserService securityUserService, UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.securityUserService = securityUserService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        final UserDetails userDetails
                = securityUserService.loadUserByUsername(jwtRequest.getUsername());
        final String token =
                jwtUtility.generateToken(userDetails);
        UserDto userDto =this.userService.findUserByName(jwtRequest.getUsername());
        return  new Login(token,userDto.getCompany(),userDto.getUserId());
    }

}
