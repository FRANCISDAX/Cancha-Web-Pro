package com.cibersoftcys.canchawebpro.Reservas.dominio.puertos.entrada;

import java.util.List;

import com.cibersoftcys.canchawebpro.Reservas.aplicacion.dtos.ReservaRequest;
import com.cibersoftcys.canchawebpro.Reservas.aplicacion.dtos.ReservaResponse;

public interface ReservaServicioPuerto {

    ReservaResponse crearReserva(ReservaRequest request);
    ReservaResponse obtenerReservaPorId(Long id);
    List<ReservaResponse> obteberTodasReservas();
    ReservaResponse actualizarReserva(Long id, ReservaRequest request);
    void eliminarReserva(Long id);
    
}