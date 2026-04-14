package com.cibersoftcys.canchawebpro.Usuarios.infraestructura.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibersoftcys.canchawebpro.Sedes.infraestructura.entidades.SedeEntidad;
import com.cibersoftcys.canchawebpro.Usuarios.infraestructura.entidades.UsuarioEntidad;

public interface PostgreUsuarioRepositorio extends  JpaRepository<UsuarioEntidad, Long>{

    boolean existsByNombreValor(String nombre);
    boolean existsByNombreValorAndIdNot(String nombre, Long id);
    Optional<SedeEntidad> findByNombreValor(String nombre);

}
