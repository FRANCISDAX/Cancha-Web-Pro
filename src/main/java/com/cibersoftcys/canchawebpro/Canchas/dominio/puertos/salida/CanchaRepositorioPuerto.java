package com.cibersoftcys.canchawebpro.Canchas.dominio.puertos.salida;

import java.util.List;
import java.util.Optional;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;

public interface CanchaRepositorioPuerto {

    Cancha guardar(Cancha cancha);
    Optional<Cancha> buscarPorId(Long id);
    Optional<Cancha> buscarPorNombre(String nombre);
    List<Cancha> buscarTodas();
    void eliminar(Long id);
    boolean existePorNombre(String nombre);
    boolean existePorNombreYIdNot(String nombre, Long id);

}
