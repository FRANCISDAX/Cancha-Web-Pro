package com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.EstadoCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.TipoCancha;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "CanchaSolicitud")
@Data
public class CanchaRequest {

    @NotBlank(message="El nombre es obligatorio.")
    @Size(max=50, message="El nombre no puede exceder de 50 caracteres.")
    @Schema(description = "Nombre de la Cancha", example = "CANCHA FUTBOL 01")
    private String nombre;

    @NotBlank(message="El Tipo de Cancha es obligatorio.")
    @Schema(description = "Tipo de Cancha", example = "FUTBOL")
    private TipoCancha tipo;
    
    @Schema(description = "Imagen de la Cancha", example = "IMAGEN_FUTBOL.jpg")
    private String imagenUrl;

    @Schema(description = "Estado de la Cancha", example = "DISPONIBLE")
    private EstadoCancha estado;

    @Schema(description = "Sede de la Cancha", example = "1")
    private Long sedeId;

}
