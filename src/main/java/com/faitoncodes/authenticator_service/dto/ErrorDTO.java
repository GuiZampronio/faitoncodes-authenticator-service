package com.faitoncodes.authenticator_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
    public HttpStatusCode status;
    public String message;

}
