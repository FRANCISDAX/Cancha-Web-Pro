package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name="UsuarioTelefonoSolicitud")
@Data
public class TelefonoUpdateRequest {

    @Schema(description = "Fono del Usuario", example = "999 999 999")
    private String telefono;

}
