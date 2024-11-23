package com.faitoncodes.authenticator_service.service;

import com.faitoncodes.authenticator_service.dao.Usuario;
import com.faitoncodes.authenticator_service.dto.LoginDTO;
import com.faitoncodes.authenticator_service.dto.RegisterDTO;
import com.faitoncodes.authenticator_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Random;

@Service
public class AuthenticationService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    public Usuario signup(RegisterDTO input) {

        String RGBColorString = findRandomColor();

        Usuario user = new Usuario(input.getNome(), input.getEmail(), passwordEncoder.encode(input.getPassword()), input.getTipoUsuario(), RGBColorString);
        return usuarioRepository.save(user);
    }

    private String findRandomColor(){
        Random random = new Random();
        float hue = random.nextFloat();
        // Saturation between 0.1 and 0.3
        float saturation = (random.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.45f;
        Color color = new Color(Color.HSBtoRGB(hue, saturation, luminance));
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

    public Usuario authenticate(LoginDTO input) {
        Usuario user = usuarioRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return user;
    }


}
