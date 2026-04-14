package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos;

import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(name="UsuarioSolicitud")
@Data
public class UsuarioRequest {

    @NotBlank(message="Nombre del Usuario es obligatorio.")
    @Size(max=50, message="El nombre no puede exceder de 50 caracteres.")
    private String nombre;

    @NotBlank(message="Email es obligatorio.")
    private String email;

    private String telefono;

    @NotNull(message="Tipo de Usuario es obligatorio.")
    private TipoUsuario tipo;

}
