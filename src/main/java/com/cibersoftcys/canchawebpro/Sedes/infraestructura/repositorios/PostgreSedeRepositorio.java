package com.cibersoftcys.canchawebpro.Sedes.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;
import com.cibersoftcys.canchawebpro.Sedes.infraestructura.proyecciones.SedeCanchaFlat;

import java.util.List;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.enums.EstadoSede;



public interface PostgreSedeRepositorio extends JpaRepository<SedeEntidad, Long> {

    boolean existsByNombreValor(String nombre);
    boolean existsByNombreValorAndIdNot(String nombre, Long id);
    Optional<SedeEntidad> findByNombreValor(String nombre);
    List<SedeEntidad> findByEstado(EstadoSede estado);
    @Query("""
    SELECT 
        s.id as sedeId,
        s.nombre.valor as sedeNombre,
        s.direccion as direccion,
        s.distrito as distrito,
        s.ciudad as ciudad,
        s.estado as estado,
        c.id as canchaId,
        c.nombre as canchaNombre,
        c.tipo as canchaTipo
    FROM SedeEntidad s
    LEFT JOIN CanchaEntidad c ON c.sede.id = s.id
    """)
    List<SedeCanchaFlat> obtenerSedesConCanchas();

}