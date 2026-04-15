package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.entidades.UsuarioEntidad;

public interface PostgreUsuarioRepositorio extends  JpaRepository<UsuarioEntidad, Long>{

    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
    Optional<SedeEntidad> findByNombre(String nombre);
    boolean existsByEmail(String email);

}
