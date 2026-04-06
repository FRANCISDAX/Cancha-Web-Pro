package com.cibersoftcys.canchawebpro.Canchas.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibersoftcys.canchawebpro.Canchas.infraestructura.entidades.CanchaEntidad;

public interface PostgreCanchaRepositorio extends JpaRepository<CanchaEntidad, Long>{

    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
    Optional<CanchaEntidad> findByNombre(String nombre);
    boolean existsBySedeId(Long sedeId);
    
}
