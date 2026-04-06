package com.cibersoftcys.canchawebpro.Canchas.dominio.modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.EstadoCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.enums.TipoCancha;
import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.valueObject.NombreCancha;
import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Reservas.dominio.modelos.Reserva;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.Sede;

public class Cancha {

    // 📌 Atributos.
    private Long id;
    private NombreCancha nombre;
    private TipoCancha tipo;
    private String imagenUrl;
    private EstadoCancha estado;
    private BigDecimal precioPorHora;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Sede sede;
    private final List<Reserva> reservas = new ArrayList<>();

    // 🔥 Constructores.
    public Cancha() {}

    public Cancha(NombreCancha nombre, TipoCancha tipo, String imagenUrl, BigDecimal precioPorHora) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenUrl = imagenUrl;
        this.precioPorHora = precioPorHora;
        this.estado = EstadoCancha.DISPONIBLE;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = this.fechaCreacion;
    }

    // 🔧 Getters (PROTEGIDOS).
    public Long getId() { return id; }
    public NombreCancha getNombre() { return nombre; }
    public TipoCancha getTipo() { return tipo; }
    public EstadoCancha getEstado() { return estado; }
    public String getImagenUrl() { return imagenUrl; }
    public BigDecimal getPrecioPorHora() { return precioPorHora; }
    public Sede getSede() { return sede; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public List<Reserva> getReservas() { return List.copyOf(reservas); }

    // 🔧 Setters CONTROLADOS.
    public void setId(Long id) { this.id = id; }

    // 🧠 Reglas de negocio
    public void actualizarDatos(NombreCancha nombre, TipoCancha tipo, String imagenUrl, BigDecimal precioPorHora) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagenUrl = imagenUrl;
        this. precioPorHora = precioPorHora;
        actualizar();
    }

    public void asignarSede(Sede sede) {
        if (sede == null) {
            throw new RuntimeException("La Sede no puede ser nula.");
        }
        this.sede = sede;
        actualizar();
    }

    public boolean esDelTipo(TipoCancha tipo) {
        return this.tipo == tipo;
    }

    public boolean estaDisponible(Reserva nuevaReserva) {
        return true;
        //return reservas.stream()
        //        .noneMatch(r -> r.seCruzaCon(nuevaReserva));
    }

    public void agregarReserva(Reserva reserva) {
        if (!estaDisponible(reserva)) {
            throw new RuntimeException("La cancha no está disponible en ese horario.");
        }
        //reserva.setCancha(this);
        reservas.add(reserva);
        actualizar();
    }

    private void actualizar() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void ponerEnMantenimientoCancha() {
        if (this.estado == EstadoCancha.MANTENIMIENTO) {
            throw new BusinessValidationException("La Cancha ya está en Mantenimiento.");
        }
        this.estado = EstadoCancha.MANTENIMIENTO;
        actualizar();
    }

    public void ponerEnDisponibleCancha() {
        if (this.estado == EstadoCancha.DISPONIBLE) {
            throw new BusinessValidationException("La Cancha ya está Disponible.");
        }
        this.estado = EstadoCancha.DISPONIBLE;
        actualizar();
    }

    public void ponerEstadoDesdePersistencia(EstadoCancha estado) {
        this.estado = estado;
    }
    
}