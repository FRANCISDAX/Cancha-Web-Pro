package com.cibersoftcys.canchawebpro.Reservas.dominio.modelos;

import java.time.LocalDateTime;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;
import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.enums.EstadoReserva;
import com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.valueObject.RangoHorario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.Usuario;

public class Reserva {

    // 📌 Atributos.
    private Long id;
    private Usuario usuario;
    private Cancha cancha;
    private RangoHorario rangoHorario;
    private EstadoReserva estado;
    private LocalDateTime fechaCreacion;
    
    // 🔥 Constructores.
    public Reserva(Usuario usuario, Cancha cancha, RangoHorario rangoHorario) {
        this.usuario = usuario;
        this.cancha = cancha;
        this.rangoHorario = rangoHorario;
        this.estado = EstadoReserva.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    // 🔧 Getters (PROTEGIDOS).
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Cancha getCancha() { return cancha; }
    public RangoHorario getRangoHorario() { return rangoHorario; }
    public EstadoReserva getEstado() { return estado; }

    // 🔧 Setters CONTROLADOS.
    public void setId(Long id) { this.id = id; }

    // 🧠 Reglas de negocio
    public boolean seCruzaCon(Reserva otra) {
        return this.rangoHorario.seCruzaCon(otra.rangoHorario);
    }

    public boolean estaActiva() {
        return this.estado == EstadoReserva.CONFIRMADA 
            || this.estado == EstadoReserva.PENDIENTE;
    }

    public void confirmar() {
        if (this.estado != EstadoReserva.PENDIENTE) {
            throw new BusinessValidationException("Solo reservas Pendientes pueden confirmarse");
        }
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (this.estado == EstadoReserva.FINALIZADA) {
            throw new BusinessValidationException("No puedes Cancelar una reserva finalizada");
        }
        this.estado = EstadoReserva.CANCELADA;
    }

    public void finalizar() {
        if (this.estado != EstadoReserva.CONFIRMADA) {
            throw new BusinessValidationException("Solo reservas Confirmadas pueden finalizarse");
        }
        this.estado = EstadoReserva.FINALIZADA;
    }

}