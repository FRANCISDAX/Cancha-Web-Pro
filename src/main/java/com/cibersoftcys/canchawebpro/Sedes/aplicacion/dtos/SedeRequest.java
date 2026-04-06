package com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "SedeSolicitud")
@Data
public class SedeRequest {

    @NotBlank(message="El nombre es obligatorio.")
    @Size(max=50, message="El nombre no puede exceder de 50 caracteres.")
    @Schema(description = "Nombre de la Sede", example = "SEDE LORETO")
    private String nombre;

    @Schema(description = "Dirección exacta", example = "JR. PROSPERO No 500")
    private String direccion;

    @Schema(description = "Distrito", example = "IQUITOS")
    private String distrito;

    @Schema(description = "Ciudad", example = "IQUITOS")
    private String ciudad;

}
