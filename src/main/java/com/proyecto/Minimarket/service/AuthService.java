package com.proyecto.Minimarket.service;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.Minimarket.dto.request.LoginRequestDTO;
import com.proyecto.Minimarket.dto.request.RegisterRequestDTO;
import com.proyecto.Minimarket.dto.response.LoginResponseDTO;
import com.proyecto.Minimarket.dto.response.MessageResponseDTO;
import com.proyecto.Minimarket.dto.response.RefreshTokenResponse;
import com.proyecto.Minimarket.entity.Users;
import com.proyecto.Minimarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthService {
    
    private final PasswordEncoder passwordEncoder; 

    private final UserRepository usersRepository;

    private final JwtService jwtService;

    
    

    public MessageResponseDTO register(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Registro exitoso");

        if (usersRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Este nombre de usuario ya esta en uso");

        }
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRolId(request.getRol());

        usersRepository.save(user);

        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO request){
        LoginResponseDTO response = new LoginResponseDTO();
        Optional<Users> user = usersRepository.findByUsername(request.getUsername());

            if (user.isEmpty() && request.getUsername() !=null) {
            response.setMessage("Este usuario no se encuentra resgistrado");
            return response;
        } 
        Users userFound = user.get();

        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String jwt = jwtService.generateToken(userFound.getId(), userFound.getRolId(), userFound.getUsername());

        response.setMessage("Inicio de sesión exitoso");
        response.setJwt(jwt);
        return response;
    }
    public RefreshTokenResponse refreshToken(String token) throws Exception {
        String jwt = jwtService.refreshToken(token);
        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setMessage("Ok");
        response.setJwt(jwt);
        return response;
    }
}


