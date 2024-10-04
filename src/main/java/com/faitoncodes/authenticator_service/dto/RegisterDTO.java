package com.faitoncodes.authenticator_service.dto;

import lombok.Data;

@Data
public class RegisterDTO {
        private String nome;

        private String password;

        private String email;

        private Integer tipoUsuario;
}
