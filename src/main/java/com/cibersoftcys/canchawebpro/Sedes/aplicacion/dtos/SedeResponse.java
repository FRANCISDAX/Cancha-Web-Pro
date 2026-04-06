package com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonPropertyOrder({
    "id",
    "nombre",
    "direccion",
    "distrito",
    "ciudad",
    "estado"
})
@Schema(name = "SedeRespuesta")
@Data
public class SedeResponse {

    private Long id;
    private String nombre;
    private String direccion;
    private String distrito;
    private String ciudad;
    private String estado;

}