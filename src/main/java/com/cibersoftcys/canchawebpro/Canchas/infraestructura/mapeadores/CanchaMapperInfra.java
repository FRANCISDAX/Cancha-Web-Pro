package com.cibersoftcys.canchawebpro.Canchas.infraestructura.mapeadores;

import org.springframework.stereotype.Component;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject.NombreCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject.Precio;
import com.cibersoftcys.canchawebpro.Canchas.infraestructura.entidades.CanchaEntidad;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;

@Component
public class CanchaMapperInfra {

    // ✅ Mapper Dominio → Entidad (Infraestructura)
    public CanchaEntidad paraEntidad(Cancha cancha) {
        if (cancha == null) return null;

        return CanchaEntidad.builder()
            .id(cancha.getId())
            .nombre(cancha.getNombre().getValor())
            .tipo(cancha.getTipo())
            .imagenUrl(cancha.getImagenUrl())
            .estado(cancha.getEstado())
            .fechaCreacion(cancha.getFechaCreacion())
            .fechaActualizacion(cancha.getFechaActualizacion())
            .sede(mapearSedeEntidad(cancha.getSede()))
            .build();    
    }

    // ✅ Mapper Entidad → Dominio
    public Cancha paraDominio(CanchaEntidad entidad) {
        if (entidad == null) return null;

        NombreCancha nombre = new NombreCancha(entidad.getNombre());
        Precio precioPorHora = Precio.of(entidad.getPrecioPorHora());
        Cancha cancha = new Cancha(
            nombre,
            entidad.getTipo(),
            entidad.getImagenUrl(),
            precioPorHora
        );

        cancha.ponerEstadoDesdePersistencia(entidad.getEstado());
        
        cancha.setId(entidad.getId());
        if (entidad.getSede() != null) {
            SedeEntidad sedeEntidad = entidad.getSede();
            Sede sede = new Sede(
                sedeEntidad.getId(),
                sedeEntidad.getNombre(),
                sedeEntidad.getDireccion(),
                sedeEntidad.getDistrito(),
                sedeEntidad.getCiudad(),
                sedeEntidad.getEstado(),
                sedeEntidad.getFechaCreacion(),
                sedeEntidad.getFechaActualizacion()
            );            
            cancha.asignarSede(sede);
        }
        return cancha;
    }

    // 🔁 Helpers
    private SedeEntidad mapearSedeEntidad(Sede sede) {
        if (sede == null) return null;

        return SedeEntidad.builder()
            .id(sede.getId())
            .build();
    }
    
}