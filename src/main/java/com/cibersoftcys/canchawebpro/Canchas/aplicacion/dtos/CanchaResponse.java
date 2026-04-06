package com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonPropertyOrder({
    "id",
    "nombre",
    "tipo",
    "imagenUrl",
    "estado",
    "sedeId",
    "sede"
})
@Schema(name = "CanchaRespuesta")
@Data
public class CanchaResponse {

    private Long id;
    private String nombre;
    private String tipo;
    private String imagenUrl;
    private String estado;
    private Long sedeId;
    private String sede;

}