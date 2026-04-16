package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
