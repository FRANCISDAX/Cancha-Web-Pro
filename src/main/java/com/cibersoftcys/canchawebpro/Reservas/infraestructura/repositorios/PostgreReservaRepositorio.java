package com.cibersoftcys.canchawebpro.Reservas.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibersoftcys.canchawebpro.Reservas.infraestructura.entidades.ReservaEntidad;

public interface PostgreReservaRepositorio extends JpaRepository<ReservaEntidad, Long>{

}
