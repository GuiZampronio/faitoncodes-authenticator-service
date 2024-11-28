package com.faitoncodes.authenticator_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private long expiresIn;
    private Integer tipoUsuario;
    private String userColor;
}
