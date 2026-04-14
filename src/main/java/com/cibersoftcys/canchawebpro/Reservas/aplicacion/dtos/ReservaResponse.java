package com.cibersoftcys.canchawebpro.Reservas.aplicacion.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonPropertyOrder({
    "id"
})
@Schema(name = "ReservaRespuesta")
@Data
public class ReservaResponse {

    private Long id;

}
