package automation_order.backend.security.controller;

import automation_order.backend.security.model.JwtResponse;
import automation_order.backend.security.service.SecurityUserService;
import automation_order.backend.security.utility.JWTUtility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final JWTUtility jwtUtility;

    @Autowired
    public TokenController(JWTUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/isTokenExpired")
    public ResponseEntity<JwtResponse> isTokenExpired(@RequestBody JwtResponse jwtResponse) {

        try {
            if (this.jwtUtility.isTokenExpired(jwtResponse.getJwtToken())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity(jwtResponse.getJwtToken(), HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }


    }

}
