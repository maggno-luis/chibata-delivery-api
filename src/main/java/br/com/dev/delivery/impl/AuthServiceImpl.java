package br.com.dev.delivery.impl;

import br.com.dev.delivery.dto.CreateUser;
import br.com.dev.delivery.dto.LoginRequest;
import br.com.dev.delivery.dto.LoginResponse;
import br.com.dev.delivery.entites.Role;
import br.com.dev.delivery.entites.User;
import br.com.dev.delivery.exception.AuthenticationException;
import br.com.dev.delivery.exception.UserFoundException;
import br.com.dev.delivery.repository.AuthRepository;
import br.com.dev.delivery.repository.RoleRepository;
import br.com.dev.delivery.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {
        var user = authRepository.findByEmail(loginRequest.email()).orElseThrow(
                () -> new UsernameNotFoundException("Email ou Senha incorretos")
        );

        user.getRoles().forEach(role -> {
            System.out.println("Role do usuário: " + role.getName());
        });

        if (!user.isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Erro ao fazer login");
        }

        var now = Instant.now();
        var expiresIn = 3600L;

        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("server")
                .subject(user.getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, expiresIn, claims.getSubject(), scopes, user.getName(), user.getEmail());
    }

    @Override
    public User create(CreateUser dto) throws IOException {
        Optional<Role> basicRoleOpt = roleRepository.findByName(Role.Values.BASIC.name());

        if (basicRoleOpt.isEmpty()) {
            throw new IllegalStateException("Role BASIC not found in the database");
        }

        Role basicRole = basicRoleOpt.get();

        authRepository.findByEmail(dto.getEmail()).ifPresent(user -> {
            throw new UserFoundException("E-mail já está em uso");
        });

        var user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(basicRole));
        authRepository.save(user);
        return user;
    }
}
