package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(name="UsuarioPasswordSolicitud")
@Data
public class PasswordUpdateRequest {

    @NotBlank(message = "Password actual obligatorio")
    private String passwordActual;

    @NotBlank(message = "Nueva password obligatoria")
    private String nuevaPassword;

}
