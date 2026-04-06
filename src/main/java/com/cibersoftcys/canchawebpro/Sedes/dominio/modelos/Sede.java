package com.cibersoftcys.canchawebpro.Sedes.dominio.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cibersoftcys.canchawebpro.Canchas.dominio.modelos.Cancha;
import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.enums.EstadoSede;
import com.cibersoftcys.canchawebpro.Sedes.dominio.modelos.valueObject.NombreSede;

public class Sede {

    // 📌 Atributos
    private Long id;
    private NombreSede nombre;
    private String direccion;
    private String distrito;
    private String ciudad;
    private EstadoSede estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private final List<Cancha> canchas = new ArrayList<>();

    // 🔥 Constructores.
    public Sede() {}

    public Sede(NombreSede nombre, String direccion, String distrito, String ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.ciudad = ciudad;
        this.estado = EstadoSede.ACTIVA;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = this.fechaCreacion;
    }

    public Sede(Long id, NombreSede nombre, String direccion,
            String distrito, String ciudad,
            EstadoSede estado,
            LocalDateTime fechaCreacion,
            LocalDateTime fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // 🔧 Getters (PROTEGIDOS).
    public Long getId() { return id; }
    public NombreSede getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getDistrito() { return distrito; }
    public String getCiudad() { return ciudad; }
    public EstadoSede getEstado() { return estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public List<Cancha> getCanchas() { return new ArrayList<>(canchas); };

    // 🧠 Reglas de negocio
    public void actualizarDatos(String nombre, String direccion, String distrito, String ciudad) {
        if (!estaActiva()) {
            throw new BusinessValidationException("No se puede editar una Sede Inactiva.");
        }
        this.nombre = new NombreSede(nombre);
        this.direccion = direccion;
        this.distrito = distrito;
        this.ciudad = ciudad;
        actualizar();
    }

    public boolean estaActiva() {
        return this.estado == EstadoSede.ACTIVA;
    }

    public void activar() {
        this.estado = EstadoSede.ACTIVA;
        actualizar();
    }

    public void desactivar() {
        this.estado = EstadoSede.INACTIVA;
        actualizar();
    }

    public void agregarCancha(Cancha cancha) {
        if (cancha == null) {
            throw new RuntimeException("La Cancha no puede ser Nula.");
        }

        if (!estaActiva()) {
            throw new RuntimeException("No se puede agregar Canchas a una Sede Inactiva.");
        }

        cancha.asignarSede(this);
        this.canchas.add(cancha);
        actualizar();
    }

    public int totalCanchas() {
        return this.canchas.size();
    }

    public boolean tieneCanchas() {
        return !this.canchas.isEmpty();
    }

    private void actualizar() {
        this.fechaActualizacion = LocalDateTime.now();
    }
 
}