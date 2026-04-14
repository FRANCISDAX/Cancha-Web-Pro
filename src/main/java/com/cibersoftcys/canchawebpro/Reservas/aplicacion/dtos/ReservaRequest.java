package com.cibersoftcys.canchawebpro.Reservas.aplicacion.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name="ReservaSolicitud")
@Data
public class ReservaRequest {

    private String cancha;   
     
}
