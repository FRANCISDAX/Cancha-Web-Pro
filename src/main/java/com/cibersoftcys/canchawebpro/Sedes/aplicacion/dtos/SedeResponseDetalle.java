package com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonPropertyOrder({
    "id",
    "nombre",
    "direccion",
    "distrito",
    "ciudad",
    "estado",
    "totalCanchas",
    "canchas"
})
@Schema(name = "SedeRespuestaDetalle")
@Data
public class SedeResponseDetalle {

    private Long id;
    private String nombre;
    private int totalCanchas;
    private List<CanchaResponse> canchas;

}