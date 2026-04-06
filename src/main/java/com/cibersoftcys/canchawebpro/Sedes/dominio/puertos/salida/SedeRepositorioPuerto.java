package com.cibersoftcys.canchawebpro.Sedes.dominio.puertos.salida;

import java.util.List;
import java.util.Optional;

import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;

public interface SedeRepositorioPuerto {

    Sede guardar(Sede sede);
    Optional<Sede> buscarPorId(Long id);
    List<Sede> buscarTodas();
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    boolean existePorNombreYIdNot(String nombre, Long id);
    List<Sede> buscarSedesActivas();
    Optional<Sede> buscarPorNombre(String nombre);
    boolean existeCanchasPorSedeId(Long sedeId);
    
}
