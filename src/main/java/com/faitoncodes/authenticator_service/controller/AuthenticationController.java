package com.faitoncodes.authenticator_service.controller;

import com.faitoncodes.authenticator_service.dao.Usuario;
import com.faitoncodes.authenticator_service.dto.LoginDTO;
import com.faitoncodes.authenticator_service.dto.LoginResponseDTO;
import com.faitoncodes.authenticator_service.dto.RegisterDTO;
import com.faitoncodes.authenticator_service.service.AuthenticationService;
import com.faitoncodes.authenticator_service.service.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterDTO registerUserDto) {
        try{
            Usuario registeredUser = authenticationService.signup(registerUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha no cadastro do usuario", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginDTO loginUserDto){
        try{
            Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getUserId(), authenticatedUser.getNome());
            LoginResponseDTO loginResponse = new LoginResponseDTO(jwtToken, jwtService.getExpirationTime(), authenticatedUser.getTipoUsuario(), authenticatedUser.getColor());
            return ResponseEntity.ok(loginResponse);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autorização falhou", e);
        }

    }

}
