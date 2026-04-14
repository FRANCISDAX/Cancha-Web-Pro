package com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.valueObject;

import java.time.LocalDateTime;

public class RangoHorario {

    private final LocalDateTime inicio;
    private final LocalDateTime fin;

    public RangoHorario(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null) {
            throw new RuntimeException("Las fechas no pueden ser nulas");
        }
        if (fin.isBefore(inicio)) {
            throw new RuntimeException("La fecha Fin no puede ser menor a la fecha Inicio");
        }

        this.inicio = inicio;
        this.fin = fin;
    }

    public boolean seCruzaCon(RangoHorario otro) {
        return inicio.isBefore(otro.fin) && fin.isAfter(otro.inicio);
    }

    public LocalDateTime getInicio() { return inicio; }
    public LocalDateTime getFin() { return fin; }
    
}