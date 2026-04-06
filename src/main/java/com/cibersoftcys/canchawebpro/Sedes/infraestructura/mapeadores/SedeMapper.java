package com.cibersoftcys.canchawebpro.Sedes.infraestructura.mapeadores;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;

@Component
public class SedeMapper {

    // ✅ Mapper Dominio → Entidad (Infraestructura)
    public SedeEntidad paraEntidad(Sede sede) {
        if (sede == null) return  null;

        return SedeEntidad.builder()
            .id(sede.getId())
            .nombre(sede.getNombre())
            .direccion(sede.getDireccion())
            .distrito(sede.getDistrito())
            .ciudad(sede.getCiudad())
            .estado(sede.getEstado())
            .fechaCreacion(sede.getFechaCreacion())
            .fechaActualizacion(sede.getFechaActualizacion())
            .build();
    }

    // ✅ Mapper Entidad → Dominio
    public Sede paraDominio(SedeEntidad entidad) {
        if (entidad == null) return null;

        return new Sede(
            entidad.getId(),
            entidad.getNombre(),
            entidad.getDireccion(),
            entidad.getDistrito(),
            entidad.getCiudad(),
            entidad.getEstado(),
            entidad.getFechaCreacion(),
            entidad.getFechaActualizacion()
        );
    }

}