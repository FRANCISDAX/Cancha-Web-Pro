package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos;

//import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(name="UsuarioSolicitud")
@Data
public class UsuarioRequest {

    @NotBlank(message="Nombre del Usuario es obligatorio.")
    @Size(max=50, message="El nombre no puede exceder de 50 caracteres.")
    @Schema(description = "Nombre del Usuario", example = "JOHAN FRANZ")
    private String nombre;

    @NotBlank(message="Email es obligatorio.")
    @Schema(description = "Email del Usuario", example = "ejemplo@ejemplo.com")
    private String email;

    @Schema(description = "Fono del Usuario", example = "999 999 999")
    private String telefono;

}
