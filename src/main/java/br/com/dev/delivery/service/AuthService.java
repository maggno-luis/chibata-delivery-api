package br.com.dev.delivery.service;

import br.com.dev.delivery.dto.CreateUser;
import br.com.dev.delivery.dto.LoginRequest;
import br.com.dev.delivery.dto.LoginResponse;
import br.com.dev.delivery.entites.User;

import java.io.IOException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    User create(CreateUser createUser) throws IOException;
}
