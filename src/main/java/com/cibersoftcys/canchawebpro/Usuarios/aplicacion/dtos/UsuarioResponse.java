package com.cibersoftcys.canchawebpro.Usuarios.aplicacion.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonPropertyOrder({
    "id",
    "nombre",
    "email",
    "telefono",
    "tipo",
    "fechaRegistro"
})
@Schema(name="UsuarioRespuesta")
@Data
public class UsuarioResponse {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String tipo;
    private String fechaRegistro;
    
}