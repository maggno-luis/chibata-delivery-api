package br.com.dev.delivery.controller;

import br.com.dev.delivery.dto.CreateUser;
import br.com.dev.delivery.dto.LoginRequest;
import br.com.dev.delivery.exception.UserFoundException;
import br.com.dev.delivery.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            var result = authServiceImpl.login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> create(@RequestBody CreateUser dto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(authServiceImpl.create(dto));
        }catch (UserFoundException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor" + e.getMessage());
        }
    }

}
