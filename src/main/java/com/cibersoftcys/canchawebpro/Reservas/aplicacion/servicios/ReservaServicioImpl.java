package com.cibersoftcys.canchawebpro.Reservas.aplicacion.servicios;

import java.util.List;

import com.cibersoftcys.canchawebpro.Reservas.aplicacion.dtos.ReservaRequest;
import com.cibersoftcys.canchawebpro.Reservas.aplicacion.dtos.ReservaResponse;
import com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.Reserva;
import com.cibersoftcys.canchawebpro.Reservas.dominio.puertos.entrada.ReservaDominioPuerto;
import com.cibersoftcys.canchawebpro.Reservas.dominio.puertos.entrada.ReservaServicioPuerto;

public class ReservaServicioImpl implements ReservaServicioPuerto, ReservaDominioPuerto{

    @Override
    public ReservaResponse crearReserva(ReservaRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ReservaResponse obtenerReservaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ReservaResponse> obteberTodasReservas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ReservaResponse actualizarReserva(Long id, ReservaRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarReserva(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Reserva obtenerReserva(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}