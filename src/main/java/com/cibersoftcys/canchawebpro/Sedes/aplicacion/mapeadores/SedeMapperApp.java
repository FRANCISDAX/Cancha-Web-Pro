package com.cibersoftcys.canchawebpro.Sedes.aplicacion.mapeadores;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeRequest;
import com.cibersoftcys.canchawebpro.Sedes.aplicacion.dtos.SedeResponse;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.valueObject.NombreSede;

@Component
public class SedeMapperApp {

    // ===== DOMAIN → RESPONSE =====
    public SedeResponse toResponse(Sede sede) {
        if(sede == null) return null;

        SedeResponse response = new SedeResponse();
        response.setId(sede.getId());
        response.setNombre(sede.getNombre().getValor());
        response.setDireccion(sede.getDireccion());
        response.setDistrito(sede.getDistrito());
        response.setCiudad(sede.getCiudad());
        response.setEstado(sede.getEstado().name());
        return response;
    }

    // ===== REQUEST → DOMAIN ======
    public Sede toDomain(SedeRequest request) {
        if(request == null) return null;

        NombreSede nombre = new NombreSede(request.getNombre());
        return new Sede(
            nombre,
            request.getDireccion(),
            request.getDistrito(),
            request.getCiudad()
        );
    }

}