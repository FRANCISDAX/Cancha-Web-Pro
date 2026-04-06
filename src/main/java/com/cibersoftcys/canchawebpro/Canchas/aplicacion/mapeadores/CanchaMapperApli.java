package com.cibersoftcys.canchawebpro.Canchas.aplicacion.mapeadores;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaRequest;
import com.cibersoftcys.canchawebpro.Canchas.aplicacion.dtos.CanchaResponse;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.TipoCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject.NombreCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject.Precio;

@Component
public class CanchaMapperApli {

    // ===== DOMAIN → RESPONSE =====
    public CanchaResponse toResponse(Cancha cancha) {
        if(cancha == null) return null;

        CanchaResponse response = new CanchaResponse();
        response.setId(cancha.getId());
        response.setNombre(cancha.getNombre().getValor());
        response.setTipo(cancha.getTipo().name().toUpperCase());
        response.setImagenUrl(cancha.getImagenUrl());
        if (cancha.getEstado() != null) {
            response.setEstado(cancha.getEstado().name());
        }
        if (cancha.getSede() != null) {
            response.setSedeId(cancha.getSede().getId());
            response.setSede(
                cancha.getSede().getNombre() != null
                    ? cancha.getSede().getNombre().getValor()
                    : null
            );
        }
        response.setPrecioPorHora(
            cancha.getPrecioPorHora() != null 
                ? cancha.getPrecioPorHora().getValor() 
                : BigDecimal.ZERO
        );
        return response;

    }
    
    // ===== REQUEST → DOMAIN ======
    public Cancha toDomain(CanchaRequest request) {
        if(request == null) return null;

        NombreCancha nombre = new NombreCancha(request.getNombre());
        TipoCancha tipo = request.getTipo();
        Precio precioPorHora = Precio.of(request.getPrecioPorHora());
        return new Cancha(
            nombre,
            tipo,
            request.getImagenUrl(),
            precioPorHora
        );
    }

}
