package com.cibersoftcys.canchawebpro.Reservas.dominio.puertos.salida;

import java.util.List;
import java.util.Optional;

import com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.Reserva;

public interface ReservaRepositorioPuerto {

    Reserva guardar(Reserva reserva);
    Optional<Reserva> buscarPorId(Long id);
    Optional<Reserva> buscarPorNombre(String nombre);
    List<Reserva> buscarTodas();
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    boolean existePorNombreYIdNot(String nombre, Long id);

}
