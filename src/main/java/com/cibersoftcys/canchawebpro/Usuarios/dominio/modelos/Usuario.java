package com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos;

import java.time.LocalDateTime;

import com.cibersoftcys.canchawebpro.Excepciones.BusinessValidationException;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.enums.TipoUsuario;
import com.cibersoftcys.canchawebpro.Usuarios.dominio.modelos.valueObject.NombreUsuario;

public class Usuario {

    // 📌 Atributos.
    private Long id;
    private NombreUsuario nombre;
    private String email;
    private String telefono;
    private TipoUsuario tipo;
    private LocalDateTime fechaRegistro;

    // 🔥 Constructores.
    public Usuario() {}

    public Usuario(NombreUsuario nombre, String email, String telefono, TipoUsuario tipo) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.tipo = TipoUsuario.CLIENTE;
        this.fechaRegistro = LocalDateTime.now();
    }

    // 🔧 Getters (PROTEGIDOS).
    public Long getId() { return id; }
    public NombreUsuario getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public TipoUsuario getTipo() { return tipo; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }

    // 🔧 Setters CONTROLADOS.
    public void setId(Long id) { this.id = id; }

    // 🧠 Reglas de negocio
    public void actualizarDatos(NombreUsuario nombre, String email, String telefono) {

        if (nombre == null) {
            throw new BusinessValidationException("El nombre no puede ser nulo");
        }

        if (email == null || email.isBlank()) {
            throw new BusinessValidationException("El email no puede ser vacío");
        }

        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public void cambiarTipo(TipoUsuario nuevoTipo) {

        if (nuevoTipo == null) {
            throw new BusinessValidationException("El tipo de usuario es obligatorio");
        }

        if (this.tipo == nuevoTipo) {
            throw new BusinessValidationException("El usuario ya tiene ese tipo");
        }

        this.tipo = nuevoTipo;
    }

    public boolean esCliente() {
        return this.tipo == TipoUsuario.CLIENTE;
    }

    public boolean esAdmin() {
        return this.tipo == TipoUsuario.ADMIN;
    }

}