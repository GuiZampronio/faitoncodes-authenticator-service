package com.faitoncodes.authenticator_service.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginDTO {
    private String email;

    private String password;
}
